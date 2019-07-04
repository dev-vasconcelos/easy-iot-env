package br.com.maincore.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.maincore.mqtt.MQTT;

@RestController
@RequestMapping("/api/easy-iot-env")
public class RequestController {
	
	MQTT mqtt = new MQTT();
	
	@GetMapping("/publish")
	public ResponseEntity<?> test(@RequestParam("message") String message, @RequestParam("topic") String topic) {
		try {
			System.out.println("cheguei aqu");
			
			mqtt.publishMessage(topic, message);

			return ResponseEntity.ok(true);
		}catch (InternalError | Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
	
}
