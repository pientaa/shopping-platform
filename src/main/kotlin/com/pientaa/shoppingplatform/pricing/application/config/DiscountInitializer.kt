package com.pientaa.shoppingplatform.pricing.application.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pientaa.shoppingplatform.pricing.domain.DiscountService
import com.pientaa.shoppingplatform.pricing.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.pricing.domain.model.FixedPercentageDiscount
import jakarta.annotation.PostConstruct
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class DiscountInitializer(
    private val discountService: DiscountService,
) {

    @PostConstruct
    fun initializeDiscounts() {
        val resource = ClassPathResource("discounts.yml")
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        val discountConfig = mapper.readValue(resource.inputStream, DiscountYamlConfig::class.java)

        discountConfig.fixedPercentageDiscounts.forEach { config ->
            val discount = FixedPercentageDiscount(discountModifier = DiscountModifier.from(config.discountModifier))
            discountService.createDiscount(discount)
        }

        discountConfig.countBasedDiscounts.forEach { config ->
            val discount = CountBasedDiscount(
                discountModifier = DiscountModifier.from(config.discountModifier),
                productId = config.productId,
                threshold = config.threshold
            )
            discountService.createDiscount(discount)
        }
    }
}

data class DiscountYamlConfig(
    val fixedPercentageDiscounts: List<FixedPercentageDiscountYamlConfig>,
    val countBasedDiscounts: List<CountBasedDiscountYamlConfig>
)

data class FixedPercentageDiscountYamlConfig(
    val discountModifier: String
)

data class CountBasedDiscountYamlConfig(
    val discountModifier: String,
    val productId: UUID,
    val threshold: Int
)
