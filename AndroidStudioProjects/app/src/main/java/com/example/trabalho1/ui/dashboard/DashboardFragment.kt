package com.example.trabalho1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.trabalho1.R

class DashboardFragment : Fragment() {

    private val conversionRates = mapOf(
        "Real" to mapOf("Dólar" to 0.20, "Euro" to 0.18, "Peso" to 27.0),
        "Dólar" to mapOf("Real" to 5.0, "Euro" to 0.91, "Peso" to 135.0),
        "Euro" to mapOf("Real" to 5.5, "Dólar" to 1.1, "Peso" to 150.0),
        "Peso" to mapOf("Real" to 0.037, "Dólar" to 0.0074, "Euro" to 0.0067)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val etValue = view.findViewById<EditText>(R.id.etValue)
        val spinnerFrom = view.findViewById<Spinner>(R.id.spinnerFrom)
        val spinnerTo = view.findViewById<Spinner>(R.id.spinnerTo)
        val btnConvert = view.findViewById<Button>(R.id.btnConvert)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)

        btnConvert.setOnClickListener {
            val fromCurrency = spinnerFrom.selectedItem.toString()
            val toCurrency = spinnerTo.selectedItem.toString()
            val value = etValue.text.toString().toDoubleOrNull()

            if (value != null) {
                val result = convertCurrency(value, fromCurrency, toCurrency)
                tvResult.text = "Resultado: %.2f %s".format(result, toCurrency)
            } else {
                Toast.makeText(requireContext(), "Insira um valor válido", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun convertCurrency(value: Double, from: String, to: String): Double {
        if (from == to) return value
        val rate = conversionRates[from]?.get(to)
        return if (rate != null) value * rate else 0.0
    }
}
