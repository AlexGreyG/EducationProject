package com.example.educationproject.domain

import androidx.lifecycle.LiveData

interface SugarListRepository {
    fun addSugarItem(sugarItem:SugarItem)
    fun deleteSugarItem(sugarItem: SugarItem)
    fun editSugarItem(sugarItem: SugarItem)
    fun getSugarItem(id:Int):SugarItem?
    fun getSugarList():LiveData<List<SugarItem>>
}