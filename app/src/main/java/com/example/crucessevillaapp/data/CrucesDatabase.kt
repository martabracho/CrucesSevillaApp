package com.example.crucessevillaapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Semaforo::class], version = 1, exportSchema = false)
abstract class CrucesDatabase : RoomDatabase() {
    abstract fun semaforoDao(): SemaforoDao

    companion object{
        @Volatile
        private var INSTANCE: CrucesDatabase? = null

        fun getDatabase(context: Context): CrucesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    CrucesDatabase::class.java,
                    "cruces_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}