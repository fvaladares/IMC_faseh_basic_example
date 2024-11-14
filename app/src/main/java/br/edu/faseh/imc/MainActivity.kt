package br.edu.faseh.imc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.faseh.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    /*
    TODO(AJUSTES)
    Remover todos os warnings do XML da view.

    Adicionar Logs dos métodos invocados. Quais tipos de logs existem? Log.i, Log.?
     */

    //TODO(AJUSTE) - Extrair todos os textos do xml para o arquivo strings.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configListeners()

        Log.i("Fabricio Valadares", "Passei por aqui")
    }

    // TODO(AJUSTES) - INCLUIR UM BOTÃO PARA LIMPAR OS CAMPOS PREENCHIDOS.

    // TODO(AJUSTE-OPT) - Incluir uma opção de sexo utilizando radiobuttons.

    private fun configListeners() {
        binding.btnEnviar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val peso = binding.etPeso.text.toString()
            val altura = binding.etAltura.text.toString()
            var continuar = true

            if (peso.isBlank()) {
                continuar = false
                binding.etPeso.error = getString(R.string.campo_obrigatorio_lbl)
            }


            if (altura.isBlank()) {
                continuar = false
                binding.etAltura.error = getString(R.string.campo_obrigatorio_lbl)
            }


            if (continuar) {
                calcularImc(
                    nome = nome,
                    peso = peso.toDouble(),
                    altura = altura.toDouble() / 100
                )
            } else {
                Toast.makeText(this, getString(R.string.campos_obrigatorios_lbl), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun calcularImc(
        nome: String,
        peso: Double,
        altura: Double,
    ) {
        val imc = peso / (altura * altura)

        val intent: Intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra(NOME, nome)
        intent.putExtra(IMC, imc)
        startActivity(intent)
    }

    companion object {
        const val NOME = "nome"
        const val IMC = "imc"
    }

}