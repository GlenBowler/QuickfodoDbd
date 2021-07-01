package Main;

public class Quickfoods {
	public static void main(String[] args) {
	
	//Calling my invoice class	
	Invoice newInv=new Invoice (0);
	newInv.createOrderNum();
	
	//Calling my customer class
	Customer newCustomer = new Customer (0,null,null,null,null,null);
	newCustomer.createCustomer();
	
	//Calling my resturant class
	Resturant newResturant= new Resturant(null,null,null,0,0);
	newResturant.createResturant();
	
	//Calling my menu class
	Menu newMenu = new Menu(0,null,0,null,0,null,0,null,null,0,null);
	newMenu.createMenu();
	
	//Printing out the invoice for the user on console
	System.out.println("Order Number: "+Invoice.getOrderNumber());
	System.out.println("Customer: "+newCustomer.getCustomerFullname());
	System.out.println("Email: "+newCustomer.getcustomerEmail());
	System.out.println("Phone number: "+newCustomer.getCustomerPhoneNum());
	System.out.println("Location: "+newCustomer.getCustomerCity());
	System.out.println("You have ordered the following from "+newResturant.getName()+ "in "+newResturant.getLocationOfResturant());
	System.out.println("");
	System.out.println(newMenu.getQuantity1()+" X "+newMenu.getItem1());
	System.out.println(newMenu.getQuantity2()+" X "+newMenu.getItem2());
	System.out.println(newMenu.getQuantity3()+" X "+newMenu.getItem3());
	System.out.println(newMenu.getQuantity4()+" X "+newMenu.getItem4());
	System.out.println("");
	System.out.println("Specail instructions:"+newMenu.getSpecailInstructions());
	System.out.println("Total: "+newMenu.getTotal());
	System.out.println(newMenu.getTheDriver()+" is the nearest to you and he will be delivering the order to you at :");
	System.out.println(newCustomer.getCustomerAdress());

	
	//
	//
	
	//
	//
	
}
	
}
