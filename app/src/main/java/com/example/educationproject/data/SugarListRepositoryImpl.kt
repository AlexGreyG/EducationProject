package com.example.educationproject.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.educationproject.domain.SugarItem
import com.example.educationproject.domain.SugarListRepository
import kotlin.random.Random

object SugarListRepositoryImpl : SugarListRepository {
    private var autoId = 0
    private val sugarListLD = MutableLiveData<List<SugarItem>>()
    private val sugarList = sortedSetOf<SugarItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    init {
        for (i in 0..10) {
            addSugarItem(SugarItem(i.toFloat(), "name $i", Random.Default.nextBoolean()))
        }
    }

    override fun addSugarItem(sugarItem: SugarItem) {
        if (sugarItem.id == SugarItem.UNDEFINED_ID) {
            sugarItem.id = autoId++
        }
        sugarList.add(sugarItem)
        updateLD()
    }

    override fun deleteSugarItem(sugarItem: SugarItem) {
        sugarList.remove(sugarItem)
        updateLD()
    }

    override fun editSugarItem(sugarItem: SugarItem) {
        val old = getSugarItem(sugarItem.id)
        if (old != null) {
            sugarList.remove(old)
        }
        addSugarItem(sugarItem)
    }

    override fun getSugarItem(id: Int): SugarItem? {
        return sugarList.find { it.id == id }
    }

    override fun getSugarList(): LiveData<List<SugarItem>> {
        return sugarListLD
    }

    private fun updateLD() {
        sugarListLD.postValue(sugarList.toList())
    }

}