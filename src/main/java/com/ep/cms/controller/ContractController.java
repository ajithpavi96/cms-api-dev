package com.ep.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ep.cms.dto.ContractResourceInputDto;
import com.ep.cms.dto.ContractResourceOutputDto;
import com.ep.cms.model.Contract;
import com.ep.cms.service.ContractServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/cms")
public class ContractController {
	
	@Autowired
	private ContractServiceImpl contractServiceImpl;
	
	@GetMapping("/getContracts")
	public List<ContractResourceOutputDto> getContracts() {
		
		return contractServiceImpl.getContracts();
	}
	
	@PostMapping("/saveContract")
	public Contract saveContracts(@RequestBody ContractResourceInputDto contractResourceInputDto) {
		Contract saveContracts = contractServiceImpl.saveContracts(contractResourceInputDto);
		return saveContracts;
	}
	

}
