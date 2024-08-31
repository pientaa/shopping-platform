package com.pientaa.shoppingplatform.discounts.domain.port

import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount

interface ProductCountDiscountRepository {
    fun getAllActive(): List<CountBasedDiscount>
}
