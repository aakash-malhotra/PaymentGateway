package com.personal;

import com.personal.bank.Bank;
import com.personal.transaction.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TransactionRouter {

    public static String getMetrics() {
        StringBuilder builder = new StringBuilder();
        Memory.BANKS.values().forEach((bank) -> {
            builder.append(bank.getName()).append(" ").append(bank.getPercentOfTrafficAllotted()).append("\n");
        });
        return builder.toString();
    }

    public static boolean routePaymentsToBank(TransactionDetail transactionDetail) {
        var supportedBanksList = Memory.PAY_MODES_BANK.get(transactionDetail.getPayMode().getName());
        int random = new Random().nextInt(100) + 1;
        int sum = 0;
        for (String bankName : supportedBanksList) {
            Bank bank = Memory.BANKS.get(bankName);
            sum += bank.getPercentOfTrafficAllotted();
            if (random <= sum) return bank.processPayment(transactionDetail);
        }
        return false;
    }

}
