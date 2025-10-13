package com.example.pruebaprimeraclase

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"
        tvBienvenida.text = "Bienvenido $usuario"

        Snackbar.make(findViewById(android.R.id.content), "Sesión iniciada...", Snackbar.LENGTH_LONG)
            .show()

        val btnSalir = findViewById<Button>(R.id.btnSalir)
        btnSalir.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Desea cerrar la sesión?")
                .setPositiveButton("Sí") {_,_ ->
                    finish()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}