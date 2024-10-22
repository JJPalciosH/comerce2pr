package com.example.e_comerce.data.network

import com.example.e_comerce.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}