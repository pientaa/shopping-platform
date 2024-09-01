package com.pientaa.shoppingplatform.discounts.external.product.dto

import java.math.BigDecimal
import java.util.UUID

data class ProductExternalDTO(
    val id: UUID,
    val amount: BigDecimal,
    var currency: String,
)
