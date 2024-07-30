package com.example.crucessevillaapp.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//import kotlinx.serialization.Serializable
import java.io.Serializable


@Entity(tableName = "cruces")
class Semaforo(

    @PrimaryKey(autoGenerate = true)
   // @ColumnInfo(name = "id")
    val id: Int = 0,

    @SerializedName("CENTRAL")
    val central: Int,

    @SerializedName("DIRECCION")
    val direccion: String,

    @SerializedName("NODO")
    val nodo: Int,

    @SerializedName("NUMCRUCE")
   // @PrimaryKey()
    val numCruce: Int,

    @SerializedName("TIPO")
    val tipo: String

): Serializable
/*{

    override fun toString(): String {
        return "Category[central: ${this.central}, direccion: ${this.direccion}, nodo: ${this.nodo}, numCruce: ${this.numCruce}, " +
                "tipo= ${this.tipo}]"
    }
}*/
