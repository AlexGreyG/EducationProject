package com.example.educationproject.domain

import androidx.lifecycle.LiveData

class GetSugarListUseCase(private val sugarListRepository: SugarListRepository) {
    fun getSugarList():LiveData<List<SugarItem>>{
        return sugarListRepository.getSugarList()
    }
}