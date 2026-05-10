package com.elink.growlog.domain.model;

import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.domain.model.valueobjects.transationtype.TransactionType;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {
    private final UUID transactionId;
    private final Money montantBrut;
    private final Currency currency;
    private final Money montantNet;
    private final Money fee;
    private final LocalDate date;
    private final TransactionType transactionType;

    public Transaction(Money montantBrut, Currency currency, Money montantNet, LocalDate date, TransactionType transactionType) {
        this.transactionId = UUID.randomUUID();
        try {
            this.currency = Currency.valueOf(currency.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency: " + currency);
        }
        this.montantBrut = montantBrut;
        this.montantNet = montantNet;
        this.fee = new Money(montantBrut.amount().subtract(montantNet.amount()), currency);
        this.date = date;
        this.transactionType = transactionType;
    }

    public Money getMontantBrut() {
        return montantBrut;
    }

    public Money getMontantNet() {
        return montantNet;
    }

    public Money getFee() {
        return fee;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public UUID getTransactionId() {
        return transactionId;
    }
}
