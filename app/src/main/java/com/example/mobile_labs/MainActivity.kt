package com.example.mobile_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.commit

interface OnOKButtonClickedListener {
    fun onOKButtonClicked(author: String, years: RadioButton?)
}

class MainActivity : AppCompatActivity(), OnOKButtonClickedListener {

    private val outputTextFragment = OutputTextFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.author_and_years_fragment_container_view, AuthorAndYearsFragment())
                add(R.id.output_text_fragment_container_view, outputTextFragment)
            }
        }
    }

    override fun onOKButtonClicked(author: String, years: RadioButton?) {
        if (years == null) {
            val text = "Please pick some years range"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(applicationContext, text, duration).show()
            return
        }
        val outputText = getString(R.string.output_template, author, years.text.toString())
        outputTextFragment.setOutputText(outputText)
    }
}
