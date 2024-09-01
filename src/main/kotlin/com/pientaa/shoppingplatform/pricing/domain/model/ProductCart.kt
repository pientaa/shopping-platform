package com.pientaa.shoppingplatform.pricing.domain.model

data class ProductCart(
    val cart: Map<Product, Quantity>
) {
    companion object {
        fun from(vararg items: Pair<Product, Quantity>) = ProductCart(items.toMap())
    }

    fun isNotEmpty(): Boolean = cart.isNotEmpty()
}
