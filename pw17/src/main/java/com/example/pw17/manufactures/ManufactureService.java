package com.example.pw17.manufactures;

import com.example.pw17.phones.Phone;
import com.example.pw17.phones.PhoneRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufactureService {

	private final ManufactureRepo manufactureRepo;

	private final PhoneRepo phoneRepo;

	public ManufactureService(
			ManufactureRepo manufactureRepo, PhoneRepo phoneRepo
	) {
		this.manufactureRepo = manufactureRepo;
		this.phoneRepo = phoneRepo;
	}

	public void save(Manufacture manufacture, Long id) {
		manufacture.setPhone(phoneRepo.findById(id));
//		iManufactureRepo.save(manufacture);
		manufactureRepo.save(manufacture);
	}

	public List<Manufacture> findAll() {
		return manufactureRepo.findAll();
	}

	public void deleteById(Long id) {
		manufactureRepo.deleteById(id);
	}

	public Manufacture findById(Long id) {
		return manufactureRepo.findById(id);
	}

	public Phone getPhone(Long id) {
		return findById(id).getPhone();
	}

	public List<ManufactureDTO> findByFilters(String name, String address) {
		return manufactureRepo
				.findByFilters(name, address).stream()
				.map(ManufactureDTO::withPhone).toList();
	}
}
