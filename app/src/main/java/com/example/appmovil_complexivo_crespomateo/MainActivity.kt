package com.example.appmovil_complexivo_crespomateo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil_complexivo_crespomateo.model.Producto
import com.example.appmovil_complexivo_crespomateo.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var productAdapter: ProductoAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar el adaptador con una lista vacía inicialmente
        productAdapter = ProductoAdapter(this, listOf())
        recyclerView.adapter = productAdapter



        // Configurar la búsqueda
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter.filter(newText)
                return false
            }
        })

        // Obtener los productos de la API
        fetchProducts()
    }

    private fun fetchProducts() {
        RetrofitClient.instance.getProducts().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(call: Call<List<Producto>>, response: Response<List<Producto>>) {
                if (response.isSuccessful) {
                    response.body()?.let { productList ->
                        productAdapter.updateData(productList)
                    }
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                // Manejar el error
            }
        })
    }
}