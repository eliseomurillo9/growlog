package com.elink.growlog.domain.commands;

import com.elink.growlog.domain.model.Transaction;
import com.elink.growlog.domain.model.valueobjects.accountstatus.AccountStatus;
import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;
import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;

import java.time.LocalDate;
import java.util.List;

public record CreateAccount(
        String name,
        String bank,
        AccountType type,
        Currency currency,
        LocalDate deactivationDate
){}