package com.personal.paymode;

import com.personal.transaction.TransactionDetail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

@Getter @Setter @ToString @EqualsAndHashCode
public class PayMode implements IPayMode{

    private String name;
    private Function<TransactionDetail, Boolean> checkValidTransaction;

    public PayMode(String name, Function<TransactionDetail, Boolean> function) {
        this.name = name;
        this.checkValidTransaction = function;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isValidRequest(TransactionDetail transactionDetail) {
        return checkValidTransaction.apply(transactionDetail);
    }
}
