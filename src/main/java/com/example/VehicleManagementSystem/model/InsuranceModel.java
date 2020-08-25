package com.example.VehicleManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "insurances")
public class InsuranceModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String provider;
	
	@Column(name = "insurance_number")
	private String insuranceNumber;
	
	@Column(name = "valid_date")
	private String insuranceValidDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	
	@OneToOne
	@JoinColumn(name="vehicle_id")
	private VehicleModel vehicle;
	
	public InsuranceModel() {}

	public InsuranceModel(String provider, String insuranceNumber, String insuranceValidDate, UserModel user,
			VehicleModel vehicle) {
		super();
		this.provider = provider;
		this.insuranceNumber = insuranceNumber;
		this.insuranceValidDate = insuranceValidDate;
		this.user = user;
		this.vehicle = vehicle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getInsuranceValidDate() {
		return insuranceValidDate;
	}

	public void setInsuranceValidDate(String insuranceValidDate) {
		this.insuranceValidDate = insuranceValidDate;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	
	
}