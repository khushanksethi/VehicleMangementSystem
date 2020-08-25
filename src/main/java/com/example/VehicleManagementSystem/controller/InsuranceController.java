package com.example.VehicleManagementSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.VehicleManagementSystem.model.InsuranceModel;
import com.example.VehicleManagementSystem.repository.InsuranceRepository;
import com.example.VehicleManagementSystem.service.InsuranceService;

@RestController
public class InsuranceController {
	
	@Autowired
	InsuranceService insuranceService;
	
	@Autowired
	InsuranceRepository insuranceRepo;
	
	//create new insurance or update existing insurance details
	@GetMapping("/createInsurance")
	public @ResponseBody InsuranceModel createOrUpdateInsurance(@RequestBody InsuranceModel insurance, @RequestParam("plateNumber") String plateNumber, Model m) {
		return insuranceService.saveOrUpdateInsurance(insurance, plateNumber,m);
	}
	
	//retrieve insurance details using insurance no.
	@GetMapping("/getInsurance")
	public @ResponseBody InsuranceModel getInsurance(@RequestParam("insuranceNumber") String insuranceNumber) {
		return insuranceRepo.findInsurance(insuranceNumber);	
	}
	
	//delete insurance details using insurance no
	@GetMapping("/delinsurance")
	public @ResponseBody InsuranceModel deleteInsurance(@RequestParam("insuranceNumber") String insuranceNumber) {
		return insuranceRepo.deleteInsurance(insuranceNumber);	
	}
	
	//transfer insurance ownership to different user
	@GetMapping("/transferInsurances")
	public @ResponseBody InsuranceModel TransferInsurance(@RequestParam("insuranceNumber")String insuranceNumber, @RequestParam("newUserId") int newUserId, Model m) {
		return insuranceService.TransferInsurance(insuranceNumber, newUserId, m);
	}
}
