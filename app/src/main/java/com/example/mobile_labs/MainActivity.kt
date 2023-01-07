package com.example.mobile_labs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.commit
import java.io.File

interface OnOKButtonClickedListener {
    fun onOKButtonClicked(author: String, years: RadioButton?)
}

class MainActivity : AppCompatActivity(), OnOKButtonClickedListener {

    private lateinit var file: File
    private lateinit var databaseHelper: DatabaseHelper
    private val outputTextFragment = OutputTextFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        file = File(filesDir, "answers")
        databaseHelper = DatabaseHelper.getInstance(this)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.author_and_years_fragment_container_view, AuthorAndYearsFragment())
                add(R.id.output_text_fragment_container_view, outputTextFragment)
            }
        }

        val showAnswers = findViewById<Button>(R.id.show_answers)
        showAnswers.setOnClickListener {
            val intent = Intent(this, AnswersActivity::class.java)
            intent.putExtra("filename", file.name)
            startActivity(intent)
        }
    }

    override fun onOKButtonClicked(author: String, years: RadioButton?) {
        val duration = Toast.LENGTH_SHORT
        if (years == null) {
            val text = "Please pick some years range"
            Toast.makeText(applicationContext, text, duration).show()
            return
        }
        val outputText = getString(R.string.output_template, author, years.text.toString())
        outputTextFragment.setOutputText(outputText)
        databaseHelper.insertAnswer(author, years.text.toString())
        val text = "New answer has been written to DB"
        Toast.makeText(applicationContext, text, duration).show()
    }
}
