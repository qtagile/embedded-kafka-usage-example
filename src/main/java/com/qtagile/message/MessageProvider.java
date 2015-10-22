package com.qtagile.message;

import java.util.Optional;

public interface MessageProvider {
    void send(String key, String value);

    Optional<String> read();
}
