package com.example.educationproject.domain

class GetSugarItemUseCase(private val sugarListRepository: SugarListRepository) {
    fun getSugarItem(id: Int): SugarItem? {
        return sugarListRepository.getSugarItem(id)
    }
}