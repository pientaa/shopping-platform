package com.pientaa.shoppingplatform.pricing

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.tomakehurst.wiremock.WireMockServer
import com.pientaa.shoppingplatform.config.IntegrationTest
import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceCalculationDTO
import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceDTO
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal
import java.util.UUID

@IntegrationTest
class PriceControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) : StringSpec({
    val wiremock = WireMockServer(8080)
    listener(WireMockListener(wiremock, ListenerMode.PER_SPEC))

    beforeTest {
        wiremock.mockProducts()
    }

    "POST /price/calculate, OK" {
        forAll(
            row("e8b8f792-81d5-4df3-97cb-9dc3d3c3e4d8", 2, "34.18", "USD"),
            row("c5f8cdd8-0d5c-4e5d-998e-23c6a0d3486e", 3, "76.92", "USD"),
            row("c5f8cdd8-0d5c-4e5d-998e-23c6a0d3486e", 8, "51.30", "USD"),
            row("d4e5f7b2-91e8-4c62-b6ae-14f5d3c3e8b3", 1, "42.74", "USD"),
            row("d4e5f7b2-91e8-4c62-b6ae-14f5d3c3e8b3", 2, "42.75", "USD"),
        ) { productId, quantity, expectedAmount, expectedCurrency ->

            val request = TotalPriceCalculationDTO(
                productId = UUID.fromString(productId),
                quantity = quantity
            )

            val result = mockMvc.post("/api/price/calculate") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(request)
                accept(MediaType.APPLICATION_JSON)
            }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }.andReturn()
                .let { objectMapper.readValue<TotalPriceDTO>(it.response.contentAsString) }

            result.amount shouldBe BigDecimal(expectedAmount)
            result.currency shouldBe expectedCurrency
        }
    }
})