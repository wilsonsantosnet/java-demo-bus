# Azure CLI

## Criar uma instancia
```
az servicebus namespace create --resource-group bus-poc --name seed-bus-pr --location westus --tags tag1=departamentoA tag2=Company --sku Premium
```

## Criar uma fila
```
az servicebus queue create --resource-group bus-poc --namespace-name seed-bus-pr --name queueSampleSeed 
```

## Criar um tópico

```
az servicebus topic create --resource-group pocs --namespace-name seed-bus-p --name topicSampleSeed 
```

## Assinar um tópico
```
az servicebus topic subscription create --resource-group pocs --namespace-name seed-bus-p --topic-name topicSampleSeed --name consumer1
```


# Documentação Azure CLI

1. [cli-net-core-principais-comandos](https://wilsonsantosnet.medium.com/cli-net-core-principais-comandos-d728b0e16634)

# Exemplos com Java Console (pacote azure-messaging-servicebus)

1. [demoSender](https://github.com/wilsonsantosnet/java-demo-bus/tree/main/demoSender)
1. [demoreciver](https://github.com/wilsonsantosnet/java-demo-bus/tree/main/demoreciver)
1. [service-bus-java-how-to-use-queues](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-queues)

## Azure Active Directory (RBAC)

1. [service-bus-authentication-and-authorization](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-authentication-and-authorization)
1. [create-a-service-bus-client-using-microsoft-identity-platform-formerly-azure-active-directory](https://docs.microsoft.com/en-us/java/api/overview/azure/messaging-servicebus-readme?view=azure-java-stable#create-a-service-bus-client-using-microsoft-identity-platform-formerly-azure-active-directory)


# Exemplo Spring Boot (pacote spring-cloud-azure-starter-servicebus-jms)

1. [singlewebappbus](https://github.com/wilsonsantosnet/java-demo-bus/tree/main/springboot/singlewebappbus)
1. [configure-spring-boot-starter-java-app-with-azure-service-bus](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-service-bus)
1. [start.spring.io](https://start.spring.io)


# Exemplo Spring Boot (pacote azure-messaging-servicebus e azure-identity)

1. [springboot-sdkms](https://github.com/wilsonsantosnet/java-demo-bus/tree/main/springboot-sdkms)

# ServiceBusExplorer

1. [ServiceBusExplorer](https://github.com/paolosalvatori/ServiceBusExplorer)


