package com.morohon.kafka.example.listener;

import com.morohon.kafka.example.dto.KafkaExampleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class KafkaExampleListener {

    private final AtomicBoolean flag = new AtomicBoolean(true);

    @KafkaListener(topics = "${kafka.example.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(KafkaExampleMessage kafkaExampleMessage, Acknowledgment acknowledgment) {

        log.debug("Consume message: {}", kafkaExampleMessage);

        if (flag.get()) {
            acknowledgment.acknowledge();
            flag.set(false);
        }

    }
}
