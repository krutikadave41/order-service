# order-service
This service pushes data to a Kafka Broker with a REST API Endpoint.

## Steps to use this service:
- Clone this repository
- Run maven clean build install
- Run Kafka broker at 9092
- Start this service as a spring boot application
- Hit REST endpoint with order details as follows:
URL: localhost:8081/api/v1/order
Method Type: POST
Sample Request: 
{
    "name": "IPad Mini",
    "qty": 1,
    "price": 34000
}

Then OrderProducerService will send the order details to the Kafka broker through KafkaTemplate
