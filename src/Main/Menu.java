package Main;
//All my imports for the this class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu{
	//Constructor for my meny
	public Menu(int quantity1, String item1, int quantity2, String item2, int quantity3, String item3, int quantity4,
			String item4, String specailInstructions, int total, String theDriver) {
		super();
		this.quantity1 = quantity1;
		this.item1 = item1;
		this.quantity2 = quantity2;
		this.item2 = item2;
		this.quantity3 = quantity3;
		this.item3 = item3;
		this.quantity4 = quantity4;
		this.item4 = item4;
		this.specailInstructions = specailInstructions;
		this.total = total;
		this.theDriver = theDriver;
	}	
	//Attributes
	int quantity1;
	String item1;
	int quantity2;
	String item2;
	int quantity3;
	String item3;
	int quantity4;
	String item4;
	String specailInstructions;
	int total;
	String theDriver;
	
	//Getters 
	public int getQuantity1() {
		return quantity1;
	}
	public String getItem1() {
		return item1;
	}
	public int getQuantity2() {
		return quantity2;
	}
	public String getItem2() {
		return item2;
	}
	public int getQuantity3() {
		return quantity3;
	}
	public String getItem3() {
		return item3;
	}
	public int getQuantity4() {
		return quantity4;
	}
	public String getItem4() {
		return item4;
	}
	public String getSpecailInstructions() {
		return specailInstructions;
	}
	public int getTotal() {
		return total;
	}
	public String getTheDriver() {
		return theDriver;
	}
	
	//Create menu 
	public Menu createMenu(){
		try {
			//Connecting to the database
			Connection connection = DriverManager.getConnection(
						 "jdbc:mysql://127.0.01:5500/QuickFoodMS",
						 "root",
						 "Welcome.2021");
			// Create a direct line to the database for running our queries
			Statement statement= connection.createStatement();
			ResultSet results;
			//Varaibles
			int resturantNumber=Resturant.getResturantNumber();
			int choice = 1;
			int count1 = 0,count2=0,count3=0,count4=0;
			int invoiceNum=Invoice.getOrderNumber();
			String  specailInstructions="None";
			Scanner input = new Scanner (System.in);
			String customerLocation =Customer.getCustomerCity();
			
			//Getting items from database and store value as var
			String order1 = "";
			ResultSet dish1 = statement.executeQuery("SELECT Item1 FROM menu WHERE Resturant_number="+resturantNumber+"");
            while(dish1.next()) {
                  order1 = dish1.getString("Item1");
                }
        	String order2 = "";
            ResultSet dish2 = statement.executeQuery("SELECT Item2 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(dish2.next()) {
                order2 = dish2.getString("Item2");
                }
            String order3 = "";
            ResultSet dish3 = statement.executeQuery("SELECT Item3 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(dish3.next()) {
                order3 = dish3.getString("Item3");
                }
            String order4= "";
            ResultSet dish4 = statement.executeQuery("SELECT Item4 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(dish4.next()) {
                order4 = dish4.getString("Item4");
                }
          
           //Getting the item price from database and store as variable
    		int price1=0;
            ResultSet myprice1 = statement.executeQuery("SELECT Price1 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(myprice1.next()) {
                price1 = myprice1.getInt("Price1");
                }
            int price2=0;
            ResultSet myprice2 = statement.executeQuery("SELECT Price2 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(myprice2.next()) {
                price2 = myprice2.getInt("Price2");
                }
            int price3=0;
            ResultSet myprice3 = statement.executeQuery("SELECT Price3 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(myprice3.next()) {
                price3 = myprice3.getInt("Price3");
                }
            int price4=0;
            ResultSet myprice4 = statement.executeQuery("SELECT Price4 FROM Menu WHERE Resturant_number="+resturantNumber+"");
            while(myprice4.next()) {
                price4 = myprice4.getInt("Price4");
                }
            
	        //While lop to show user his option he can pick from. If he choose 0 will end the order and excecute all his/her items to invoice table
			while (choice !=0) {
				//Print out the options for user to choose from
				System.out.println("Please select the item number you want to take. Press 0 once you are done ordering");
				System.out.println("1 "+order1+" R"+price1);
				System.out.println("2 "+order2+" R"+price2);
				System.out.println("3 "+order3+" R"+price3);
				System.out.println("4 "+order4+" R"+price4);
				choice=input.nextInt();
				
				//If user choose option adding one to count so that we can get the total count at end
				if (choice ==1) {
					count1++;
				}
				else if (choice==2) {
					count2++;
				}
				else if (choice==3) {
					count3++;
				}
				else if(choice ==4) {
					count4++;
				}
				else {
					//Calculating the total for the whole order
					total=(count1*price1)+(count2*price2)+(count3*price3)+(count4*price4);
					//Updating the Invocie with order
					statement.executeUpdate("UPDATE Invoice SET Item1 ='"+order1+"' WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Item2 ='"+order2+"' WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Item3 ='"+order3+"' WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Item4 ='"+order4+"' WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Qauntiy1 ="+count1+" WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Qauntity2  ="+count2+" WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Qauntity3  ="+count3+" WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET Qauntity4  ="+count4+" WHERE (Ordernumber ="+invoiceNum+")");	
					statement.executeUpdate("UPDATE Invoice SET total ="+total+" WHERE (Ordernumber ="+invoiceNum+")");	
					break;
				}
				
				//Getting the driver for your load
				int leastLoads=0;
				ResultSet least= statement.executeQuery("SELECT MIN(Trips) FROM drivers WHERE Location='"+customerLocation+"'");
				while (least.next()) {
					//driverName =least.getString("DriverName");
					leastLoads=least.getInt("MIN(Trips)");
				}
				String driverName="";
				ResultSet driver=statement.executeQuery("SELECT DriverName FROM drivers WHERE Location='"+customerLocation+"' AND Trips ='"+leastLoads+"'");
				while(driver.next()) {
					driverName=driver.getString("DriverName");
				}
				//Update my Invoice with driver that will do the load
				statement.executeUpdate("UPDATE Invoice SET Driver_Name ='"+driverName+"' WHERE (Ordernumber ="+invoiceNum+")");
				//Updating the driver trips with one with driver that will be doing load
				statement.executeUpdate("UPDATE drivers SET Trips = Trips + 1 WHERE (DriverName ='"+driverName+"')");
				
				//Assiginging values to my attributes 
				quantity1=count1;
				quantity2=count2;
				quantity3=count3;
				quantity4=count4;
				item1=order1;
				item2=order2;
				item3=order3;
				item4=order4;
				theDriver=driverName;
			}	
			 // Close up our connections
			 statement.close();
			// results.close();
			 connection.close();
			}
		//Catch any errors
		catch (SQLException e) {
			 // We only want to catch a SQLException - anything else is off-limits for now.
				 e.printStackTrace();
			}
		//Creating a newMenu
		Menu newMenus = new Menu(quantity1,item1,quantity2,item2,quantity3,item3,quantity4,item4,specailInstructions,total,theDriver);
		return newMenus;
	
	}
	
}
	


