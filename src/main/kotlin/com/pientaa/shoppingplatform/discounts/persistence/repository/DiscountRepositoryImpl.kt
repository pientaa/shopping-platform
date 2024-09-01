package com.pientaa.shoppingplatform.discounts.persistence.repository

import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Discount
import com.pientaa.shoppingplatform.discounts.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.domain.port.DiscountRepository
import com.pientaa.shoppingplatform.discounts.persistence.entity.ProductCountDiscountEntity
import com.pientaa.shoppingplatform.discounts.persistence.entity.TotalPriceDiscountEntity
import org.springframework.stereotype.Repository

@Repository
class DiscountRepositoryImpl(
    private val productCountDiscountJpaRepository: ProductCountDiscountJpaRepository,
    private val totalPriceDiscountJpaRepository: TotalPriceDiscountJpaRepository,
) : DiscountRepository {
    override fun save(discount: Discount<*>): Discount<*> {
        return when (discount) {
            is FixedPercentageDiscount -> totalPriceDiscountJpaRepository.save(discount.toEntity()).toModel()
            is CountBasedDiscount -> productCountDiscountJpaRepository.save(discount.toEntity()).toModel()
            else -> throw IllegalArgumentException("Discount must be one of: count based, fixed percentage based")
        }
    }

    override fun getAllActive(): List<Discount<*>> =
        totalPriceDiscountJpaRepository.findAllByActive(active = true).map { it.toModel() } +
                productCountDiscountJpaRepository.findAllByActive(active = true).map { it.toModel() }
}
