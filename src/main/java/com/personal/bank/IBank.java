package com.personal.bank;

import com.personal.transaction.TransactionDetail;

public interface IBank {
    boolean processPayment(TransactionDetail transactionDetail);
    String getName();
}