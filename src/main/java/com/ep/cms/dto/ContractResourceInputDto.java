package com.ep.cms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractResourceInputDto {
	
	@JsonProperty("contract_name")
	private String contractName;
	
	@JsonProperty("contract_desc")
	private String contractDescription;
	
	@JsonProperty("start_date")
	private Date startDate;
	
	@JsonProperty("end_date")
	private Date endDate;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("isActive")
	private Boolean isActive;

}