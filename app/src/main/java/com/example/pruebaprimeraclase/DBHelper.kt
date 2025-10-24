package com.example.pruebaprimeraclase


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context, "Negocio.db", null, 1){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE productos("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "nombre TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS productos")
        onCreate(db)
    }

    fun insertarProducto(nombre:String){
       val db =  writableDatabase
       val values = ContentValues()
       values.put("nombre", nombre)
        db.insert("productos", null, values)
    }

    fun obtenerProductos(): List<String>{
        val db = readableDatabase
        val lista = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT nombre FROM productos", null)
        if(cursor.moveToFirst()){
            do{
                lista.add(cursor.getString(0))
            } while(cursor.moveToNext())
        }
        return lista
    }


}