package com.example.pw15.phones;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phones")
public class PhoneController {

	private PhoneService phoneService;

	public PhoneController(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	@PostMapping
	public void save(@RequestBody Phone phone) {
		phoneService.save(phone);
	}

	@DeleteMapping
	public void deleteById(@RequestParam Long id) {
		phoneService.deleteById(id);
	}

	@GetMapping
	public List<Phone> findAll() {
		return phoneService.findAll();
	}
}
