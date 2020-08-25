package com.example.VehicleManagementSystem.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.VehicleManagementSystem.model.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Integer> {
		
	//retrieve vehicle details using vehicle plate no.
	@Query("FROM VehicleModel WHERE plateNumber=:plateNumber")
	public VehicleModel findVehicle(String plateNumber);
	
	//delete vehicle using vehicle plate no.
	@Query("DELETE v FROM VehicleModel v WHERE v.plateNumber=:plateNumber")
	public VehicleModel deleteVehicle(String plateNumber);
	
	//retrieve details of Set of vehicle registered with given Authority 
	@Query("FROM VehicleModel WHERE Authority.id=:AuthId")
	public Set<VehicleModel> getVehiclesByAuthId(int AuthId);
	
	//retrieve details of Set of vehicle owned by given User 
	@Query("FROM VehicleModel WHERE user.id=: userId")
	public Set<VehicleModel> getVehiclesByUser(int userId);
	
}
