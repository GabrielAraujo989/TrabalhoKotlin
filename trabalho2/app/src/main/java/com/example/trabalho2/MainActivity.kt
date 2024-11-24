package com.example.trabalho2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var lastNumber = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        setupNumberButtons()
        setupOperatorButtons()
        setupUtilityButtons()
    }

    private fun setupNumberButtons() {
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                currentInput += (it as Button).text
                updateResult(currentInput)
            }
        }
    }

    private fun setupOperatorButtons() {
        val operators = mapOf(
            R.id.btnPlus to "+",
            R.id.btnMinus to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )
        operators.forEach { (id, op) ->
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    lastNumber = currentInput
                    currentInput = ""
                    operator = op
                }
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            if (currentInput.isNotEmpty() && lastNumber.isNotEmpty() && operator.isNotEmpty()) {
                val result = calculate(lastNumber.toDouble(), currentInput.toDouble(), operator)
                updateResult(result.toString())
                resetState(result.toString())
            }
        }
    }

    private fun setupUtilityButtons() {
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            resetState("0")
        }

        findViewById<Button>(R.id.btnSqrt).setOnClickListener {
            handleSingleOperation { sqrt(it) }
        }

        findViewById<Button>(R.id.btnPercent).setOnClickListener {
            handleSingleOperation { it / 100 }
        }

        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener {
            handleSingleOperation { -it }
        }

    }

    private fun calculate(num1: Double, num2: Double, op: String): Double {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }

    private fun factorial(n: Int): Int {
        return if (n <= 1) 1 else n * factorial(n - 1)
    }

    private fun handleSingleOperation(operation: (Double) -> Double) {
        if (currentInput.isNotEmpty()) {
            val input = currentInput.toDouble()
            val result = operation(input)
            updateResult(result.toString())
            resetState(result.toString())
        }
    }

    private fun updateResult(value: String) {
        tvResult.text = value
    }

    private fun resetState(value: String) {
        currentInput = value
        lastNumber = ""
        operator = ""
    }
}

