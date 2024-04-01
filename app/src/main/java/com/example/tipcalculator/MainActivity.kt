package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var switchRoundUp: Switch
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTxt)
        radioGroup = findViewById(R.id.rgTip)
        switchRoundUp = findViewById(R.id.switchTip)
        calculateButton = findViewById(R.id.btnCal)
        resultTextView = findViewById(R.id.txtView)

        calculateButton.setOnClickListener {
            val costOfService = editText.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val tipPercentage = when (radioButton?.id) {
                R.id.rdBtn1 -> 0.20
                R.id.rdBtn2 -> 0.18
                R.id.rdBtn3 -> 0.15
                else -> 0.0
            }
            var tipAmount = costOfService * tipPercentage
            if (switchRoundUp.isChecked) {
                tipAmount = kotlin.math.ceil(tipAmount)
            }
            resultTextView.text = "Tip Amount: $${"%.2f".format(tipAmount)}"
        }
    }
}