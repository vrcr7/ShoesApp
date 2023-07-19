package com.example.shoesapp

import com.example.shoesapp.model.Shoes
import com.example.shoesapp.model.remote.clases.DetailsShoeApiClass
import com.example.shoesapp.model.remote.clases.ShoesApiClass
import com.example.shoesapp.model.repository.fromInternetToShoesEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

//Test unitario del Mapper
class ShoesMapperTest {

    private lateinit var shoesApiList: List<ShoesApiClass>
    private lateinit var detailsShoeApiList: List<DetailsShoeApiClass>

    @Before
    fun setup() {
        // Datos de prueba para el mapeo
        shoesApiList = listOf(
            ShoesApiClass(1, "Zapato 1", "Origen 1", "imagen1.png", "Marca 1", 100),
            ShoesApiClass(2, "Zapato 2", "Origen 2", "imagen2.png", "Marca 2", 150)
        )

        detailsShoeApiList = listOf(
            DetailsShoeApiClass(1, "Nombre1", "Origen1", "imagen1", "Marca1", 100, 100, true),
            DetailsShoeApiClass(2, "Nombre2", "Origen2", "imagen2", "Marca2", 150, 200, false)
        )
    }

    @After
    fun tearDown() {
        // Limpiar los datos de prueba después de cada caso de prueba
    }

    @Test
    fun testFromInternetToShoesEntity() {
        // Llamar a la función de mapeo
        val result = fromInternetToShoesEntity(shoesApiList, detailsShoeApiList)

        // Verificar que el resultado tenga el tamaño esperado
        assertEquals(2, result.size)

        // Verificar los datos mapeados del primer zapato
        val firstShoes = result[0]
        assertEquals(1, firstShoes.id)
        assertEquals("Zapato 1", firstShoes.nombre)
        assertEquals("Origen 1", firstShoes.origen)
        assertEquals("imagen1.png", firstShoes.imagenLink)
        assertEquals("Marca 1", firstShoes.marca)
        assertEquals(100, firstShoes.numero)
        assertEquals(100, firstShoes.precio)
        assertEquals(true, firstShoes.entrega)

        // Verificar los datos mapeados del segundo zapato
        val secondShoes = result[1]
        assertEquals(2, secondShoes.id)
        assertEquals("Zapato 2", secondShoes.nombre)
        assertEquals("Origen 2", secondShoes.origen)
        assertEquals("imagen2.png", secondShoes.imagenLink)
        assertEquals("Marca 2", secondShoes.marca)
        assertEquals(150, secondShoes.numero)
        assertEquals(200, secondShoes.precio)
        assertEquals(false, secondShoes.entrega)
    }
}