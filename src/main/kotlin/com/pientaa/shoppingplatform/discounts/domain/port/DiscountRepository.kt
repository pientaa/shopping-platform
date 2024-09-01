package com.pientaa.shoppingplatform.discounts.domain.port

import com.pientaa.shoppingplatform.discounts.domain.model.Discount

interface DiscountRepository {
    fun save(discount: Discount<*>): Discount<*>
    fun getAllActive(): List<Discount<*>>
}