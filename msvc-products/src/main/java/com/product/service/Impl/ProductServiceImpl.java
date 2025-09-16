package com.product.service.Impl;

import com.core.ProductCreateEvent;
import com.product.entity.Product;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreateEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Override
    public String createProduct(Product product) throws Exception {
        String productId = UUID.randomUUID().toString();
        //Persistir en la DB
        ProductCreateEvent productCreateEvent =
                new ProductCreateEvent(productId, product.getTitle(), product.getPrice(), product.getQuantity());

        //Parametros son ("nombre del topico", "clave del mensaje","el evento")

        // ****** ASINCRONO *****
//        CompletableFuture<SendResult<String, ProductCreateEvent>> future = kafkaTemplate.send("product-created-events-topic", productId, productCreateEvent);
//        future.whenComplete((result, ex) -> {
//            if (ex != null) {
//                logger.error("********Error al enviar mensaje{}", ex.getMessage());
//            } else {
//                logger.info("********Mensaje enviado correctamente {}", result.getRecordMetadata());
//            }
//        });
//        future.join(); //-> sincrono

        //****** SINCRONO
        logger.info("Antes de publicar el ProductCreateEvent");
        SendResult<String, ProductCreateEvent> result = kafkaTemplate.send("product-created-events-topic", productId, productCreateEvent).get();
        logger.info("Paticion {}", result.getRecordMetadata().partition());
        logger.info("Topic {}", result.getRecordMetadata().topic());
        logger.info("Offset {}", result.getRecordMetadata().offset());
        logger.info("********Retorna el producto id");
        return productId;
    }
}
