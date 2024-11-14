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

    private fun configListeners() {
        binding.btnEnviar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val peso = binding.etPeso.text.toString()
            val altura = binding.etAltura.text.toString()
            var continuar = true

            if (peso.isBlank()) {
                continuar = false
                binding.etPeso.error = "Campo obrigatório."
            }


            if (altura.isBlank()) {
                continuar = false
                binding.etAltura.error = "Campo obrigatório."
            }


            if (continuar) {
                calcularImc(
                    nome = nome,
                    peso = peso.toDouble(),
                    altura = altura.toDouble() / 100
                )
            } else {
                Toast.makeText(this, "Campos obrigatórios devem ser preenchidos", Toast.LENGTH_LONG)
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
        intent.putExtra("nome", nome)
        intent.putExtra("imc", imc)
        startActivity(intent)
    }

}