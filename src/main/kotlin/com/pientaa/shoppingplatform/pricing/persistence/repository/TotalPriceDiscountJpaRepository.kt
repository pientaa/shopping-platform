package com.pientaa.shoppingplatform.pricing.persistence.repository

import com.pientaa.shoppingplatform.pricing.persistence.entity.TotalPriceDiscountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TotalPriceDiscountJpaRepository : JpaRepository<TotalPriceDiscountEntity, UUID> {
    fun findAllByActive(active: Boolean): List<TotalPriceDiscountEntity>
}
