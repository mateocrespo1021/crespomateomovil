package com.example.appmovil_complexivo_crespomateo.model

data class Producto(
    val id: Int? = null,
    val nombre: String,
    val idCategoria: Categoria,
    val descripcion: String ,
    val precio: Double? = null,
    val stock: Int? = null,
    val fechaCaducidad: String? = null,
    val imagen: String? = null,
    val ubicacion: String? = null
)

