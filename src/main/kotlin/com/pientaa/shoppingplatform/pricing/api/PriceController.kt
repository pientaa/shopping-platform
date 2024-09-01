package com.pientaa.shoppingplatform.pricing.api

import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceCalculationDTO
import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceDTO
import com.pientaa.shoppingplatform.pricing.application.PriceCalculationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/price")
class PriceController(private val priceCalculationService: PriceCalculationService) {

    @PostMapping("/calculate")
    fun calculateTotalPrice(@RequestBody request: TotalPriceCalculationDTO): ResponseEntity<TotalPriceDTO> {
        val totalPrice = priceCalculationService.calculateTotalPrice(request)
        return ResponseEntity.ok(totalPrice)
    }
}
