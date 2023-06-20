package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem

interface CartItemDataSource {

    suspend fun addCartItem(cartItem: CartItem)

    // main thread에서 실행되지 않기위해 suspend 사용
    suspend fun getCartItems(): List<CartItem>
}