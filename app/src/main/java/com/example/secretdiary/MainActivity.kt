package com.example.secretdiary

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var isChangePasswordMode = false;

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
                .apply {
                    minValue = 0
                    maxValue = 9
                }
    }

    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
                .apply {
                    minValue = 0
                    maxValue = 9
                }
    }

    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
                .apply {
                    minValue = 0
                    maxValue = 9
                }
    }

    private val buttonOpen: AppCompatButton by lazy {
        findViewById(R.id.buttonOpen)
    }

    private val buttonChangePassword: AppCompatButton by lazy {
        findViewById(R.id.buttonChangePassword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        buttonOpen.setOnClickListener(this)
        buttonChangePassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonOpen -> enterPassword()
            R.id.buttonChangePassword -> changePassword()
        }
    }

    private fun enterPassword() {

        if (isChangePasswordMode) {
            Toast.makeText(this, "Changing password", Toast.LENGTH_SHORT).show()
            return
        }

        val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
        val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

        if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {

        } else {

            showErrorAlertDialog()

        }
    }

    private fun changePassword() {

        val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
        val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

        if (isChangePasswordMode) {

            passwordPreference.edit(true) {
                putString("password", passwordFromUser)
            }

            isChangePasswordMode = false
            buttonChangePassword.setBackgroundColor(Color.BLACK)

        } else {

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {

                isChangePasswordMode = true
                Toast.makeText(this, "Please enter a new password", Toast.LENGTH_SHORT).show()
                buttonChangePassword.setBackgroundColor(Color.RED)

            } else {

                showErrorAlertDialog()

            }
        }
    }

    private fun showErrorAlertDialog() {

        AlertDialog.Builder(this)
                .setTitle("Failure")
                .setMessage("Wrong password")
                .setPositiveButton("OK") { _, _ -> }
                .create()
                .show()

    }
}