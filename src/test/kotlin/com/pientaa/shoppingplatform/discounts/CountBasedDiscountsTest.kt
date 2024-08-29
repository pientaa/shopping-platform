package com.pientaa.shoppingplatform.discounts

import io.kotest.core.spec.style.BehaviorSpec

class CountBasedDiscountsTest : BehaviorSpec({

    Given("Cunt-based discount: 5% for 5-10; 20% for 11+ products") {
        And("Only one product in the cart") {
            When("Calculate discount") {
                Then("Price should not be changed") {

                }
            }
        }

        And("4 products in the cart") {
            When("Calculate discount") {
                Then("Price should not be changed") {

                }

            }
        }

        And("5 products in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 5%") {

                }

            }
        }

        And("10 products in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 5%") {

                }

            }
        }

        And("11 products in the cart") {
            When("Calculate discount") {
                Then("Price should be lowered by 10%") {

                }

            }
        }
    }
})
