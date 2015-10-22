package com.qtagile.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    private static final String KEY = "test-key";

    private final MessageProvider messageProvider;
    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public MessageServiceImpl(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    @Scheduled(fixedRate = 1000)
    public void write(){
        while(counter.get() < 10){
            messageProvider.send(KEY, String.format("message number %d", counter.getAndIncrement()));
        }
    }

    @Override
    @Scheduled(initialDelay = 500, fixedRate = 1000)
    public void read() {
        logger.info("read message: {}", messageProvider.read().orElse("DEFAULT"));
    }
}
