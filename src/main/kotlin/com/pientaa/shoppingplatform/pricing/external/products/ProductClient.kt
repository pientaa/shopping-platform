package com.pientaa.shoppingplatform.pricing.external.products

import com.pientaa.shoppingplatform.pricing.domain.model.Product
import java.util.UUID

interface ProductClient {
    fun getProduct(id: UUID): Product
}