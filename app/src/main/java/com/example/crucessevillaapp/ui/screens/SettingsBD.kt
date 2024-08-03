package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crucessevillaapp.data.Semaforo
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crucessevillaapp.MainActivity
import com.example.crucessevillaapp.data.CrucesDatabase
import com.example.crucessevillaapp.data.Utils.Companion.getJsonDataFromAsset

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsBD(navController: NavController) {
    Scaffold() {
        BodyContentBD(navController, LocalContext.current)
    }
}

@Composable
fun BodyContentBD(navController: NavController, context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val appContext = context.applicationContext
        val database = CrucesDatabase.getDatabase(appContext)
        //navController.navigate(route = AppScreens.ViewCruces.route)
        val jsonFileString = getJsonDataFromAsset(appContext, "Cruces.json")
        //jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val listCruces = object : TypeToken<List<Semaforo>>() {}.type

        val cruces: List<Semaforo> = gson.fromJson(jsonFileString, listCruces)

        Button(onClick = {

            //cruces.forEachIndexed { idx, semaforo -> Log.i("data", "> Item $idx:\n${semaforo.numCruce}\n${semaforo.direccion}") }
            CoroutineScope(Dispatchers.IO).launch {
                val listSemaforos : LiveData<List<Semaforo>> = database.semaforoDao().getAll()
                listSemaforos.value?.forEachIndexed { idx, semaforo -> Log.i("data", "> Item $idx:\n${semaforo.numCruce}\n${semaforo.direccion}") }
            }


        }) {
            Text(text = "Leer BD")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        /**
         * Botón para la primera carga de la base de datos
         */

        Button(onClick = {
             CoroutineScope(Dispatchers.IO).launch {
                cruces.forEachIndexed { idx, semaforo -> database.semaforoDao().insertAll(semaforo) }
            }
         }) {
            Text(text = "Guardar BD en Room")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        /**
         * Botón para borrar la base de datos
         */

        Button(onClick = {
             CoroutineScope(Dispatchers.IO).launch {
                database.semaforoDao().deleteAll()
            }
         }) {
            Text(text = "Borrar Base de Datos")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Atrás")
        }
    }
}