package com.hiberus.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.bill.service.BillService;
import com.hiberus.commons.dto.BillDTO;
import com.hiberus.commons.dto.OrderDTO;
import com.hiberus.commons.expection.CustomException;

@RestController
public class BillController {

	@Autowired
	BillService billService;

	@PostMapping("bill")
	public ResponseEntity<BillDTO> generateBill(@RequestBody OrderDTO orderDTO) throws CustomException{
		return new ResponseEntity<>(billService.calculateBill(orderDTO), HttpStatus.OK);
	}
}
