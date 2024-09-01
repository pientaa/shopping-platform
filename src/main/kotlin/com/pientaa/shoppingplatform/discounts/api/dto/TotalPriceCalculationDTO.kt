package com.pientaa.shoppingplatform.discounts.api.dto

import java.util.UUID

data class TotalPriceCalculationDTO(
    val productId: UUID,
    val quantity: Int,
)
