package com.example.trabalho1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.trabalho1.R

class NotificationsFragment : Fragment() {

    private var tipPercentage = 15 // Valor inicial da porcentagem de gorjeta

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        val etBillAmount = view.findViewById<EditText>(R.id.etBillAmount)
        val sbTipPercentage = view.findViewById<SeekBar>(R.id.sbTipPercentage)
        val tvTipPercentage = view.findViewById<TextView>(R.id.tvTipPercentage)
        val btnCalculateTip = view.findViewById<Button>(R.id.btnCalculateTip)
        val tvTipResult = view.findViewById<TextView>(R.id.tvTipResult)

        // Atualiza o texto da porcentagem ao movimentar o SeekBar
        sbTipPercentage.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tipPercentage = progress
                tvTipPercentage.text = "Porcentagem da gorjeta: $tipPercentage%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Calcula a gorjeta ao pressionar o botão
        btnCalculateTip.setOnClickListener {
            val billAmount = etBillAmount.text.toString().toDoubleOrNull()

            if (billAmount != null) {
                val tipAmount = (billAmount * tipPercentage) / 100
                val totalAmount = billAmount + tipAmount

                tvTipResult.text = "Gorjeta: R$ %.2f\nTotal: R$ %.2f".format(tipAmount, totalAmount)
            } else {
                Toast.makeText(requireContext(), "Insira um valor válido", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
