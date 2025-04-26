package com.personal.bank;

import com.personal.transaction.TransactionDetail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Random;

@Getter @Setter @ToString @EqualsAndHashCode
public class Bank implements IBank{

    private String name;
    private List<String> payModes;
    private int percentOfTrafficAllotted;

    public Bank(String name, List<String> payModes, int percentOfTrafficAllotted) {
        this.name = name;
        this.payModes = payModes;
        this.percentOfTrafficAllotted = percentOfTrafficAllotted;
    }

    @Override
    public boolean processPayment(TransactionDetail transactionDetail) {
        return new Random().nextBoolean();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
