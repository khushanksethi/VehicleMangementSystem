package com.example.VehicleManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.VehicleManagementSystem.model.InsuranceModel;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceModel, Integer> {
		
	//retrieve Insurance details using Insurance no.
	@Query("FROM InsuranceModel v WHERE v.insuranceNumber=:insuranceNumber")
	public InsuranceModel findInsurance(String insuranceNumber);
	
	//delete Insurance details using Insurance no.
	@Query("DELETE i FROM InsuranceModel i WHERE i.insuranceNumber=:insuranceNumber")
	public InsuranceModel deleteInsurance(String insuranceNumber);
		
	//retrieve Insurance details mapped to vehicle using vehicle plate no.
	@Query("FROM InsuranceModel WHERE vehicle.plateNumber=:plateNumber")
	public InsuranceModel findInsuranceByVehicle(String plateNumber);
	
	
}
