package com.example.pruebaprimeraclase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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

        val rvLista = findViewById<RecyclerView>(R.id.rvLista)
        rvLista.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val dbHelper = DBHelper(this)
        val datos = dbHelper.obtenerProductos().toMutableList()

        val adapter = DatoAdapter(datos)
        rvLista.adapter = adapter

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener{
            val input = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Nuevo Producto")
                .setMessage("Ingrese el nombre del producto")
                .setView(input)
                .setPositiveButton("Agregar") {_,_ ->
                    val nuevoProducto = input.text.toString()
                    if(nuevoProducto.isNotEmpty()){
                        dbHelper.insertarProducto(nuevoProducto)
                        datos.add(nuevoProducto)
                        adapter.notifyItemInserted(datos.size - 1)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

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