package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class Product(
    val id: UUID,
    val price: Money
)

data class ProductCart(
    val cart: Map<Product, Quantity>
) {
    companion object {
        fun from(vararg items: Pair<Product, Quantity>) = ProductCart(items.toMap())
    }

    fun isNotEmpty(): Boolean = cart.isNotEmpty()
}

data class ProductCartEntry(
    val cartEntry: Map.Entry<Product, Quantity>
) {
    val productPrice: Money
        get() = cartEntry.key.price
    val productQuantity: Quantity
        get() = cartEntry.value
}
