package com.pientaa.shoppingplatform.discounts.application

import com.pientaa.shoppingplatform.discounts.api.dto.TotalPriceCalculationDTO
import com.pientaa.shoppingplatform.discounts.api.dto.TotalPriceDTO
import com.pientaa.shoppingplatform.discounts.domain.DiscountService
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import com.pientaa.shoppingplatform.discounts.domain.model.ProductCart
import com.pientaa.shoppingplatform.discounts.domain.model.Quantity
import com.pientaa.shoppingplatform.discounts.external.product.ProductClient
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
