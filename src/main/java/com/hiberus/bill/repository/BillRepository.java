package com.hiberus.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiberus.commons.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
