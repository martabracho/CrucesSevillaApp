package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.TextView
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
import androidx.compose.runtime.remember
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
    fun SecondBodyContent(navController: NavController, context: Context, idCruce: String?) {
        val appContext = context.applicationContext
        val database = CrucesDatabase.getDatabase(appContext)

        val numCruceState = remember { mutableSetOf(0) }
        val direccionState = remember { mutableSetOf("") }
        val tipoState = remember { mutableSetOf("") }
        val nodoState = remember { mutableSetOf(0) }
        val centralState = remember { mutableSetOf(0) }

        idCruce?.let {
            database.semaforoDao().getById(it.toInt()).observeForever {
                numCruceState.clear()
                numCruceState.add(it.numCruce)
                direccionState.clear()
                direccionState.add(it.direccion)
                tipoState.clear()
                tipoState.add(it.tipo)
                nodoState.clear()
                nodoState.add(it.nodo)
                centralState.clear()
                centralState.add(it.central)
            }
        }
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            numCruceState.forEach {
                Text("NumCruce: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            direccionState.forEach {
                Text("Direccion: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            tipoState.forEach {
                Text("Tipo: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            nodoState.forEach {
                Text("Nodo: $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            centralState.forEach {
                Text("Central: $it")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Volver")
            }
        }
    }

