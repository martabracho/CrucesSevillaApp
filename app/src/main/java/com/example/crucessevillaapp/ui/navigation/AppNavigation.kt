package com.example.crucessevillaapp.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crucessevillaapp.ui.screens.InfoCruce
import com.example.crucessevillaapp.ui.screens.Portada
import com.example.crucessevillaapp.ui.screens.SettingsBD
import com.example.crucessevillaapp.ui.screens.ViewCruces


/**
 * Elemento Composable que se encarga de orquestar la navegación, conoce las pantallas de la app, se encarga
 * del paso entre pantallas de la manera correcta
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController() //Elemento que tenemos que propagar por todas las pantallas para saber el estado de
    //navegación en cualquier momento
    NavHost(navController = navController, startDestination = AppScreens.Portada.route){
        composable(route = AppScreens.Portada.route){
            Portada(navController)
        }
        composable(route = AppScreens.InfoCruce.route + "/{idCruce}",
            arguments = listOf(navArgument("idCruce") { type = NavType.StringType })){
            InfoCruce(navController, it.arguments?.getString("idCruce"))
        }
        composable(route = AppScreens.SettingsBD.route){
            SettingsBD(navController)
        }
        composable(route = AppScreens.ViewCruces.route){
            ViewCruces(navController)
        }
    }
}

