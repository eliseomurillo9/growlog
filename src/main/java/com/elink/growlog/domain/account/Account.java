package com.elink.growlog.domain.account;

import com.elink.growlog.domain.account.commands.CreateAccount;
import com.elink.growlog.domain.model.valueobjects.accountstatus.AccountStatus;
import com.elink.growlog.domain.model.valueobjects.accounttype.AccountType;
import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.domain.transaction.Transaction;
import com.elink.growlog.domain.transaction.commands.CreateTransaction;
import com.elink.growlog.utils.Assert;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.elink.growlog.domain.model.valueobjects.transationtype.TransactionType.*;
import static java.util.Collections.emptyList;

@AggregateRoot
public class Account {
    private final UUID compteId;
    private String name;

    private String bank;

    private AccountType type;

    private Currency currency;

    private Money balance;

    private AccountStatus status;

    private LocalDate deactivationDate;

    private List<Transaction> transactions;

    private static final Pattern ACCOUNT_NAME_PATTERN = Pattern.compile("^(?!\\s*$).{1,20}$");
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

    public Account addTransaction(CreateTransaction command) {
        validCurrency(command.currency(), this.currency);
        activeAccount();
        // TODO: TransactionType check

        var transaction = Transaction.create(command);

        recalculateBalance(transaction);

        return this;
    }

    private void validCurrency(Currency transactionCurrency, Currency accountCurrency) {
        if (transactionCurrency != accountCurrency) {
            throw new IllegalArgumentException("Invalid currency: " + transactionCurrency);
        }
    }

    private void activeAccount() {
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active: " + status);
        }
    }

    private void recalculateBalance(Transaction transaction) {
        final var transactionType = transaction.transactionType();
        if(transactionType.equals(DEPOSIT) || transactionType.equals(SELL)) {
            balance = Money.of(balance.amount().add(transaction.netAmount().amount()), balance.currency());
        }

        if(transactionType.equals(WITHDRAW) || transactionType.equals(BUY)) {
            if (balance.amount().compareTo(transaction.netAmount().amount()) < 0) {
                throw new IllegalArgumentException("Insufficient balance for transaction: " + transaction.id());
            }
            balance = Money.of(balance.amount().subtract(transaction.netAmount().amount()), balance.currency());
        }
    }
}
