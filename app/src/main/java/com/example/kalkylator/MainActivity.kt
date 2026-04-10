package com.example.kalkylator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputHeight: EditText
    private lateinit var inputWeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var bmiValue: TextView
    private lateinit var bmiStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputHeight = findViewById(R.id.inputHeight)
        inputWeight = findViewById(R.id.inputWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        bmiValue = findViewById(R.id.bmiValue)
        bmiStatus = findViewById(R.id.bmiStatus)

        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {

        val heightText = inputHeight.text.toString()
        val weightText = inputWeight.text.toString()

        if (heightText.isEmpty()) {
            Toast.makeText(this, "Введите ваш рост", Toast.LENGTH_SHORT).show()
            return
        }
        if (weightText.isEmpty()) {
            Toast.makeText(this, "Введите ваш вес", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightText.toFloatOrNull()
        val weight = weightText.toFloatOrNull()

        if (height == null || weight == null) {
            Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_SHORT).show()
            return
        }

        if (height <= 0 || weight <= 0) {
            Toast.makeText(this, "Рост и вес должны быть больше 0", Toast.LENGTH_SHORT).show()
            return
        }

        val heightInMeters = height / 100

        val bmi = weight / (heightInMeters * heightInMeters)

        val bmiRounded = String.format("%.1f", bmi)

        bmiValue.text = bmiRounded

        bmiStatus.text = getBMIDescription(bmi)
        bmiStatus.setTextColor(getStatusColor(bmi))
    }

    private fun getBMIDescription(bmi: Float): String {
        return when {
            bmi < 18.5 -> "⚠️ Недостаточный вес"
            bmi < 25 -> "✅ Нормальный вес"
            bmi < 30 -> "⚠️ Избыточный вес"
            else -> "❌ Ожирение"
        }
    }

    private fun getStatusColor(bmi: Float): Int {
        return when {
            bmi < 18.5 -> android.graphics.Color.parseColor("#FF9800") // Оранжевый
            bmi < 25 -> android.graphics.Color.parseColor("#4CAF50")   // Зелёный
            bmi < 30 -> android.graphics.Color.parseColor("#FFC107")   // Жёлтый
            else -> android.graphics.Color.parseColor("#F44336")       // Красный
        }
    }
}