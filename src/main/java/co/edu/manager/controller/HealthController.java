package co.edu.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edu-api/v1")
public class HealthController {
	
	@GetMapping("/health")
	public ResponseEntity<?> gethealth(){
		
		return new ResponseEntity("Health Ok", HttpStatus.OK);
	}

}
