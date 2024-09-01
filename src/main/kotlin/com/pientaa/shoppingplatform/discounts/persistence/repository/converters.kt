package com.pientaa.shoppingplatform.discounts.persistence.repository

import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.persistence.entity.ProductCountDiscountEntity
import com.pientaa.shoppingplatform.discounts.persistence.entity.TotalPriceDiscountEntity

fun CountBasedDiscount.toEntity(): ProductCountDiscountEntity = ProductCountDiscountEntity(
    discountModifier = discountModifier.value,
    productId = productId,
    threshold = threshold,
)

fun FixedPercentageDiscount.toEntity(): TotalPriceDiscountEntity = TotalPriceDiscountEntity(
    discountModifier = discountModifier.value
)

fun TotalPriceDiscountEntity.toModel(): FixedPercentageDiscount =
    FixedPercentageDiscount(
        id = id,
        discountModifier = DiscountModifier(discountModifier)
    )

fun ProductCountDiscountEntity.toModel(): CountBasedDiscount = CountBasedDiscount(
    id = id,
    discountModifier = DiscountModifier(discountModifier),
    productId = productId,
    threshold = threshold
)
