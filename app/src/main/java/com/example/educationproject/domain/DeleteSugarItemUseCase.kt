package com.example.educationproject.domain

class DeleteSugarItemUseCase(private val sugarListRepository: SugarListRepository)  {
    fun deleteSugarItem(sugarItem: SugarItem){
        sugarListRepository.deleteSugarItem(sugarItem)
    }
}