package com.example.mobile_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val authorsList = findViewById<Spinner>(R.id.author_list)
        val yearsGroup = findViewById<RadioGroup>(R.id.years_group)
        val okButton = findViewById<Button>(R.id.ok)
        val resText = findViewById<TextView>(R.id.result)

        okButton.setOnClickListener {
            val author = authorsList.selectedItem.toString()
            val id = yearsGroup.checkedRadioButtonId
            if (id == -1) {
                val text = "Please pick some years range"
                val duration = Toast.LENGTH_SHORT
                Toast.makeText(applicationContext, text, duration).show()
                return@setOnClickListener
            }
            val years = yearsGroup.findViewById<RadioButton>(id).text.toString()
            resText.text = getString(R.string.output_template, author, years)
        }
    }
}