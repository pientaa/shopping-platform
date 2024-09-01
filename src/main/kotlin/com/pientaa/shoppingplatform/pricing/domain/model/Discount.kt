package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

interface Discount {
    val id: UUID
    val discountModifier: DiscountModifier
    fun appliedTo(item: Discountable): Money
}

sealed interface Discountable
