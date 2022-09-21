package com.example.order.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.base.dto.OrderEvent;

@Service
public class OrderProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducerService.class);
	private NewTopic topic;
	
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	public OrderProducerService(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(OrderEvent orderEvent) {
		LOGGER.info(String.format("Sending message to the broker"));
		Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		
		kafkaTemplate.send(message);
	}
}
