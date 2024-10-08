package com.pientaa.shoppingplatform.products

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.pientaa.shoppingplatform.config.IntegrationTest
import com.pientaa.shoppingplatform.products.api.ProductDTO
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.math.BigDecimal

@IntegrationTest
class ProductControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) : StringSpec({

    "GET /products/{id], OK" {
        forAll(
            row("e8b8f792-81d5-4df3-97cb-9dc3d3c3e4d8", "19.99", "USD"),
            row("c5f8cdd8-0d5c-4e5d-998e-23c6a0d3486e", "29.99", "USD"),
            row("a6d5e7be-6b3e-4f21-9a1e-39f6d3d4e6a1", "15.50", "USD"),
            row("d4e5f7b2-91e8-4c62-b6ae-14f5d3c3e8b3", "49.99", "USD"),
            row("a0f1c8b4-8421-4c47-a550-5e9c2c6b0e8f", "12.99", "USD"),
        ) { id, price, currency ->

            val result = mockMvc.get("/api/products/$id") {
                accept(MediaType.APPLICATION_JSON)
            }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }.andReturn()
                .let { objectMapper.readValue<ProductDTO>(it.response.contentAsString) }

            result.amount shouldBe BigDecimal(price)
            result.currency shouldBe currency
        }
    }
})
