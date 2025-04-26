package com.personal.paymode;

import com.personal.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg/paymode")
public class PayModeController {

    @Autowired
    ClientService clientService;

    @GetMapping("/all")
    public List<PayMode> listPayModes(@RequestParam(required = false) String clientName) {
        return clientService.listSupportedPayModes(clientName);
    }

    @PutMapping("/addPayModes")
    public PayMode addClientPayModes(@RequestParam(required = false) String clientName, @RequestParam String payModeName, @RequestParam(required = false) List<String> payModeRequires) {
        return clientService.addSupportForPayMode(clientName, payModeName, payModeRequires);
    }

    @DeleteMapping("/removePayModes")
    public PayMode removeClientPayModes(@RequestParam(required = false) String clientName, @RequestParam(required = false) String payModeName) {
        return clientService.removePayMode(clientName, payModeName);
    }
}
