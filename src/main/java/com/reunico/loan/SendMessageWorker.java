package com.reunico.loan;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class SendMessageWorker {

    private final ZeebeClient zeebeClient;

    public SendMessageWorker(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @JobWorker(type = "sendMessage")
    public void sendMessage(final ActivatedJob job) {

        zeebeClient.newPublishMessageCommand()
                .messageName("Message_startAssessmentProcess")
                .correlationKey("123")
                .send();

        System.out.println("Message sent");
    }
}
