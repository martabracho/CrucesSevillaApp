package com.example.crucessevillaapp.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Semaforo(

    @SerializedName("CENTRAL")
    val central: String,

    @SerializedName("DIRECCION")
    val direccion: String,

    @SerializedName("NODO")
    val nodo: String,

    @SerializedName("NUMCRUCE")
    val numCruce: String,

    @SerializedName("TIPO")
    val tipo: String

) {

    override fun toString(): String {
        return "Category[central: ${this.central}, direccion: ${this.direccion}, nodo: ${this.nodo}, numCruce: ${this.numCruce}, " +
                "tipo= ${this.tipo}]"
    }
}
