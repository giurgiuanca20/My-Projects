package com.example.MonitoringAndCommunicationMicroservice;

import com.example.MonitoringAndCommunicationMicroservice.services.MonitoringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MonitoringAndCommunicationMicroserviceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonitoringAndCommunicationMicroserviceApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(MonitoringAndCommunicationMicroserviceApplication.class, args);
		MonitoringService monitoring = context.getBean(MonitoringService.class);
		monitoring.listenToDeviceChanges();
	}

}
