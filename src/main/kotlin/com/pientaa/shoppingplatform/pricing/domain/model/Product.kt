package com.pientaa.shoppingplatform.pricing.domain.model

import java.util.UUID

data class Product(
    val id: UUID,
    val price: Money
)
