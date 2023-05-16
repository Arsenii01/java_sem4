package com.example.pw17.manufactures;

import com.example.pw17.phones.Phone;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "manufactures")
public class Manufacture {

	private String name;
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_id")
	private Phone phone;

	public Manufacture() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Manufacture{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", phone=" + phone +
				", id=" + id +
				'}';
	}
}
