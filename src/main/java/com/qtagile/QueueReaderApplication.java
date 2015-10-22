package com.qtagile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QueueReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueReaderApplication.class, args);
    }
}
