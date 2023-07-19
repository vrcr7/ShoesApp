package com.example.shoesapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shoesapp.R
import com.example.shoesapp.databinding.FragmentBodyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BodyFragment : Fragment() {
    private lateinit var binding: FragmentBodyBinding



    private var id: Int = 0
    private var nombre: String? = null
    private var marca: String? = null
    private var precio: Int = 0
    private var imagenLink: String? = null
    private var entrega: Boolean = false
    private var numero: Int = 0
    private var origen: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBodyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //enviar correo
        val sendLayout = binding.send
        sendLayout.setOnClickListener {
            val email = "Zapato.ventas@unica.cl"
            val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(email))
            intentEmail.type = "plain/text"
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Consulta ${nombre} id ${id}")
            intentEmail.putExtra(
                Intent.EXTRA_TEXT, "Hola\n\nVi la propiedad ${nombre} de código ${id} y me " +
                    "gustaría que me contactaran a este correo o al siguiente número\n\nQuedo atento.")
            intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            startActivity(Intent.createChooser(intentEmail, "gmail"))
        }

        // Obtén los datos pasados desde los argumentos del fragmento
        arguments?.let {
            id = it.getInt("id", 0)
            nombre = it.getString("nombre")
            marca = it.getString("marca")
            precio = it.getInt("precio", 0)
            imagenLink = it.getString("imagenLink")
            entrega = it.getBoolean("entrega", false)
            numero = it.getInt("numero",0)
            origen = it.getString("origen")

        }

        // Asigna los datos a las vistas correspondientes
        binding.nameTextView.text = nombre
        binding.priceTextView.text = "Precio: $precio"
        binding.marcaTextView.text = "Marca: $marca"
        binding.entregaTextView.text = if (entrega) "Acepta envío" else "Sin envío"
        binding.numeroTextView.text = "Número: $numero"
        binding.origenTextView.text = "Origen: $origen"

        // Carga la imagen utilizando Glide
        Glide.with(this)
            .load(imagenLink)
            //.placeholder(R.drawable.placeholder_image) // Establece el placeholder
            //.error(R.drawable.error_image)
            .into(binding.imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
