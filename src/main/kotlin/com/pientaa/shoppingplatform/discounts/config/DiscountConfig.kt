package com.pientaa.shoppingplatform.discounts.config

import com.pientaa.shoppingplatform.discounts.domain.DiscountService
import com.pientaa.shoppingplatform.discounts.domain.port.DiscountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscountConfig {
    @Bean
    fun discountService(discountRepository: DiscountRepository) = DiscountService(discountRepository)
}
