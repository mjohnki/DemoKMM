package de.johnki.kmmtest

import kotlinx.coroutines.flow.Flow

class IssRepository(private val database: IssDatabase, private val api: Api) {

    fun getIss(): Flow<Iss?> {
        updateIss()
        return database.getIss()
    }

    fun updateIss() {
        api.getIss {  iss ->
            database.insertIss(iss.id,
                iss.latitude,
                iss.longitude,
                iss.altitude,
                iss.velocity,
                iss.visibility,
                iss.timestamp)
        }
    }
}