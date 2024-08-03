@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.crucessevillaapp.data.CrucesDatabase
import com.example.crucessevillaapp.data.Semaforo
import com.example.crucessevillaapp.ui.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Portada (navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Cruces de Sevilla - Inicio")
                },
                Modifier.background(Color(0xFF2196F3)),

                navigationIcon ={
                    Icon(imageVector = Icons.Default.Settings,
                        contentDescription = "Herramientas",
                        modifier = Modifier.clickable {

                            navController.navigate(route = AppScreens.SettingsBD.route)
                        }
                    )
                }
            )
        }
    )
    {
        BodyContent(navController, LocalContext.current)
    }
}

    @Composable
    fun BodyContent(navController: NavController, context: Context){
        val appContext = context.applicationContext
        val database = CrucesDatabase.getDatabase(appContext)

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var textFieldValue by remember { mutableStateOf("") }

            TextField(
                value = textFieldValue,
                onValueChange = {
                        newText ->
                    textFieldValue = newText.trimStart { it == '0' }
                },
                label = { Text("Introduce el número de cruce") },
                placeholder = { Text("") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(route = AppScreens.InfoCruce.route + "/" + textFieldValue)
            }) {
                Text("Consulta")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {

                CoroutineScope(Dispatchers.IO).launch {
                    val listSemaforos : LiveData<List<Semaforo>> = database.semaforoDao().getAll()
                    //TODO pasar todos los datos en listSemaforos a la pantalla de visualización
                    listSemaforos.value?.forEachIndexed { idx, semaforo -> Log.i("data", "> Item $idx:\n${semaforo.numCruce}\n${semaforo.direccion}") }
                }

            }) {
                Text("Leer todos los datos")
            }
        }
    }


