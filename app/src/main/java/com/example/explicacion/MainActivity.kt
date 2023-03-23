package com.example.explicacion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.explicacion.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var contador = 0
        val imagen : ImageView = binding.icono
        val buscarvideo : Button = binding.btnArchivo
        val buscarURL : Button = binding.btnURL
        val urltext : TextInputEditText = binding.txturlimagen
        val contenedor : TextInputLayout = binding.dirURL
        var urlimagen : String


        imagen.setImageURI(Uri.parse("android.resource://" + packageName  + "/" + R.raw.icono))

        urltext.visibility = View.INVISIBLE
        contenedor.visibility = View.INVISIBLE
        buscarvideo.setOnClickListener()
        {
            startActivity(Intent(this, BuscarArchivoCel()::class.java))
        }

        buscarURL.setOnClickListener()
        {
            startActivity(Intent(this, BuscarUrl()::class.java))
        }

        imagen.setOnClickListener {
            if(contador == 0)
            {
                urltext.visibility = View.VISIBLE
                contenedor.visibility = View.VISIBLE
                Toast.makeText(this, "Has encontrado el Easter EGG", Toast.LENGTH_SHORT).show()
                Handler().postDelayed(
                    {
                    Toast.makeText(this, "Ahora pon una URL para buscar una imagen", Toast.LENGTH_SHORT).show()
                }, 4000) // 4 segundos
            }else
            {
                urlimagen = urltext.text.toString()
                Glide.with(this)
                    .load(urlimagen)
                    .into(imagen)
            }
            contador++
        }
    }
}