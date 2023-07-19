package com.example.shoesapp.view

import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoesapp.R
import com.example.shoesapp.databinding.ActivityDetailBinding
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var headerContainer: FrameLayout
    private lateinit var bodyContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Obtén los datos pasados desde la actividad anterior
        val shoeId = intent.getIntExtra("id", 0)
        val nombre = intent.getStringExtra("nombre")
        val shoeMarca = intent.getStringExtra("marca")
        val shoeprecio = intent.getIntExtra("precio",0)
        val imagenLink = intent.getStringExtra("imagenLink")
        val shoeEntrega = intent.getBooleanExtra("entrega", false)
        val shoeNumero = intent.getIntExtra("numero", 0)
        val shoeOrigen = intent.getStringExtra("origen")

      //  bodyContainer = findViewById(R.id.bodyContainer)


        // Cargar el fragmento del cuerpo
        val bodyFragment = BodyFragment()
        val bodyBundle = Bundle().apply {
            putInt("id", shoeId)
            putString("nombre", nombre)
            putString("marca", shoeMarca)
            putInt("precio", shoeprecio)
            putString("imagenLink", imagenLink)
            putBoolean("entrega", shoeEntrega)
            putInt("numero", shoeNumero)
            putString("origen", shoeOrigen)
        }

        bodyFragment.arguments = bodyBundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.bodyContainer, bodyFragment)
            .commit()
    }
}
