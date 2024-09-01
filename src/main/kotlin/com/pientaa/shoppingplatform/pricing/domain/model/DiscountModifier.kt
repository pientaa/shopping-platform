package com.pientaa.shoppingplatform.pricing.domain.model

import java.math.BigDecimal

@JvmInline
value class DiscountModifier(val value: BigDecimal) {
    operator fun times(discountModifier: DiscountModifier): DiscountModifier =
        DiscountModifier(this.value * discountModifier.value)

    init {
        require(value > BigDecimal.ZERO && value <= BigDecimal.ONE) {
            "Discount modifier value must be a value between 0 and 1"
        }
    }

    companion object {
        fun from(s: String) = DiscountModifier(BigDecimal(s))
    }
}
