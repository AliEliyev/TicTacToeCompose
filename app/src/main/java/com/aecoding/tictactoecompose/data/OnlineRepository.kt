package com.aecoding.tictactoecompose.data

import com.aecoding.tictactoecompose.domain.entities.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class OnlineRepository(private val firestore: FirebaseFirestore) {

    suspend fun saveRoom(room: Room): Result<Unit> =
        try {
            firestore.collection("rooms")
                .document(room.roomId)
                .set(room)
                .await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun fetchRoom(room: Room): Result<Room> = try {
        val querySnapshot = Firebase.firestore.collection("games")
            .document(room.roomId).get().await()
        val fetchedRoom = querySnapshot.toObject(Room::class.java)!!

        Result.Success(fetchedRoom)
    } catch (e: Exception) {
        Result.Error(e)
    }
}