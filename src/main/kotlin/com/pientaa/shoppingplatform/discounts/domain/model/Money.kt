package com.pientaa.shoppingplatform.discounts.domain.model

import java.math.BigDecimal
import java.util.Currency


/**
 * Represents an amount of money in a specific currency.
 *
 * This class encapsulates monetary value and its associated currency, ensuring that operations
 * like addition, subtraction, and multiplication are performed safely, taking currency into account.
 * The `Money` class is immutable and provides operator overloading for convenient arithmetic operations.
 *
 * @property amount The amount of money. It must be non-negative.
 * @property currency The currency of the money, represented by the [Currency] class.
 *
 * @throws IllegalArgumentException if [amount] is negative or if operations are attempted between different currencies.
 */
class Money private constructor(
    val amount: BigDecimal,
    val currency: Currency,
) {
    companion object {
        private const val SCALE = 2

        fun of(amount: BigDecimal, currency: Currency): Money {
            return Money(amount.setScale(SCALE), currency)
        }
    }

    init {
        require(amount >= BigDecimal.ZERO) { "Amount must be non-negative" }
    }

    operator fun plus(other: Money): Money {
        validateSameCurrency(other)
        return of(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        validateSameCurrency(other)
        return of(amount - other.amount, currency)
    }

    operator fun times(multiplier: BigDecimal): Money {
        return of(amount * multiplier, currency)
    }

    operator fun times(multiplier: Int): Money {
        return of(amount * BigDecimal(multiplier), currency)
    }

    private fun validateSameCurrency(other: Money) {
        require(currency == other.currency) { "Cannot operate on different currencies" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (amount != other.amount) return false
        if (currency != other.currency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = amount.hashCode()
        result = 31 * result + currency.hashCode()
        return result
    }

    override fun toString(): String {
        return "Money(amount=$amount, currency=$currency)"
    }

}
