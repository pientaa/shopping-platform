package com.pientaa.shoppingplatform.pricing

import com.pientaa.shoppingplatform.pricing.domain.model.CountBasedDiscount
import com.pientaa.shoppingplatform.pricing.domain.model.DiscountModifier
import com.pientaa.shoppingplatform.pricing.domain.model.Money
import com.pientaa.shoppingplatform.pricing.domain.model.Product
import com.pientaa.shoppingplatform.pricing.domain.model.ProductCartEntry
import com.pientaa.shoppingplatform.pricing.domain.model.Quantity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.util.UUID

class CountBasedDiscountsTest : BehaviorSpec({

    Given("Count-based discount: 5% for over 5 product count") {
        val discount = CountBasedDiscount(
            discountModifier = DiscountModifier(BigDecimal("0.95")),
            threshold = 5,
            productId = UUID.randomUUID()
        )

        And("Product count = 1") {
            val cartEntry = ProductCartEntry.from(
                Product(id = UUID.randomUUID(), price = Money.ofDollars("100.00")) to Quantity(1),
            )

            When("Calculate cart entry price") {
                val cartEntryPrice = discount.appliedTo(cartEntry)

                Then("The price should not be changed") {
                    cartEntryPrice shouldBe cartEntry.productPrice
                }
            }
        }

        And("Product count = 4") {
            val cartEntry = ProductCartEntry.from(
                Product(id = UUID.randomUUID(), price = Money.ofDollars("100.00")) to Quantity(4),
            )
            When("Calculate cart entry price") {
                val cartEntryPrice = discount.appliedTo(cartEntry)

                Then("The price should not be changed") {
                    cartEntryPrice shouldBe cartEntry.productPrice
                }
            }
        }

        And("Product count = 5") {
            val cartEntry = ProductCartEntry.from(
                Product(id = UUID.randomUUID(), price = Money.ofDollars("100.00")) to Quantity(5),
            )

            When("Calculate cart entry price") {
                val cartEntryPrice = discount.appliedTo(cartEntry)

                Then("The price should be lowered by 5%") {
                    cartEntryPrice shouldBe cartEntry.productPrice * BigDecimal("0.95")
                }
            }
        }

        And("Product count = 10") {
            val cartEntry = ProductCartEntry.from(
                Product(id = UUID.randomUUID(), price = Money.ofDollars("100.00")) to Quantity(5),
            )
            When("Calculate cart entry price") {
                val cartEntryPrice = discount.appliedTo(cartEntry)

                Then("The price should be lowered by 5%") {
                    cartEntryPrice shouldBe cartEntry.productPrice * BigDecimal("0.95")
                }
            }
        }
    }
})
