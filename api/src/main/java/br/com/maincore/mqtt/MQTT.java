package br.com.maincore.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.json.JSONObject;

public class MQTT implements MqttCallback {

	private String topic = "/app";
	private static MqttClient client;
	private JSONObject json;
	private IMqttClient publisher;

	public void connect(String host, String clientId) throws MqttSecurityException {
		try {
			client = new MqttClient(host, clientId);
			client.setCallback(this);
			client.connect();
			System.out.println("conectado");
			client.subscribe(topic, 2);
			System.out.println("inscrito");

		} catch (MqttException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("conection lost: " + cause);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {}

	public void publishMessage(String topic, String message) throws MqttException {

		byte[] payload = message.getBytes();
		MqttMessage msg = new MqttMessage(payload);

		msg.setQos(0);
		msg.setRetained(true);
		System.out.println("topic: " + topic);
		client.publish(topic, msg);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {}

}
