package com.elink.growlog.domain.account;

import com.elink.growlog.domain.commands.CreateAccount;
import com.elink.growlog.domain.model.Transaction;
import com.elink.growlog.domain.model.valueobjects.accountstatus.AccountStatus;
import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;
import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.utils.Assert;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.Collections.emptyList;

@AggregateRoot
public class Account {
    private final UUID compteId;
    private String name;

    private String bank;

    private AccountType type;

    private Currency currency;

    private final Money balance;

    private AccountStatus status;

    private LocalDate deactivationDate;

    private List<Transaction> transactions;

    private static final Pattern ACCOUNT_NAME_PATTERN = Pattern.compile("^^(?!\\s*$).{1,20}$");
    private static final Pattern BANK_NAME_PATTERN = Pattern.compile("^(?!\\s*$).{1,30}$");


    public Account(UUID compteId, String name, String bank, AccountType type, Currency currency, Money balance, AccountStatus status, LocalDate deactivationDate, List<Transaction> transactions) {
        this.compteId = compteId;
        this.name = name;
        this.bank = bank;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
        this.deactivationDate = deactivationDate;
        this.transactions = transactions;
    }

    public static Account create(CreateAccount command) {
        Assert.matchesPattern(ACCOUNT_NAME_PATTERN, command.name(), "Account name");
        Assert.matchesPattern(BANK_NAME_PATTERN, command.name(), "Bank name");
        Assert.notNull(command.type().name(), "Account type");
        Assert.notNull(command.currency().name(), "Currency");

        return new Account(UUID.randomUUID(), command.name(), command.bank(), command.type(), command.currency(), Money.of(BigDecimal.ZERO, command.currency()), AccountStatus.ACTIVE, command.deactivationDate(), emptyList());
    }

    public UUID getCompteId() {
        return compteId;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setDeactivationDate(LocalDate deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }


}
