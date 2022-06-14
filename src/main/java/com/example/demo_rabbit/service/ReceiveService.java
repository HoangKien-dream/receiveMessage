package com.example.demo_rabbit.service;

import com.example.demo_rabbit.reposi.RepositoryOrder;
import com.example.demo_rabbit.entity.Order;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class ReceiveService {
    private final static String QUEUE_NAME = "hello";
    @Autowired
    RepositoryOrder repositoryOrder;
    public void getMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            Order order = gson.fromJson(message,Order.class);
            System.out.println(" [x] Received '" + order.getProduct() + "'");
            repositoryOrder.save(order);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
