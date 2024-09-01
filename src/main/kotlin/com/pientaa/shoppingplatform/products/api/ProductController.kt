package com.pientaa.shoppingplatform.products.api

import com.pientaa.shoppingplatform.products.persistence.ProductEntity
import com.pientaa.shoppingplatform.products.persistence.ProductJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productRepository: ProductJpaRepository,
) {
    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID): ResponseEntity<ProductDTO> =
        productRepository.findByIdOrNull(id)?.let { ResponseEntity.ok(it.toDTO()) }
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
}

private fun ProductEntity.toDTO(): ProductDTO = ProductDTO(
    id = id,
    amount = price.amount,
    currency = price.currency.currencyCode,
)
