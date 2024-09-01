package com.pientaa.shoppingplatform.pricing.api.dto

import java.math.BigDecimal

data class TotalPriceDTO(
    val amount: BigDecimal,
    val currency: String,
)
