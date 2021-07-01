package Main;
//All my imports for the this class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Resturant {
	//Constructor fo the resturant
	public Resturant(String name, String locationOfResturant, String numberOfResturant, int resturantID,
			int resturantNumber) {
		this.name = name;
		this.locationOfResturant = locationOfResturant;
		this.numberOfResturant = numberOfResturant;
		this.resturantID = resturantID;
		Resturant.resturantNumber = resturantNumber;
	}

	//Attributes
	String name;
	String locationOfResturant;
	String numberOfResturant;
	int resturantID;
	static int resturantNumber;

	//Getters for my resturant
	public String getName() {
		return name;
	}
	public String getLocationOfResturant() {
		return locationOfResturant;
	}
	public String getNumberOfResturant() {
		return numberOfResturant;
	}
	public int getResturantID() {
		return resturantID;
	}
	public static int getResturantNumber() {
		return resturantNumber;
	}
	
	//Creating my resturant 
	public Resturant createResturant() {
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
						 "jdbc:mysql://127.0.01:5500/QuickFoodMS",
						 "root",
						 "Welcome.2021");
			// Create a direct line to the database for running our queries
			Statement statement= connection.createStatement();
			ResultSet results,name,numberOfResturant,resturantID;
			//Varaibles
			String locationOfResturant;
			Scanner input = new Scanner (System.in);
	
			//Getting details from my customer class that will be needed in my restaurant
			String customerLocation =Customer.getCustomerCity();
			int invoiceNum=Invoice.getOrderNumber();
			locationOfResturant=customerLocation;
			
			//Getting user input to see what restruant he/she want to order from
			System.out.println("Please enter the resturant number you want to order from");
			results = statement.executeQuery("SELECT * FROM resturants WHERE Location ='"+customerLocation+"'");
				while (results.next()) {
				 System.out.println(results.getString("Number")+". "+results.getString("Name"));
				 }
			resturantNumber=input.nextInt();
			//Accessing the database Select certain data and update tables
			name=statement.executeQuery("SELECT Name FROM resturants WHERE Number ='"+resturantNumber+"'");
			resturantID=statement.executeQuery("SELECT 'Menu_idResturant' FROM resturants WHERE Name ='"+name+"' AND Location = '"+locationOfResturant+"'");
			numberOfResturant=statement.executeQuery("SELECT 'Contact number' FROM resturants WHERE Name ='"+name+"' AND Location = '"+locationOfResturant+"'");
			statement.executeUpdate("UPDATE Invoice SET `Resturant ID` ='" + resturantID + "'WHERE (`Ordernumber` ='" + invoiceNum + "')");
			 // Close up our connections
			 statement.close();
			// results.close();
			 connection.close();
		}
		
		//Catch to get any errors that migh occur
		catch (SQLException e) {
			 // We only want to catch a SQLException - anything else is off-limits for now.
				 e.printStackTrace();
				}
		//Creating my new new Resturant
		Resturant newRes = new Resturant(name,locationOfResturant,numberOfResturant,resturantID,resturantNumber);
		return newRes;
	}

}
