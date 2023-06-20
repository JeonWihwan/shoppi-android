package com.shoppi.app.repository.cart

import com.shoppi.app.database.CartItemDao
import com.shoppi.app.model.CartItem

class CartItemLocalDataSource(
    private val dao: CartItemDao
) : CartItemDataSource {

    override suspend fun addCartItem(cartItem: CartItem) {
        dao.insert(cartItem)
    }

    override suspend fun getCartItems(): List<CartItem> {
        // DB와 통신 필요
        // jetpack의 Room 사용
        return dao.load()
    }

}