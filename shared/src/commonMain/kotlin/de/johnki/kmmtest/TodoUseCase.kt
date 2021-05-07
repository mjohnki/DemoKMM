package de.johnki.kmmtest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach

class TodoUseCase(private val repo: TodoRepository) {

    fun getTodosFlow(): Flow<List<Todo>> {
        return repo.getTodos()
    }

    fun getTodosCommonFlow() :CommonFlow<List<Todo>>{
        return repo.getTodos().asCommonFlow()
    }
}