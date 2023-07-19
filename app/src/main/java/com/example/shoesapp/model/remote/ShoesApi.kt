package com.example.shoesapp.model.remote

import com.example.shoesapp.model.remote.clases.DetailsShoeApiClass
import com.example.shoesapp.model.remote.clases.ShoesApiClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoesApi {



    // listado de telefonos
    @GET("shoes")
    suspend fun fecthShoeList(): Response<List<ShoesApiClass>>


    // seleccionar uno

    @GET("shoes/{id}")
    suspend fun fechShoeDetail(@Path("id") id: Int): Response<DetailsShoeApiClass>
}