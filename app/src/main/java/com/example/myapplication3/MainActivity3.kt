package com.example.myapplication3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val name = intent.getStringExtra("USER_NAME")
        val score = intent.getIntExtra("SCORE", 0)

        val txtResult: TextView = findViewById(R.id.txtResult)
        txtResult.text = "Nama: $name\nScore: $score"
    }
}
