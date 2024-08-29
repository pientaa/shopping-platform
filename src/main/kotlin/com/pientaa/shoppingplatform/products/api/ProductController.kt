package com.pientaa.shoppingplatform.products.api

import com.pientaa.shoppingplatform.products.persistence.ProductEntity
import com.pientaa.shoppingplatform.products.persistence.ProductJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ProductController(
    private val productRepository: ProductJpaRepository,
) {

    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable id: UUID): ResponseEntity<ProductEntity> =
        productRepository.findByIdOrNull(id)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
}
