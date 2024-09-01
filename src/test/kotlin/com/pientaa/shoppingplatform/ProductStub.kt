package com.pientaa.shoppingplatform

import com.pientaa.shoppingplatform.pricing.domain.model.Money
import com.pientaa.shoppingplatform.pricing.domain.model.Product
import java.math.BigDecimal
import java.util.Currency
import java.util.UUID

object ProductStub {

    /**
     * A list of products populated from the SQL script
     * located at: resources/db/migration/V2__product_data.sql
     */
    val products: List<Product>
        get() = listOf(
            Product(
                id = UUID.fromString("e8b8f792-81d5-4df3-97cb-9dc3d3c3e4d8"),
                price = Money.of(BigDecimal("19.99"), Currency.getInstance("USD"))
            ),
            Product(
                id = UUID.fromString("c5f8cdd8-0d5c-4e5d-998e-23c6a0d3486e"),
                price = Money.of(BigDecimal("29.99"), Currency.getInstance("USD"))
            ),
            Product(
                id = UUID.fromString("a6d5e7be-6b3e-4f21-9a1e-39f6d3d4e6a1"),
                price = Money.of(BigDecimal("15.50"), Currency.getInstance("USD"))
            ),
            Product(
                id = UUID.fromString("d4e5f7b2-91e8-4c62-b6ae-14f5d3c3e8b3"),
                price = Money.of(BigDecimal("49.99"), Currency.getInstance("USD"))
            ),
            Product(
                id = UUID.fromString("a0f1c8b4-8421-4c47-a550-5e9c2c6b0e8f"),
                price = Money.of(BigDecimal("12.99"), Currency.getInstance("USD"))
            )
        )
}
