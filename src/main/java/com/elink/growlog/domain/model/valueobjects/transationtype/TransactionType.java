package com.elink.growlog.domain.model.valueobjects.transationtype;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum TransactionType {
    DEPOSIT,

    WITHDRAW,
    BUY,
    SELL
}
