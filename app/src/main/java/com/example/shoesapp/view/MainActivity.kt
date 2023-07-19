package com.example.shoesapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.shoesapp.R
import com.example.shoesapp.databinding.ActivityMainBinding
import com.example.shoesapp.model.Shoes
import com.example.shoesapp.model.ShoesRoomDatabase
import com.example.shoesapp.model.repository.ShoesRepository
import com.example.shoesapp.view.viewmodel.ShoesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var shoesViewModel: ShoesViewModel
    internal var data: MutableLiveData<List<Shoes>> = MutableLiveData()
    private lateinit var headerContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instanciar la base de datos y el DAO
        //para disponibilizar una MutableLiveData<List<Shoes>> = MutableLiveData()
        //para toda la aplicacion, mis funciones pasan por la Main activity
        val database = ShoesRoomDatabase.getDatabase(applicationContext)
        val shoesDao = database.ShoesDao()
        // Crear una instancia del repositorio pasando el DAO
        val shoesRepository = ShoesRepository(shoesDao)
        // Crear una instancia de ViewModel directamente
        shoesViewModel = ShoesViewModel(application, shoesRepository)

        headerContainer = findViewById(R.id.headerContainer)
        val headerFragment = HeaderFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.headerContainer, headerFragment)
            .commit()


        shoesViewModel.allDatos.observe(this, Observer { newList ->
            // Actualizar la variable de datos
            data.value = newList
            Log.i("", data.value?.size.toString())
            if (data.value != null && data.value!!.isNotEmpty()) {
                // Agregar el fragmento si no ha sido agregado antes
                val newFragment = ListaFragment()
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainer, newFragment)
                    .commit()
            }
        })


    }

    fun mostrar(id: String)
    {

        val shoeId: Int =  id.toInt()// Obtén el ID del teléfono deseado
    // Obtén el objeto Shoe correspondiente al ID
        val shoes: Shoes? = data.value?.find { it.id == shoeId }

        // Verifica si se encontró el teléfono
        if (shoes != null) {
            val intent = Intent(this, DetailActivity::class.java)
            // Agrega los datos del objeto Phone al intent utilizando putExtra
            intent.putExtra("id", shoes.id)
            intent.putExtra("nombre", shoes.nombre)
            intent.putExtra("marca", shoes.marca)
            intent.putExtra("precio", shoes.precio)
            intent.putExtra("imagenLink", shoes.imagenLink)
            intent.putExtra("entrega", shoes.entrega)
            intent.putExtra("numero", shoes.numero)
            intent.putExtra("origen", shoes.origen)
            // Inicia la actividad con el intent
            startActivity(intent)
        } else {
            // Maneja el caso en el que no se encuentre el teléfono
            Toast.makeText(this, "Zapato no encontrado", Toast.LENGTH_SHORT).show()
        }
    }
}
