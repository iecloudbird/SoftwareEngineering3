import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        //CRUD for Delivery Person
        System.out.println("5. Create Delivery Person");
        System.out.println("6. Read Delivery Person");
        System.out.println("7. Update Delivery Person");
        System.out.println("8. Delete Delivery Person");
      //CRUD for Invoice (Duplicate 1 of 2)
        System.out.println("9. Create Invoice");
        System.out.println("10. Update Invoice");
        System.out.println("11. Cancel Invoice");
        System.out.println("12. View All Invoices");
      //CRUD for Publication
        System.out.println("13. Create Publication");
        System.out.println("14. Read Publication");
        System.out.println("15. Update Publication");
        System.out.println("16. Delete Publication");
      //CRUD for Order
        System.out.println("17. Create Order");
        System.out.println("18. Read Order");
        System.out.println("19. Update Order");
        System.out.println("20. Delete Order");
      //CRUD for Invoice (Duplicate 2 of 2)
        System.out.println("21. Create Invoice");
        System.out.println("22. Read Invoice");
        System.out.println("23. Update Invoice");
        System.out.println("24. Delete Invoice");
        //CRUD for Delivery Docket
        System.out.println("25. Create Delivery Docket");
        System.out.println("26. Read Delivery Docket");
        System.out.println("27. Update Delivery Docket");
        System.out.println("28. Delete Delivery Docket");
        
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
        String id = keyboard.nextLine().toUpperCase();
        
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
	
	private static boolean printDeliveryPersonTable(ResultSet rs) throws Exception {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%30s", rs.getMetaData().getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            String id = rs.getString("delivery_person_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phone = rs.getString("phone_number");
            String area = rs.getString("assigned_area");
            String status = rs.getString("status");
            
            System.out.printf("%30s", id);
            System.out.printf("%30s", firstName);
            System.out.printf("%30s", lastName);
            System.out.printf("%30s", phone);
            System.out.printf("%30s", area);
            System.out.printf("%30s", status);
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        
        return true;
    }
	private static void readDeliveryPerson(Scanner keyboard, MySQLAccess dao) {
        // Retrieve and print all delivery person records
        try (ResultSet rSet = dao.retrieveAllDeliveryPersons()) { // Implement `retrieveAllDeliveryPersons` in `MySQLAccess`
            if (rSet != null) {
                boolean tablePrinted = printDeliveryPersonTable(rSet);
                if (tablePrinted) {
                    rSet.close();
                }
            } else {
                System.out.println("No Records Found");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving delivery person records: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
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
    
   private static void createInvoice(Scanner scanner, MySQLAccess dao) throws CustomerExceptionHandler {
    System.out.println("Enter Invoice ID:");
    String invoiceId = scanner.nextLine();
    System.out.println("Enter Customer ID:");
    String custId = scanner.nextLine();

    System.out.println("Enter Payment Method (card/cash):");
    String paymentMethod = scanner.nextLine();
    System.out.println("Enter Order Date (YYYY-MM-DD):");
    String dateString = scanner.nextLine();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date orderDate = null;
    try {
        orderDate = dateFormat.parse(dateString);
    } catch (ParseException e) {
        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
    }
    System.out.println("Enter Total Amount:");
    double totalAmount = scanner.nextDouble();
    scanner.nextLine(); // Consume newline
    System.out.println("Enter Delivery ID (optional, leave blank if not applicable):");
    String deliveryId = scanner.nextLine();
    System.out.println("Enter Publication ID (optional, leave blank if not applicable):");
    String publicationId = scanner.nextLine();
    System.out.println("Enter Order Status:");
    String orderStatus = scanner.nextLine();

    // Ensure orderDate is not null before calling the constructor
    if (orderDate != null) {
        // Convert to java.sql.Date if necessary
        java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());

        Invoice newInvoice = new Invoice(invoiceId, custId, paymentMethod, sqlDate, totalAmount, deliveryId, publicationId, orderStatus);
        
        if (dao.insertInvoice(newInvoice)) {
            System.out.println("Invoice created successfully.");
        } else {
            System.out.println("Failed to create invoice.");
        }
    } else {
        System.out.println("Invoice creation failed due to invalid date.");
    }
}

   private static void updateInvoice(Scanner scanner, MySQLAccess dao) throws CustomerExceptionHandler {
	    System.out.println("Enter Invoice ID to update:");
	    String invoiceId = scanner.nextLine();
	    System.out.println("Enter New Customer ID:");
	    String custId = scanner.nextLine();
	    System.out.println("Enter Payment Method (card/cash):");
	    String paymentMethod = scanner.nextLine();
	    System.out.println("Enter New Order Date (YYYY-MM-DD):");
	    String dateString = scanner.nextLine();
	    
	    Date orderDate = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        orderDate = dateFormat.parse(dateString);
	    } catch (ParseException e) {
	        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
	        return; // Exit or handle the invalid input case
	    }

	    System.out.println("Enter New Total Amount:");
	    double totalAmount = 0.0;
	    try {
	        totalAmount = Double.parseDouble(scanner.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid total amount. Please enter a valid number.");
	        return;
	    }

	    System.out.println("Enter New Delivery ID (optional, leave blank if not applicable):");
	    String deliveryId = scanner.nextLine();
	    System.out.println("Enter New Publication ID (optional, leave blank if not applicable):");
	    String publicationId = scanner.nextLine();
	    System.out.println("Enter New Order Status:");
	    String orderStatus = scanner.nextLine();

	    Invoice updatedInvoice = new Invoice(invoiceId, custId, paymentMethod, orderDate, totalAmount, deliveryId, publicationId, orderStatus);
	    
	    if (dao.updateInvoiceDetails(updatedInvoice)) {
	        System.out.println("Invoice updated successfully.");
	    } else {
	        System.out.println("Failed to update invoice.");
	    }
	}

	private static void cancelInvoice(Scanner scanner, MySQLAccess dao) {
	    System.out.println("Enter Invoice ID to cancel:");
	    String invoiceId = scanner.nextLine();
	    if (dao.deleteInvoiceById(invoiceId)) {
	        System.out.println("Invoice cancelled successfully.");
	    } else {
	        System.out.println("Failed to cancel invoice.");
	    }
	}

	private static void viewAllInvoices(Scanner scanner, MySQLAccess dao) {
	    try (ResultSet rSet = dao.retrieveAllInvoices()) { // Implement `retrieveAllInvoices` in `MySQLAccess`
	        if (rSet != null) {
	            printInvoiceTable(rSet); // Call method to print invoice table
	        } else {
	            System.out.println("No Records Found");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving invoice records: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Unexpected error: " + e.getMessage());
	    }
	}

	private static boolean printInvoiceTable(ResultSet rs) throws Exception {
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    System.out.println("Table: " + rs.getMetaData().getTableName(1));
	    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	        System.out.printf("%30s", rs.getMetaData().getColumnName(i));
	    }
	    System.out.println();
	    while (rs.next()) {
	        String invoiceId = rs.getString("invoice_id");
	        String custId = rs.getString("customer_id");
	        String paymentMethod = rs.getString("payment_method");
	        Date orderDate = rs.getDate("order_date");
	        double totalAmount = rs.getDouble("total_amount");
	        String deliveryId = rs.getString("delivery_id");
	        String publicationId = rs.getString("publication_id");
	        String orderStatus = rs.getString("order_status");
	
	        System.out.printf("%30s", invoiceId);
	        System.out.printf("%30s", custId);
	        System.out.printf("%30s", paymentMethod);
	        System.out.printf("%30s", orderDate);
	        System.out.printf("%30.2f", totalAmount);
	        System.out.printf("%30s", deliveryId);
	        System.out.printf("%30s", publicationId);
	        System.out.printf("%30s", orderStatus);
	        System.out.println();
	    }
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    
	    return true;
	}

    
    private static void createPublication(Scanner keyboard, MySQLAccess dao) {
    	//Implementation for creating Publication
    	System.out.println("Enter Publication ID (format PUB001): \n");
    	String id = keyboard.nextLine();
        
        System.out.println("Enter Publication Title: \n");
        String title = keyboard.nextLine();
        
        System.out.println("Enter Publication Stock Amount: \n");
        int stock = keyboard.nextInt();
        
        System.out.println("Enter Price: \n");
        double price = keyboard.nextDouble();
        
        System.out.println("Enter Type of Publication (Newspaper or Magazine): \n");
        String type = keyboard.next();
        
        System.out.println("Enter Delivery Frequency (Daily, Weekly, Monthly): \n");
        String frequency = keyboard.next();
        
        try {
        	 Publication publication = new Publication(id, title, stock, price, type, frequency);
             boolean insertResult = dao.insertPublication(publication);
            System.out.println(insertResult ? "Publication Created" : "ERROR: Publication NOT Created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private static boolean printPublicationTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			String id = rs.getString("publication_id");
			String title = rs.getString("publication_name");
	        int stock = rs.getInt("stock_number");
	        double price = rs.getDouble("publication_price");
	        String type = rs.getString("publication_type");
	        String frequency = rs.getString("publication_frequency");
	        
			System.out.printf("%30s", id);
			System.out.printf("%30s", title);
			System.out.printf("%30s", stock);
			System.out.printf("%30s", type);
			System.out.printf("%30s", price); 
	        System.out.printf("%30s", frequency);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}
    
private static void updatePublication(Scanner keyboard, MySQLAccess dao) {
    // Implementation for updating publication details
    System.out.println("Enter Publication ID to update:");
    String updateId = keyboard.nextLine();

    // Get updated details
    System.out.println("Enter New Publication Title: \n");
    String title = keyboard.nextLine();
    System.out.println("Enter New Publication Stock amount: \n");
    int stock = keyboard.nextInt();
    System.out.println("Enter New Publication Price: \n");
    double price = keyboard.nextDouble();
    System.out.println("Enter New Type of Publication (Newspaper/Magazine): \n");
    String type = keyboard.next();
    System.out.println("Enter New Delivery Frequency (Daily/Weekly/Monthly): \n");
    String status = keyboard.next();

    try {
        // Create a new Publication object with the updated details
        Publication publication = new Publication(updateId, title, stock, price, type, status);
        boolean updateResult = dao.updatePublicationDetails(publication); // You'll need to implement this method in MySQLAccess
        System.out.println(updateResult ? "Publication Details Updated" : "ERROR: Publication Details NOT Updated");
    } catch (PublicationException e) {
        System.out.println("ERROR: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}
	private static void deletePublication(Scanner keyboard, MySQLAccess dao) {
	    // Implementation for deleting publication
//	    System.out.println("Enter Publication ID to delete:");
//	    String deleteId = keyboard.nextLine();
//	    boolean deleteResult = true;
		//Delete Publication Record by ID
		System.out.println("Enter Publication ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Publication ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String deletepublicationId = keyboard.next().toUpperCase();
		if (deletepublicationId.compareTo("DELETEALL") == 0) {
		    boolean deleteAllResult = dao.deleteAllPublications();
		    if (deleteAllResult) {
		        System.out.println("Publication Table Emptied");
		    } else {
		        System.out.println("ERROR: Could not empty the table");
		    }
		} else {
		    boolean deleteResult = dao.deletePublicationById(deletepublicationId);
		    if (deleteResult) {
		        System.out.println("Publication Deleted");
		    } else {
		        System.out.println("ERROR: Publication Details NOT Deleted or Do Not Exist");
		    }
		}
	}
	private static void createOrder(Scanner keyboard, MySQLAccess dao) throws CustomerExceptionHandler {
    	//Implementation for creating Order
    	System.out.println("Enter Order ID (format ORD0001): \n");
    	String orderId = keyboard.nextLine();
    	
    	System.out.println("Enter Customer ID: \n");
    	int custId = keyboard.nextInt();
    	
    	System.out.println("Enter delivery ID (format DP001): \n");
    	String deliveryId = keyboard.next();
    	
    	System.out.println("Enter publication ID (format PUB001): \n");
    	String publicationId = keyboard.next();
    	
//    	System.out.println("Enter the order date: \n");
//    	String dateString = keyboard.nextLine();
//    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
    	Date orderDate = new Date(2024,11,01);
//		try {
//			orderDate = dateformat.parse(dateString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//    	Date orderDate = keyboard.next();
    	
    	System.out.println("Enter the order status (format PENDING/CONFIRMED/DISPATCHED/DELIVERED/CANCELLED/POSTPONED): \n");
    	//String orderStatus = keyboard.next();
    	//OrderStatus orderStatus = keyboard.next();
    	//////////////////////////////////////////
    	String input = keyboard.next();
    	OrderStatus orderStatus;
    	//new Date(); format to yyyy:mm:dd
    	try {
    	    orderStatus = OrderStatus.valueOf(input.toUpperCase());
    	} catch (IllegalArgumentException e) {
    	    throw new CustomerExceptionHandler("Invalid order status entered");
    	}
    	
        
        try {
        	 Order order = new Order(orderId, custId, deliveryId, publicationId, orderDate, orderStatus);
             boolean insertResult = dao.insertOrder(order);
            System.out.println(insertResult ? "Order Created" : "ERROR: Order NOT Created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
private static boolean printOrderTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			String orderId = rs.getString("order_id");
			int custId = rs.getInt("cust_id");
	        String deliveryId = rs.getString("delivery_id");
	        String publicationId = rs.getString("publication_id");
	        Date orderDate = rs.getDate("order_date");
	        Boolean orderStatus = rs.getBoolean("order_status");
	        
			System.out.printf("%30s", orderId);
			System.out.printf("%30s", custId);
			System.out.printf("%30s", deliveryId);
			System.out.printf("%30s", publicationId);
			System.out.printf("%30s", orderDate); 
	        System.out.printf("%30s", orderStatus);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}
    
private static void updateOrder(Scanner keyboard, MySQLAccess dao) throws CustomerExceptionHandler {
    // Implementation for updating order details
    System.out.println("Enter Order ID to update:");
    String orderId = keyboard.nextLine();

    // Get updated details
    System.out.println("Enter Customer ID: \n");
    int custId = keyboard.nextInt();
    System.out.println("Enter Delivery ID: \n");
    String deliveryId = keyboard.next();
    System.out.println("Enter Publication ID: \n");
    String publicationId = keyboard.next();
    System.out.println("Enter Order Date: \n");
    String dateString = keyboard.nextLine();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
	java.util.Date orderDate = null;
	try {
		orderDate = dateformat.parse(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    //String orderDate = keyboard.next();
    System.out.println("Enter Order Status: \n");
    String input = keyboard.next();
	OrderStatus orderStatus;

	try {
	    orderStatus = OrderStatus.valueOf(input.toUpperCase());
	} catch (IllegalArgumentException e) {
	    throw new CustomerExceptionHandler("Invalid order status entered");
	}
	
    
    try {
    	 Order order = new Order(orderId, custId, deliveryId, publicationId, orderDate, orderStatus);
         boolean insertResult = dao.insertOrder(order);
        System.out.println(insertResult ? "Order Created" : "ERROR: Order NOT Created");
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
    //String orderStatus = keyboard.next();

    try {
        // Create a new Publication object with the updated details
        Order order = new Order(orderId, custId, deliveryId, publicationId, orderDate, orderStatus);
        boolean updateResult = dao.updateOrderDetails(order); // You'll need to implement this method in MySQLAccess
        System.out.println(updateResult ? "Order Details Updated" : "ERROR: Order Details NOT Updated");
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}
	private static void deleteOrder(Scanner keyboard, MySQLAccess dao) {
	    // Implementation for deleting Order
//	    System.out.println("Enter Publication ID to delete:");
//	    String deleteId = keyboard.nextLine();
//	    boolean deleteResult = true;
		//Delete Publication Record by ID
		System.out.println("Enter Order ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Order ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String deleteorderId = keyboard.next().toUpperCase();
		if (deleteorderId.compareTo("DELETEALL") == 0) {
		    boolean deleteAllResult = dao.deleteAllOrders();
		    if (deleteAllResult) {
		        System.out.println("Order Table Emptied");
		    } else {
		        System.out.println("ERROR: Could not empty the table");
		    }
		} else {
		    boolean deleteResult = dao.deleteOrderById(deleteorderId);
		    if (deleteResult) {
		        System.out.println("Order Deleted");
		    } else {
		        System.out.println("ERROR: Order Details NOT Deleted or Do Not Exist");
		    }
		}
	}
	private static void createInvoice(Scanner keyboard, MySQLAccess dao) {
    	//Implementation for creating Publication
    	System.out.println("Enter the Invoice ID (format INV0001): \n");
    	String invoice_id = keyboard.nextLine();
        
        System.out.println("Enter the Customer ID: \n");
        String cust_id = keyboard.nextLine();
        
        System.out.println("Enter Payment Method: \n");
        String payment_method = keyboard.next();
        
        System.out.println("Enter the Order Date: \n");
        //String order_date = keyboard.next();
        String dateString = keyboard.nextLine();
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
    	java.util.Date order_date = null;
    	try {
    		order_date = dateformat.parse(dateString);
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
        
        System.out.println("Enter the Total Amount: \n");
        Double total_amount = keyboard.nextDouble();
        
        System.out.println("Enter the delivery person ID: \n");
        String delivery_persons = keyboard.next();
        
        System.out.println("Enter the Publication ID: \n");
        String publication_id = keyboard.next();
        
        System.out.println("Enter the Order Status: \n");
        String order_status = keyboard.next();
        
        try {
        	 Invoice invoice = new Invoice(invoice_id, cust_id, payment_method, order_date, total_amount, delivery_persons, publication_id, order_status);
             boolean insertResult = dao.insertInvoice(invoice);
            System.out.println(insertResult ? "Invoice Created" : "ERROR: Invoice NOT Created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
private static boolean printInvoiceTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			String invoice_id = rs.getString("invoice_id");
			String cust_id = rs.getString("cust_id");
	        String payment_method = rs.getString("payment_method");
	        Date order_date = rs.getDate("order_date");
	        Double total_amount = rs.getDouble("total_amount");
	        String delivery_persons = rs.getString("delivery_persons");
	        String publication_id = rs.getString("publication_id");
	        String order_status = rs.getString("order_status");
	        
			System.out.printf("%30s", invoice_id);
			System.out.printf("%30s", cust_id);
			System.out.printf("%30s", payment_method);
			System.out.printf("%30s", order_date);
			System.out.printf("%30s", total_amount); 
	        System.out.printf("%30s", delivery_persons);
	        System.out.printf("%30s", publication_id);
	        System.out.printf("%30s", order_status);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}
    
private static void updateInvoice(Scanner keyboard, MySQLAccess dao) {
    // Implementation for updating publication details
    System.out.println("Enter Invoice ID to update:");
    String invoice_id = keyboard.nextLine();

    // Get updated details
    System.out.println("Enter Customer ID: \n");
    String cust_id = keyboard.nextLine();
    System.out.println("Enter payment method: \n");
    String payment_method = keyboard.next();
    System.out.println("Enter Order Date: \n");
//    Date order_date = keyboard.next();
    String dateString = keyboard.nextLine();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
	java.util.Date order_date = null;
	try {
		order_date = dateformat.parse(dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    System.out.println("Enter Total Amount: \n");
    Double total_amount = keyboard.nextDouble();
    System.out.println("Enter Delivery Person: \n");
    String delivery_persons = keyboard.next();
    System.out.println("Enter Publication ID: \n");
    String publication_id = keyboard.next();
    System.out.println("Enter Order Status: \n");
    String order_status = keyboard.next();

    try {
        // Create a new Publication object with the updated details
        Invoice invoice = new Invoice(invoice_id, cust_id, payment_method, order_date, total_amount, delivery_persons, publication_id, order_status);
        boolean updateResult = dao.updateInvoiceDetails(invoice); // You'll need to implement this method in MySQLAccess
        System.out.println(updateResult ? "Invoice Details Updated" : "ERROR: Invoice Details NOT Updated");
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}
	private static void deleteInvoice(Scanner keyboard, MySQLAccess dao) {
	    // Implementation for deleting publication
//	    System.out.println("Enter Publication ID to delete:");
//	    String deleteId = keyboard.nextLine();
//	    boolean deleteResult = true;
		//Delete Publication Record by ID
		System.out.println("Enter Invoice ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Invoice ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String deleteinvoiceId = keyboard.next().toUpperCase();
		if (deleteinvoiceId.compareTo("DELETEALL") == 0) {
		    boolean deleteAllResult = dao.deleteAllinvoices();
		    if (deleteAllResult) {
		        System.out.println("Invoice Table Emptied");
		    } else {
		        System.out.println("ERROR: Could not empty the table");
		    }
		} else {
		    boolean deleteResult = dao.deletePublicationById(deleteinvoiceId);
		    if (deleteResult) {
		        System.out.println("Invoice Deleted");
		    } else {
		        System.out.println("ERROR: Invoice Details NOT Deleted or Do Not Exist");
		    }
		}
	}
	private static void createDeliveryDocket(Scanner keyboard, MySQLAccess dao) {
    	//Implementation for creating Publication
    	System.out.println("Enter the Delivery Docket ID (format DD001): \n");
    	String docket_id = keyboard.nextLine();
        
        System.out.println("Enter the Order ID: \n");
        String order_id = keyboard.nextLine();
        
        System.out.println("Enter Delivery ID: \n");
        String delivery_id = keyboard.next();
        
        System.out.println("Enter the Delivery Date: \n");
        String delivery_date = keyboard.next();
//        String dateString = keyboard.nextLine();
//    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
//    	java.util.Date delivery_date = null;
//    	try {
//    		delivery_date = dateformat.parse(dateString);
//    	} catch (ParseException e) {
//    		// TODO Auto-generated catch block
//    		e.printStackTrace();
//    	} 
        
        System.out.println("Enter the Delivery Status: \n");
        String delivery_status = keyboard.next();
        
        System.out.println("Enter the Delivery Details: \n");
        String delivery_details = keyboard.next();
        
        
        try {
        	 DeliveryDocket deliveryDocket = new DeliveryDocket(docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details);
             boolean insertResult = dao.insertDeliveryDocket(deliveryDocket);
            System.out.println(insertResult ? "Delivery Docket Created" : "ERROR: Delivery Docket NOT Created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
private static boolean printDeliveryDocketTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Delivery Dockets Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			String docket_id = rs.getString("docket_id");
			String order_id = rs.getString("order_id");
	        String delivery_id = rs.getString("delivery_id");
	        Date delivery_date = rs.getDate("delivery_date");
	        Double delivery_status = rs.getDouble("delivery_status");
	        String delivery_details = rs.getString("delivery_details");
	        
			System.out.printf("%30s", docket_id);
			System.out.printf("%30s", order_id);
			System.out.printf("%30s", delivery_id);
			System.out.printf("%30s", delivery_date);
			System.out.printf("%30s", delivery_status); 
	        System.out.printf("%30s", delivery_details);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}
    
private static void updateDeliveryDocket(Scanner keyboard, MySQLAccess dao) {
    // Implementation for updating Delivery Docket details
    System.out.println("Enter Delivery Docket ID to update:");
    String docket_id = keyboard.nextLine();

    // Get updated details
    System.out.println("Enter Order ID: \n");
    String order_id = keyboard.nextLine();
    System.out.println("Enter delivery ID: \n");
    String delivery_id = keyboard.next();
    System.out.println("Enter Delivery Date: \n");
    String delivery_date = keyboard.next();
//    String dateString = keyboard.nextLine();
//	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
//	java.util.Date order_date = null;
//	try {
//		order_date = dateformat.parse(dateString);
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
    System.out.println("Enter Delivery Status: \n");
    String delivery_status = keyboard.next();
    System.out.println("Enter Delivery Details: \n");
    String delivery_details = keyboard.next();

    try {
        // Create a new Publication object with the updated details
        DeliveryDocket deliveryDocket = new DeliveryDocket(docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details);
        boolean updateResult = dao.updateDeliveryDocketDetails(deliveryDocket); // You'll need to implement this method in MySQLAccess
        System.out.println(updateResult ? "Delivery Docket Details Updated" : "ERROR: Delivery Docket Details NOT Updated");
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}
	private static void deleteDeliveryDocket(Scanner keyboard, MySQLAccess dao) {
	    // Implementation for deleting publication
//	    System.out.println("Enter Publication ID to delete:");
//	    String deleteId = keyboard.nextLine();
//	    boolean deleteResult = true;
		//Delete Publication Record by ID
		System.out.println("Enter Delivery Docket ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Delivery Docket ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String deleteDeliveryDocketId = keyboard.next().toUpperCase();
		if (deleteDeliveryDocketId.compareTo("DELETEALL") == 0) {
		    boolean deleteAllResult = dao.deleteAllinvoices();
		    if (deleteAllResult) {
		        System.out.println("Delivery Docket Table Emptied");
		    } else {
		        System.out.println("ERROR: Could not empty the table");
		    }
		} else {
		    boolean deleteResult = dao.deletePublicationById(deleteDeliveryDocketId);
		    if (deleteResult) {
		        System.out.println("Delivery Docket Deleted");
		    } else {
		        System.out.println("ERROR: Delivery Docket Details NOT Deleted or Do Not Exist");
		    }
		}
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
						readDeliveryPerson(keyboard,dao);
				        
				        break;
				case "7":
						updateDeliveryPerson(keyboard, dao);
				       
				        break;
				case "8":
					 	deleteDeliveryPerson(keyboard, dao);
				        // Create invoice logic...
				        break;
				case "13":
					createPublication(keyboard, dao);
						break;
				case "14":
					try (ResultSet rSet = dao.retrieveAllPublications()) {
					    if (rSet != null) {
					        boolean tablePrinted = printPublicationTable(rSet);
					        if (tablePrinted) {
					            rSet.close();
					        }
					    } else {
					        System.out.println("No Records Found");
					    }
					} catch (SQLException e) {
					    System.out.println("Error retrieving publication records: " + e.getMessage());
					}
					break;
				case "15":
					updatePublication(keyboard, dao);
					break;
				case "16":
					deletePublication(keyboard, dao);
					break;
				case "17":
					createOrder(keyboard, dao);
						break;
				case "18":
					try (ResultSet rSet = dao.retrieveAllOrders()) {
					    if (rSet != null) {
					        boolean tablePrinted = printOrderTable(rSet);
					        if (tablePrinted) {
					            rSet.close();
					        }
					    } else {
					        System.out.println("No Records Found");
					    }
					} catch (SQLException e) {
					    System.out.println("Error retrieving Order records: " + e.getMessage());
					}
					break;
				case "19":
					updateOrder(keyboard, dao);
					break;
				case "20":
					deleteOrder(keyboard, dao);
					break;
				case "21":
					createInvoice(keyboard, dao);
						break;
				case "22":
					try (ResultSet rSet = dao.retrieveAllInvoices()) {
					    if (rSet != null) {
					        boolean tablePrinted = printInvoiceTable(rSet);
					        if (tablePrinted) {
					            rSet.close();
					        }
					    } else {
					        System.out.println("No Records Found");
					    }
					} catch (SQLException e) {
					    System.out.println("Error retrieving publication records: " + e.getMessage());
					}
					break;
				case "23":
					updateInvoice(keyboard, dao);
					break;
				case "24":
					deleteInvoice(keyboard, dao);
					break;
				case "25":
					createDeliveryDocket(keyboard, dao);
						break;
				case "26":
					try (ResultSet rSet = dao.retrieveAllDeliveryDockets()) {
					    if (rSet != null) {
					        boolean tablePrinted = printDeliveryDocketTable(rSet);
					        if (tablePrinted) {
					            rSet.close();
					        }
					    } else {
					        System.out.println("No Records Found");
					    }
					} catch (SQLException e) {
					    System.out.println("Error retrieving delivery dockets: " + e.getMessage());
					}
					break;
				case "27":
					updateDeliveryDocket(keyboard, dao);
					break;
				case "28":
					deleteDeliveryDocket(keyboard, dao);
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
