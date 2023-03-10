package com.example.educationproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educationproject.data.SugarListRepositoryImpl
import com.example.educationproject.data.SugarListRepositoryImpl.getSugarList
import com.example.educationproject.domain.DeleteSugarItemUseCase
import com.example.educationproject.domain.EditSugarItemUseCase
import com.example.educationproject.domain.GetSugarListUseCase
import com.example.educationproject.domain.SugarItem

class MainViewModel : ViewModel() {
    private val repo = SugarListRepositoryImpl
    private val getSugarListUseCase = GetSugarListUseCase(repo)
    private val deleteSugarItemUseCase = DeleteSugarItemUseCase(repo)
    private val editSugarItemUseCase = EditSugarItemUseCase(repo)

    val sugarList = getSugarListUseCase.getSugarList()

    fun deleteSugarItem(sugarItem: SugarItem) {
        deleteSugarItemUseCase.deleteSugarItem(sugarItem)
    }


    fun editSugarItem(sugarItem: SugarItem) {
        editSugarItemUseCase.editSugarItem(sugarItem)
    }
}