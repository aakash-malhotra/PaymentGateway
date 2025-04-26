package com.personal.transaction;

import com.personal.paymode.PayMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class TransactionDetail {

    private PayMode payMode;
    private String client;

    //cards
    private String cardName;
    private String CardNumber;
    private String CardExpiryAt;
    private String CardCvv;

    //upi
    private String vpaId;

    //netbanking
    private String userName;
    private String Password;
}
