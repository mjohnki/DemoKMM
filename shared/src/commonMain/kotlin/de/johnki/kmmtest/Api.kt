package de.johnki.kmmtest

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

class Api {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val baseUrl = "https://api.wheretheiss.at/"

    @Serializable()
    data class RawIss(
        val id: Long,
        val latitude: Double,
        val longitude: Double,
        val altitude: Double,
        val velocity: Double,
        val visibility: String,
        val timestamp: Long
    )

    fun getIss(callback: (iss: RawIss) -> Unit) {
        val url = baseUrl + "v1/satellites/25544"
        GlobalScope.launch(ApplicationDispatcher) {
            val results = client.get<RawIss>(url)
            callback(results)
        }
    }
}