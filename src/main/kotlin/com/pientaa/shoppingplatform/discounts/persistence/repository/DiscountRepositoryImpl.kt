package com.pientaa.shoppingplatform.discounts.persistence.repository

import com.pientaa.shoppingplatform.discounts.domain.port.ProductCountDiscountRepository
import com.pientaa.shoppingplatform.discounts.domain.port.TotalPriceDiscountDiscountRepository
import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.persistence.entity.ProductCountDiscountEntity
import com.pientaa.shoppingplatform.discounts.persistence.entity.TotalPriceDiscountEntity
import org.springframework.stereotype.Repository

//TODO: Divide it

@Repository
class ProductCountDiscountRepositoryImpl(
    private val productCountDiscountJpaRepository: ProductCountDiscountJpaRepository,
) : ProductCountDiscountRepository {
    override fun getAllActive(): List<CountBasedDiscount> = productCountDiscountJpaRepository.findAll()
        .map { entity: ProductCountDiscountEntity ->
            CountBasedDiscount(
                discountModifier = DiscountModifier(entity.discountModifier),
                productId = entity.productId,
                threshold = entity.threshold
            )
        }
}

@Repository
class TotalPriceDiscountDiscountRepositoryImpl(
    private val totalPriceDiscountJpaRepository: TotalPriceDiscountJpaRepository
) : TotalPriceDiscountDiscountRepository {
    override fun getAllActive(): List<FixedPercentageDiscount> = totalPriceDiscountJpaRepository.findAll()
        .map { entity: TotalPriceDiscountEntity ->
            FixedPercentageDiscount(
                discountModifier = DiscountModifier(entity.discountModifier),
            )
        }
}
