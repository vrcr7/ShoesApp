package com.example.shoesapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoesapp.model.Shoes
import com.example.shoesapp.model.ShoesRoomDatabase
import com.example.shoesapp.model.repository.ShoesRepository
import kotlinx.coroutines.launch

class ShoesViewModel (application: Application, private var repository: ShoesRepository) : ViewModel()  {

    init{
        val bd= ShoesRoomDatabase.getDatabase(application)
        val  shoesDao = bd.ShoesDao()
        repository = ShoesRepository(shoesDao)
        // lama el método del respositorio
        viewModelScope.launch {
            repository.fechShoes()
        }
    }

    // devuelve todos los datos de la bd
    val allDatos: LiveData<List<Shoes>> = repository.allDatos

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAllShoes(shoes: List<Shoes>) = viewModelScope.launch {
        repository.insert(shoes)
    }
    // Ellimina todos los datos de la db
    //vaciar la lista de usuarios registrados
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    //Elimina un usuario, un registro a traves de la id
    fun eliminarUno(id: Int) = viewModelScope.launch {
        repository.deleteUno(id)
    }

}