package demo;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.messaging.servicebus.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    static String connectionString = "Endpoint=sb://seed-bus-pr.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=/uO585mW9T1CzLEo4RxVZb2C4sBVPltFt6YS7V16ljc=";
    static String queueName = "queuesampleseed"; 

    public static void main( String[] args ) throws JsonProcessingException
    {
        //sendMessageBatch();
        sendMessage();
    }

    static void sendMessageBatch()
    {
        // create a Service Bus Sender client for the queue 
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        // Creates an ServiceBusMessageBatch where the ServiceBus.
        ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();        

        // create a list of messages
        List<ServiceBusMessage> listOfMessages = createMessages();

        // We try to add as many messages as a batch can fit based on the maximum size and send to Service Bus when
        // the batch can hold no more messages. Create a new batch for next set of messages and repeat until all
        // messages are sent.        
        for (ServiceBusMessage message : listOfMessages) {
            if (messageBatch.tryAddMessage(message)) {
                continue;
            }

            // The batch is full, so we create a new batch and send the batch.
            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + queueName);

            // create a new batch
            messageBatch = senderClient.createMessageBatch();

            // Add that message that we couldn't before.
            if (!messageBatch.tryAddMessage(message)) {
                System.err.printf("Message is too large for an empty batch. Skipping. Max size: %s.", messageBatch.getMaxSizeInBytes());
            }
        }

        if (messageBatch.getCount() > 0) {
            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + queueName);
        }

        //close the client
        senderClient.close();
    }

    static List<ServiceBusMessage> createMessages()
    {
        // create a list of messages and return it to the caller
        ServiceBusMessage[] messages = {
                new ServiceBusMessage("First message"),
                new ServiceBusMessage("Second message"),
                new ServiceBusMessage("Third message")
        };
        return Arrays.asList(messages);
    }

    static void sendMessage() throws JsonProcessingException
    {
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
    }
}
