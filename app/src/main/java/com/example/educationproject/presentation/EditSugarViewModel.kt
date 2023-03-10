package com.example.educationproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educationproject.data.SugarListRepositoryImpl
import com.example.educationproject.data.SugarListRepositoryImpl.getSugarList
import com.example.educationproject.domain.*

class EditSugarViewModel : ViewModel() {
    private val repo = SugarListRepositoryImpl
    private val editSugarItemUseCase = EditSugarItemUseCase(repo)
    private val addSugarItemUseCase = AddSugarItemUseCase(repo)
    private val getSugarItem = GetSugarItemUseCase(repo)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private val _errorInputValue = MutableLiveData<Boolean>()
    val errorInputValue: LiveData<Boolean>
        get() = _errorInputName

    private val _sugarItem = MutableLiveData<SugarItem>()
    val sugarItem: LiveData<SugarItem>
        get() = _sugarItem

    private val _canExit = MutableLiveData<Unit>()
    val canExit: LiveData<Unit>
        get() = _canExit

    fun addSugarItem(sugarItem: SugarItem) {
        addSugarItemUseCase.addSugarItem(sugarItem)
    }

    fun getSugarItem(id: Int) {
        val sugarItem = getSugarItem.getSugarItem(id)
        _sugarItem.value = sugarItem
    }


    fun editSugarItem(name: String?, value: String?) {
        val name = parseName(name)
        val value = parseValue(value)

        if (validateInput(name, value)) {
            val sugarItem = _sugarItem.value
            sugarItem?.let {
                val item = it.copy()
                item.date = name
                item.sugar = value.toFloat()
                editSugarItemUseCase.editSugarItem(item)
            }
            finish()
        }
    }

    fun addSugarItem(name: String?, value: String?) {
        val name = parseName(name)
        val value = parseValue(value)

        if (validateInput(name, value)) {
            val sugarItem = SugarItem(value.toFloat(), name, false)
            addSugarItem(sugarItem)
            finish()
        }
    }

    private fun parseName(name: String?) = name ?: ""

    private fun parseValue(value: String?) = value?.toIntOrNull() ?: 0
    private fun validateInput(name: String, value: Int): Boolean {
        if (name == "") {
            _errorInputName.value = true
            return false
        } else if (value <= 0) {
            _errorInputValue.value = true
            return false
        } else {
            return true
        }
    }

    private fun finish() {
        _canExit.value = Unit
    }
}