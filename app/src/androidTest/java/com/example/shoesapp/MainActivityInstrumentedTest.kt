package com.example.shoesapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shoesapp.view.DetailActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityInstrumentedTest {

    @Test
    fun testMostrarValidShoe() {
        // Crear un Intent simulado con los datos de un zapato válido
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("id", 1)
        intent.putExtra("nombre", "Zapato de prueba")
        intent.putExtra("marca", "Marca de prueba")
        intent.putExtra("precio", "99.99")
        intent.putExtra("imagenLink", "https://ejemplo.com/imagen.png")
        intent.putExtra("entrega", "1-2 días")
        intent.putExtra("numero", "10")
        intent.putExtra("origen", "Origen de prueba")

        // Iniciar la actividad bajo prueba con el Intent simulado
        val activityScenario = ActivityScenario.launch<DetailActivity>(intent)

        // Esperar hasta que se inicie la actividad y esté lista para ser utilizada
        activityScenario.onActivity { activity ->
            // Verificar que la actividad no sea nula
            assertNotNull(activity)

            // Verificar que los datos del Intent se muestran correctamente en la actividad
            assertEquals(1, activity.intent.getIntExtra("id", -1))
            assertEquals("Zapato de prueba", activity.intent.getStringExtra("nombre"))
            assertEquals("Marca de prueba", activity.intent.getStringExtra("marca"))
            assertEquals("99.99", activity.intent.getStringExtra("precio"))
            assertEquals("https://ejemplo.com/imagen.png", activity.intent.getStringExtra("imagenLink"))
            assertEquals("1-2 días", activity.intent.getStringExtra("entrega"))
            assertEquals("10", activity.intent.getStringExtra("numero"))
            assertEquals("Origen de prueba", activity.intent.getStringExtra("origen"))
        }
    }


}
