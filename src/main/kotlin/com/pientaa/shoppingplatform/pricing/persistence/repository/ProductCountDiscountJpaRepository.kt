package com.pientaa.shoppingplatform.pricing.persistence.repository

import com.pientaa.shoppingplatform.pricing.persistence.entity.ProductCountDiscountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductCountDiscountJpaRepository : JpaRepository<ProductCountDiscountEntity, UUID> {
    fun findAllByActive(active: Boolean): List<ProductCountDiscountEntity>
}
