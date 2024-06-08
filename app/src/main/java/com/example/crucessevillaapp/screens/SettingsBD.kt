package com.example.crucessevillaapp.screens

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
import com.example.crucessevillaapp.data.Utils.Companion.getJsonDataFromAsset

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
        Button(onClick = {
            val appContext = context.applicationContext

            //navController.navigate(route = AppScreens.ViewCruces.route)
            val jsonFileString = getJsonDataFromAsset(appContext, "Cruces.json")
            jsonFileString?.let { Log.i("data", it) }

            val gson = Gson()
            val listCruces = object : TypeToken<List<Semaforo>>() {}.type

            val cruces: List<Semaforo> = gson.fromJson(jsonFileString, listCruces)
            cruces.forEachIndexed { idx, semaforo -> Log.i("data", "> Item $idx:\n$semaforo") }
            //TODO meter todos los datos en la base de datos con room OJO que hay que pasar a int los números que ahora son string
        }) {
            Text(text = "Leer BD")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        Button(onClick = { /*TODO guardar todos los datos de la sqlite en room*/ }) {
            Text(text = "Guardar BD en Room")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Atrás")
        }
    }
}