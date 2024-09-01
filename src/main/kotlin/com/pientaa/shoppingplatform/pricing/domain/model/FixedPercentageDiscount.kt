package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class FixedPercentageDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier
) : Discount {
    override fun appliedTo(item: Discountable): Money {
        return when (item) {
            is ProductCartEntry -> throw IllegalArgumentException("ProductCartEntry type is not supported by this type of discount")

            is Money -> item * discountModifier.value
        }
    }

    fun merge(other: FixedPercentageDiscount) =
        FixedPercentageDiscount(id = id, discountModifier = discountModifier * other.discountModifier)
}
