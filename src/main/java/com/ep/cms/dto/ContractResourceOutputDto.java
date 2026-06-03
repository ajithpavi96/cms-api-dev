package com.ep.cms.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractResourceOutputDto {
	
	private Long contractId;
	private String contractName;
	private String contractDescription;
	private Date startDate;
	private Date endDate;
	private Boolean isActive;
	private String status;

}
