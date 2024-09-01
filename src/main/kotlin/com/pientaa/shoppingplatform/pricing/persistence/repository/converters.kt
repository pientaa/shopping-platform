package com.pientaa.shoppingplatform.pricing.persistence.repository

import com.pientaa.shoppingplatform.pricing.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.pricing.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.pricing.persistence.entity.ProductCountDiscountEntity
import com.pientaa.shoppingplatform.pricing.persistence.entity.TotalPriceDiscountEntity

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
