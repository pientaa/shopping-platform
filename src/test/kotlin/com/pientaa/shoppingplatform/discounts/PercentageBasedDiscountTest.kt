package com.pientaa.shoppingplatform.discounts

import com.pientaa.shoppingplatform.discounts.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.discounts.domain.model.FixedPercentageDiscount
import com.pientaa.shoppingplatform.discounts.domain.model.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class PercentageBasedDiscountTest : BehaviorSpec({

    Given("Fixed 10% discount to the total price") {
        val discount = FixedPercentageDiscount(discountModifier = DiscountModifier(BigDecimal("0.90")))

        And("One product in quantity of 1 in the cart") {
            val cart = Money.ofDollars("100.00")

            When("Calculate discount") {
                val totalPrice = discount.appliedTo(cart)

                Then("Price should be lowered by 10%") {
                    totalPrice.amount shouldBe BigDecimal("90.00")
                }
            }
        }

        And("One product in quantity of 20, the other one in quantity of 10") {
            val cart = Money.ofDollars("2150.00")
            When("Calculate discount") {
                val totalPrice = discount.appliedTo(cart)

                Then("Price should be lowered by 10%") {
                    totalPrice.amount shouldBe BigDecimal("1935.00")
                }
            }
        }
    }
})
