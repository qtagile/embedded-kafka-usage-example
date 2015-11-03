/**
 The MIT License (MIT)

 Copyright (c) 2015 QT Agile LTD http://qtagile.com/

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */
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
