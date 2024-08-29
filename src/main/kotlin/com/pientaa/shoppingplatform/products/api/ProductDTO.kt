package com.pientaa.shoppingplatform.products.api

import java.math.BigDecimal

data class ProductDTO(
    val amount: BigDecimal,
    var currency: String,
)