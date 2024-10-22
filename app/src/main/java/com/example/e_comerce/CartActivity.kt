package com.example.e_comerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_comerce.data.local.CartDatabase
import com.example.e_comerce.data.model.CartItem
import com.example.e_comerce.databinding.ActivityCartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartAdapter = CartAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = cartAdapter

        loadCartItems()
    }

    private fun loadCartItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val cartItems = CartDatabase.getDatabase(applicationContext).cartDao().getCartItems()
            withContext(Dispatchers.Main) {
                cartAdapter.submitList(cartItems)
            }
        }
    }
}