package com.example.crucessevillaapp.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import java.io.IOException


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewCruces (navController: NavController) {
    Scaffold() {
       readDataFromJSON(navController = navController)
    }
}



@Composable
fun readDataFromJSON( navController: NavController) {


}



    /*val dataList = mutableListOf<Semaforo>()
    val fileLines = readFileFromAssets("CrucesDB.db")
    println(fileLines)
    val jsonArray = JSONArray(fileLines)
    for (i in 0 until jsonArray.length()) {
        val item = jsonArray.getJSONObject(i)
        val central = item.getInt("central")
        val direccion = item.getString("direccion")
        val nodo = item.getInt("nodo")
        val numCruce = item.getInt("numCruce")
        val tipo = item.getString("tipo")
        dataList.add(Semaforo(central, nodo, numCruce, direccion, tipo))
    }

    fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()



    for (i in 0 until dataArray.length()) {
        val item = dataArray.getJSONObject(i)
        val central = item.getInt("central")
        val direccion = item.getString("direccion")
        val nodo = item.getInt("nodo")
        val numCruce = item.getInt("numCruce")
        val tipo = item.getString("tipo")
        dataList.add(Semaforo(central, nodo, numCruce, direccion, tipo))
    }

*/




