package com.personal;

import com.personal.bank.*;
import com.personal.client.Client;
import com.personal.paymode.IPayMode;
import com.personal.paymode.PayMode;
import com.personal.paymode.PayModeFactory;
import com.personal.transaction.TransactionDetail;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class Memory {
    public static final Map<String, Bank> BANKS = new HashMap<>();
    public static final Map<String, Client> CLIENTS = new HashMap<>();
    public static Map<String, PayMode> PAY_MODES = new HashMap<>();
    public static final Map<String, List<String>> PAY_MODES_BANK = new HashMap<>();

    static {
        initializePayModes();
        initializeBanks();
        initializePayModesSupportedBanks();
    }

    private static void initializePayModesSupportedBanks() {
        var upiSupportedLists = new ArrayList<String>();
        upiSupportedLists.add("HDFC");
        PAY_MODES_BANK.put("UPI", upiSupportedLists);

        var netBankingSupportedLists = new ArrayList<String>();
        netBankingSupportedLists.add("HDFC");
        netBankingSupportedLists.add("ICICI");
        PAY_MODES_BANK.put("NET_BANKING", netBankingSupportedLists);

        var creditSupportedLists = new ArrayList<String>();
        creditSupportedLists.add("HDFC");
        PAY_MODES_BANK.put("CREDIT_CARD", creditSupportedLists);

        var debitSupportedLists = new ArrayList<String>();
        debitSupportedLists.add("HDFC");
        debitSupportedLists.add("ICICI");
        PAY_MODES_BANK.put("DEBIT_CARD", debitSupportedLists);
    }

    private static void initializeBanks() {
        BANKS.put("HDFC", new BankFactory().name("HDFC").build());
        BANKS.put("ICICI", new BankFactory().name("ICICI").build());
        BANKS.put("SBI", new BankFactory().name("SBI").build());
    }

    private static void initializePayModes() {
        PAY_MODES.put("UPI", new PayModeFactory().name("UPI").isValidWhen(checkForDetailsInPayment(List.of("vpa"))).build());
        PAY_MODES.put("NET_BANKING", new PayModeFactory().name("NET_BANKING").isValidWhen(checkForDetailsInPayment(List.of("username"))).build());
        PAY_MODES.put("CREDIT_CARD", new PayModeFactory().name("CREDIT_CARD").isValidWhen(checkForDetailsInPayment(List.of("card"))).build());
        PAY_MODES.put("DEBIT_CARD", new PayModeFactory().name("DEBIT_CARD").isValidWhen(checkForDetailsInPayment(List.of("card"))).build());
    }

    public static Function<TransactionDetail, Boolean> checkForDetailsInPayment(List<String> checkFor) {
        return (transactionDetail) -> {
            boolean check = true;
            for (var checkName : checkFor) {
                if (checkName.equalsIgnoreCase("username"))
                    check = Objects.nonNull(transactionDetail.getUserName()) && !transactionDetail.getUserName().isBlank() &&
                            Objects.nonNull(transactionDetail.getPassword()) && !transactionDetail.getPassword().isBlank();
                if (check && checkName.equalsIgnoreCase("card"))
                    check = Objects.nonNull(transactionDetail.getCardName()) && !transactionDetail.getCardName().isBlank() &&
                            Objects.nonNull(transactionDetail.getCardNumber()) && !transactionDetail.getCardNumber().isBlank() &&
                            Objects.nonNull(transactionDetail.getCardCvv()) && !transactionDetail.getCardCvv().isBlank() &&
                            Objects.nonNull(transactionDetail.getCardExpiryAt()) && !transactionDetail.getCardExpiryAt().isBlank();
                if (check && checkName.equalsIgnoreCase("vpa"))
                    check = Objects.nonNull(transactionDetail.getVpaId()) && !transactionDetail.getVpaId().isBlank();
            }
            return check;
        };
    }
}
