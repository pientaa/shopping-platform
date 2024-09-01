package com.pientaa.shoppingplatform.pricing.external.products

import com.pientaa.shoppingplatform.pricing.external.products.dto.ProductExternalDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "product-service", url = "\${product.service.url}")
interface ProductFeignClient {
    @GetMapping("/api/products/{id}")
    fun getProduct(@PathVariable("id") id: UUID): ResponseEntity<ProductExternalDTO>
}
