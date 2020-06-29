package com.hiberus.bill.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hiberus.bill.repository.BillRepository;
import com.hiberus.bill.service.BillService;
import com.hiberus.commons.dto.BillDTO;
import com.hiberus.commons.dto.OrderDTO;
import com.hiberus.commons.dto.ProductDTO;
import com.hiberus.commons.error.Errors;
import com.hiberus.commons.expection.CustomException;
import com.hiberus.commons.model.Bill;
import com.hiberus.commons.model.Client;
import com.hiberus.commons.model.Product;

import lombok.extern.java.Log;

@Log
@Service
public class BillServiceImp implements BillService {

	@Autowired
	BillRepository billRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public BillDTO calculateBill(OrderDTO orderDTO) throws CustomException {
		log.info("INPUT DATA REQUEST: " + orderDTO);
		Bill bill = new Bill();
		Client client = new Client();
		double totalPurchaseAmount = 0;
		int totalProducts = 0;
		client.setClientId(orderDTO.getClientId());
		bill.setClient(client);
		List<ProductDTO> productsDTO = orderDTO.getProducts();
		List<Product> products = new ArrayList<>();
		if (productsDTO.isEmpty()) {
			throw new CustomException(HttpStatus.BAD_REQUEST.value(), Errors.NO_PRODUCTS_TO_BUY.description);
		}
		for (ProductDTO productDTO : productsDTO) {
			Product product = new Product();
			modelMapper.map(productDTO, product);
			products.add(product);
			totalProducts = productDTO.getQuantity() + totalProducts;
			totalPurchaseAmount = productDTO.getQuantity() * productDTO.getCost() + totalPurchaseAmount;
		}

		bill.setTotalAmount(totalPurchaseAmount);
		bill.setTotalProducts(totalProducts);
		bill.setProducts(products);
		billRepository.save(bill);
		log.info("OUTPUT DATA REQUEST: " + orderDTO);
		return new BillDTO(bill.getBillId(), bill.getTotalAmount(), bill.getTotalProducts(),
				"Bill generated succesfully");

	}

}
