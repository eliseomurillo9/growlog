package com.elink.growlog.domain.account.usecase;

import com.elink.growlog.domain.account.Account;
import com.elink.growlog.domain.account.commands.CreateAccount;
import org.jmolecules.ddd.annotation.Service;

// Domain
@Service
public class AccountCreation {
    private AccountCreation() {
        /* This utility class should not be instantiated */
    }

    public static Account execute(CreateAccount command) {
        return Account.create(command);
    }
}
