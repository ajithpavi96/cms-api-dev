package com.ep.cms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@Getter
@Setter
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_seq")
	@SequenceGenerator(name = "contract_id_seq", sequenceName = "contract_id_seq",  allocationSize=1)
	@Column(name="contract_id")
	private Long contractId;
	
	@Column(name="contract_name")
	private String contractName;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="contract_description")
	private String contractDescription;
	
	@Column(name="isActive")
	private Boolean active;
	
	@Column(name="status")
	private String status;
	

}
