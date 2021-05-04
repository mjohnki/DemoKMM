package de.johnki.kmmtest

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val database: TodoDatabase, private val api: Api) {
    private fun addTodo(title: String, completed: Boolean) {
        database.addTodo(title, completed)
    }

    fun getTodos(): Flow<List<Todo>> {
        api.getTodos { todos ->
            todos.forEach {
                addTodo(it.title, it.completed)
            }
        }
        return database.getTodos()
    }

    fun deleteAll() {
        database.deleteAll()
    }
}