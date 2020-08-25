package com.example.VehicleManagementSystem.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "authorities")
public class AuthorityModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private String authority_name;
	
	@Column
	private String address;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "authority")
	private Set<UserModel> user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "authority")
	private Set<VehicleModel> vehicle;
	
	public AuthorityModel() {}

	public AuthorityModel(String authority_name, String address, Set<UserModel> user, Set<VehicleModel> vehicle) {
		super();
		this.authority_name = authority_name;
		this.address = address;
		this.user = user;
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<UserModel> getUser() {
		return user;
	}

	public void setUser(Set<UserModel> user) {
		this.user = user;
	}

	public Set<VehicleModel> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<VehicleModel> vehicle) {
		this.vehicle = vehicle;
	}

	
}