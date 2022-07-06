package sdkms.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sdkms.example.demo.User;

@RestController
public class HomeController {


    @GetMapping("/messages")
    public String GetMessage() throws JsonProcessingException {
        
        String queueName = "queuesampleseed"; 
        String tenantId = "72f988bf-86f1-41af-91ab-2d7cd011db47";
        String clientId = "a4624d1c-dc11-4336-afc6-6dfad792302c";
        String clientSecret = "rXx8Q~jOuVsSxAIW-.JphSQ8MhWz6GWKU41AXcEI";
        String endpoint = "seed-bus-pr.servicebus.windows.net";

        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
            .tenantId(tenantId)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();
            
        // create a Service Bus Sender client for the queue 
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                //.connectionString(connectionString)
                .credential(endpoint, credential)
                .sender()
                .queueName(queueName)
                .buildClient();

        // send one message to the queue
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Wilson");
        String userJson = mapper.writeValueAsString(user);

        ServiceBusMessage msg = new ServiceBusMessage(userJson);
        msg.setContentType("application/json");
        senderClient.sendMessage(msg);
        System.out.println("Sent a single message to the queue: " + queueName);        
        
        return "ok spring boot vs code";


    }
}