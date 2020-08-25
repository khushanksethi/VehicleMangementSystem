package com.example.VehicleManagementSystem.service;


import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.VehicleManagementSystem.model.InsuranceModel;
import com.example.VehicleManagementSystem.model.UserModel;
import com.example.VehicleManagementSystem.model.VehicleModel;
import com.example.VehicleManagementSystem.repository.InsuranceRepository;
import com.example.VehicleManagementSystem.repository.UserRepository;
import com.example.VehicleManagementSystem.repository.VehicleRepository;

@Service
public class InsuranceService {

	@Autowired
	InsuranceRepository insuranceRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public InsuranceModel saveOrUpdateInsurance(InsuranceModel insurance, String plateNumber, Model m){
		try {
			if(insuranceRepo.findInsuranceByVehicle(plateNumber)!=null)	{	//check if there is an existing insurance mapped to vehicle plate no. 
				InsuranceModel existingInsurance=insuranceRepo.findInsuranceByVehicle(plateNumber);		//retrieve existing insurance details mapped to vehicle plate no.
				VehicleModel vehicle=vehicleRepo.findVehicle(plateNumber);	//retrieve vehicle details details from DB
				insurance.setVehicle(vehicle);	//update vehicle details to new insurance
				if(insuranceRepo.save(insurance)!=null) {	//update insurance in DB
					String updateYes="Data updated";
					m.addAttribute("insuranceUpdation", updateYes);
					
					vehicle.setInsurance(insurance);	//update vehicle insurance details 
					if(vehicleRepo.save(vehicle)!=null) { 
						m.addAttribute("vehicleInsuranceUpdation", updateYes);
						
						insuranceRepo.delete(existingInsurance);	//delete previous insurance from DB
					}
					else {
						String updateNo="Data not updated";		
						m.addAttribute("vehicleInsuranceUpdation", updateNo);	//vehicle insurance not updated
					}
				}
				else {
					String updateNo="Data not updated";
					m.addAttribute("insuranceUpdation", updateNo);
				}
			}
			else {
				VehicleModel vehicle=vehicleRepo.findVehicle(plateNumber);	//retrieve vehicle details details from DB
				insurance.setVehicle(vehicle);	//save vehicle details to new insurance
				if(insuranceRepo.save(insurance)!=null) {	//save new insurance if insurance details not found in DB
					String saveYes="Data saved";
					m.addAttribute("insuranceCreation", saveYes);
					
					vehicle.setInsurance(insurance);	//save vehicle insurance details in DB
					if(vehicleRepo.save(vehicle)!=null) { 
						m.addAttribute("vehicleInsurancemapping", saveYes);
					}
					else {
						String updateNo="Data not save";		
						m.addAttribute("vehicleInsurancemapping", updateNo);	//vehicle insurance details not saved in DB
					}
				}
				else {
					String saveNo="Data not saved";		//no new insurance created
					m.addAttribute("insuranceCreation", saveNo);
				}	
			}	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return insurance;
	}

	public InsuranceModel TransferInsurance(String insuranceNumber, int newUserId, Model m) {
		
		UserModel currentUser= userRepo.findUserByInsurance(insuranceNumber);	// retrieve current user details based on insurance no.
		InsuranceModel insurance= insuranceRepo.findInsurance(insuranceNumber);	//retrieve insurance details based on insurance no.
		currentUser.getInsurance().remove(insurance);	//delete insurance details mapped to current user
		try {
			if(userRepo.save(currentUser)!=null) {
				String currentuserYes="data deleted";
				m.addAttribute("currentUserInsuranceDeletion", currentuserYes);
			}
			else {
				String currentuserNo="data not updated";
				m.addAttribute("currentUserInsuranceDeletion", currentuserNo);
			}
			
			UserModel newUser= userRepo.findById(newUserId);	//retrieve user details to whom insurance ownership needs to be transfered
			insurance.setUser(newUser);		//update new user details mapped to insurance
			if(insuranceRepo.save(insurance)!=null) {
				String userYes="data updated";
				m.addAttribute("insuranceOwnerUpdation", userYes);
				
				Set<InsuranceModel> i=new Set<InsuranceModel>() {
					
					@Override
					public boolean add(InsuranceModel insurance) {	//add insurance ownership to new user
						// TODO Auto-generated method stub
						return true;
					}
					
					@Override
					public int size() {
						// TODO Auto-generated method stub
						return 0;
					}
		
					@Override
					public boolean isEmpty() {
						// TODO Auto-generated method stub
						return false;
					}
		
					@Override
					public boolean contains(Object o) {
						// TODO Auto-generated method stub
						return false;
					}
		
					@Override
					public Iterator<InsuranceModel> iterator() {
						// TODO Auto-generated method stub
						return null;
					}
		
					@Override
					public Object[] toArray() {
						// TODO Auto-generated method stub
						return null;
					}
		
					@Override
					public <T> T[] toArray(T[] a) {
						// TODO Auto-generated method stub
						return null;
					}
		
					@Override
					public boolean remove(Object o) {
						// TODO Auto-generated method stub
						return false;
					}
		
					@Override
					public boolean containsAll(Collection<?> c) {
						// TODO Auto-generated method stub
						return false;
					}
		
					@Override
					public boolean addAll(Collection<? extends InsuranceModel> c) {
						// TODO Auto-generated method stub
						return false;
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
					public void clear() {
						// TODO Auto-generated method stub
						
					}
					
				};
				
				newUser.setInsurance(i);	
				if(userRepo.save(newUser)!=null) {
					String insuranceYes="data updated";
					m.addAttribute("userInsuranceUpdation", insuranceYes);
				}
				else {
					String insuranceNo="data not updated";
					m.addAttribute("userInsuranceUpdation", insuranceNo);
				}
			}
			else {
				String userNo="data not updated";
				m.addAttribute("insuranceOwnerUpdation", userNo);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return insurance;
	}

	
}