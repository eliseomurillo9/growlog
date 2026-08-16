package com.elink.growlog.domain.account.repository;

import com.elink.growlog.domain.account.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(UUID id);
}
