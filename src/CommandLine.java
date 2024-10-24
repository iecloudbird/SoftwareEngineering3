import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CommandLine {
	
	private static void listCustomerFuctionalityAvailable() {
		
		//Present Customer with Functionality 	Options
		
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Customer Account");
		System.out.println("2. View ALL Customer Records");
		System.out.println("3. Update Customer Record by ID");
        System.out.println("4. Delete Inactive Customer Records");
        System.out.println("5. Create Delivery Person");
        System.out.println("6. Update Delivery Person");
        System.out.println("7. Delete Delivery Person");
        System.out.println("8. Create Invoice");
        System.out.println("9. Update Invoice");
        System.out.println("10. Cancel Invoice");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println(" ");
		
	}

	
	private static boolean printCustomerTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			int id = rs.getInt("customer_id");
	        String name = rs.getString("customer_name"); 
	        String addr = rs.getString("customer_address"); 
	        String phone = rs.getString("customer_phone");
	        String email = rs.getString("customer_email"); 
	        boolean subscribed = rs.getBoolean("is_subscribed");
	        
			System.out.printf("%30s", id);
			System.out.printf("%30s", name);
			System.out.printf("%30s", addr);
			System.out.printf("%30s", phone);
			System.out.printf("%30s", email); 
	        System.out.printf("%30s", subscribed);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}

	private static void updateCustomer(Scanner keyboard, MySQLAccess dao) {
        // Implementation for updating customer details
        System.out.println("Enter Customer ID to update:");
        int updateId = keyboard.nextInt();
        keyboard.nextLine();
        
        // Get updated details
        System.out.printf("Enter New Customer Name: \n");
        String custName = keyboard.nextLine();
        System.out.printf("Enter New Customer Address: \n");
        String custAddr = keyboard.nextLine();
        System.out.printf("Enter New Customer PhoneNumber: \n");
        String custPhoneNumber = keyboard.nextLine();
        System.out.printf("Enter New Customer Email: \n");
        String custEmail = keyboard.nextLine();
        
        try {
            boolean updateResult = dao.updateCustomerDetails(updateId, custName, custAddr, custPhoneNumber, custEmail);
            System.out.println(updateResult ? "Customer Details Updated" : "ERROR: Customer Details NOT Updated");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
	
	private static void createDeliveryPerson(Scanner keyboard, MySQLAccess dao) {
        // Implementation for creating a delivery person
		System.out.printf("Enter Delivery Person First Name: \n");
        String firstName = keyboard.nextLine();
        
        System.out.printf("Enter Delivery Person Last Name: \n");
        String lastName = keyboard.nextLine();
        
        System.out.printf("Enter Delivery Person ID (format DP/123): \n");
        String id = keyboard.nextLine();
        
        System.out.printf("Enter Delivery Person Phone Number: \n");
        String phone = keyboard.nextLine();
        
        System.out.printf("Enter Assigned Area: \n");
        String area = keyboard.nextLine();
        
        System.out.printf("Enter Status (Out for delivery/Returned/Inactive): \n");
        String status = keyboard.nextLine();
        
        try {
        	 DeliveryPerson deliveryPerson = new DeliveryPerson(firstName, lastName, id, phone, area, status);
             boolean insertResult = dao.insertDeliveryPerson(deliveryPerson);
            System.out.println(insertResult ? "Delivery Person Created" : "ERROR: Delivery Person NOT Created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
	private static void updateDeliveryPerson(Scanner keyboard, MySQLAccess dao) {
	    // Implementation for updating delivery person details
	    System.out.println("Enter Delivery Person ID to update:");
	    String updateId = keyboard.nextLine();

	    // Get updated details
	    System.out.printf("Enter New Delivery Person First Name: \n");
	    String firstName = keyboard.nextLine();
	    System.out.printf("Enter New Delivery Person Last Name: \n");
	    String lastName = keyboard.nextLine();
	    System.out.printf("Enter New Delivery Person Phone Number (10 digits): \n");
	    String phone = keyboard.nextLine();
	    System.out.printf("Enter New Assigned Area: \n");
	    String area = keyboard.nextLine();
	    System.out.printf("Enter New Status (Out for delivery/Returned/Inactive): \n");
	    String status = keyboard.nextLine();

	    try {
	        // Create a new DeliveryPerson object with the updated details
	        DeliveryPerson deliveryPerson = new DeliveryPerson(firstName, lastName, updateId, phone, area, status);
	        boolean updateResult = dao.updateDeliveryPersonDetails(deliveryPerson); // You'll need to implement this method in MySQLAccess
	        System.out.println(updateResult ? "Delivery Person Details Updated" : "ERROR: Delivery Person Details NOT Updated");
	    } catch (DeliveryPersonException e) {
	        System.out.println("ERROR: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    }
	}

    private static void deleteDeliveryPerson(Scanner keyboard, MySQLAccess dao) {
        // Implementation for deleting delivery person
        System.out.println("Enter Delivery Person ID to delete:");
        String deleteId = keyboard.nextLine();
        boolean deleteResult = true;
    }
	public static void main(String[] args) {
		
		//Create the Database Object
		
		try {
			
			MySQLAccess dao = new MySQLAccess();

			// Configure System for Running
			Scanner keyboard = new Scanner(System.in); 
			String functionNumber = "-99";
			boolean keepAppOpen = true;
		
			while (keepAppOpen == true) {
			
				//Present list of functionality and get selection
				listCustomerFuctionalityAvailable();
				functionNumber = keyboard.next();
				keyboard.nextLine(); 
		
				switch (functionNumber) {
		
				case "1":
					//Get Customer Details from the User
					System.out.printf("Enter Customer Name: \n");
					String custName = keyboard.nextLine();
					System.out.printf("Enter Customer Address: \n");
					String custAddr = keyboard.nextLine();
					System.out.printf("Enter Customer PhoneNumber: \n");
					String custphoneNumber = keyboard.nextLine();
					System.out.printf("Enter Customer Email: \n");
					String custEmail = keyboard.nextLine();
					System.out.println("Is the customer subscribed? (true/false): ");
					while (!keyboard.hasNextBoolean()) {
					    System.out.println("Invalid input. Please enter 'true' or 'false'.");
					    keyboard.next(); // Consume invalid input
					}
					Boolean subscriptionStatus = keyboard.nextBoolean();
					keyboard.nextLine();
					
					try {
					    Customer custObj = new Customer(custName, custAddr, custphoneNumber, custEmail, subscriptionStatus);
					  //Insert Customer Details into the database
					    boolean insertResult = dao.insertCustomerDetailsAccount(custObj);
					    if (insertResult) {
					        System.out.println("Customer Details Saved");
					    } else {
					        System.out.println("ERROR: Customer Details NOT Saved");
					    }
					} catch (CustomerExceptionHandler e) {
					    System.out.println("ERROR: " + e.getMessage());
					}
					
				case "2": 
					//Retrieve ALL Customer Records
					try (ResultSet rSet = dao.retrieveAllCustomerAccounts()) {
					    if (rSet != null) {
					        boolean tablePrinted = printCustomerTable(rSet);
					        if (tablePrinted) {
					            rSet.close();
					        }
					    } else {
					        System.out.println("No Records Found");
					    }
					} catch (SQLException e) {
					    System.out.println("Error retrieving customer records: " + e.getMessage());
					}
					break;
					
				case "3":
					updateCustomer(keyboard, dao);
				    break;
				
				case "4":
					//Delete Customer Record by ID
					System.out.println("Enter Customer ID to be deleted or -99 to Clear all Rows:");
					while (!keyboard.hasNextInt()) {
					    System.out.println("Invalid input. Please enter a valid numeric Customer ID or -99.");
					    keyboard.next();  // Consume invalid input
					}
					int deleteCustId = keyboard.nextInt();
					if (deleteCustId == -99) {
					    boolean deleteAllResult = dao.deleteAllCustomers();
					    if (deleteAllResult) {
					        System.out.println("Customer Table Emptied");
					    } else {
					        System.out.println("ERROR: Could not empty the table");
					    }
					} else {
					    boolean deleteResult = dao.deleteCustomerById(deleteCustId);
					    if (deleteResult) {
					        System.out.println("Customer Deleted");
					    } else {
					        System.out.println("ERROR: Customer Details NOT Deleted or Do Not Exist");
					    }
					}
					break;
				case "5":
				        createDeliveryPerson(keyboard, dao);
				        break;
				case "6":
				        updateDeliveryPerson(keyboard, dao);
				        break;
				case "7":
				        deleteDeliveryPerson(keyboard, dao);
				        break;
				case "8":
				        // Create invoice logic...
				        break;
				        
				case "99":
					System.out.println("Are you sure you want to exit? (yes/no): ");
					String exitConfirmation = keyboard.nextLine();
					if (exitConfirmation.equalsIgnoreCase("yes")) {
					    keepAppOpen = false;
					    System.out.println("Closing the Application");
					}
					break;
			
				default:
					System.out.println("No Valid Function Selected");
					break;
				} // end switch
		
			}// end while
		
			//Tidy up Resources
			keyboard.close();
		
		}
	
		catch(Exception e) {
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
		} // end try-catch
		

	} // end main
	
	
}
