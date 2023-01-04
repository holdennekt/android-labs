package com.example.mobile_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*

class Answers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val table = findViewById<TableLayout>(R.id.answers_table)

        val filename = intent.getStringExtra("filename")
        openFileInput(filename).bufferedReader().useLines { lines ->
            var linesCount = 0
            lines.forEach {
                val row = TableRow(this)
                val (author, years) = it.split(":")
                val authorCell = TextView(this)
                authorCell.text = author
                authorCell.gravity = Gravity.CENTER
                row.addView(authorCell)
                val yearsCell = TextView(this)
                yearsCell.text = years
                yearsCell.gravity = Gravity.CENTER
                row.addView(yearsCell)
                table.addView(row)
                linesCount++
            }
            val duration = Toast.LENGTH_SHORT
            val text = "There are $linesCount answers stored"
            Toast.makeText(applicationContext, text, duration).show()
        }

        val clearButton = findViewById<Button>(R.id.clear_answers)
        clearButton.setOnClickListener {
            openFileOutput(filename, MODE_PRIVATE).bufferedWriter().use {
                it.write("")
            }
            table.removeViews(1, table.childCount - 1)
        }
    }
}