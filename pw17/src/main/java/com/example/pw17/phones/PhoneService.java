package com.example.pw17.phones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

	private final PhoneRepo phoneRepo;

	public PhoneService(PhoneRepo phoneRepo) {
		this.phoneRepo = phoneRepo;
	}

	public void save(Phone phone) {
		phoneRepo.save(phone);
	}

	public void deleteById(Long id) {
		phoneRepo.deleteById(id);
	}

	public List<Phone> findAll() {
		return phoneRepo.findAll();
	}

	public Phone findById(Long id) {
		return phoneRepo.findById(id);
	}

	public List<PhoneDTO> findByFilters(
			String name, String creationYear
	) {
		return phoneRepo.findByFilters(name, creationYear)
				.stream().map(PhoneDTO::withManufacture).toList();
	}
}
