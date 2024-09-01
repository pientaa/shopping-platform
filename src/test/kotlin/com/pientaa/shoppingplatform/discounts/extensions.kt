package com.pientaa.shoppingplatform.discounts

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.pientaa.shoppingplatform.ProductStub
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import java.math.BigDecimal
import java.util.Currency
import java.util.Locale

fun Money.Companion.ofDollars(amount: String) =
    of(amount = BigDecimal(amount), currency = Currency.getInstance(Locale.US))

fun WireMockServer.mockProducts() {
    ProductStub.products.forEach { product ->
        stubFor(
            WireMock.get(WireMock.urlEqualTo("/api/products/${product.id}"))
                .willReturn(
                    WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
                            {
                                "id": "${product.id}",
                                "amount": "${product.price.amount}",
                                "currency": "${product.price.currency.currencyCode}"
                            }
                        """.trimIndent()
                        )
                )
        )
    }
}
