package com.example.appmovil_complexivo_crespomateo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovil_complexivo_crespomateo.model.Producto

class ProductoAdapter(
    private val context: Context,
    private var productList: List<Producto>
) : RecyclerView.Adapter<ProductoAdapter.ProductViewHolder>(), Filterable {

    private var filteredProductList: List<Producto> = productList

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productPrecio: TextView = itemView.findViewById(R.id.productprecio)
        private val productCategoria: TextView = itemView.findViewById(R.id.productCategory)
        private val productDescripcion: TextView = itemView.findViewById(R.id.productDescripcion)

        fun bind(product: Producto) {
            productName.text = product.nombre
            productPrecio.text = "Precio: ${product.precio}"
            productDescripcion.text = "Descripcion  ${product.descripcion}"

            // Concatenar la URL base con el nombre de la imagen
            val imageUrl = "https://satisfied-expression-production.up.railway.app/uploads/${product.imagen}"


            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background) // Opcional
                .error(R.drawable.photo) // Opcional
                .into(productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredProductList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = filteredProductList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase()
                val filteredList = if (query.isNullOrEmpty()) {
                    productList
                } else {
                    productList.filter {
                        it.nombre.lowercase().contains(query) ||
                                it.descripcion.lowercase().contains(query)

                    }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredProductList = results?.values as List<Producto>
                notifyDataSetChanged()
            }
        }
    }

    fun updateData(newProductList: List<Producto>) {
        productList = newProductList
        filteredProductList = newProductList
        notifyDataSetChanged()
    }
}