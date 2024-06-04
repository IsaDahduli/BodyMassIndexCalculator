package com.isa.bodymassindexcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.btn_calculate)
        val resetButton: Button = findViewById(R.id.btn_reset)
        val heightEditText: EditText = findViewById(R.id.et_height_cm)
        val weightEditText: EditText = findViewById(R.id.et_weight_kg)
        val bmiResultTextView: TextView = findViewById(R.id.tv_total)
        val weightConditionTextView: TextView = findViewById(R.id.weight_body_condition)

        calculateButton.setOnClickListener {
            val heightValue = heightEditText.text.toString().toFloatOrNull()
            val weightValue = weightEditText.text.toString().toFloatOrNull()

            if (heightValue != null && weightValue != null && heightValue > 0 && weightValue > 0) {
                val heightInMeters = heightValue / 100
                val bmiValue = weightValue / (heightInMeters * heightInMeters)
                bmiResultTextView.text = "Total BMI: %.2f".format(bmiValue)

                val weightCondition = when {
                    bmiValue < 18.5 -> "Underweight"
                    bmiValue in 18.5..24.9 -> "Normal weight"
                    bmiValue in 25.0..29.9 -> "Overweight"
                    else -> "Obesity"
                }
                weightConditionTextView.text = "Weight Condition: $weightCondition"
            } else {
                Toast.makeText(this, "Please enter valid height and weight values.", Toast.LENGTH_SHORT).show()
                bmiResultTextView.text = "Total BMI: "
                weightConditionTextView.text = "Weight Condition: "
            }
        }

        resetButton.setOnClickListener {
            heightEditText.text.clear()
            weightEditText.text.clear()
            bmiResultTextView.text = "Total BMI: "
            weightConditionTextView.text = "Weight Condition: "
        }
    }
}
