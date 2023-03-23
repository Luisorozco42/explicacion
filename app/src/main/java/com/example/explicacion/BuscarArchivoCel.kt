package com.example.explicacion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.example.explicacion.databinding.ActivityBuscarArchivoCelBinding

class BuscarArchivoCel : AppCompatActivity() {
    private lateinit var archivos: ActivityBuscarArchivoCelBinding
    private val PICK_VIDEO_REQUEST = 1
    private lateinit var direccion : Uri
    private lateinit var video : VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        archivos = ActivityBuscarArchivoCelBinding.inflate(layoutInflater)
        setContentView(archivos.root)

        video = archivos.video
        val btn: Button = archivos.btnBusqueda
        btn.setOnClickListener {
            selecionarVideo()
        }
    }

    fun selecionarVideo() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_VIDEO_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == Activity.RESULT_OK && data != null) {

            direccion = data.data!!
            video.setVideoURI(direccion)
            val controlador: MediaController = MediaController(this)
            video.setMediaController(controlador)
            controlador.setAnchorView(video)
            Toast.makeText(this, "Ya puede visualizar su video", Toast.LENGTH_SHORT).show()
        }
    }

}