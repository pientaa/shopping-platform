package com.pientaa.shoppingplatform.pricing.domain.model

data class ProductCartEntry(
    val cartEntry: Map.Entry<Product, Quantity>
) : Discountable {
    companion object {
        fun from(item: Pair<Product, Quantity>) = ProductCartEntry(mapOf(item).entries.first())
    }

    val productPrice: Money
        get() = cartEntry.key.price
    val productQuantity: Quantity
        get() = cartEntry.value
}
