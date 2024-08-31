package com.pientaa.shoppingplatform.discounts.domain.port

import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount

interface TotalPriceDiscountDiscountRepository {
    fun getAllActive(): List<FixedPercentageDiscount>
}
