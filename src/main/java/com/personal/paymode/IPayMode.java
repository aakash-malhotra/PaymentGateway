package com.personal.paymode;

import com.personal.transaction.TransactionDetail;

public interface IPayMode {
    String getName();
    boolean isValidRequest(TransactionDetail transactionDetail);
}
