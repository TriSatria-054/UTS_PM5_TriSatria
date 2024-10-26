package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName: EditText = findViewById(R.id.edt_name)
        val btnSubmit: Button = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            val name = edtName.text.toString()

            if (name.isNotEmpty()) {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Masukkan nama Anda terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
