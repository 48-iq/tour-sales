package dev.ivanov.tour_sales.services;

import org.springframework.stereotype.Service;

@Service
public class IdService {
    public String generate() {
        return String.valueOf(System.currentTimeMillis());
    }
}
