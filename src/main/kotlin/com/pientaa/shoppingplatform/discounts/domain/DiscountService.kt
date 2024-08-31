package com.pientaa.shoppingplatform.discounts.domain

import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Discount
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import com.pientaa.shoppingplatform.discounts.domain.model.ProductCart
import com.pientaa.shoppingplatform.discounts.domain.model.ProductCartEntry
import java.util.UUID

class DiscountService {
    fun calculateTotalPrice(cart: ProductCart, discounts: List<Discount<*>>): Money {
        require(cart.isNotEmpty()) { "Cart is empty" }

        val productsDiscounts: Map<UUID, CountBasedDiscount> =
            discounts.filterIsInstance<CountBasedDiscount>().associateBy { it.productId }

        val cartDiscount: FixedPercentageDiscount? = discounts.filterIsInstance<FixedPercentageDiscount>()
            .takeIf { it.isNotEmpty() }
            ?.reduce(FixedPercentageDiscount::merge)

        return cart.cart.map {
            val (product, quantity) = it
            (productsDiscounts[product.id]?.appliedTo(ProductCartEntry(it)) ?: product.price) * quantity.value
        }
            .reduce { acc, money -> acc + money }
            .let { totalPrice -> cartDiscount?.appliedTo(totalPrice) ?: totalPrice }
    }
}
