package com.example.crucessevillaapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SemaforoDao {
    @Query("SELECT * FROM cruces")
    fun getAll(): LiveData<List<Semaforo>>

    @Query("SELECT * FROM cruces WHERE numCruce = :id")
    fun getById(id: Int): LiveData<Semaforo>

    @Insert
    fun insertAll(vararg semaforos: Semaforo)

    @Update
    fun update(semaforo: Semaforo)

    @Query("DELETE FROM cruces")
    fun deleteAll()
}