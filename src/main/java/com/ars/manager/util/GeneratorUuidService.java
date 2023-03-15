package com.ars.manager.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneratorUuidService {

    public String generate() {
        return UUID.randomUUID().toString();
    }
}
