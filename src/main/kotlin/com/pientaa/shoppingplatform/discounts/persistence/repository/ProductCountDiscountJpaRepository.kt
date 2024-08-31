package com.pientaa.shoppingplatform.discounts.persistence.repository

import com.pientaa.shoppingplatform.discounts.persistence.entity.ProductCountDiscountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductCountDiscountJpaRepository : JpaRepository<ProductCountDiscountEntity, UUID>
