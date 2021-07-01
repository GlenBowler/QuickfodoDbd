package Main;
//All my imports for the this class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Invoice {
	//Constructor
	public Invoice(int orderNumber) {
		Invoice.orderNumber = orderNumber;
	}
	//Attributes
	static int orderNumber;
	//Getters
	public static int getOrderNumber() {
		return orderNumber;
	}
	//Function to createOrderNum
	public  Invoice createOrderNum() {
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
						 "jdbc:mysql://127.0.01:5500/QuickFoodMS",
						 "root",
						 "Welcome.2021");
			// Create a direct line to the database for running our queries
			Statement statement= connection.createStatement();
			//User order number
			int min = 1000;
			int max = 999999;
			orderNumber = ((int) (Math.random() * (max - min + 1) + min));
			
			// Add invoice number to invoice table
			statement.executeUpdate("INSERT INTO Invoice (`Ordernumber`) VALUES ('" + orderNumber + "')");
		}
		//Catch any errors
		 catch (SQLException e) {
			 // We only want to catch a SQLException - anything else is off-limits for now.
				 e.printStackTrace();
				}
		//Creaating my Inv
		Invoice newInv=new Invoice(orderNumber);
		return newInv;
	}
}
