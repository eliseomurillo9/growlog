package com.elink.growlog.domain.transaction.commands;

import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.domain.model.valueobjects.transationtype.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTransaction(
        UUID transactionId,
        UUID accountId,
        Money grossAmmount,
        Money netAmmount,
        LocalDate date,
        TransactionType typeTransaction,
        Currency currency,
        Money fee
){
    public static CreateTransaction of(UUID transactionId, UUID accountId, Money grossAmmount, Money netAmmount, LocalDate date, TransactionType typeTransaction, Currency currency, Money fee) {
        return new CreateTransaction(transactionId, accountId, grossAmmount, netAmmount, date, typeTransaction, currency, fee);
    }
}

