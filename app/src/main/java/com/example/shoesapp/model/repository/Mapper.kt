package com.example.shoesapp.model.repository

import androidx.room.ColumnInfo
import com.example.shoesapp.model.Shoes
import com.example.shoesapp.model.remote.clases.DetailsShoeApiClass
import com.example.shoesapp.model.remote.clases.ShoesApiClass

fun fromInternetToShoesEntity(shoeList: List<ShoesApiClass>, detailsShoeList: List<DetailsShoeApiClass>): List<Shoes> {

    return shoeList.mapIndexed { index, shoeApi ->
        val detailsShoeApi = detailsShoeList[index]

        Shoes(
            id = shoeApi.id,
            nombre = shoeApi.nombre,
            origen = shoeApi.origen,
            imagenLink = shoeApi.imagenLink,
            marca = shoeApi.marca,
            numero = shoeApi.numero,
            precio = detailsShoeApi.precio,
            entrega = detailsShoeApi.entrega,
        )
    }
}

