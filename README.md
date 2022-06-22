# Azure CLI

az servicebus namespace create --resource-group bus-poc --name seed-bus-pr --location westus --tags tag1=departamentoA tag2=Company --sku Premium

az servicebus queue create --resource-group bus-poc --namespace-name seed-bus-pr --name queueSampleSeed 

# Exemplo com Java Console

1. https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-queues

# Exemplo Spring Boot

1. https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-service-bus
1. https://start.spring.io/


