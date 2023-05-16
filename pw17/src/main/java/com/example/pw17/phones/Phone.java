package com.example.pw17.phones;

import com.example.pw17.manufactures.Manufacture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "phone")
	private List<Manufacture> manufactures = new ArrayList<>();

	private String name;

	@Column(name = "creation_year")
	private int creationYear;

	public Phone() {}

	public String getName() {
		return name;
	}

	public int getCreationYear() {
		return creationYear;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreationYear(int creationYear) {
		this.creationYear = creationYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Manufacture> getManufactures() {
		return manufactures;
	}

	public void setManufactures(
		List<Manufacture> manufactures
	) {
		this.manufactures = manufactures;
	}

	@Override
	public String toString() {
		return "Phone{" +
				"id=" + id +
				", manufactures=" + manufactures +
				", name='" + name + '\'' +
				", creationYear=" + creationYear +
				'}';
	}
}
