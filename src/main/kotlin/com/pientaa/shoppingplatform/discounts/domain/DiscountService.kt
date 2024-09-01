package com.pientaa.shoppingplatform.discounts.domain

import com.pientaa.shoppingplatform.discounts.domain.model.Discount
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import com.pientaa.shoppingplatform.discounts.domain.model.ProductCart
import com.pientaa.shoppingplatform.discounts.domain.port.DiscountRepository

class DiscountService(
    private val discountRepository: DiscountRepository,
) {
    fun calculateTotalPrice(productCart: ProductCart): Money =
        PriceCalculator().calculateTotalPrice(productCart, discountRepository.getAllActive())

    fun createDiscount(discount: Discount<*>): Discount<*> = discountRepository.save(discount)
}
