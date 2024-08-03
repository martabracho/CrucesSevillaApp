package com.example.crucessevillaapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "cruces")
class Semaforo(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("CENTRAL")
    val central: Int,

    @SerializedName("DIRECCION")
    val direccion: String,

    @SerializedName("NODO")
    val nodo: Int,

    @SerializedName("NUMCRUCE")
    val numCruce: Int,

    @SerializedName("TIPO")
    val tipo: String

): Serializable
