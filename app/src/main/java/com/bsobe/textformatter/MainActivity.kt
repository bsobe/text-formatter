package com.bsobe.textformatter

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bsobe.formatter.TextFormatter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewCreditCardNumber = findViewById<TextView>(R.id.textViewCreditCardNumber)
        TextFormatter.Builder()
            .setPattern("#### #### #### ####")
            .attach(textViewCreditCardNumber)

        val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)
        TextFormatter.Builder()
            .customizeIndex(index = 1, customizedCharacter = " (")
            .customizeIndex(index = 4, customizedCharacter = ") ")
            .addSpaceAt(index = 7)
            .addSpaceAt(index = 9)
            .attach(editTextPhoneNumber)
    }
}
