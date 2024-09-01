package com.pientaa.shoppingplatform.discounts.api.dto

import java.math.BigDecimal

data class TotalPriceDTO(
    val amount: BigDecimal,
    val currency: String,
)
