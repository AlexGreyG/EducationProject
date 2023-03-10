package com.example.educationproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.educationproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adaprer: SugarListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        adaprer = SugarListAdapter()
        adaprer.onSugarItemLongClickListener = {
            val item = it.copy()
            item.isDeleted = !item.isDeleted
            viewModel.editSugarItem(item)
            Log.d("sosme2", it.toString())
        }


        adaprer.onSugarItemClickListener = {
            val item = it
            startActivity(EditSugarActivity.startEdit(this, item.id))
        }

        viewModel.sugarList.observe(this) {
            Log.d("sosme", it.toString())
            adaprer.list = it
        }
        setupRV()


        val fab = findViewById<FloatingActionButton>(R.id.amAdd)
        fab.setOnClickListener {
            startActivity(EditSugarActivity.startAdd(this@MainActivity))
        }




    }


    fun setupRV() {
        val rv = findViewById<RecyclerView>(R.id.amList)
        rv.adapter = adaprer

    }
}