package com.example.VehicleManagementSystem.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private String name; 
	private String password;
	private String gender;
	private String nationality;
	private String licence;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "authority_id")
	private AuthorityModel authority;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private Set<VehicleModel> vehicle;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private Set<InsuranceModel> insurance;
	
	public UserModel() {
		
	}

	public UserModel(String name, String password, String gender, String nationality, String licence,
			AuthorityModel authority, Set<VehicleModel> vehicle, Set<InsuranceModel> insurance) {
		super();
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.nationality = nationality;
		this.licence = licence;
		this.authority = authority;
		this.vehicle = vehicle;
		this.insurance = insurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public AuthorityModel getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityModel authority) {
		this.authority = authority;
	}

	public Set<VehicleModel> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Set<VehicleModel> vehicle) {
		this.vehicle = vehicle;
	}

	public Set<InsuranceModel> getInsurance() {
		return insurance;
	}

	public void setInsurance(Set<InsuranceModel> insurance) {
		this.insurance = insurance;
	}
	
	
	
}