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
@Table(name = "vehicles")
public class VehicleModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String source; 
	
	@Column
	private String category;
	
	@Column(name = "plate_number")
	private String plateNumber;
	
	@Column
	private String manufacture; 
	
	@Column
	private String type;
	
	@Column
	private String color;
	
	@Column(name = "registration_date")
	private String registrationDate;
	
	@Column(name = "pending_fines")
	private String pendingFines;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	
	@ManyToOne
	@JoinColumn(name = "authority_id")
	private AuthorityModel authority;
	
	@OneToOne(mappedBy = "vehicle")
	private InsuranceModel insurance;
	
	public VehicleModel() {
		
	}

	public VehicleModel(String source, String category, String plateNumber, String manufacture, String type,
			String color, String registrationDate, String pendingFines, UserModel user, AuthorityModel authority,
			InsuranceModel insurance) {
		super();
		this.source = source;
		this.category = category;
		this.plateNumber = plateNumber;
		this.manufacture = manufacture;
		this.type = type;
		this.color = color;
		this.registrationDate = registrationDate;
		this.pendingFines = pendingFines;
		this.user = user;
		this.authority = authority;
		this.insurance = insurance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPendingFines() {
		return pendingFines;
	}

	public void setPendingFines(String pendingFines) {
		this.pendingFines = pendingFines;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public AuthorityModel getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityModel authority) {
		this.authority = authority;
	}

	public InsuranceModel getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceModel insurance) {
		this.insurance = insurance;
	}
	
	
}