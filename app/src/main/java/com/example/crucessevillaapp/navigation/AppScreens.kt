package com.example.crucessevillaapp.navigation

sealed class AppScreens (val route : String){
    object Portada: AppScreens("portada")
    object InfoCruce: AppScreens("info_cruce")
}