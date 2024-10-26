package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var isDarkTheme = false
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName: EditText = findViewById(R.id.edt_name)
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        val switchTheme = findViewById<Switch>(R.id.switchTheme)

        //mengatasi reset value ketika device dirotasi
        if (savedInstanceState != null) {
            userName = savedInstanceState.getString("USER_NAME")
            edtName.setText(userName) //kembalikan nilai sesuai isi editText
        }

        isDarkTheme = (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        switchTheme.isChecked = isDarkTheme
        updateTheme()  //tema awal

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            isDarkTheme = isChecked
            updateTheme() //ganti tema
        }

        btnSubmit.setOnClickListener {
            userName = edtName.text.toString() //nyimpan nama user saat tombol ditekan

            if (userName!!.isNotEmpty()) {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("USER_NAME", userName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Masukkan nama Anda terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("USER_NAME", userName) //mengatasi reset value ketika device dirotasi
    }

    private fun updateTheme() {
        //tentukan warna sesuai tema saat ini
        val backgroundColor = if (isDarkTheme) R.color.backgroundDark else R.color.backgroundLight
        val textColor = if (isDarkTheme) R.color.textDark else R.color.textLight

        //mainlayout activity1 secara keseluruhan ganti sesuai tema
        findViewById<ConstraintLayout>(R.id.mainLayout)
            .setBackgroundColor(ContextCompat.getColor(this, backgroundColor))

        //ganti tema textview, button,edittext
        findViewById<TextView>(R.id.txtWelcome0).setTextColor(ContextCompat.getColor(this, textColor))
        findViewById<TextView>(R.id.txtWelcome).setTextColor(ContextCompat.getColor(this, textColor))
        findViewById<EditText>(R.id.edt_name).setTextColor(ContextCompat.getColor(this, textColor))
        findViewById<EditText>(R.id.edt_name).setHintTextColor(ContextCompat.getColor(this, textColor))
        findViewById<Button>(R.id.btn_submit).setTextColor(ContextCompat.getColor(this, textColor))
    }
}
