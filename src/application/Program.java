package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {
   
	public static void main(String[] args) throws ParseException {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		int number = scan.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		scan.nextLine();
		LocalDate date = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Contract value: ");
		double totalValue = scan.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Enter the number of installments: ");
		Integer n = scan.nextInt();
			
		ContractService service = new ContractService(new PaypalService());
		service.processContract(contract, n);			

		System.out.println("\nInstallments:");

		for (Installment installent : contract.getInstallment()) {
			System.out.println(installent);
		}

		scan.close();
	}
}
