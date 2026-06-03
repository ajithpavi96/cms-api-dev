package com.ep.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ep.cms.dto.ContractResourceInputDto;
import com.ep.cms.dto.ContractResourceOutputDto;
import com.ep.cms.model.Contract;

public interface ContractService {
	
	public List<ContractResourceOutputDto> getContracts();
	
	public Contract saveContracts(ContractResourceInputDto contractResourceInputDto);
	
}
