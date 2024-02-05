package services;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {		
		for (int i=1; i<=months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			double amount = contract.getTotalValue() / months;			
			amount += onlinePaymentService.paymentFee(amount);
			amount += onlinePaymentService.interest(amount, i);			
			contract.getInstallment().add(new Installment(dueDate, amount));
		}		
	}
}
