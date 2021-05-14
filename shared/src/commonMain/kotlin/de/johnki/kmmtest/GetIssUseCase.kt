package de.johnki.kmmtest

import kotlinx.coroutines.flow.Flow

class GetIssUseCase(private val repo: IssRepository) {

    fun getIssFlow(): Flow<Iss?> {
        return repo.getIss()
    }

    fun getIssCommonFlow() :CommonFlow<Iss?>{
        return repo.getIss().asCommonFlow()
    }
}