package com.pientaa.shoppingplatform.pricing.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "total_price_discount")
class TotalPriceDiscountEntity(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val discountModifier: BigDecimal,

    @Column(nullable = false)
    val active: Boolean = true
)
