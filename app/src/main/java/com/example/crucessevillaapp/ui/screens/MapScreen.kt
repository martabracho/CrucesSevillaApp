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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONObject
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

    LaunchedEffect(direccion) {
        if (direccion != null) {
            sevilleGeoPoint = fetchCoordinates(direccion)
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

suspend fun fetchCoordinates(direccion: String): GeoPoint {
    return withContext(Dispatchers.IO) {
        searchCoordinates(direccion)
    }
}

suspend fun searchCoordinates(direccion: String): GeoPoint {
    val url = "https://nominatim.openstreetmap.org/search?q=$direccion&format=json&limit=1"
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


