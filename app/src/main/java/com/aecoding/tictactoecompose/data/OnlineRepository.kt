package com.aecoding.tictactoecompose.data

import com.aecoding.tictactoecompose.data.dto.RoomDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class OnlineRepository(private val firestore: FirebaseFirestore) {

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

    fun listenForRoomUpdates(roomId: String, onRoomUpdated: (RoomDto) -> Unit) {
        roomListener = firestore.collection("rooms")
            .document(roomId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    println("Error listening for room updates: ${error.message}")
                    return@addSnapshotListener
                }
                if (snapshot != null && snapshot.exists()) {
                    val room = snapshot.toObject(RoomDto::class.java)
                    if (room != null) {
                        onRoomUpdated(room)
                    }
                }
            }
    }

    fun stopListeningForRoomUpdates() {
        roomListener?.remove()
    }
}