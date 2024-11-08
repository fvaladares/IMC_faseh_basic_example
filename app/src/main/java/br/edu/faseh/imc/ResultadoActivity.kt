package br.edu.faseh.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.faseh.imc.databinding.ActivityResultadoBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

class ResultadoActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prepararCampos()
    }

    private fun prepararCampos() {
        val nome = intent.getStringExtra("nome")
        val imc = intent.getDoubleExtra("imc", 0.0)

        when (imc) {
            in 0.0..18.0 -> {
                configurarTextViews(
                    nome = nome,
                    imc = imc,
                    classificacao = resources.getStringArray(R.array.peso_classificacao)[0]
                )
            }

            in 25.0..29.9 -> {
                configurarTextViews(
                    nome = nome,
                    imc = imc,
                    classificacao = resources.getStringArray(R.array.peso_classificacao)[2]
                )
            }
        }
    }

    private fun configurarTextViews(
        nome: String?,
        imc: Double,
        classificacao: String,
    ) {
        binding.apply {
            tvBoasVindas.text = getString(R.string.boas_vindas, nome)
            val imcString = BigDecimal.valueOf(imc).setScale(2, RoundingMode.HALF_UP).toString()
            tvImc.text = imcString
            tvClassificacao.text = getString(R.string.classificacao, classificacao)

        }
    }
}