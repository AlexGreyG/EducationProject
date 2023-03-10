package com.example.educationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        loadData {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        }


        lifecycleScope.launch {
            Toast.makeText(this@MainActivity, "Starting to load", Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity, "Starting to load = "+loadData2(), Toast.LENGTH_LONG).show()
        }

    }


    private fun loadData(callback: (String) -> Unit) {
        thread {
            Handler(Looper.getMainLooper()).post{
                Toast.makeText(this, "Starting to load", Toast.LENGTH_LONG).show()
            }

            //или проще
            runOnUiThread{
                Toast.makeText(this, "Starting to load", Toast.LENGTH_LONG).show()
            }


            Thread.sleep(5000)
            Handler(Looper.getMainLooper()).post{
                callback.invoke("Sos me")
            }
        }
    }

    private suspend fun loadData2():String{
        delay(5000)
        return "Sosme1"
    }
}