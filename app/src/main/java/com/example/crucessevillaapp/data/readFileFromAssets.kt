package com.example.crucessevillaapp.data

import java.io.BufferedReader
import java.io.InputStreamReader


fun readFileFromAssets(fileName: String): List<String> {
        val lines = mutableListOf<String>()
        try {
            val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.useLines { lines.addAll(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lines
    }
