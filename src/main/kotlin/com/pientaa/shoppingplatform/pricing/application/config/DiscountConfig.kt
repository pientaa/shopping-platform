package com.pientaa.shoppingplatform.pricing.application.config

import com.pientaa.shoppingplatform.pricing.domain.DiscountService
import com.pientaa.shoppingplatform.pricing.domain.port.DiscountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscountConfig {
    @Bean
    fun discountService(discountRepository: DiscountRepository) = DiscountService(discountRepository)
}
