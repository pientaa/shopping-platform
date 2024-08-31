package com.pientaa.shoppingplatform.discounts

import java.math.BigDecimal

fun discountModifierFrom(percentageValue: BigDecimal): BigDecimal {
    require(percentageValue > BigDecimal.ZERO && percentageValue <= BigDecimal.valueOf(100)) {
        "Percentage value must be positive and cannot be higher than 100.00"
    }

    return BigDecimal.ONE - (percentageValue / BigDecimal("100.00"))
}
