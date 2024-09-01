package com.pientaa.shoppingplatform.pricing.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "product_count_discount")
class ProductCountDiscountEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val discountModifier: BigDecimal,

    @Column(nullable = false)
    val productId: UUID,

    @Column(nullable = false)
    val threshold: Int,

    @Column(nullable = false)
    val active: Boolean = true
)
