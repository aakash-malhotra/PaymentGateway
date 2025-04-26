package com.personal.bank;

import com.personal.PaymentGateway;
import com.personal.transaction.TransactionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pg/bank")
public class BankController {

    @Autowired
    PaymentGateway paymentGateway;

    @PostMapping("/makePayment")
    public boolean makePayment(@RequestParam TransactionDetail transactionDetail) {
        return paymentGateway.makePayment(transactionDetail);
    }



    @GetMapping("/metrics")
    public String removeClientPayModes() {
        return paymentGateway.showDistribution();
    }
}
