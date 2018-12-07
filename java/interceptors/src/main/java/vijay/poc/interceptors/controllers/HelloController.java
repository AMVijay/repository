package vijay.poc.interceptors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	@ResponseBody
	public ResponseEntity<String> index() {
		System.out.println("In index()");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Hello, World", HttpStatus.OK);
		return responseEntity;
	}
	
}
