package com.example.DeviceManagement.producer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

@Service
public class SmartMeterSimulator {
    private String exchangeName;
    private String deviceId;

//    @Value("${SENSOR_FILE_PATH}")
//    private String sensorFilePath;

    public SmartMeterSimulator() {

    }

    public void loadConfig(String simulareId) {
      //  try (InputStream input =  new FileInputStream("/app/config" + simulareId + ".properties")) {
        try (InputStream input =  new FileInputStream("config1.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            this.exchangeName = "device_change_exchange";
            this.deviceId = prop.getProperty("device.id");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load configuration.");
        }
    }

    @Async
    public void start() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(exchangeName, "topic");

            BufferedReader br = new BufferedReader(new FileReader("sensor.csv"));

            String line;

            while ((line = br.readLine()) != null) {

                long timestamp = System.currentTimeMillis();

                JSONObject jsonMessage = new JSONObject();
                jsonMessage.put("timestamp", timestamp);
                jsonMessage.put("device_id", deviceId);
                jsonMessage.put("measurement_value", Double.parseDouble(line));

                String routingKey = "device." + deviceId;
                channel.basicPublish(exchangeName, routingKey, null, jsonMessage.toString().getBytes());

                System.out.println("Sent message: " + jsonMessage);

                Thread.sleep(2000);     //2sec
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

