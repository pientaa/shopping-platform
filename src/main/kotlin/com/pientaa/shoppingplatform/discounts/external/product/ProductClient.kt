package com.pientaa.shoppingplatform.discounts.external.product

import com.pientaa.shoppingplatform.discounts.domain.model.Product
import java.util.UUID

interface ProductClient {
    fun getProduct(id: UUID): Product
}