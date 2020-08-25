package com.example.VehicleManagementSystem.service;


import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.VehicleManagementSystem.model.UserModel;
import com.example.VehicleManagementSystem.model.VehicleModel;
import com.example.VehicleManagementSystem.repository.UserRepository;
import com.example.VehicleManagementSystem.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public VehicleModel saveOrUpdateVehicle(VehicleModel vehicle, Model m){
		try {
			if(vehicleRepo.findVehicle(vehicle.getPlateNumber()) != null ) {	//retrieve vehicle details using vehicle plate no. from DB
				if(vehicleRepo.save(vehicle)!=null) {	//update vehicle details in DB based on given inputs
				String yes="Data updated";
				m.addAttribute("updation", yes);
				}
				else {
					String no="Data not updated";
					m.addAttribute("updation", no);
				}
			}
			else {
				if(vehicleRepo.save(vehicle)!=null) {	//create new vehicle if vehicle details not found in DB
					String yes="Data saved";
					m.addAttribute("signup", yes);
				}
				else {
					String no="Data not saved";
					m.addAttribute("signup", no);
				}	
			}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return vehicle;
	}
	
	public VehicleModel TransferVehicle(String plateNumber, int newUserId, Model m){
		UserModel currentUser= userRepo.findUserByVehicle(plateNumber);	// retrieve current user details based on vehicle plate no.
		VehicleModel vehicle= vehicleRepo.findVehicle(plateNumber);	//retrieve vehicle details based on vehicle plate no.
		currentUser.getVehicle().remove(vehicle);	//delete vehicle information from current user
		try {
			if(userRepo.save(currentUser)!=null) {
				String currentuserYes="data deleted";
				m.addAttribute("currentUserInsuranceDeletion", currentuserYes);
			}
			else {
				String currentuserNo="data not updated";
				m.addAttribute("currentUserInsuranceDeletion", currentuserNo);
			}
			
			UserModel newUser= userRepo.findById(newUserId);	//retrieve user details to whom vehicle ownership needs to be transfered
			vehicle.setUser(newUser);	//update new user details mapped to vehicle
			if(vehicleRepo.save(vehicle)!=null) {
				String userYes="data updated";
				m.addAttribute("vehicleOwnerUpdation", userYes);
			
				Set<VehicleModel> v=new Set<VehicleModel>() {
					
					@Override
					public <T> T[] toArray(T[] a) {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Object[] toArray() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public int size() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public boolean retainAll(Collection<?> c) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean removeAll(Collection<?> c) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean remove(Object o) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public Iterator<VehicleModel> iterator() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public boolean isEmpty() {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean containsAll(Collection<?> c) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean contains(Object o) {
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public void clear() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public boolean addAll(Collection<? extends VehicleModel> c) {	
						// TODO Auto-generated method stub
						return false;
					}
					
					@Override
					public boolean add(VehicleModel vehicle) {		//add vehicle ownership to new user
						// TODO Auto-generated method stub
						return true;
					}
				};
				
				newUser.setVehicle(v);	//add vehicle details to new user
				if(userRepo.save(newUser)!=null) {
					String insuranceYes="data updated";
					m.addAttribute("userVehicleUpdation", insuranceYes);
				}
				else {
					String insuranceNo="data not updated";
					m.addAttribute("userVehicleUpdation", insuranceNo);
				}
			}
			else {
				String userNo="data not updated";
				m.addAttribute("vehicleOwnerUpdation", userNo);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vehicle;
	}

}