package com.example.calculator.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val _result = MutableLiveData("")
    private val result: LiveData<String> = _result

    private var firstNumber = ""
    private var secondNumber = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resultTextView.text = result.value.toString()

        binding.clearButton.setOnClickListener {
            clearData()
        }

        binding.deleteButton.setOnClickListener {
            if (_result.value?.isNotBlank() == true) {
                _result.value = _result.value?.dropLast(1).toString()
            }
        }

        binding.divisionButton.setOnClickListener {
            appendOperator("/")
        }

        binding.multiplicationButton.setOnClickListener {
            appendOperator("X")
        }

        binding.subtractionButton.setOnClickListener {
            appendOperator("-")
        }

        binding.addButton.setOnClickListener {
            appendOperator("+")
        }

        binding.equalsButton.setOnClickListener {
            // 计算结果
            calculateResult()
        }

        binding.buttonZero.setOnClickListener {
            appendNumber("0")
        }

        binding.buttonOne.setOnClickListener {
            appendNumber("1")
        }

        binding.buttonTwo.setOnClickListener {
            appendNumber("2")
        }

        binding.buttonThree.setOnClickListener {
            appendNumber("3")
        }

        binding.buttonFour.setOnClickListener {
            appendNumber("4")
        }

        binding.buttonFive.setOnClickListener {
            appendNumber("5")
        }

        binding.buttonSix.setOnClickListener {
            appendNumber("6")
        }

        binding.buttonSeven.setOnClickListener {
            appendNumber("7")
        }

        binding.buttonEight.setOnClickListener {
            appendNumber("8")
        }

        binding.buttonNine.setOnClickListener {
            appendNumber("9")
        }

        binding.decimalButton.setOnClickListener {
            appendNumber(".")
        }

        // 观察 LiveData 的变化并更新 UI
        result.observe(this) { value ->
            binding.resultTextView.text = value
        }
    }

    private fun appendNumber(number: String) {
        // 如果已经有运算符，添加到第二个数字
        if (operator.isNotBlank()) {
            secondNumber += number
        } else {
            // 否则添加到第一个数字
            firstNumber += number
        }
        _result.value = "${_result.value}$number"
    }

    private fun appendOperator(op: String) {
        // 如果已经有运算符或者没有数字，不做任何操作
        if (operator.isNotBlank() || firstNumber.isBlank()) {
            return
        }
        operator = op
        _result.value = "${_result.value}$op"
    }

    private fun calculateResult() {
        if (firstNumber.isBlank() || secondNumber.isBlank() || operator.isBlank()) {
            return
        }

        val first = firstNumber.toDouble()
        val second = secondNumber.toDouble()
        val resultValue = when (operator) {
            "+" -> first + second
            "-" -> first - second
            "X" -> first * second
            "/" -> first / second
            else -> 0.0
        }

        _result.value = resultValue.toString()
    }

    private fun clearData() {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        _result.value = ""
    }
}
