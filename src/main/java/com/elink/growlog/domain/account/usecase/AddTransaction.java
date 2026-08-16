package com.elink.growlog.domain.account.usecase;

import com.elink.growlog.domain.account.Account;
import com.elink.growlog.domain.account.repository.AccountRepository;
import com.elink.growlog.domain.transaction.commands.CreateTransaction;
import org.jmolecules.ddd.annotation.Service;

@Service
public class AddTransaction {
    AccountRepository accountRepository;
    public AddTransaction(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account execute(CreateTransaction command) {

        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        final var accountWithNewTransaction = account.addTransaction(command);

        return accountRepository.save(accountWithNewTransaction);
    }
}
