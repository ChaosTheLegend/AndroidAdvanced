package com.example.hacknplanstat2

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json as KotlinJson

//HackNPlanS url
const val sourceUrl = "https://api.hacknplan.com"

val json = Json{
    ignoreUnknownKeys = true
}

val httpClient = HttpClient(CIO) {
    expectSuccess = false
    install(JsonFeature) {
        serializer = KotlinxSerializer(KotlinJson { ignoreUnknownKeys = true }
        )
    }
}

