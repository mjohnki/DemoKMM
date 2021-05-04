package de.johnki.kmmtest

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import de.johnki.shared.Database
import kotlinx.coroutines.flow.Flow

expect class DriverFactory {
     fun createDriver(): SqlDriver
}

class TodoDatabase(driverFactory: DriverFactory) {
    private var driver: SqlDriver = driverFactory.createDriver()
    private val database: Database by lazy {
        Database(driver)
    }

    fun addTodo(title: String, completed: Boolean) {
        val completedNum = if (completed) 1L else 0L
        database.databaseQueries.insert(title, completedNum)
    }

    fun getTodos(): Flow<List<Todo>> {
        return database.databaseQueries.selectAll().asFlow()
            .mapToList()
    }

    fun deleteAll() {
        database.databaseQueries.deleteAll()
    }
}