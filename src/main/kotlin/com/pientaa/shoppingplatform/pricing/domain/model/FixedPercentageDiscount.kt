package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class FixedPercentageDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier
) : Discount<Money> {
    override fun appliedTo(item: Money): Money {
        return item * discountModifier.value
    }

    fun merge(other: FixedPercentageDiscount) =
        FixedPercentageDiscount(id = id, discountModifier = discountModifier * other.discountModifier)
}
