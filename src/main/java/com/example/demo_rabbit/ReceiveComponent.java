package com.example.demo_rabbit;

import com.example.demo_rabbit.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ReceiveComponent {
    @Autowired
    ReceiveService receiveService;

    @Bean
    public void check() throws IOException, TimeoutException {
        receiveService.getMessage();
    }
}
