package com.hiberus.bill.service;

import org.springframework.stereotype.Service;

import com.hiberus.commons.dto.BillDTO;
import com.hiberus.commons.dto.OrderDTO;
import com.hiberus.commons.expection.CustomException;

@Service
public interface BillService {

	public BillDTO calculateBill(OrderDTO orderDTO) throws CustomException;

}
