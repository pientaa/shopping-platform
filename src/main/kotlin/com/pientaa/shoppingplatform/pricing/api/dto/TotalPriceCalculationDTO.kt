package com.pientaa.shoppingplatform.pricing.api.dto

import java.util.UUID

data class TotalPriceCalculationDTO(
    val productId: UUID,
    val quantity: Int,
)
