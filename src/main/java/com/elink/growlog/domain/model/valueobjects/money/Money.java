package com.elink.growlog.domain.model.valueobjects.money;

import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;

@ValueObject
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

    public static Money of(BigDecimal amount, Currency currency) {
       return new Money(amount, currency);
    }
}
