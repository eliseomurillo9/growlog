package com.elink.growlog.domain.transaction;

import com.elink.growlog.domain.model.valueobjects.currency.Currency;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import com.elink.growlog.domain.model.valueobjects.transationtype.TransactionType;
import com.elink.growlog.domain.transaction.commands.CreateTransaction;

import java.time.LocalDate;
import java.util.UUID;

public record Transaction(
    Currency currency,
    LocalDate date,
    UUID id,
    UUID accountId,
    Money grossAmount,
    Money netAmount,
    Money fee,
    TransactionType transactionType
) {
    public static Transaction create(CreateTransaction command) {

        try {
            /** TODO:
             * 3. check if the transaction is valid for the account type (e.g., no withdrawals for savings accounts)
             * 4. check if the transaction date is not in the future
             * 5. Fee should be calculated with gross - net ammount
             * 6. Check that the transaction is a validate enum
             * */

            final var transactionCurrency = Currency.valueOf(command.currency().name());
            return new Transaction(
                    transactionCurrency,
                    command.date(),
                    UUID.randomUUID(),
                    command.accountId(),
                    command.grossAmmount(),
                    command.netAmmount(),
                    command.fee(),
                    command.typeTransaction()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency: " + command.currency());
        }
    }

}
