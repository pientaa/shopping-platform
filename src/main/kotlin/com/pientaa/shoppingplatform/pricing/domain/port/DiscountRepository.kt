package com.pientaa.shoppingplatform.pricing.domain.port

import com.pientaa.shoppingplatform.pricing.domain.model.Discount

interface DiscountRepository {
    fun save(discount: Discount): Discount
    fun getAllActive(): List<Discount>
}
