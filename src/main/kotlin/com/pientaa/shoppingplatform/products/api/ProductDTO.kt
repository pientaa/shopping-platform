package com.pientaa.shoppingplatform.products.api

import java.math.BigDecimal
import java.util.UUID

data class ProductDTO(
    val id: UUID,
    val amount: BigDecimal,
    var currency: String,
)