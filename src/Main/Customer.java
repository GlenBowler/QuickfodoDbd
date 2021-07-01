package Main;

//All my imports for the this class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
	//Attributes
	int customerOrderNumber;
	String customerName;
	String customerSurname;
	String customerFullname;
	String customerEmail;
	String customerPhoneNum;
	static String customerCity;
	String customerAdress;
	int customerID;

	//Getters for customer
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public String getCustomerFullname() {
		return customerFullname;
	}
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	
	public static String getCustomerCity() {
		return customerCity;
	}
	public String getcustomerEmail() {
		return customerEmail;
	}
	public String getCustomerAdress() {
		return customerAdress;
	}
	public int getcustomerID() {
		return customerID;
	}

	//Constructor
	public Customer(int customerID,String customerFullName,String customerEmail,String customerPhoneNum,String customerCity,String customerAdress) {
		
		this.customerFullname=customerFullName;
		this.customerEmail=customerEmail;
		this.customerPhoneNum=customerPhoneNum;
		this.customerCity=customerCity;
		this.customerAdress=customerAdress;
		this.customerID=customerID;
	}
	
	//Creating a new customer
	public Customer createCustomer() {
	try {
		//Connecting to the database
		Connection connection = DriverManager.getConnection(
					 "jdbc:mysql://127.0.01:5500/QuickFoodMS",
					 "root",
					 "Welcome.2021");
		// Create a direct line to the database for running our queries
		Statement statement= connection.createStatement();
		
		int invoiceNum=Invoice.getOrderNumber();
		Scanner input = new Scanner (System.in);
		//User order number
		int min = 1000;
		int max = 999999;
		//Customer ID
		customerID = ((int) (Math.random() * (max - min + 1) + min));
	
		//Getting user input
		//Name
		System.out.println("Please enter your name");
		 customerName=input.nextLine();
		//Surname
		System.out.println("Please enter your surname");
		 customerSurname=input.nextLine();
		//Fullname
		customerFullname=customerName+" "+customerSurname;	
		//Email adress
		System.out.println("Please enter your email address");
		customerEmail=input.nextLine();
		//Phone number
		System.out.println("Please enter your phone number");
		customerPhoneNum=input.nextLine();
		//Location
		System.out.println("Please enter your city your live in");
		customerCity=input.nextLine();
		//Adress
		System.out.println("Please enter your adress");
		customerAdress=input.nextLine();
		
		// Add customer id to invoice table
		statement.executeUpdate("UPDATE Invoice SET `CustomerID` ='" + customerID + "'WHERE (`Ordernumber` ='" + invoiceNum + "')");
		//add in all values to row for customer table
		statement.executeUpdate("INSERT INTO Customer VALUES ('" + customerID + "','" + customerFullname + "','" + customerEmail + "','" + customerPhoneNum + "','" + customerCity + "','" + customerAdress + "') ");
		// Close up our connections
		statement.close();
		connection.close();
		}
		
	//Catch if somtehting not working correctly
	 catch (SQLException e) {
	 // We only want to catch a SQLException - anything else is off-limits for now.
		 e.printStackTrace();
		}
	//Creating my customer
	Customer newCustomer = new Customer (customerID, customerFullname, customerEmail, customerPhoneNum, customerCity, customerAdress);
	return newCustomer;
}
}