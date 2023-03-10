package com.example.educationproject.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.educationproject.domain.SugarItem

class SugarItemDiffCallback : DiffUtil.ItemCallback<SugarItem>() {
    override fun areItemsTheSame(oldItem: SugarItem, newItem: SugarItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SugarItem, newItem: SugarItem): Boolean {
        return oldItem == newItem
    }
}