package com.pientaa.shoppingplatform.discounts

import com.pientaa.shoppingplatform.discounts.domain.model.Money
import java.math.BigDecimal
import java.util.Currency
import java.util.Locale

fun Money.Companion.ofDollars(amount: String) =
    of(amount = BigDecimal(amount), currency = Currency.getInstance(Locale.US))
