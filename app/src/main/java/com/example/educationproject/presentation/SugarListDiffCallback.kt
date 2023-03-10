package com.example.educationproject.presentation

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.educationproject.domain.SugarItem

class SugarListDiffCallback(
    private val oldList: List<SugarItem>, private val newList: List<SugarItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        val isM = oldItem==newItem
        Log.d("sosme4", ">>>>"+isM+"-"+oldItem.id+"-"+newItem.id)
        return oldItem.isDeleted == newItem.isDeleted && oldItem.id == newItem.id
    }

}