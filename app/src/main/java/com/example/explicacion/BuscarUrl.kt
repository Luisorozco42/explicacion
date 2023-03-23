package com.example.explicacion

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast

import com.example.explicacion.databinding.ActivityBuscarUrlBinding

class BuscarUrl : AppCompatActivity() {
    private lateinit var url : ActivityBuscarUrlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = ActivityBuscarUrlBinding.inflate(layoutInflater)
        setContentView(url.root)

        var texto : String

        url.cargar.setOnClickListener{

            val vid = url.videoUrl
            val controlador : MediaController = MediaController(this)
            texto = url.etCodigoTexto.text.toString()
            val urls : Uri = Uri.parse(texto)

            vid.setVideoURI(urls)
            vid.setMediaController(controlador)
            controlador.setAnchorView(vid)
            Toast.makeText(this, "Se esta preparando el video", Toast.LENGTH_SHORT).show()

            vid.setOnPreparedListener {
                if (!it.isPlaying) {
                    // El video esta preparado
                    Toast.makeText(this, "Ya puede ver su video", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}