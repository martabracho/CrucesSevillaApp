@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.crucessevillaapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.clickable

import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
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

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crucessevillaapp.navigation.AppScreens

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
                navigationIcon ={
                    Icon(imageVector = Icons.Default.Settings,
                        contentDescription = "Herramientas",
                        modifier = Modifier.clickable {
                            /*TODO Que aparezca una pantalla con los botones de la base de datos*/
                            navController.navigate(route = AppScreens.SettingsBD.route)
                        }
                    )
                }
            )
        }
    )
    {
        BodyContent(navController)
    }
}

    @Composable
    fun BodyContent(navController: NavController){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(value = "Número de cruce", onValueChange = {} )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(route = AppScreens.InfoCruce.route + "/1")
            }) {
                Text("Consulta")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text("Leer todos los datos")
            }
        }
    }




