package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class CountBasedDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier,
    val productId: UUID,
    val threshold: Int,
) : Discount {
    override fun appliedTo(item: Discountable): Money {
        return when (item) {
            is ProductCartEntry -> if (item.productQuantity.value >= threshold) {
                item.productPrice * discountModifier.value
            } else {
                item.productPrice
            }

            is Money -> throw IllegalArgumentException("Money type is not supported by this type of discount")
        }
    }
}
