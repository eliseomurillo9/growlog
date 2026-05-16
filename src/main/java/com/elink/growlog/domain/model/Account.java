package com.elink.growlog.domain.model;

import com.elink.growlog.domain.model.valueobjects.accountstatus.AccountStatus;
import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@AggregateRoot
public class Account {
    private final UUID compteId;
    private final String nom;

    private final String bank;

    private final AccountType type;

    private final Currency currency;

    private final Money balance;

    private final AccountStatus status;

    private final LocalDate deactivationDate;

    private final List<Transaction> transactions;

    public Account(
            String nom,
            String bank,
            AccountType type,
            Currency currency,
            Money balance,
            AccountStatus status,
            LocalDate deactivationDate,
            List<Transaction> transactions) {
        this.compteId = UUID.randomUUID();
        this.nom = nom;
        this.bank = bank;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.deactivationDate = deactivationDate;
        this.transactions = transactions;
    }



    public UUID getCompteId() {
        return compteId;
    }

    public String getNom() {
        return nom;
    }

    public String getBank() {
        return bank;
    }

    public AccountType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDate getDeactivationDate() {
        return deactivationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
