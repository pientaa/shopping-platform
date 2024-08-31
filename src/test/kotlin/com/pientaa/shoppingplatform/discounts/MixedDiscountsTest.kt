package com.pientaa.shoppingplatform.discounts

import com.pientaa.shoppingplatform.discounts.domain.DiscountService
import com.pientaa.shoppingplatform.discounts.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Discount
import com.pientaa.shoppingplatform.discounts.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import com.pientaa.shoppingplatform.discounts.domain.model.Product
import com.pientaa.shoppingplatform.discounts.domain.model.ProductCart
import com.pientaa.shoppingplatform.discounts.domain.model.Quantity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class MixedDiscountsTest : BehaviorSpec({
    val service = DiscountService()

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