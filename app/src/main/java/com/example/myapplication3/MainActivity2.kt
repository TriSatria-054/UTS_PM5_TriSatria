package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private val questions = arrayOf(
        "Siapa penemu teori relativitas?",
        "Apa rumus kuadrat dari a + b?",
        "Berapa jumlah provinsi di Indonesia?",
        "Apa nama ibu kota Jepang?",
        "Apa unsur kimia dengan simbol 'O'?",
        "Siapa penulis novel 'Laskar Pelangi'?",
        "Apa yang dimaksud dengan fotosintesis?",
        "Siapa yang melukis 'Starry Night'?",
        "Apa yang menyebabkan gerhana matahari?",
        "Siapa yang dikenal sebagai Bapak Proklamasi di Indonesia?"
    )

    private val options = arrayOf(
        arrayOf("Albert Einstein", "Isaac Newton", "Nikola Tesla", "Galileo Galilei", "Stephen Hawking"),
        arrayOf("a^2 + b^2", "a + b", "a^2 - b^2", "ab", "a^2 + 2ab + b^2"),
        arrayOf("34", "36", "38", "35", "39"),
        arrayOf("Seoul", "Hanoi", "Bangkok", "Tokyo", "Beijing"),
        arrayOf("Osmium", "Oganesson", "Oksigen", "Ondan", "Bromin"),
        arrayOf("Dewi Lestari", "Ayudia Bing Slamet", "Andrea Hirata", "Tere Liye", "Pramoedya Ananta Toer"),
        arrayOf("Proses penguapan air", "Proses tanaman menghasilkan makanan", "Proses fotosintesis", "Proses pertumbuhan tanaman", "Proses pembuatan pupuk"),
        arrayOf("Salvador Dalí", "Claude Monet", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso"),
        arrayOf("Ketika bumi menghalangi sinar matahari", "Ketika bulan menghalangi sinar matahari", "Ketika planet berputar", "Ketika bulan penuh", "Ketika bintang bergerak"),
        arrayOf("Megawati", "B.J. Habibie", "Gus Dur", "Soekarno", "Soeharto")
    )

    private val correctAnswers = arrayOf(0, 4, 2, 3, 2, 2, 1, 2, 1, 3)


    private var score = 0
    private var userAnswers = IntArray(questions.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val name = intent.getStringExtra("USER_NAME")
        val txtDisplayName: TextView = findViewById(R.id.txtDisplayName)
        txtDisplayName.text = "Selamat datang, $name!"


        displayQuestions()

        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            checkAnswers()
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("USER_NAME", name)
            intent.putExtra("SCORE", score)
            intent.putExtra("CORRECT_ANSWERS", correctAnswers)
            intent.putExtra("USER_ANSWERS", userAnswers)
            startActivity(intent)
            finish()
        }
    }

    private fun displayQuestions() {
        val questionContainer: LinearLayout = findViewById(R.id.questionContainer)
        for (i in questions.indices) {
            val questionTextView = TextView(this)
            questionTextView.text = "${i + 1}. ${questions[i]}"
            questionContainer.addView(questionTextView)

            val radioGroup = RadioGroup(this)
            for (j in options[i].indices) {
                val radioButton = RadioButton(this)
                radioButton.id = j
                radioButton.text = options[i][j]
                radioGroup.addView(radioButton)
            }
            questionContainer.addView(radioGroup)
        }
    }

    private fun checkAnswers() {
        val questionContainer: LinearLayout = findViewById(R.id.questionContainer)
        for (i in questions.indices) {
            val radioGroup = questionContainer.getChildAt(i * 2 + 1) as RadioGroup //radiogrup indeks ganjil, textview indeks genap
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId != -1) {
                userAnswers[i] = selectedId
                if (selectedId == correctAnswers[i]) {
                    score++
                }
            }
        }
    }
}
