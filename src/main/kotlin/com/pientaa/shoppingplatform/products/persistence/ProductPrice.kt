package com.pientaa.shoppingplatform.products.persistence

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal
import java.util.Currency

@Embeddable
data class ProductPrice(
    @Column(nullable = false, name = "price_amount")
    val amount: BigDecimal,
    @Column(nullable = false, name = "price_currency")
    var currency: Currency,
)
