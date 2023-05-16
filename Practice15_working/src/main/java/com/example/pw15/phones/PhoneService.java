package com.example.pw15.phones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

	@Autowired
	private PhoneRepo phoneRepo;

	public void save(Phone phone) {
		phoneRepo.save(phone);
	}

	public void deleteById(Long id) {
		phoneRepo.deleteById(id);
	}

	public List<Phone> findAll() {
		return phoneRepo.findAll();
	}
}
