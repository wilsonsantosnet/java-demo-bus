package com.example.demoservicebus.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class QueueReceiveController {

    private static final String QUEUE_NAME = "queuesampleseed";

    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(User user) {
        logger.info("Received message: {}", user.getName());
    }


    // @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    // public void receiveMessage(String payload) throws JsonMappingException, JsonProcessingException {
        
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     User dto = objectMapper.readValue(payload, User.class);
    //     logger.info("Received message: {}", dto.getName());
    // }

    // @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    // public void receiveMessage2(User2 value) {
    //     logger.info("Received message: {}", value.Name);
    // }
}