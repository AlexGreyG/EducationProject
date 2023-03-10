package com.example.educationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }


    private fun loadData(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            callback.invoke("Sos me")
        }
    }
}