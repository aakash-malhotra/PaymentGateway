package com.personal.paymode;

import com.personal.transaction.TransactionDetail;

import java.util.function.Function;

public class PayModeFactory {

    private String name;
    private Function<TransactionDetail, Boolean> function;

    public PayModeFactory name(String name) {
        this.name = name;
        return this;
    }

    public PayModeFactory isValidWhen(Function<TransactionDetail, Boolean> function) {
        this.function = function;
        return this;
    }

    public PayMode build(){
        return new PayMode(this.name, this.function);
    }
}
