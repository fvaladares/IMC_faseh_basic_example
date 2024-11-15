package br.edu.faseh.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.faseh.imc.databinding.ActivityResultadoBinding
import java.math.BigDecimal
import java.math.RoundingMode

class ResultadoActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultadoBinding


//   TODO(AJUSTES) - Remover os warnings dos XML da view
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

    /**
     * TODO(AJUSTES)
     * Adicionar as demais classificações
     * Referência das classificaçẽos:
     * https://abeso.org.br/obesidade-e-sindrome-metabolica/calculadora-imc/
     */
    private fun prepararCampos() {
        val nome = tratarNome(intent.getStringExtra(MainActivity.NOME))
        val imc = intent.getDoubleExtra(MainActivity.IMC, 0.0)

        when (imc) {
//            abaixo do normal
            in 0.0..18.0 -> {
                configurarView(
                    nome = nome,
                    imc = imc,
                    classificacao = resources.getStringArray(R.array.peso_classificacao)[0],
                    imgId = R.drawable.imc_05
                )
            }

//            sobrepeso

            in 25.0..29.9 -> {
                configurarView(
                    nome = nome,
                    imc = imc,
                    classificacao = resources.getStringArray(R.array.peso_classificacao)[2],
                    imgId = R.drawable.imc_04
                )
            }
        }
    }

    private fun configurarView(
        nome: String?,
        imc: Double,
        classificacao: String,
        imgId: Int = R.drawable.imc_01,
    ) {
        binding.apply {
            tvBoasVindas.text = getString(R.string.boas_vindas, nome)
            val imcString = BigDecimal.valueOf(imc).setScale(2, RoundingMode.HALF_UP).toString()
            tvImc.text = imcString
            tvClassificacao.text = getString(R.string.classificacao, classificacao)
            ivImc.setImageDrawable(AppCompatResources.getDrawable(this@ResultadoActivity, imgId))

        }
    }
}

private fun ResultadoActivity.tratarNome(nome: String?): String? =
    if (nome.isNullOrBlank()) getString(R.string.ola_tudo_bem) else "$nome!"











