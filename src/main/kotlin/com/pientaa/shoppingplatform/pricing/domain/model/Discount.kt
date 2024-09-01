package com.pientaa.shoppingplatform.pricing.domain.model

import java.math.BigDecimal
import java.util.UUID

interface Discount<in T : Any> {
    val id: UUID
    val discountModifier: DiscountModifier
    fun appliedTo(item: T): Money
}
