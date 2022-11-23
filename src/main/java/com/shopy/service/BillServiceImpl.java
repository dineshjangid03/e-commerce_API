package com.shopy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.BillException;
import com.shopy.exception.OrderException;
import com.shopy.model.Bill;
import com.shopy.model.Order;
import com.shopy.repository.BillRepo;
import com.shopy.repository.OrderRepo;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepo br;
	
	@Autowired
	private OrderRepo ordeRepo;

	@Override
	public Bill addBill(Bill bill, int orderId) throws BillException, OrderException {
		Optional<Order> ord=ordeRepo.findById(orderId);
		if(ord.isEmpty())
			throw new OrderException("order not found with id "+orderId);
		
		bill.setOrder(ord.get());
		bill.setTotalPrice(ord.get().getCart().getTotalPrice());
		bill.setTotalItem(ord.get().getCart().getTotalItems());
		return br.save(bill);
	}

	@Override
	public Bill viewBill(int billId) throws BillException {
		Optional<Bill>bil=br.findById(billId);
		if(bil.isEmpty())
			throw new BillException("bill not found with id "+billId);
		
		return bil.get();
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		List<Bill> list=br.billBetweenDate(startDate, endDate);
		if(list.size()==0)
			throw new BillException("bill not found between "+startDate+" and "+endDate);
		return list;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		return null;
	}

	@Override
	public Bill removeBill(int billId) throws BillException {
		Optional<Bill>bil=br.findById(billId);
		if(bil.isEmpty())
			throw new BillException("bill not found with id "+billId);
		Bill b=bil.get();
		b.setOrder(null);
		br.delete(b);
		return b;
	}

}
