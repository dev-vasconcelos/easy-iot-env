package br.com.maincore;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.maincore.mqtt.MQTT;

@SpringBootApplication
public class AppApiEasyIotEnvApplication {

	public static void main(String[] args) throws MqttSecurityException {
		SpringApplication.run(AppApiEasyIotEnvApplication.class, args);
	
		String host = "tcp://localhost:1883";
		String clientId = UUID.randomUUID().toString();
		
		MQTT mqtt = new MQTT();
		mqtt.connect(host, clientId);
		
	}

}
