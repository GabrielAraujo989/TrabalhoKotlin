package com.example.trabalho1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.trabalho1.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referências aos elementos do layout
        val etKmRodados = view.findViewById<EditText>(R.id.etKmRodados)
        val etPedagios = view.findViewById<EditText>(R.id.etPedagios)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado)

        // Valores fixos
        val custoPorLitro = 5.80
        val pedagioValor = 12.00
        val consumoKmPorLitro = 10.0 // Exemplo: 10 km por litro
        val velocidadeMedia = 80.0 // km/h

        btnCalcular.setOnClickListener {
            // Obter valores de entrada
            val kmRodados = etKmRodados.text.toString().toDoubleOrNull() ?: 0.0
            val pedagios = etPedagios.text.toString().toIntOrNull() ?: 0

            // Cálculos
            val custoCombustivel = (kmRodados / consumoKmPorLitro) * custoPorLitro
            val custoPedagios = pedagios * pedagioValor
            val custoTotal = custoCombustivel + custoPedagios
            val tempoViagemHoras = kmRodados / velocidadeMedia

            // Exibir resultados
            val resultado = """
                Valor de pedágios: R$ %.2f
                Valor de combustível: R$ %.2f
                Custo total: R$ %.2f
                Tempo de viagem: %.1f horas
            """.trimIndent().format(custoPedagios, custoCombustivel, custoTotal, tempoViagemHoras)

            tvResultado.text = resultado
        }

        return view
    }
}
