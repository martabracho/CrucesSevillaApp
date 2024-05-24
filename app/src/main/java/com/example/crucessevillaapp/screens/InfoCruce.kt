package com.example.crucessevillaapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp




    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun InfoCruce(){
        Scaffold {
            SecondBodyContent()
        }
    }

    @Composable
    fun SecondBodyContent(){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(value = "", onValueChange = {} )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text("Consulta")
            }
        }
    }

    @Preview (showBackground = true)
    @Composable
    fun InfoCrucePreview(){
        InfoCruce()
    }



