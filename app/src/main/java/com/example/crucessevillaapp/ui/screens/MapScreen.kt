package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.crucessevillaapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.net.URL


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavController, direccion: String?) {
    Scaffold () { BodyContentMapScreen(navController, LocalContext.current, direccion) }


}

@Composable
fun BodyContentMapScreen(navController: NavController, context: Context, direccion: String?) {

    // Set a custom user agent for osmdroid
    Configuration.getInstance().userAgentValue = "martific"
    //Initialize GeoPojnt
    var sevilleGeoPoint by remember { mutableStateOf(GeoPoint(37.38283000, -5.97317000))}
    val context = LocalContext.current

    LaunchedEffect(direccion) {
        if (direccion != null) {
            val place1 = searchPlace1(direccion, context)
            val place2 = searchPlace2(direccion, context)
            sevilleGeoPoint = fetchCoordinates(place1)
            if (place2!=null){
                //Añadir calle a place2
                val sevilleGeoPoint2 = fetchCoordinates(place2)
                //comparar sevilleGeoPoint con sevilleGeoPoint2
            }

            Log.d("Dirección", direccion)
            Log.d("Coordenadas", sevilleGeoPoint.toString())
        }
    }

// Initialize the MapView
    val mapView = remember {MapView(context)}

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            mapView .apply {
                setMultiTouchControls(true)
                controller.setCenter(sevilleGeoPoint) // Centra el mapa en Sevilla
                controller.setZoom(18.0) // Establece un nivel de zoom inicial

                // Añadir marcador
                val marker = Marker(this).apply {
                    position = sevilleGeoPoint
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    title = direccion
                }
                overlays.add(marker)
            }
        },

        update = {
            it.controller.setCenter(sevilleGeoPoint)
            // Actualizar la posición del marcador
            val marker = it.overlays.find { overlay -> overlay is Marker } as? Marker
            marker?.position = sevilleGeoPoint
        }
    )


}

/**
 * Función que recibe una dirección y devuelve la primera parte de la misma
 * y le añade la palabra "calle" si lo es
 */
fun searchPlace1(direccion: String, context: Context): String {
    val auxDir = direccion.substringBefore("-")
    var result = ""

    if (auxDir.contains("PP.")){
        result = auxDir.substringAfter("PP.")
    } else {
        result = auxDir
    }

    val street_type = context.resources.getStringArray(R.array.street_types)

    if (street_type.any { keyword -> result.contains(keyword) }) {
        return result.trim()
    } else {
        return "calle " + result.trim()
    }

}

fun searchPlace2(direccion: String, context: Context): String {

    if (direccion.contains("-")) {
        val auxDir = direccion.substringAfter("-").substringBefore("-")
        var result = auxDir
        val street_type = context.resources.getStringArray(R.array.street_types)

        if (auxDir.contains("PP.")){
            result = auxDir.substringAfter("PP.")
        } else {
            result = auxDir
        }

        if (street_type.any { keyword -> result.contains(keyword) }) {
            return result.trim()
        } else {
            return "calle " + result.trim()
        }

    } else {
        return ""
    }

}

suspend fun fetchCoordinates(direccion: String): GeoPoint {
    return withContext(Dispatchers.IO) {
        searchCoordinates(direccion)
    }
}

suspend fun searchCoordinates(direccion: String): GeoPoint {
    val modifiedDir = direccion.replace(" ", "+")
    val url = "https://nominatim.openstreetmap.org/search?street=$modifiedDir&viewbox=-6.0329183,37.4529579,-5.8191571,37.300203&bounded=1&format=json&limit=1"
    var lat = 0.0
    var lon = 0.0

    try{
        val response = withContext(Dispatchers.IO){
                URL(url).readText()
        }
        val json = JSONArray(response)
        if (json.length() > 0) {
            val jsonObject = json.getJSONObject(0)
            lat = jsonObject.getString("lat").toDouble()
            lon = jsonObject.getString("lon").toDouble()
        }

    } catch (e: Exception) {
            Log.e("Error", e.toString())
    }


    return GeoPoint(lat, lon)
}


