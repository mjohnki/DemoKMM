package de.johnki.kmmtest.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.asLiveData
import de.johnki.kmmtest.Api
import de.johnki.kmmtest.DriverFactory
import de.johnki.kmmtest.TodoDatabase
import de.johnki.kmmtest.TodoRepository


class MainActivity : AppCompatActivity() {

    private val api = Api()
    private val driverFactory = DriverFactory(this)
    private val database =  TodoDatabase(driverFactory)
    private val repository = TodoRepository(database, api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        val todosFlow = repository.getTodos()
        val todos = todosFlow.asLiveData()

        todos.observe(this){
            tv.text = "Size: ${it.size}"
        }
    }
}
