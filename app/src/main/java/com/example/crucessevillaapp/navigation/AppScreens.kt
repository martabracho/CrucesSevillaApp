package com.example.crucessevillaapp.navigation

sealed class AppScreens (val route : String){
    object Portada: AppScreens("portada")
    object InfoCruce: AppScreens("info_cruce")
    object SettingsBD: AppScreens("settings_bd")
    object ViewCruces: AppScreens("view_cruces")
}