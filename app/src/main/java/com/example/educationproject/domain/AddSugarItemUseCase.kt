package com.example.educationproject.domain

class AddSugarItemUseCase(private val sugarListRepository: SugarListRepository) {
    fun addSugarItem(sugarItem:SugarItem){
        sugarListRepository.addSugarItem(sugarItem)
    }
}
