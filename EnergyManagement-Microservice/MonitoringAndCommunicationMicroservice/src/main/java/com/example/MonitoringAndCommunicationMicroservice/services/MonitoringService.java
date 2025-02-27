package com.example.MonitoringAndCommunicationMicroservice.services;

import com.example.MonitoringAndCommunicationMicroservice.entities.EnergyConsumption;
import com.example.MonitoringAndCommunicationMicroservice.repositories.EnergyConsumptionRepository;
import com.rabbitmq.client.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MonitoringService {
    private static Map<String, Double> hourlyConsumption = new HashMap<>();
    private static Map<String, Integer> messageCount = new HashMap<>();
    private static final int SAVE_THRESHOLD = 6;
    public int limit=5;
    private final static String EXCHANGE_NAME = "device_change_exchange";

    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;

    @Autowired
    private NotificationService notificationService;


    public void listenToDeviceChanges() throws Exception {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();


            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "device.#");

            System.out.println("Waiting for device changes...");

            channel.basicConsume(queueName, true, (cancelTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Received message: " + message);

                JSONObject json = new JSONObject(message);
                String deviceId = json.getString("device_id");
                double measurementValue = json.getDouble("measurement_value");
                long timestamp = json.getLong("timestamp");

                hourlyConsumption.put(deviceId, hourlyConsumption.getOrDefault(deviceId, 0.0) + measurementValue);

                messageCount.put(deviceId, messageCount.getOrDefault(deviceId, 0) + 1);

                if (messageCount.get(deviceId) >= SAVE_THRESHOLD) {

                    EnergyConsumption energyConsumption = new EnergyConsumption(deviceId, hourlyConsumption.get(deviceId) / 6, timestamp);
                    energyConsumptionRepository.save(energyConsumption);
                    messageCount.put(deviceId, 0);
                    if (hourlyConsumption.get(deviceId) / 6 > limit) {
                        String alertMessage = "ALERT! Consumption exceeded the limit for device: " + deviceId;
                        System.out.println(alertMessage);
                        notificationService.sendNotification(alertMessage,deviceId);
                    }
                    hourlyConsumption.put(deviceId, 0.0);

                }

            }, cancelTag -> {
                System.out.println("Error with the server");
            });

        synchronized (MonitoringService.class) {
            MonitoringService.class.wait();
        }
    }

    public List<EnergyConsumption> getEnergyConsumptionForDay(String deviceId, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        long startMillis = startOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
        long endMillis = endOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
        return energyConsumptionRepository.getEnergyConsumptionForDay(deviceId, startMillis, endMillis);
    }
}
