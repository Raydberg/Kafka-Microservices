package com.email.notification.events;

import com.core.ProductCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
//     Se uwa para indicar que es la clase destino de los mensajes entrantes del topic
//    Este metodo debe ser invocado cada vez que un nuevo mensaje se recibe de tema kafka especificando
//     el nombre del topic que se proporciona

@KafkaListener(topics = "product-created-events-topic")
public class ProductCreatedEventHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Este metodo se llama automaticamente en cuanto el consumer
     * kafka consuma un nuevo mensake del topic
     *
     */
    @KafkaHandler
    public void handle(ProductCreateEvent productCreatedEvent) {
        logger.info("Recibiendo nuevo evento {}", productCreatedEvent.getTitle());
    }

}
