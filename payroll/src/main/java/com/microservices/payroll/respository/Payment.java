package com.microservices.payroll.respository;

import com.microservices.payroll.domain.Pay;

public interface Payment {
	
	public Pay getPay();
	
	public Pay savePay(Pay pay);
}
