package com.pientaa.shoppingplatform.pricing.application

import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceCalculationDTO
import com.pientaa.shoppingplatform.pricing.api.dto.TotalPriceDTO
import com.pientaa.shoppingplatform.pricing.domain.DiscountService
import com.pientaa.shoppingplatform.pricing.domain.model.Money
import com.pientaa.shoppingplatform.pricing.domain.model.ProductCart
import com.pientaa.shoppingplatform.pricing.domain.model.Quantity
import com.pientaa.shoppingplatform.pricing.external.products.ProductClient
import org.springframework.stereotype.Service

@Service
class PriceCalculationService(
    private val discountService: DiscountService,
    private val productClient: ProductClient,
) {
    fun calculateTotalPrice(totalPriceCalculationDTO: TotalPriceCalculationDTO): TotalPriceDTO {
        val product = productClient.getProduct(totalPriceCalculationDTO.productId)

        return ProductCart.from(product to Quantity(totalPriceCalculationDTO.quantity))
            .let { discountService.calculateTotalPrice(it) }
            .toDTO()
    }
}

private fun Money.toDTO(): TotalPriceDTO = TotalPriceDTO(amount, currency.currencyCode)
