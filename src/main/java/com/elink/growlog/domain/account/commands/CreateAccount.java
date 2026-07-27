package com.elink.growlog.domain.account.commands;
import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;
import com.elink.growlog.domain.model.valueobjects.currency.Currency;

import java.time.LocalDate;
public record CreateAccount(
        String name,
        String bank,
        AccountType type,
        Currency currency,
        LocalDate deactivationDate
){
    public static CreateAccount of(String name, String bank, AccountType type, Currency currency, LocalDate deactivationDate) {
        return new CreateAccount(name, bank, type, currency, deactivationDate);

    }
}