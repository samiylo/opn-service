package com.opn.opn_service.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NeshaService {

    private static final Logger logger = LoggerFactory.getLogger(NeshaService.class);

    private final List<String> messages;

    public NeshaService() {
        this.messages = List.of(
                "I have an appointment",
                "I'm going to WWE next weekend",
                "Who wanna get lit"
        );
    }

    private final AtomicInteger counter = new AtomicInteger(0);

    public String getMessageFromNesha() {
        int index = counter.getAndUpdate(i -> (i + 1) % messages.size());
        return messages.get(index);
    }
}
