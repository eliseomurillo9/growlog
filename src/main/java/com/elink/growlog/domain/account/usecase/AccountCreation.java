package com.elink.growlog.domain.account.usecase;

import com.elink.growlog.domain.account.Account;
import com.elink.growlog.domain.account.commands.CreateAccount;
import com.elink.growlog.domain.account.repository.AccountRepository;
import org.jmolecules.ddd.annotation.Service;

// Domain
@Service
public class AccountCreation {
    AccountRepository accountRepository;
    public AccountCreation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    public Account execute(CreateAccount command) {
        final var account =  Account.create(command);

        return accountRepository.save(account);
    }
}
