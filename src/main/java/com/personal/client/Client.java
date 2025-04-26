package com.personal.client;

import com.personal.paymode.IPayMode;
import com.personal.paymode.PayMode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @EqualsAndHashCode
public class Client {

    private String name;
    private List<PayMode> supportedPayModes = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public void addPayMode(PayMode payMode) {
        supportedPayModes.add(payMode);
    }

    public void removePayMode(PayMode payMode) {
        supportedPayModes.remove(payMode);
    }

}
