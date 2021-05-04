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
    private val client = HttpClient{
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }
    }

    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    @Serializable
    data class ApiTodo(val id: Int, val userId: Int, val title: String, val completed: Boolean)

    fun getTodos(callback: (List<ApiTodo>) -> Unit) {
        val url = baseUrl + "todos/"
        GlobalScope.launch(ApplicationDispatcher) {
            val results = client.get<List<ApiTodo>>(url)
            callback(results)
        }
    }
}