package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class CountBasedDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier,
    val productId: UUID,
    val threshold: Int,
) : Discount<ProductCartEntry> {
    override fun appliedTo(item: ProductCartEntry): Money {
        return if (item.productQuantity.value >= threshold) {
            item.productPrice * discountModifier.value
        } else {
            item.productPrice
        }
    }
}
