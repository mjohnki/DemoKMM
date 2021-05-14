package de.johnki.kmmtest

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import de.johnki.shared.Database
import kotlinx.coroutines.flow.Flow

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

class IssDatabase(driverFactory: DriverFactory) {
    private var driver: SqlDriver = driverFactory.createDriver()
    private val database: Database by lazy {
        Database(driver)
    }

    fun insertIss(
        id: Long,
        latitude: Double,
        longitude: Double,
        altitude: Double,
        velocity: Double,
        visibility: String,
        timestamp: Long
    ) {
        database.databaseQueries.insert(
            id,
            latitude,
            longitude,
            altitude,
            velocity,
            visibility,
            timestamp
        )
    }

    fun getIss(): Flow<Iss?> {
        return database.databaseQueries.select().asFlow()
            .mapToOneOrNull()
    }
}