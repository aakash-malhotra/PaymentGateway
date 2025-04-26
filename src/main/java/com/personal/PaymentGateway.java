package com.personal;

import com.personal.client.Client;
import com.personal.client.ClientService;
import com.personal.paymode.IPayMode;
import com.personal.transaction.TransactionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.personal.client.ClientService.*;

import java.util.Objects;

@Service
public class PaymentGateway {

    @Autowired
    private ClientService clientService;

    public String showDistribution(){
        return TransactionRouter.getMetrics();
    }

    public boolean makePayment(TransactionDetail transactionDetail) {
        if(Objects.isNull(transactionDetail) || !clientService.hasClient(transactionDetail.getClient()) || Objects.isNull(transactionDetail.getPayMode())) {
            System.out.println("Invalid transaction details");
            return false;
        }
        Client client = clientService.getClient(transactionDetail.getClient());
        IPayMode payMode = transactionDetail.getPayMode();
        if(!client.getSupportedPayModes().contains(payMode)) {
            System.out.println("Client does not support this pay mode");
            return false;
        }
        if(!payMode.isValidRequest(transactionDetail)) {
            System.out.println("Required payment details missing for this pay mode");
            return false;
        }
        return TransactionRouter.routePaymentsToBank(transactionDetail);
    }
}
