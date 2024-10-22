package com.example.e_comerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_comerce.data.local.CartDatabase
import com.example.e_comerce.data.model.CartItem
import com.example.e_comerce.data.model.Product
import com.example.e_comerce.databinding.ActivityProductDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = intent.getParcelableExtra("product")
        product?.let {
            binding.productName.text = it.name
            binding.productPrice.text = "$${it.price}"
            // Cargar la imagen del producto con Glide o Picasso

            binding.addToCartButton.setOnClickListener {
                addToCart(it)
            }
        }
    }

    private fun addToCart(product: Product) {
        val cartItem = CartItem(
            productId = product.id,
            productName = product.name,
            price = product.price,
            quantity = 1
        )

        CoroutineScope(Dispatchers.IO).launch {
            CartDatabase.getDatabase(applicationContext).cartDao().insert(cartItem)
        }
    }
}