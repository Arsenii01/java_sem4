package com.example.pw17.phones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phones")
public class PhoneController {

	private final PhoneService phoneService;

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

	@GetMapping("/{id}")
	public Phone findById(@PathVariable Long id) {
		return phoneService.findById(id);
	}

	@GetMapping("/search")
	public List<PhoneDTO> searchStudents(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "creationYear", required = false) String creationYear
	) {
		return phoneService.findByFilters(name, creationYear);
	}
}
