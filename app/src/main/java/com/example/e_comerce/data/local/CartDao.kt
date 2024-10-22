package com.example.e_comerce.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_comerce.data.model.CartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartItem>

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}