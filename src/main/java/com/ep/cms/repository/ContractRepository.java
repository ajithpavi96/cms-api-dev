package com.ep.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.cms.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
