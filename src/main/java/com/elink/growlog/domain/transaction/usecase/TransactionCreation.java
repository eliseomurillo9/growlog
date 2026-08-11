package com.elink.growlog.domain.transaction.usecase;

import com.elink.growlog.domain.transaction.Transaction;
import com.elink.growlog.domain.transaction.commands.CreateTransaction;

public class TransactionCreation {
    private TransactionCreation() {}

    public static Transaction invoke(CreateTransaction command) {
        return Transaction.atCreation(command);
    }
}
