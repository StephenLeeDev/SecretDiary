package com.example.secretdiary

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val editTextDiary = findViewById<EditText>(R.id.editTextDiary)
        val sharedPreference = getSharedPreferences("diary", Context.MODE_PRIVATE)

        editTextDiary.setText(sharedPreference.getString("detail", ""))

        val runnable = Runnable {
            sharedPreference.edit {
                putString("detail", editTextDiary.text.toString())
            }
        }

        editTextDiary.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}