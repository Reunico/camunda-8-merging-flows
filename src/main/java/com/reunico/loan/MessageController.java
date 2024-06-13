package com.reunico.loan;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final ZeebeClient zeebeClient;

    public MessageController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @PostMapping("/message/{message}/{applicationId}")
    void sendMessage(@PathVariable String message, @PathVariable String applicationId) {
        zeebeClient
                .newPublishMessageCommand()
                .messageName(message)
                .correlationKey(applicationId)
                .send()
        ;
    }
}
