package com.aecoding.tictactoecompose.data

import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RoomRepository(private val firestore: FirebaseFirestore) {

    private var roomListener: ListenerRegistration? = null

    suspend fun saveRoom(room: RoomDto): Result<Unit> =
        try {
            firestore.collection("rooms")
                .document(room.roomId)
                .set(room)
                .await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun fetchRoom(roomId: String): Result<RoomDto> = try {
        val querySnapshot = Firebase.firestore.collection("rooms")
            .document(roomId)
            .get()
            .await()
        val fetchedRoom = querySnapshot.toObject(RoomDto::class.java)!!
        Result.Success(fetchedRoom)
    } catch (e: Exception) {
        Result.Error(e)
    }

    fun getRoomFlow(roomId: String): Flow<Result<RoomDto>> = callbackFlow {
        roomListener = firestore.collection("rooms")
            .document(roomId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.Error(error))
                }
                if (snapshot != null && snapshot.exists()) {
                    val room = snapshot.toObject(RoomDto::class.java)
                    if (room != null) {
                        trySend(Result.Success(room))
                    }
                }
            }
        awaitClose { channel.close() }
    }
}