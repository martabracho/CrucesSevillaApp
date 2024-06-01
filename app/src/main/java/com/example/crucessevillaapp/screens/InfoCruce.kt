package com.example.crucessevillaapp.screens

import android.annotation.SuppressLint
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

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


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
            SecondBodyContent(navController, idCruce)
        }
    }

    @Composable
    fun SecondBodyContent(navController: NavController, idCruce: String?){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(value = "", onValueChange = {} )
            Spacer(modifier = Modifier.height(16.dp))
            idCruce?.let {
                Text(it)
            }
            Button(onClick = {
                navController.navigate(route = "Portada")
            }) {
                Text("Volver")
            }
        }
    }





