package br.edu.faseh.imc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
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
            val peso = binding.etPeso.text.toString().toDouble()
            val altura = (binding.etAltura.text.toString().toDouble()) / 100

            calcularImc(nome = nome, peso = peso, altura = altura)
        }
    }

    private fun calcularImc(nome: String, peso: Double, altura: Double) {
        val imc = peso / (altura * altura)

        val intent: Intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("nome", nome)
        intent.putExtra("imc", imc)
        startActivity(intent)
    }

}