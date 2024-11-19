import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandLine {
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static void listCustomerFuctionalityAvailable() {
		
		//Present Customer with Functionality 	Options
		
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
//		System.out.println("1. Create Customer Account");
//		System.out.println("2. View ALL Customer Records");
//		System.out.println("3. Update Customer Record by ID");
//        System.out.println("4. Delete Inactive Customer Records");
//        //CRUD for Delivery Person
//        System.out.println("5. Create Delivery Person");
//        System.out.println("6. Read Delivery Person");
//        System.out.println("7. Update Delivery Person");
//        System.out.println("8. Delete Delivery Person");
//        //CRUD for Invoice (Duplicate 1 of 2)
//        System.out.println("9. Create Invoice");
//        System.out.println("10. Update Invoice");
//        System.out.println("11. Cancel Invoice");
//        System.out.println("12. View All Invoices");
//        //CRUD for Publication
//        System.out.println("13. Create Publication");
//        System.out.println("14. Read Publication");
//        System.out.println("15. Update Publication");
//        System.out.println("16. Delete Publication");
//        //CRUD for Order
//        System.out.println("17. Create Order");
//        System.out.println("18. Read Order");
//        System.out.println("19. Update Order");
//        System.out.println("20. Delete Order");
//        //CRUD for Invoice (Duplicate 2 of 2)
//        System.out.println("21. Create Invoice");
//        System.out.println("22. Read Invoice");
//        System.out.println("23. Update Invoice");
//        System.out.println("24. Delete Invoice");
//        //CRUD for Delivery Docket
//        System.out.println("25. Create Delivery Docket");
//        System.out.println("26. Read Delivery Docket");
//        System.out.println("27. Update Delivery Docket");
//        System.out.println("28. Delete Delivery Docket");
//        //CRUD for Delivery Area
//        System.out.println("31. Create Delivery Area");
//        System.out.println("32. View All Delivery Areas");
//        System.out.println("33. Update Delivery Area by ID");
//        System.out.println("34. Delete Delivery Area by ID");
//        
//        //CRUD for Warning Letter:
//        System.out.println("35. Create Warning Letter");
//        System.out.println("36. View All Warning Letters");
//        System.out.println("37. Update Warning Letter by ID");
//        System.out.println("38. Delete Warning Letter by ID");
//        
//        //CRUD for Newsagent
//        System.out.println("41. Create Newsagent");
//        System.out.println("42. View All Newsagents");
//        System.out.println("43. Update Newsagent by Name");
//        System.out.println("44. Delete Newsagent by Name");
//        
//        //CRUD for Storage
//        System.out.println("45. Create Storage Record");
//        System.out.println("46. View All Storage Records");
//        System.out.println("47. Update Storage Record by ID");
//        System.out.println("48. Delete Storage Record by ID");



//		System.out.println("=============================================");
//		System.out.println(" ");
		///////////////////////////////////////////////////////////////////
		System.out.println("101. Customer CRUD Actions");
		System.out.println("102. Delivery Person CRUD Actions");
		System.out.println("103. Invoice CRUD Actions");
		System.out.println("104. Publication Actions");
		System.out.println("105. Order CRUD Actions");
		System.out.println("106. Delivery Docket CRUD Actions");
		System.out.println("107. Delivery Area CRUD Actions");
		System.out.println("108. Warning Letter CRUD Actions");
		System.out.println("109. Newsagent CRUD Actions");
		System.out.println("110. Storage Record CRUD Actions");
		
		System.out.println(" ");
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
        System.out.printf("Enter New Customer Name (Length: 2-50): \n");
        String custName = keyboard.nextLine();
        System.out.printf("Enter New Customer Address (Length: 5-60): \n");
        String custAddr = keyboard.nextLine();
        System.out.printf("Enter New Customer PhoneNumber (Length: 7-15): \n");
        String custPhoneNumber = keyboard.nextLine();
        System.out.printf("Enter New Customer Email (Length: 5-50): \n");
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
		System.out.printf("Enter Delivery Person First Name (Length: 1-15): \n");
        String firstName = keyboard.nextLine();
        
        System.out.printf("Enter Delivery Person Last Name (Length: 1-25): \n");
        String lastName = keyboard.nextLine();
        
        System.out.printf("Enter Delivery Person ID (format DP123): \n");
        String id = keyboard.nextLine().toUpperCase();
        
        System.out.printf("Enter Delivery Person Phone Number (Length: 10): \n");
        String phone = keyboard.nextLine();
        
        System.out.printf("Enter Assigned Area (format AREA00): \n");
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
	    System.out.println("Enter Delivery Person ID to update (format DP000):");
	    String updateId = keyboard.nextLine();

	    // Get updated details
	    System.out.printf("Enter New Delivery Person First Name (Length: 1-15): \n");
	    String firstName = keyboard.nextLine();
	    System.out.printf("Enter New Delivery Person Last Name (Length: 1-25): \n");
	    String lastName = keyboard.nextLine();
	    System.out.printf("Enter New Delivery Person Phone Number (10 digits): \n");
	    String phone = keyboard.nextLine();
	    System.out.printf("Enter New Assigned Area (format AREA00): \n");
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
        if(dao.deleteDeliveryPersonById(deleteId)) {
            boolean deleteResult = true;
            System.out.println("Delivery Person deleted successfully.");
        }
        else {
        	System.out.println("Id does not exist, Please enter an existing id to update.)");
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
        keyboard.nextLine(); //Consume new line please
        
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
	        String title = rs.getString("title");
	        int stock = rs.getInt("number_in_stocks");
	        double price = rs.getDouble("price");
	        String type = rs.getString("type");
	        String frequency = rs.getString("delivery_frequency");

	        System.out.printf("%30s", id);
	        System.out.printf("%30s", title);
	        System.out.printf("%30d", stock);
	        System.out.printf("%30s", type);
	        System.out.printf("%30.2f", price);
	        System.out.printf("%30s", frequency);
	        System.out.println();
		}
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
	    keyboard.nextLine();
	    System.out.println("Enter New Type of Publication (Newspaper/Magazine): \n");
	    String type = keyboard.next();
	    System.out.println("Enter New Delivery Frequency (Daily/Weekly/Monthly): \n");
	    String status = keyboard.next();
	
	    try {
	        // Create a new Publication object with the updated details
	        Publication publication = new Publication(updateId, title,stock,price, type, status);
	        boolean updateResult = dao.updatePublicationDetails(publication); // You'll need to implement this method in MySQLAccess
	        System.out.println(updateResult ? "Publication Details Updated" : "ERROR: Publication Details NOT Updated");
	    } catch (PublicationException e) {
	        System.out.println("ERROR: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    }
	}
	private static void deletePublication(Scanner keyboard, MySQLAccess dao) {
		System.out.println("Enter Publication ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Publication ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String deletepublicationId = keyboard.next().toUpperCase();
		if ("DELETEALL".equals(deletepublicationId)) {
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
    	keyboard.nextLine();
    	
    	System.out.println("Enter delivery ID (format AREA01): \n");
    	String deliveryId = keyboard.nextLine();
    	
    	System.out.println("Enter publication ID (format PUB001): \n");
    	String publicationId = keyboard.nextLine();
        
        System.out.print("Enter Order Date (YYYY-MM-DD), or leave empty for today: \n");
        String orderDateStr = keyboard.nextLine();
        Optional<LocalDate> orderDate= orderDateStr.isEmpty() 
                ? Optional.empty() 
                : Optional.of(LocalDate.parse(orderDateStr));

    	System.out.println("Enter the order status (format PENDING/CONFIRMED/DISPATCHED/DELIVERED/CANCELLED/POSTPONED): \n");
    	String input = keyboard.nextLine();
    	OrderStatus orderStatus;
    	try {
    	    orderStatus = OrderStatus.valueOf(input.toUpperCase());
    	} catch (IllegalArgumentException e) {
    	    throw new CustomerExceptionHandler("Invalid order status entered");
    	}
    	
        
        try {
        	Order order = new Order(orderId, custId, deliveryId, publicationId,orderDate, orderStatus);
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
	        String orderStatus = rs.getString("order_status");
	        
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
	    keyboard.nextLine();
	    
	    System.out.println("Enter Delivery ID (format: AREA00): \n");
	    String deliveryId = keyboard.nextLine();
	    
	    System.out.println("Enter Publication ID (format: PUB001): \n");
	    String publicationId = keyboard.nextLine();
	    
	    System.out.print("Enter Order Date (YYYY-MM-DD), or leave empty for today: \n");
        String orderDateStr = keyboard.nextLine();
        Optional<LocalDate> orderDate= orderDateStr.isEmpty() 
                ? Optional.empty() 
                : Optional.of(LocalDate.parse(orderDateStr));

	    System.out.println("Enter the order status (format PENDING/CONFIRMED/DISPATCHED/DELIVERED/CANCELLED/POSTPONED): \n");
	    String input = keyboard.nextLine();
	    OrderStatus orderStatus;
	    try {
	        orderStatus = OrderStatus.valueOf(input.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        throw new CustomerExceptionHandler("Invalid order status entered.");
	    }

	    // Attempt to update order
	    try {
	        Order order = new Order(orderId, custId, deliveryId, publicationId, orderDate, orderStatus);
	        boolean updateResult = dao.updateOrderDetails(order); // Assuming dao.updateOrderDetails updates an order
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
        String payment_method = keyboard.nextLine();
        
        System.out.print("Enter Issue Date (YYYY-MM-DD), or leave empty for today: \n");
        String orderDateStr = keyboard.nextLine();
        Optional<LocalDate> order_date= orderDateStr.isEmpty() 
                ? Optional.empty() 
                : Optional.of(LocalDate.parse(orderDateStr));
        
        
        System.out.println("Enter the Total Amount: \n");
        Double total_amount = keyboard.nextDouble();
        
        System.out.println("Enter the delivery docket ID (format DD00001): \n");
        String delivery_docket_id = keyboard.next();
        
        System.out.println("Enter the Publication ID (format PUB001): \n");
        String publication_id = keyboard.next();
        
        System.out.println("Enter the Order Status (format Pending,Paid,Cancelled,Refunded): \n");
        String order_status = keyboard.next();
        
        try {
        	 Invoice invoice = new Invoice(invoice_id, cust_id, payment_method, order_date, total_amount, delivery_docket_id, publication_id, order_status);
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
	        String delivery_docket_id = rs.getString("delivery_docket_id");
	        String publication_id = rs.getString("publication_id");
	        String order_status = rs.getString("order_status");
	        
			System.out.printf("%30s", invoice_id);
			System.out.printf("%30s", cust_id);
			System.out.printf("%30s", payment_method);
			System.out.printf("%30s", order_date);
			System.out.printf("%30s", total_amount); 
	        System.out.printf("%30s", delivery_docket_id);
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
    System.out.println("Enter payment method (cash or card): \n");
    String payment_method = keyboard.nextLine();

    System.out.print("Enter Issue Date (YYYY-MM-DD), or leave empty for today: \n");
    String orderDateStr = keyboard.nextLine();
    Optional<LocalDate> order_date= orderDateStr.isEmpty() 
            ? Optional.empty() 
            : Optional.of(LocalDate.parse(orderDateStr));

    System.out.println("Enter Total Amount: \n");
    Double total_amount = keyboard.nextDouble();
    System.out.println("Enter Delivery Docket ID (format DD00001):");
    String delivery_docket_id = keyboard.next();
    System.out.println("Enter Publication ID (format PUB000): \n");
    String publication_id = keyboard.next();
    System.out.println("Enter Order Status (format Pending,Paid,Cancelled,Refunded): \n");
    String order_status = keyboard.next();

    try {
        // Create a new Publication object with the updated details
        Invoice invoice = new Invoice(invoice_id, cust_id, payment_method, order_date, total_amount, delivery_docket_id, publication_id, order_status);
        boolean updateResult = dao.updateInvoiceDetails(invoice); // You'll need to implement this method in MySQLAccess
        System.out.println(updateResult ? "Invoice Details Updated" : "ERROR: Invoice Details NOT Updated");
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}
	private static void deleteInvoice(Scanner keyboard, MySQLAccess dao) {
		System.out.println("Enter Invoice ID to be deleted or 'DELETEALL' to Clear all Rows:");
		while (!keyboard.hasNext()) {
		    System.out.println("Invalid input. Please enter a valid Invoice ID or DELETEALL.");
		    keyboard.next();  // Consume invalid input
		}
		String input = keyboard.next().toUpperCase().trim();

	    // If input is DELETEALL, delete all invoices
	    if ("DELETEALL".equals(input)) {
	        boolean deleteAllResult = dao.deleteAllinvoices();
	        if (deleteAllResult) {
	            System.out.println("Invoice Table Emptied");
	        } else {
	            System.out.println("ERROR: Could not empty the table");
	        }
	    } else {
	        // Otherwise, delete a specific invoice by ID
	        boolean deleteResult = dao.deleteInvoiceById(input);
	        if (deleteResult) {
	            System.out.println("Invoice Deleted");
	        } else {
	            System.out.println("ERROR: Invoice Details NOT Deleted or Do Not Exist");
	        }
	    }
	}
	private static void createDeliveryDocket(Scanner keyboard, MySQLAccess dao) {
    	//Implementation for creating Publication
    	System.out.println("Enter the Delivery Docket ID (format DD00001): \n");
    	String docket_id = keyboard.nextLine();
        
        System.out.println("Enter the Order ID (format ORD0001): \n");
        String order_id = keyboard.nextLine();
        
        System.out.println("Enter Delivery ID (format DP123): \n");
        String delivery_id = keyboard.nextLine();
        
        System.out.println("Enter the Delivery Date (leave empty for today): ");
        String delivery_dateInput = keyboard.nextLine();
        Optional<LocalDate> delivery_date = delivery_dateInput.isEmpty()
        		? Optional.empty() 
                : Optional.of(LocalDate.parse(delivery_dateInput));
        
        System.out.println("Enter the Delivery Status (Delivered, Out for delivery, Not delivered): \n");
        String delivery_status = keyboard.nextLine();
        
        System.out.println("Enter the Delivery Details: \n");
        String delivery_details = keyboard.nextLine();
        
        
        try {
        	 DeliveryDocket deliveryDocket = new DeliveryDocket(docket_id, order_id, delivery_id, delivery_date ,delivery_status, delivery_details);
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
	        String delivery_person_id = rs.getString("delivery_person_id");
	        Date delivery_date = rs.getDate("delivery_date");
	        String delivery_status = rs.getString("delivery_status");
	        String delivery_details = rs.getString("delivery_details");
	        
			System.out.printf("%30s", docket_id);
			System.out.printf("%30s", order_id);
			System.out.printf("%30s", delivery_person_id);
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
	    System.out.println("Enter Delivery Docket ID to update (format DD00001):");
	    String docket_id = keyboard.nextLine();
	
	    // Get updated details
	    System.out.println("Enter Order ID (format ORD0001): \n");
	    String order_id = keyboard.nextLine();
        System.out.println("Enter Delivery ID (format DP123): \n");
        String delivery_id = keyboard.nextLine();
        System.out.println("Enter the Delivery Date (leave empty for today): ");
        String delivery_dateInput = keyboard.nextLine();
        Optional<LocalDate> delivery_date = delivery_dateInput.isEmpty()
        		? Optional.empty() 
                : Optional.of(LocalDate.parse(delivery_dateInput));
        
        System.out.println("Enter the Delivery Status (Delivered, Out for delivery, Not delivered): \n");
        String delivery_status = keyboard.nextLine();
        
        System.out.println("Enter the Delivery Details: \n");
        String delivery_details = keyboard.nextLine();
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

	    System.out.println("Enter Delivery Docket ID to be deleted or 'DELETEALL' to clear all rows:");
	    String deleteDeliveryDocketId = keyboard.nextLine().trim().toUpperCase();
	    
	    if ("DELETEALL".equals(deleteDeliveryDocketId)) {
	        boolean deleteAllResult = dao.deleteAllDeliveryDockets();
	        if (deleteAllResult) {
	            System.out.println("Delivery Docket Table Emptied");
	        } else {
	            System.out.println("ERROR: Could not empty the table");
	        }
	    } else {
	        boolean deleteResult = dao.deleteDeliveryDocketById(deleteDeliveryDocketId);
	        if (deleteResult) {
	            System.out.println("Delivery Docket Deleted");
	        } else {
	            System.out.println("ERROR: Delivery Docket Details NOT Deleted or Do Not Exist");
	        }
	    }
	}
	//DELIVERY AREA CRUD 
	// Insert a new delivery area
	private static void insertDeliveryArea(Scanner scanner, MySQLAccess dao) {
        try {
            System.out.print("Enter Area ID (AREA00): ");
            String areaId = scanner.nextLine();

            System.out.print("Enter Area Name: ");
            String areaName = scanner.nextLine();

            System.out.print("Enter Delivery Person ID (DP000): ");
            String deliveryPersonId = scanner.nextLine();

            System.out.print("Enter Total Customers: ");
            int totalCustomers = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Create the DeliveryArea object
            DeliveryArea area = new DeliveryArea(areaId, areaName, deliveryPersonId, totalCustomers);

            // Insert into the database
            boolean result = dao.insertDeliveryArea(area);
            System.out.println(result ? "Delivery area inserted successfully!" : "Failed to insert delivery area.");
        } catch (DeliveryAreaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
	// Print all delivery areas in CLI
	private static void viewAllDeliveryAreas(MySQLAccess dao) {
	    try (ResultSet rs = dao.getAllDeliveryAreas()) {  // Assume getAllDeliveryAreas() returns all delivery area records
	        if (rs != null) {
	            printDeliveryAreaTable(rs);
	            rs.close();
	        } else {
	            System.out.println("No Delivery Areas Found");
	        }
	    } catch (SQLException e) {
	        System.out.println("ERROR: Failed to retrieve delivery areas.");
	        e.printStackTrace();
	    }
	}

	// Print Delivery Area Table
	private static void printDeliveryAreaTable(ResultSet rs) throws SQLException {
	    System.out.println("--------------------------------------------------------------------------------------------------------");
	    System.out.println("Table: " + rs.getMetaData().getTableName(1));
	    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	        System.out.printf("%30s", rs.getMetaData().getColumnName(i));
	    }
	    System.out.println();
	    while (rs.next()) {
	        String areaId = rs.getString("area_id");
	        String areaName = rs.getString("area_name");
	        String deliveryPersonId = rs.getString("delivery_person_id");
	        int totalCustomers = rs.getInt("total_customers");

	        System.out.printf("%30s", areaId);
	        System.out.printf("%30s", areaName);
	        System.out.printf("%30s", deliveryPersonId);
	        System.out.printf("%30d", totalCustomers);
	        System.out.println();
	    }
	    System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	
	// Update a delivery area
    private static void updateDeliveryArea(Scanner scanner, MySQLAccess dao) {
        try {
            System.out.print("Enter Area ID to update: ");
            String areaId = scanner.nextLine();

            System.out.print("Enter New Area Name: ");
            String areaName = scanner.nextLine();

            System.out.print("Enter New Delivery Person ID: ");
            String deliveryPersonId = scanner.nextLine();

            System.out.print("Enter New Total Customers: ");
            int totalCustomers = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Create the DeliveryArea object with updated details
            DeliveryArea area = new DeliveryArea(areaId, areaName, deliveryPersonId, totalCustomers);

            // Update the database
            boolean result = dao.updateDeliveryArea(area);
            System.out.println(result ? "Delivery area updated successfully!" : "Failed to update delivery area.");
        } catch (DeliveryAreaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Delete a delivery area
    private static void deleteDeliveryArea(Scanner scanner, MySQLAccess dao) {
        System.out.print("Enter Area ID to delete: ");
        String areaId = scanner.nextLine();

        boolean result = dao.deleteDeliveryArea(areaId);
        System.out.println(result ? "Delivery area deleted successfully!" : "Failed to delete delivery area.");
    }
	
	//Warning letter CRUD:
	private static void insertWarningLetter(Scanner scanner, MySQLAccess dao) {
		System.out.print("Enter Warning Letter ID (format WL123): ");
        String letterId = scanner.nextLine();

        System.out.print("Enter Order ID (format ORD0001): ");
        String orderId = scanner.nextLine();

        System.out.print("Enter Customer ID (integer): ");
        int custId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Reason: ");
        String reason = scanner.nextLine();

        System.out.print("Enter Due Amount: ");
        double dueAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Issue Date (YYYY-MM-DD), or leave empty for today: ");
        String issueDateStr = scanner.nextLine();
        Optional<LocalDate> issueDate = issueDateStr.isEmpty() 
                ? Optional.empty() 
                : Optional.of(LocalDate.parse(issueDateStr));

        try {
            // Create a new WarningLetter object
            WarningLetter letter = new WarningLetter(letterId, orderId, custId, reason, dueAmount, issueDate);
            boolean result = dao.insertWarningLetter(letter);
            System.out.println(result ? "Warning letter inserted successfully!" : "Failed to insert warning letter.");
        } catch (CustomerExceptionHandler e) {
            System.out.println("ERROR: " + e.getMessage());
        }
	}
	private static boolean printWarningLetterTable(ResultSet rs) throws SQLException {
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    System.out.println("Table: " + rs.getMetaData().getTableName(1));
	    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	        System.out.printf("%30s", rs.getMetaData().getColumnName(i));
	    }
	    System.out.println();
	    while (rs.next()) {
	    	String letterId = rs.getString("letter_id");
	        String orderId = rs.getString("order_id");
	        String custId = rs.getString("cust_id");
	        String reason = rs.getString("reason");
	        double dueAmount = rs.getDouble("due_amount");
	        Date issueDate = rs.getDate("issue_date");

	        System.out.printf("%30s", letterId);
	        System.out.printf("%30s", orderId);  // Display the order ID
	        System.out.printf("%30s", custId);
	        System.out.printf("%30s", reason);
	        System.out.printf("%30.2f", dueAmount);
	        System.out.printf("%30s", issueDate != null ? issueDate.toString() : "N/A"); // Handle issue_date column
	        System.out.println();
	    }
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    return true;
	}

	private static void viewAllWarningLetters(MySQLAccess dao) {
	    try (ResultSet rs = dao.getAllWarningLetters()) { 
	        if (rs != null) {
	            boolean tablePrinted = printWarningLetterTable(rs);
	            if (tablePrinted) {
	                rs.close();
	            }
	        } else {
	            System.out.println("No Warning Letters Found");
	        }
	    } catch (SQLException e) {
	        System.out.println("ERROR: Failed to retrieve warning letters.");
	        e.printStackTrace();
	    }
	   
	}

	private static void updateWarningLetter(Scanner scanner, MySQLAccess dao) {
		System.out.print("Enter Warning Letter ID to update: ");
        String letterId = scanner.nextLine();

        //update order id and customer id not necessary, will only yield more underlying bugs.
//        System.out.print("Enter New Order ID (format ORD0001): ");
//        String orderId = scanner.nextLine();
//
//        System.out.print("Enter New Customer ID (integer): ");
//        int custId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Reason: ");
        String reason = scanner.nextLine();

        System.out.print("Enter New Due Amount: ");
        double dueAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Issue Date (YYYY-MM-DD), or leave empty for today: ");
        String issueDateStr = scanner.nextLine();
        Optional<LocalDate> issueDate = issueDateStr.isEmpty() 
                ? Optional.empty() 
                : Optional.of(LocalDate.parse(issueDateStr));

        try {
            // Create a new WarningLetter object with updated information
            WarningLetter letter = new WarningLetter(letterId, reason, dueAmount, issueDate);

            // Update the WarningLetter in the database
            boolean result = dao.updateWarningLetter(letter);
            System.out.println(result ? "Warning letter updated successfully!" : "Failed to update warning letter.");
        } catch (CustomerExceptionHandler e) {
            System.out.println("ERROR: " + e.getMessage());
        }
	}

	private static void deleteWarningLetter(Scanner scanner, MySQLAccess dao) {
	    System.out.print("Enter Warning Letter ID to delete: ");
	    String letterId = scanner.nextLine();

	    boolean result = dao.deleteWarningLetter(letterId);
	    System.out.println(result ? "Warning letter deleted successfully!" : "Failed to delete warning letter.");
	}

	
	//Newsagent CRUD
	private static void createNewsagent(Scanner keyboard, MySQLAccess dao) throws CustomerExceptionHandler {
	    System.out.printf("Enter Agent Name: \n");
	    String name = keyboard.nextLine();

	    System.out.printf("Enter Agent Address: \n");
	    String address = keyboard.nextLine();

	    System.out.printf("Enter Agent Phone Number: \n");
	    String phone = keyboard.nextLine();

	    System.out.printf("Enter Agent Email: \n");
	    String email = keyboard.nextLine();

	    Newsagent agent = new Newsagent(name, address, phone, email);
	    boolean result = dao.insertNewsagent(agent);
	    System.out.println(result ? "Newsagent created successfully!" : "ERROR: Failed to create newsagent.");
	}

	private static void viewAllNewsagents(MySQLAccess dao) {
	    try (ResultSet rs = dao.getNewsagent()) { 
	        while (rs.next()) {
	            String name = rs.getString("agent_name");
	            String address = rs.getString("agent_address");
	            String phone = rs.getString("agent_phone");
	            String email = rs.getString("agent_email");

	            System.out.println("Name: " + name);
	            System.out.println("Address: " + address);
	            System.out.println("Phone: " + phone);
	            System.out.println("Email: " + email);
	            System.out.println("--------------------");
	        }
	    } catch (SQLException | CustomerExceptionHandler e) {
	        e.printStackTrace();
	    }
	}

	private static void updateNewsagent(Scanner keyboard, MySQLAccess dao) throws CustomerExceptionHandler {
	    System.out.printf("Enter Agent Name to update: \n");
	    String name = keyboard.nextLine();

	    System.out.printf("Enter New Address: \n");
	    String address = keyboard.nextLine();

	    System.out.printf("Enter New Phone Number: \n");
	    String phone = keyboard.nextLine();

	    System.out.printf("Enter New Email: \n");
	    String email = keyboard.nextLine();

	    Newsagent agent = new Newsagent(name, address, phone, email);
	    boolean result = dao.updateNewsagent(agent);
	    System.out.println(result ? "Newsagent updated successfully!" : "ERROR: Failed to update newsagent.");
	}

	private static void deleteNewsagent(Scanner keyboard, MySQLAccess dao) {
	    System.out.printf("Enter Agent Name to delete: \n");
	    String name = keyboard.nextLine();

	    boolean result = dao.deleteNewsagent(name);
	    System.out.println(result ? "Newsagent deleted successfully!" : "ERROR: Failed to delete newsagent.");
	}
	
	//STORAGE CRUD CLI METHOD:
	private static void createStorage(Scanner keyboard, MySQLAccess dao) throws Storage.StorageException {
        System.out.printf("Enter Storage ID (format ST001): \n");
        String storageId = keyboard.nextLine().toUpperCase();

        System.out.printf("Enter Publication ID (format PUB001): \n");
        String publicationId = keyboard.nextLine().toUpperCase();

        System.out.printf("Enter Description Details: \n");
        String description = keyboard.nextLine();

        System.out.printf("Enter Capacity: \n");
        int capacity = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline

        System.out.printf("Enter Current Stock Level: \n");
        int currentStockLevel = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline

        Storage storage = new Storage(storageId, publicationId, description,capacity,currentStockLevel);
        
        try {
            boolean result = dao.insertStorage(storage);
            System.out.println(result ? "Storage record created successfully!" : "ERROR: Storage record creation failed.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

	private static boolean printStorageTable(ResultSet rs) throws SQLException {
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    System.out.println("Table: " + rs.getMetaData().getTableName(1));
	    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	        System.out.printf("%30s", rs.getMetaData().getColumnName(i));
	    }
	    System.out.println();
	    while (rs.next()) {
	    	String storageId = rs.getString("storage_id");
	        String publicationId = rs.getString("publication_id");
	        String description = rs.getString("description_details");
	        int capacity = rs.getInt("capacity");
	        int currentStockLevel = rs.getInt("current_stock");

	        System.out.printf("%30s", storageId);
	        System.out.printf("%30s", publicationId);
	        System.out.printf("%30s", description);
	        System.out.printf("%30d", capacity);  // Display capacity
	        System.out.printf("%30d", currentStockLevel);
	        System.out.println();
	    }
	    System.out.println("------------------------------------------------------------------------------------------------------");
	    return true;
	}

	private static void viewAllStorageRecords(MySQLAccess dao) {
	    try (ResultSet rs = dao.getAllStorage()) {  // Assume getAllStorage() returns all storage records
	        if (rs != null) {
	            boolean tablePrinted = printStorageTable(rs);
	            if (tablePrinted) {
//	                rs.close();
	            }
	        } else {
	            System.out.println("No Storage Records Found");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving storage records: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Unexpected error: " + e.getMessage());
	    }
	}

    private static void updateStorageRecord(Scanner keyboard, MySQLAccess dao) {
        System.out.printf("Enter Storage ID to update (format ST001): \n");
        String storageId = keyboard.nextLine().toUpperCase();
        
        System.out.printf("Enter Publication ID to update (format PUB001): \n");
        String publicationId = keyboard.nextLine().toUpperCase();

        System.out.printf("Enter New Description Details: \n");
        String description = keyboard.nextLine();

        System.out.printf("Enter New Capacity: \n");
        int capacity = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline

        System.out.printf("Enter New Current Stock Level: \n");
        int currentStockLevel = keyboard.nextInt();
        keyboard.nextLine(); // Consume newline

        try {
            Storage storage = new Storage(storageId, publicationId, description, capacity, currentStockLevel);
            boolean result = dao.updateStorage(storage);
            System.out.println(result ? "Storage record updated successfully!" : "ERROR: Storage record update failed.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void deleteStorageRecord(Scanner keyboard, MySQLAccess dao) {
        System.out.printf("Enter Storage ID to delete: \n");
        String storageId = keyboard.nextLine().toUpperCase();

        try {
            boolean result = dao.deleteStorage(storageId);
            System.out.println(result ? "Storage record deleted successfully!" : "ERROR: Storage record deletion failed.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private static void displayCustomerOrderSummary(Scanner keyboard, MySQLAccess dao) {
        System.out.print("Enter Customer ID: ");
        int customerId = keyboard.nextInt();  
        keyboard.nextLine();
        
        // Fetch customer details
        try {
        	
            ResultSet resultSet = dao.retrieveCustomerAndOrdersById(customerId);

            if (resultSet != null && resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                String customerAddress = resultSet.getString("customer_address");
                String customerPhone = resultSet.getString("customer_phone");
                String customerEmail = resultSet.getString("customer_email");
                boolean isSubscribed = resultSet.getBoolean("is_subscribed");

                System.out.println(" ");
                System.out.println("===============================================");
                System.out.println("Order Summary for " +customerName);
                System.out.println("===============================================");
                System.out.println("Customer Details:");
                System.out.printf("Name       : %s%n", customerName);
                System.out.printf("Address    : %s%n", customerAddress);
                System.out.printf("Phone      : %s%n", customerPhone);
                System.out.printf("Email      : %s%n", customerEmail);
                System.out.printf("Subscribed : %s%n", isSubscribed ? "Yes" : "No");
                System.out.println("===============================================");

                // Display orders
                System.out.println("Orders and Publications:");
                do {
                	String orderId = resultSet.getString("order_id");
                    String orderDate = resultSet.getString("order_date");
                    String orderStatus = resultSet.getString("order_status");
                    String publicationId = resultSet.getString("publication_id");
                    String publicationTitle = resultSet.getString("title");
                    String publicationType = resultSet.getString("type");
                    double publicationPrice = resultSet.getDouble("price");
                    String deliveryFrequency = resultSet.getString("delivery_frequency");

                    if (orderId != null) {
                        System.out.println("\nOrder Details:");
                        System.out.printf("  Order ID     : %s%n", orderId);
                        System.out.printf("  Order Date   : %s%n", orderDate);
                        System.out.printf("  Order Status : %s%n", orderStatus);
                        System.out.println("\nPublication Details:");
                        System.out.printf("    ID            : %s%n", publicationId);
                        System.out.printf("    Title         : %s%n", publicationTitle);
                        System.out.printf("    Type          : %s%n", publicationType);
                        System.out.printf("    Price         : $%.2f%n", publicationPrice);
                        System.out.printf("    Delivery Freq : %s%n", deliveryFrequency);
                        System.out.println("------------------------------------");
                    }
                    
                } while (resultSet.next());
                System.out.println("===============================================");
            } else {
                System.out.println("Customer not found or no associated orders.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer or orders: " + e.getMessage());
        }
    }
    private static void displayCustomerInvoices(Scanner keyboard, MySQLAccess dao) {
        System.out.print("Enter Customer ID: ");
        String customerId = keyboard.nextLine();

        try {
            ResultSet resultSet = dao.retrieveInvoicesWithDetails(customerId);

            if (resultSet != null && resultSet.next()) {
                do {
                    // Invoice Header
                    String invoiceId = resultSet.getString("invoice_id");
                    String orderDate = resultSet.getString("order_date");
                    String orderStatus = resultSet.getString("order_status");
                    String paymentMethod = resultSet.getString("payment_method");
                    double totalAmount = resultSet.getDouble("total_amount");

                    System.out.println("====================================");
                    System.out.println("          INVOICE SUMMARY           ");
                    System.out.println("====================================");
                    System.out.printf("Invoice ID        : %s%n", invoiceId);
                    System.out.printf("Date Issued       : %s%n", orderDate);
                    System.out.printf("Order Status      : %s%n", orderStatus);
                    System.out.printf("Payment Method    : %s%n", paymentMethod);
                    System.out.println("------------------------------------");

                    // Customer Details
                    String customerName = resultSet.getString("customer_name");
                    String customerAddress = resultSet.getString("customer_address");
                    String customerPhone = resultSet.getString("customer_phone");
                    String customerEmail = resultSet.getString("customer_email");

                    System.out.println("Customer Details:");
                    System.out.printf("  Name    : %s%n", customerName);
                    System.out.printf("  Address : %s%n", customerAddress);
                    System.out.printf("  Phone   : %s%n", customerPhone);
                    System.out.printf("  Email   : %s%n", customerEmail);
                    System.out.println("------------------------------------");

                    // Order Details
                    String publicationId = resultSet.getString("publication_id");
                    String title = resultSet.getString("title");
                    String type = resultSet.getString("type");
                    double price = resultSet.getDouble("price");
                    String deliveryFrequency = resultSet.getString("delivery_frequency");

                    System.out.println("Order Details:");
                    System.out.printf("  Publication ID : %s%n", publicationId);
                    System.out.printf("  Title          : %s%n", title);
                    System.out.printf("  Type           : %s%n", type);
                    System.out.printf("  Price          : $%.2f%n", price);
                    System.out.printf("  Frequency      : %s%n", deliveryFrequency);
                    System.out.println("------------------------------------");

                    // Delivery Details
                    String docketId = resultSet.getString("docket_id");
                    String deliveryDate = resultSet.getString("delivery_date");
                    String deliveryStatus = resultSet.getString("delivery_status");
                    String deliveryDetails = resultSet.getString("delivery_details");

                    System.out.println("Delivery Details:");
                    System.out.printf("  Docket ID      : %s%n", docketId);
                    System.out.printf("  Delivery Date  : %s%n", deliveryDate);
                    System.out.printf("  Delivery Status: %s%n", deliveryStatus);
                    System.out.printf("  Instructions   : %s%n", deliveryDetails);
                    System.out.println("------------------------------------");

                    // Total Amount
                    System.out.printf("Total Amount: $%.2f%n", totalAmount);
                    System.out.println("====================================");
                    System.out.println();
                } while (resultSet.next());
            } else {
                System.out.println("No invoices found for this customer.");
            }
        } catch (SQLException e) {
            System.out.println("Error displaying invoices: " + e.getMessage());
        }
    }

    private static void generateWarningLetter(Scanner keyboard, MySQLAccess dao) throws NumberFormatException, CustomerExceptionHandler {
        System.out.print("Enter Customer ID: ");
        String customerId = keyboard.nextLine();

        try {
            ResultSet resultSet = dao.retrieveInvoicesWithDetails(customerId);
            
            if (resultSet != null && resultSet.next()) {
                System.out.print("Enter Invoice ID to generate warning letter for: ");
                String invoiceId = keyboard.nextLine();

                double totalAmount = 0;
                boolean isOverdue = false;
                String customerName = "";
                int monthsOverdue = 0;
                String orderId = resultSet.getString("order_id");
                do {
                    String currentInvoiceId = resultSet.getString("invoice_id");
                    if (currentInvoiceId.equals(invoiceId)) {
                        totalAmount = resultSet.getDouble("total_amount");
                        String orderStatus = resultSet.getString("order_status");
//                        String orderId = resultSet.getString("order_id");
                        customerName = resultSet.getString("customer_name");
                        Date orderDate = resultSet.getDate("order_date");

                        // Check if the invoice is overdue
                        isOverdue = "Overdue".equalsIgnoreCase(orderStatus);

                        if (isOverdue) {
                            // Calculate months overdue
                            LocalDate currentDate = LocalDate.now();
                            LocalDate orderLocalDate = orderDate.toLocalDate();
                            monthsOverdue = Period.between(orderLocalDate, currentDate).getMonths() + 
                                            Period.between(orderLocalDate, currentDate).getYears() * 12;
                        }
                        break;
                    }
                } while (resultSet.next());

                if (!isOverdue) {
                    System.out.println("The selected invoice is not overdue.");
                    return;
                }

                System.out.print("Enter Reason for Warning Letter: ");
                String reason = keyboard.nextLine();

                String letterId = "WL" +  String.format("%03d", (int)(System.currentTimeMillis() % 1000)); // Unique letter ID
                dao.insertWarningLetter(new WarningLetter(letterId, orderId, Integer.parseInt(customerId), reason, totalAmount, Optional.empty()));

                System.out.println("====================================");
                System.out.println("       WARNING LETTER GENERATED     ");
                System.out.println("====================================");

                // Generate content based on months overdue
                String warningLetter;
                if (monthsOverdue == 1) {
                    warningLetter = String.format(
                            """
                            Dear %s,

                            Subject: Reminder of Overdue Payment - First Notice

                            We are writing to inform you that your first payment for the invoice (Invoice ID: %s) is overdue. 
                            As of today, an outstanding amount of $%.2f remains unpaid. Please find your first invoice attached to this letter.

                            It is important that you address this payment as soon as possible to avoid any service interruptions. 
                            If you have already made the payment, please disregard this message. Otherwise, we request you to clear the amount by the earliest possible date.

                            Thank you for your prompt attention to this matter.

                            Sincerely,
                            Athlone Newspaper
                            """,
                            customerName, invoiceId, totalAmount
                    );
                } else if (monthsOverdue >= 2) {
                    warningLetter = String.format(
                            """
                            Dear %s,

                            Subject: Final Reminder of Overdue Payment and Suspension Notice

                            This is a final reminder regarding the overdue payment for your invoice (Invoice ID: %s). As of today, 
                            your account shows an unpaid balance of $%.2f, including the first invoice. 

                            Please note that failure to settle this balance within the next 7 days will result in the suspension of your delivery services. 
                            Additionally, we have attached your third invoice along with this letter.

                            We strongly encourage you to make the payment immediately to avoid service disruption. If you have already made the payment, please notify us.

                            Thank you for your immediate attention.

                            Sincerely,
                            Athlone Newspaper
                            """,
                            customerName, invoiceId, totalAmount
                    );
                } else {
                    warningLetter = String.format(
                            """
                            Dear %s,

                            Subject: Notice of Overdue Payment

                            We are writing to remind you of an outstanding payment for your invoice (Invoice ID: %s). 
                            As of today, an amount of $%.2f remains unpaid.

                            Please make the payment as soon as possible to avoid further action. If you have already cleared the payment, please let us know.

                            Thank you.

                            Sincerely,
                            Athlone Newspaper
                            """,
                            customerName, invoiceId, totalAmount
                    );
                }

                System.out.println(warningLetter);
                System.out.println("====================================");
            } else {
                System.out.println("No invoices found for this customer.");
            }
        } catch (SQLException e) {
            System.out.println("Error generating warning letter: " + e.getMessage());
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
					System.out.printf("Enter Customer Name (Length: 2-50): \n");
					String custName = keyboard.nextLine();
					System.out.printf("Enter Customer Address (Length: 5-60): \n");
					String custAddr = keyboard.nextLine();
					System.out.printf("Enter Customer PhoneNumber (Length: 7-15): \n");
					String custphoneNumber = keyboard.nextLine();
					System.out.printf("Enter Customer Email (Length: 5-50): \n");
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
					// Delivery Area CRUD
			    case "31":
			        insertDeliveryArea(keyboard, dao);
			        break;
			    case "32":
			        viewAllDeliveryAreas(dao);
			        break;
			    case "33":
			        updateDeliveryArea(keyboard, dao);
			        break;
			    case "34":
			        deleteDeliveryArea(keyboard, dao);
			        break;
			        
			    // Warning Letter CRUD
			    case "35":
			        insertWarningLetter(keyboard, dao);
			        break;
			    case "36":
			        viewAllWarningLetters(dao);
			        break;
			    case "37":
			        updateWarningLetter(keyboard, dao);
			        break;
			    case "38":
			        deleteWarningLetter(keyboard, dao);
			        break;
			        
			    // Newsagent CRUD
			    case "41":
			        createNewsagent(keyboard, dao);
			        break;
			    case "42":
			        viewAllNewsagents(dao);
			        break;
			    case "43":
			        updateNewsagent(keyboard, dao);
			        break;
			    case "44":
			        deleteNewsagent(keyboard, dao);
			        break;
			        
			    // Storage CRUD
			    case "45":
			        createStorage(keyboard, dao);
			        break;
			    case "46":
			        viewAllStorageRecords(dao);
			        break;
			    case "47":
			        updateStorageRecord(keyboard, dao);
			        break;
			    case "48":
			        deleteStorageRecord(keyboard, dao);
			        
				case "99":
					System.out.println("Are you sure you want to exit? (yes/no): ");
					String exitConfirmation = keyboard.nextLine();
					if (exitConfirmation.equalsIgnoreCase("yes")) {
					    keepAppOpen = false;
					    System.out.println("Closing the Application");
					}
					break;
					
				case "101":
					customerCRUD();
				case "102":
					DPCRUD();
				case "103":
					InvoiceCRUD();
				case "104":
					PublicationCRUD();
				case "105":
					OrderCRUD();
				case "106":
					DDCRUD();
				case "107":
					DACRUD();
				case "108":
					WLCRUD();
				case "109":
					NewsagentCRUD();
				case "110":
					StorageCRUD();
			
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


	private static void customerCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listCustomerCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
							//Get Customer Details from the User
							System.out.printf("Enter Customer Name (Length: 2-50): \n");
							String custName = keyboard.nextLine();
							System.out.printf("Enter Customer Address (Length: 5-60): \n");
							String custAddr = keyboard.nextLine();
							System.out.printf("Enter Customer PhoneNumber (Length: 7-15): \n");
							String custphoneNumber = keyboard.nextLine();
							System.out.printf("Enter Customer Email (Length: 5-50): \n");
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
						    displayCustomerOrderSummary(keyboard, dao); // Call the new method here
						    break;
						case "6":
						    displayCustomerInvoices(keyboard, dao); // Call the new method here
						    break;
						case "55":
							//main(String[] args);
							GoBack();
							break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void GoBack() {
		// TODO Auto-generated method stub
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
				
//						
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
						case "101":
							customerCRUD();
						case "102":
							DPCRUD();
						case "103":
							InvoiceCRUD();
						case "104":
							PublicationCRUD();
						case "105":
							OrderCRUD();
						case "106":
							DDCRUD();
						case "107":
							DACRUD();
						case "108":
							WLCRUD();
						case "109":
							NewsagentCRUD();
						case "110":
							StorageCRUD();
					
						default:
							System.out.println("No Valid Function Selected");
							break;
						} // end switch
				
					}// end while
				
					//Tidy up Resources
					keyboard.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//finally{}
	}


	private static void listCustomerCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Customer Account");
		System.out.println("2. View ALL Customer Records");
		System.out.println("3. Update Customer Record by ID");
        System.out.println("4. Delete Inactive Customer Records");
        System.out.println("5. View Customer Order Summary"); 
        System.out.println("6. View Invoices Generated (for customer)"); 
        System.out.println("55. Go back.");
        System.out.println("99. Close Application");
	}
	private static void DPCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listDPCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createDeliveryPerson(keyboard, dao);
					        break;
					case "2":
							readDeliveryPerson(keyboard,dao);
					        
					        break;
					case "3":
							updateDeliveryPerson(keyboard, dao);
					       
					        break;
					case "4":
						 	deleteDeliveryPerson(keyboard, dao);
					        // Create invoice logic...
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listDPCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Person Account");
		System.out.println("2. View ALL Delivery Person Records");
		System.out.println("3. Update Delivery Person Record by ID");
        System.out.println("4. Delete Inactive Delivery Person Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void InvoiceCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listInvoiceCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createInvoice(keyboard, dao);
					        break;
					case "2":
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
						    System.out.println("Error retrieving Invoice records: " + e.getMessage());
						}
						break;
					case "3":
							updateInvoice(keyboard, dao);
					       
					        break;
					case "4":
						 	deleteInvoice(keyboard, dao);
					        // Create invoice logic...
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listInvoiceCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Invoice Account");
		System.out.println("2. View ALL Invoice Records");
		System.out.println("3. Update Invoice Record by ID");
        System.out.println("4. Delete Invoice Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void PublicationCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listPublicationCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createPublication(keyboard, dao);
					        break;
					case "2":
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
						    System.out.println("Error retrieving Publication records: " + e.getMessage());
						}
						break;
					case "3":
							updatePublication(keyboard, dao);
					       
					        break;
					case "4":
						 	deletePublication(keyboard, dao);
					        // Create invoice logic...
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listPublicationCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Publication Account");
		System.out.println("2. View ALL Publication Records");
		System.out.println("3. Update Publication Record by ID");
        System.out.println("4. Delete Publication Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void OrderCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listOrderCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createOrder(keyboard, dao);
					        break;
					case "2":
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
						    System.out.println("Error retrieving Orders records: " + e.getMessage());
						}
						break;
					case "3":
							updateOrder(keyboard, dao);
					       
					        break;
					case "4":
						 	deleteOrder(keyboard, dao);
					        // Create invoice logic...
					        break;

					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listOrderCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Order Account");
		System.out.println("2. View ALL Order Records");
		System.out.println("3. Update Order Record by ID");
        System.out.println("4. Delete Order Record");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void DDCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listDDCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createDeliveryDocket(keyboard, dao);
					        break;
					case "2":
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
						    System.out.println("Error retrieving DeliveryDocket records: " + e.getMessage());
						}
						break;
					case "3":
							updateDeliveryDocket(keyboard, dao);
					       
					        break;
					case "4":
						 	deleteDeliveryDocket(keyboard, dao);
					        // Create invoice logic...
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listDDCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Docket Record");
		System.out.println("2. View ALL Delivery Docket Records");
		System.out.println("3. Update Delivery Docket Record by ID");
        System.out.println("4. Delete Delivery Docket Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void DACRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listDACRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        insertDeliveryArea(keyboard, dao);
					        break;
					    case "2":
					        viewAllDeliveryAreas(dao);
					        break;
					    case "3":
					        updateDeliveryArea(keyboard, dao);
					        break;
					    case "4":
					        deleteDeliveryArea(keyboard, dao);
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listDACRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Area Account");
		System.out.println("2. View ALL Delivery Area Records");
		System.out.println("3. Update Delivery Area Record by ID");
        System.out.println("4. Delete Inactive Area Person Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void WLCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listWLCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        insertWarningLetter(keyboard, dao);
					        break;
					    case "2":
					        viewAllWarningLetters(dao);
					        break;
					    case "3":
					        updateWarningLetter(keyboard, dao);
					        break;
					    case "4":
					        deleteWarningLetter(keyboard, dao);
					        break;
					    case "5":
					        generateWarningLetter(keyboard, dao);
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listWLCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Warning Letter");
		System.out.println("2. View ALL Warning Letters");
		System.out.println("3. Update Warning Letter");
        System.out.println("4. Delete Warning Letter");
        System.out.println("5. Print out Warning Letter");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void NewsagentCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listNewsagentCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						case "1":
					        createNewsagent(keyboard, dao);
					        break;
					    case "2":
					        viewAllNewsagents(dao);
					        break;
					    case "3":
					        updateNewsagent(keyboard, dao);
					        break;
					    case "4":
					        deleteNewsagent(keyboard, dao);
					        break;
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listNewsagentCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Newsagent Account");
		System.out.println("2. View ALL Newsagent Records");
		System.out.println("3. Update Newsagent Record by ID");
        System.out.println("4. Delete Newsagent Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	private static void StorageCRUD() {
		// TODO Auto-generated method stub
		//System.out.println("Hello");
		//Create the Database Object
		
				try {
					
					MySQLAccess dao = new MySQLAccess();

					// Configure System for Running
					Scanner keyboard = new Scanner(System.in); 
					String functionNumber = "-99";
					boolean keepAppOpen = true;
				
					while (keepAppOpen == true) {
					
						//Present list of functionality and get selection
						//listCustomerFuctionalityAvailable();
						listStorageCRUDAvailable();
						functionNumber = keyboard.next();
						keyboard.nextLine(); 
				
						switch (functionNumber) {
				
						// Newsagent CRUD
						case "1":
					        createStorage(keyboard, dao);
					        break;
					    case "2":
					        viewAllStorageRecords(dao);
					        break;
					    case "3":
					        updateStorageRecord(keyboard, dao);
					        break;
					    case "4":
					        deleteStorageRecord(keyboard, dao);
					case "55":
						//main(String[] args);
						GoBack();
						break;
					        
						case "99":
							System.out.println("Are you sure you want to exit? (yes/no): ");
							String exitConfirmation = keyboard.nextLine();
							if (exitConfirmation.equalsIgnoreCase("yes")) {
							    keepAppOpen = false;
							    System.out.println("Closing the Application");
							}
							break;
							
//						case "101":
//							customerCRUD();
					
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
	}


	private static void listStorageCRUDAvailable() {
		// TODO Auto-generated method stub
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Storage Record");
		System.out.println("2. View ALL Storage Records");
		System.out.println("3. Update Storage Record by ID");
        System.out.println("4. Delete Storage Records");
        System.out.println("55. Go Back");
        System.out.println("99. Close Application");
	}
	
	
}
