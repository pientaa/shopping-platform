package com.pientaa.shoppingplatform.discounts

import io.kotest.core.spec.style.BehaviorSpec

class PercentageBasedDiscountTest : BehaviorSpec({

    Given("Fixed 10% discount to the total price") {
        And("One product in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 10%") {

                }
            }
        }

        And("Two products in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 10%") {

                }
            }
        }

        And("Three products in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 10%") {

                }
            }
        }
    }
})