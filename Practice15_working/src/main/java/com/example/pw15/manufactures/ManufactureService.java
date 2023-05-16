package com.example.pw15.manufactures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureService {

	@Autowired
	private ManufactureRepo manufactureRepo;

	public void save(Manufacture manufacture) {
		manufactureRepo.save(manufacture);
	}

	public Iterable<Manufacture> findAll() {
		return manufactureRepo.findAll();
	}

	public void deleteById(Long id) {
		manufactureRepo.deleteById(id);
	}
}
