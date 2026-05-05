package com.elink.growlog.domain.valueobjects.money;

import com.elink.growlog.domain.valueobjects.currency.Currency;

import java.math.BigDecimal;

public record Money( BigDecimal amount, Currency currency) {
   public Money{
       if (currency == null) {
           throw new IllegalArgumentException("Currency cannot be null");
       }

        if (amount == null)  {
            amount = BigDecimal.ZERO;
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

    }
}
