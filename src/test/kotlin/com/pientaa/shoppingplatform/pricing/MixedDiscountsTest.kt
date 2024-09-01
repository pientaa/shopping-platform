package com.pientaa.shoppingplatform.pricing

import com.pientaa.shoppingplatform.pricing.domain.PriceCalculator
import com.pientaa.shoppingplatform.pricing.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.Discount
import com.pientaa.shoppingplatform.pricing.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.pricing.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.Money
import com.pientaa.shoppingplatform.pricing.domain.model.Product
import com.pientaa.shoppingplatform.pricing.domain.model.ProductCart
import com.pientaa.shoppingplatform.pricing.domain.model.Quantity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class MixedDiscountsTest : BehaviorSpec({
    val service = PriceCalculator()

    Given("No products in cart") {
        val productCart = ProductCart(mapOf())
        val discounts = listOf<Discount<*>>()

        When("Calculate total price") {
            Then("Should throw an exception") {
                assertThrows<Exception> { service.calculateTotalPrice(productCart, discounts) }
            }
        }
    }

    Given("3 Products with different count in cart") {
        val productCart = ProductCart(
            mapOf(
                Product(UUID.randomUUID(), Money.ofDollars("100.00")) to Quantity(1),
                Product(UUID.randomUUID(), Money.ofDollars("50.00")) to Quantity(5),
                Product(UUID.randomUUID(), Money.ofDollars("15.00")) to Quantity(20),
            )
        )

        And("Two fixed 10% discounts") {
            //TODO: Get rid of wildcards
            val fixedPercentageDiscounts: List<Discount<*>> =
                listOf(
                    FixedPercentageDiscount(discountModifier = DiscountModifier.from("0.9")),
                    FixedPercentageDiscount(discountModifier = DiscountModifier.from("0.9")),
                )

            When("Calculate total price") {
                val price = service.calculateTotalPrice(productCart, fixedPercentageDiscounts)

                Then("Total price should be updated") {
                    price shouldBe Money.ofDollars("526.50")
                }
            }

            And("Two count-based discounts") {
                val firstProduct = productCart.cart.keys.first()
                val lastProduct = productCart.cart.keys.last()

                val countBasedDiscounts: List<Discount<*>> =
                    listOf(
                        CountBasedDiscount(
                            discountModifier = DiscountModifier.from("0.5"),
                            productId = firstProduct.id,
                            threshold = 10,
                        ),
                        CountBasedDiscount(
                            discountModifier = DiscountModifier.from("0.5"),
                            productId = lastProduct.id,
                            threshold = 10,
                        ),
                    )

                When("Calculate total price") {
                    val price = service.calculateTotalPrice(productCart, fixedPercentageDiscounts + countBasedDiscounts)

                    Then("Total price should be updated") {
                        price shouldBe Money.ofDollars("405.00")
                    }
                }
            }
        }
    }
})
