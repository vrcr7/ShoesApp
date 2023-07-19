package com.example.shoesapp.model.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.shoesapp.model.Shoes
import com.example.shoesapp.model.ShoesDao
import com.example.shoesapp.model.remote.RetrofitClient
import com.example.shoesapp.model.remote.clases.DetailsShoeApiClass


class ShoesRepository (private val shoesDao: ShoesDao){

    private val networkService = RetrofitClient.retrofitInstance()

    val allDatos: LiveData<List<Shoes>> = shoesDao.getAllDatos()


    @WorkerThread
    suspend fun insert(phone: List<Shoes>) {
        shoesDao.insertAllShoes(phone)
    }
    @WorkerThread
    suspend fun deleteAll() {
        shoesDao.deleteAll()
    }
    @WorkerThread
    suspend fun deleteUno(id:Int) {
        shoesDao.deleteUno(id)
    }
    //Agregamos los datos desde la Api

    suspend fun fechShoes() {
        val service = kotlin.runCatching { networkService.fecthShoeList() }
        service.onSuccess { response ->
            when (response.code()) {
                in 200..299 -> response.body()?.let {
                        shoeList -> val detailsResponseList = mutableListOf<DetailsShoeApiClass>()

                    // Obtener los detalles de cada teléfono
                    shoeList.forEach { shoeApi ->
                        val detailsResponse = networkService.fechShoeDetail(shoeApi.id)
                        if (detailsResponse.isSuccessful) {
                            val detailsShoeApi = detailsResponse.body()
                            if (detailsShoeApi != null) {
                                detailsResponseList.add(detailsShoeApi)
                            }
                        }
                    }

                    // Insertar la lista de teléfonos con detalles en la base de datos
                    val shoesWithDetails = fromInternetToShoesEntity(shoeList, detailsResponseList) //mapeo Mapper.kt
                    shoesDao.insertAllShoes(shoesWithDetails)
                }
                else -> Log.d("Repo", "${response.code()}-${response.errorBody()}")
            }
        }
        service.onFailure { error ->
            Log.e("Error", "${error.message}")
        }
    }
}