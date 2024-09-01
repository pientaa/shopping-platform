package com.pientaa.shoppingplatform.pricing.persistence.repository

import com.pientaa.shoppingplatform.pricing.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.Discount
import com.pientaa.shoppingplatform.pricing.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.pricing.domain.port.DiscountRepository
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
