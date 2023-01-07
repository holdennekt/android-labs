package com.example.mobile_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class AnswersActivity : AppCompatActivity() {
    private lateinit var table: TableLayout
    private val databaseHelper = DatabaseHelper.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        table = findViewById(R.id.answers_table)

        refillTable()

        val clearButton = findViewById<Button>(R.id.clear_answers)
        clearButton.setOnClickListener {
            databaseHelper.deleteAnswers()
            refillTable()
        }
    }

    private fun refillTable() {
        table.removeViews(1, table.childCount - 1)
        val answers = databaseHelper.selectAnswers()
        answers.forEach { (author, years) ->
            val row = TableRow(this)
            val authorCell = TextView(this)
            authorCell.text = author
            authorCell.gravity = Gravity.CENTER
            row.addView(authorCell)
            val yearsCell = TextView(this)
            yearsCell.text = years
            yearsCell.gravity = Gravity.CENTER
            row.addView(yearsCell)
            table.addView(row)
        }
        val duration = Toast.LENGTH_SHORT
        val text = "There are ${answers.size} answers stored"
        Toast.makeText(applicationContext, text, duration).show()
    }
}