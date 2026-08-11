package com.elink.growlog.domain.transaction;

import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.domain.model.valueobjects.transationtype.TransactionType;
import com.elink.growlog.domain.transaction.commands.CreateTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    Currency currency;
    LocalDate date;
    UUID id;
    UUID AccountId;
    Money grossAmmount;
    Money netAmmount;
    Money fee;
    TransactionType transactionType;

    public Transaction(UUID id, UUID AccountId, Money grossAmmount, Money netAmmount, LocalDate date, TransactionType typeTransaction,  Currency currency,  Money fee ) {
        this.id = id;
        this.AccountId = AccountId;
        this.grossAmmount = grossAmmount;
        this.netAmmount = netAmmount;
        this.date = date;
        this.transactionType = typeTransaction;
        this.currency = currency;
        this.fee = fee;
    }

    public static Transaction atCreation (CreateTransaction command) {

        try {
            final var transactionCurrency = Currency.valueOf(command.currency().name());
            return new Transaction(
                    UUID.randomUUID(),
                    command.accountId(),
                    command.grossAmmount(),
                    command.netAmmount(),
                    command.date(),
                    command.typeTransaction(),
                    transactionCurrency,
                    command.fee()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency: " + command.currency());
        }

    }
}
