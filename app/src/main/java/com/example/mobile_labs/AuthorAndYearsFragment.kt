package com.example.mobile_labs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class AuthorAndYearsFragment : Fragment(R.layout.fragment_author_and_years) {
    private lateinit var authorsList: Spinner
    private lateinit var yearsGroup: RadioGroup
    private lateinit var okButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorsList = view.findViewById(R.id.author_list)
        yearsGroup = view.findViewById(R.id.years_group)
        okButton = view.findViewById(R.id.ok)
        okButton.setOnClickListener {
            val author = authorsList.selectedItem.toString()
            val id = yearsGroup.checkedRadioButtonId
            val years = view.findViewById<RadioButton>(id)
            (context as OnOKButtonClickedListener).onOKButtonClicked(author, years)
        }
    }
}
