package com.example.educationproject.domain

class EditSugarItemUseCase(private val sugarListRepository: SugarListRepository)  {
    fun editSugarItem(sugarItem: SugarItem){
        sugarListRepository.editSugarItem(sugarItem)
    }
}