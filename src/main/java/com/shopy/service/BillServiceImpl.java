package com.shopy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.BillException;
import com.shopy.exception.OrderException;
import com.shopy.model.Bill;
import com.shopy.repository.BillRepo;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepo br;

	@Override
	public Bill addBill(Bill bill, int orderId) throws BillException, OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill viewBill(int billId) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill removeBill(int billId) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

}
