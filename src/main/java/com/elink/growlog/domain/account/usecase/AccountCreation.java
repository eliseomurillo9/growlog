package com.elink.growlog.domain.account.usecase;

import com.elink.growlog.domain.account.Account;
import com.elink.growlog.domain.commands.CreateAccount;
import com.elink.growlog.domain.model.valueobjects.money.Money;
import org.jmolecules.ddd.annotation.Service;

@Service
public class AccountCreation {
    public static Account execute(CreateAccount command) {
        return Account.create(command);
    }
}
