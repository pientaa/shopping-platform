package com.pientaa.shoppingplatform.discounts.domain.model

import java.math.BigDecimal
import java.util.UUID

interface Discount<in T : Any> {
    val id: UUID
    val discountModifier: DiscountModifier
    fun appliedTo(item: T): Money
}

data class FixedPercentageDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier
) : Discount<Money> {
    override fun appliedTo(item: Money): Money {
        return item * discountModifier.value
    }

    fun merge(other: FixedPercentageDiscount) =
        FixedPercentageDiscount(id = id, discountModifier = discountModifier * other.discountModifier)
}

data class CountBasedDiscount(
    override val id: UUID = UUID.randomUUID(),
    override val discountModifier: DiscountModifier,
    val productId: UUID,
    val threshold: Int,
) : Discount<ProductCartEntry> {
    override fun appliedTo(item: ProductCartEntry): Money {
        return if (item.productQuantity.value >= threshold) {
            item.productPrice * discountModifier.value
        } else {
            item.productPrice
        }
    }
}


@JvmInline
value class DiscountModifier(val value: BigDecimal) {
    operator fun times(discountModifier: DiscountModifier): DiscountModifier =
        DiscountModifier(this.value * discountModifier.value)

    init {
        require(value > BigDecimal.ZERO && value <= BigDecimal.ONE) {
            "Discount modifier value must be a value between 0 and 1"
        }
    }

    companion object {
        fun from(s: String) = DiscountModifier(BigDecimal(s))
    }
}
