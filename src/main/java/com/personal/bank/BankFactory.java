package com.personal.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankFactory {

    private String name;
    private int percentOfTrafficAllotted;
    private List<String> payModes;

    public BankFactory name(String name) {
        this.name = name;
        return this;
    }

    public BankFactory supportedPayModes(List<String> paymodes) {
        this.payModes = paymodes;
        return this;
    }

    public BankFactory percentageOfTrafficAllotted(int percentage) {
        this.percentOfTrafficAllotted = percentage;
        return this;
    }

    public Bank build() {
        return new Bank(this.name, this.payModes, this.percentOfTrafficAllotted);
    }
}
