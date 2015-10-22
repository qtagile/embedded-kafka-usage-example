package com.qtagile.message;

import com.qtagile.kafka.EmbeddedKafkaServer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Component
public class MessageProviderImpl implements MessageProvider {
    private static final String TOPIC = "test";
    private final EmbeddedKafkaServer embeddedKafkaServer = new EmbeddedKafkaServer();

    @PostConstruct
    public void init(){
        embeddedKafkaServer.start();
    }

    @PreDestroy
    public void shutdown(){
        embeddedKafkaServer.stop();
    }

    @Override
    public void send(String key, String value) {
        embeddedKafkaServer.send(TOPIC, key, value);
    }

    @Override
    public Optional<String> read() {
        return embeddedKafkaServer.read(TOPIC);
    }
}
