package com.ep.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.cms.dto.ContractResourceInputDto;
import com.ep.cms.dto.ContractResourceOutputDto;
import com.ep.cms.model.Contract;
import com.ep.cms.repository.ContractRepository;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	@Override
	public List<ContractResourceOutputDto> getContracts() {
		
		List<Contract> listOfContracts = contractRepository.findAll();
		List<ContractResourceOutputDto> list = new ArrayList<>();
		listOfContracts.forEach(c->{
			ContractResourceOutputDto contractResourceOutputDto = new ContractResourceOutputDto();
			contractResourceOutputDto.setContractId(c.getContractId());
			contractResourceOutputDto.setContractName(c.getContractName());
			contractResourceOutputDto.setContractDescription(c.getContractDescription());
			contractResourceOutputDto.setStartDate(c.getStartDate());
			contractResourceOutputDto.setEndDate(c.getEndDate());
			contractResourceOutputDto.setStatus(c.getStatus());
			contractResourceOutputDto.setIsActive(c.getActive());
			list.add(contractResourceOutputDto);
			
		});
		return list;
	}

	@Override
	public Contract saveContracts(ContractResourceInputDto contractResourceInputDto) {
		Contract contract = new Contract();
		contract.setContractName(contractResourceInputDto.getContractName());
		contract.setContractDescription(contractResourceInputDto.getContractDescription());
		contract.setStartDate(contractResourceInputDto.getStartDate());
		contract.setEndDate(contractResourceInputDto.getEndDate());
		contract.setStatus(contractResourceInputDto.getStatus());
		contract.setActive(contractResourceInputDto.getIsActive());
		Contract savedContract = contractRepository.save(contract);
		return savedContract;
	}

}
