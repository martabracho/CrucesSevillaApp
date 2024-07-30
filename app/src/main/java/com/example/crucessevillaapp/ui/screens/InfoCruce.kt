package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.crucessevillaapp.data.CrucesDatabase
import com.example.crucessevillaapp.data.Semaforo


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun InfoCruce(navController: NavController, idCruce: String?){
        Scaffold (topBar = {

            TopAppBar(
                title = {

                Text("Cruces Sevilla")
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                }
            )


        }){
            SecondBodyContent(navController, LocalContext.current, idCruce)
        }
    }

    @Composable
    fun SecondBodyContent(navController: NavController, context: Context, idCruce: String?){
        val appContext = context.applicationContext
        val database = CrucesDatabase.getDatabase(appContext)

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var numCruce : Int = 0
            var direccion : String = ""
            var tipo : String = ""
            var nodo : Int = 0
            var central : Int = 0

            TextField(value = "", onValueChange = {} )
            Spacer(modifier = Modifier.height(16.dp))
            idCruce?.let {
                val sem : LiveData<Semaforo>
                //sem = database.semaforoDao().getById(it.toInt())
                //todo consultar a la bd si el numero de cruce existe y sacar los datos del cruce
                database.semaforoDao().getById(it.toInt()).observeForever {
                    //todo mostrar los datos del cruce
                    Log.i("InfoCruce", "NumCruce: ${it.numCruce}")
                    Log.i("InfoCruce", "Direccion: ${it.direccion}")
                    Log.i("InfoCruce", "Tipo: ${it.tipo}")
                    Log.i("InfoCruce", "Nodo: ${it.nodo}")
                    Log.i("InfoCruce", "Central: ${it.central}")
                    numCruce = it.numCruce
                    direccion = it.direccion
                    tipo = it.tipo
                    nodo = it.nodo
                    central = it.central
                }
                //TODO aqui es donde peta
                Text("NumCruce: " + numCruce.toString())
                Text("Direccion: " + direccion)
                Text("Tipo: " + tipo)
                Text("Nodo: " + nodo.toString())
                Text("Central: " + central.toString())


            }
            Button(onClick = {
                navController.navigate(route = "Portada")
            }) {
                Text("Volver")
            }
        }
    }





