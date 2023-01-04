package com.example.mobile_labs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView

class OutputTextFragment : Fragment(R.layout.fragment_output_text) {
    private lateinit var resText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resText = view.findViewById(R.id.result)
    }

    fun setOutputText(text: String) {
        resText.text = text
    }
}