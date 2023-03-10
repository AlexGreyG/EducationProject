package com.example.educationproject.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.educationproject.R
import com.example.educationproject.domain.SugarItem

class SugarListAdapter : RecyclerView.Adapter<SugarListAdapter.SugarListViewHolder>() {
    var list = listOf<SugarItem>()
        set(value) {
            if(list.size>9) {
                Log.d("sosme4","setter: "+list[9].isDeleted+"-"+value[9].isDeleted+">>>"+value[9].id)
            }
            val callback = SugarListDiffCallback(list, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            Log.d("sosme4","setter"+callback.toString())
            diffResult.dispatchUpdatesTo(this)
            field = value
            //notifyDataSetChanged()
        }

    var onSugarItemLongClickListener: ((SugarItem) -> Unit)? = null//вместо интерфейса с 1 функцией
    var onSugarItemClickListener: ((SugarItem) -> Unit)? = null//вместо интерфейса с 1 функцией

    class SugarListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lsName = view.findViewById<TextView>(R.id.lsDate)
        val lsData = view.findViewById<TextView>(R.id.lsData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugarListViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.list_sugar
            VIEW_TYPE_DISABLED -> R.layout.list_sugar_deleted
            else -> throw java.lang.Exception("Not valid view type")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return SugarListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return if (item.isDeleted) {
            VIEW_TYPE_DISABLED
        } else {
            VIEW_TYPE_ENABLED
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SugarListViewHolder, position: Int) {
        val item = list[position]
        Log.d("sosme3","onBindViewHolder, ${item.id} - ${item.isDeleted}")
        holder.itemView.setOnLongClickListener {
            onSugarItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener {
            onSugarItemClickListener?.invoke(item)
        }
        holder.lsData.text = item.sugar.toString()
        holder.lsName.text = item.date
    }

    override fun onViewRecycled(holder: SugarListViewHolder) {
        super.onViewRecycled(holder)
        //тут ставятся значения по умолчанию
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 5
    }


}