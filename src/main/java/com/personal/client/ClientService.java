package com.personal.client;

import com.personal.Memory;
import com.personal.paymode.PayMode;
import com.personal.paymode.PayModeFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ClientService {

    public Client getClient(String name) {
        return Memory.CLIENTS.get(name);
    }

    public Client onBoardClient(String name) {
        Client client = new Client(name);
        Memory.CLIENTS.put(name, client);
        return client;
    }

    public Client removeClient(String name) {
        return Memory.CLIENTS.remove(name);
    }

    public boolean hasClient(String name) {
        return Objects.nonNull(getClient(name));
    }

    public List<PayMode> listSupportedPayModes(String name) {
        if(name != null) {
            Client client = getClient(name);
            if (Objects.nonNull(client)) {
                return client.getSupportedPayModes();
            }
        }
        return Memory.PAY_MODES.values().stream().toList();
    }

    public PayMode addSupportForPayMode(String clientName, String payModeName, List<String> checkFor) {
        PayMode payMode = Memory.PAY_MODES.get(payModeName);
        if(payMode == null) {
            payMode = new PayModeFactory().name(payModeName).isValidWhen(Memory.checkForDetailsInPayment(checkFor)).build();
        }
        if(clientName != null) {
            Client client = getClient(clientName);
            if (Objects.nonNull(client)) {
                client.addPayMode(payMode);
            }
        }
        if(!Memory.PAY_MODES.containsKey(payMode.getName())) {
            Memory.PAY_MODES.put(payMode.getName(), payMode);
        }
        return payMode;
    }

    public PayMode removePayMode(String name, String payModeName) {
        PayMode payMode = Memory.PAY_MODES.get(payModeName);
        if(name != null) {
            Client client = getClient(name);
            if (Objects.nonNull(client)) {
                client.removePayMode(payMode);
            }
        } else {
            for (Map.Entry<String, Client> stringClientEntry : Memory.CLIENTS.entrySet()) {
                stringClientEntry.getValue().removePayMode(payMode);
            }
            Memory.PAY_MODES.remove(payModeName);
        }
        return payMode;
    }
}
