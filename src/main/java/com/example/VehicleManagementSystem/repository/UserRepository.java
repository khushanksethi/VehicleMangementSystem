package com.example.VehicleManagementSystem.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.VehicleManagementSystem.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	
	public UserModel findById(int id);
	
	//retrieve details of set of users registered with given authority 
	@Query("SELECT u FROM UserModel u WHERE authority.id=:authId")
	public Set<UserModel> getUsersByAuthId(int authId);
	
	//retrieve user details based on vehicle plate no.
	@Query("SELECT user FROM VehicleModel v WHERE v.plateNumber=:plateNumber")
	public UserModel findUserByVehicle(String plateNumber);
	
	//retrieve user details based on insurance no.
	@Query("SELECT user FROM InsuranceModel i WHERE i.insuranceNumber=:insuranceNumber")
	public UserModel findUserByInsurance(String insuranceNumber);

	
}
