package de.johnki.kmmtest

class UpdateIssUseCase(private val repository: IssRepository) {

    fun updateIss() {
        return repository.updateIss()
    }
}