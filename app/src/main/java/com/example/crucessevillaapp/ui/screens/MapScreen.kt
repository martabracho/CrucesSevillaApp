package com.example.crucessevillaapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavController) {
    Scaffold () { BodyContentMapScreen(navController, LocalContext.current) }


}

@Composable
fun BodyContentMapScreen(navController: NavController, context: Context) {
  /*  var geoPoint by remember {mutableStateOf(GeoPoint(37.6, -122.3))}
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            //mapView.setTileSource(TileSourceFactory.USGS_SAT)
            mapView.setMultiTouchControls(true)
            mapView
        }
    )*/

    // Set a custom user agent for osmdroid
    Configuration.getInstance().userAgentValue = "martific"
    //Initialize GeoPojnt
    val sevilleGeoPoint = GeoPoint(37.38283000, -5.97317000)
    // Initialize the MapView
    val mapView = MapView(context)

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context).apply {
                setMultiTouchControls(true)
                //controller.zoomTo(15.0) // Establece un nivel de zoom inicial
                controller.setCenter(sevilleGeoPoint) // Centra el mapa en Sevilla
                controller.setZoom(15.0) // Establece un nivel de zoom inicial

            }
            mapView
        }
    )


}


