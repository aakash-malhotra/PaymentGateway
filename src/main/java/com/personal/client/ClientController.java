package com.personal.client;

import com.personal.Memory;
import com.personal.paymode.IPayMode;
import com.personal.paymode.PayMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/isPresent")
    public boolean hasClient(@RequestParam String name) {
        return clientService.hasClient(name);
    }

    @GetMapping("/info")
    public Client getClient(@RequestParam String name) {
        return clientService.getClient(name);
    }

    @PostMapping("/add")
    public Client addClient(@RequestParam String name) {
        return clientService.onBoardClient(name);
    }

    @DeleteMapping("/remove")
    public Client removeClient(@RequestParam String name) {
        return clientService.removeClient(name);
    }
}
