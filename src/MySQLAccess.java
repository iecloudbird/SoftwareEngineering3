	import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
	
	
	public class MySQLAccess {
		
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		final private String databaseName = "newsagent";
		final private String host ="localhost:3307";
		final private String user = "root";
		final private String password = "";
		
		
		public MySQLAccess() throws Exception {
			
			try {
				
				//Load MySQL Driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//Setup the connection with the DB
				connect = DriverManager.getConnection("jdbc:mysql://" + host + "/newsagent?" + "user=" + user + "&password=" + password);
			}
			catch (Exception e) {
				throw e;
			}
			
			
		}	
	
		public boolean insertCustomerDetailsAccount(Customer c) {
		
			boolean insertSucessfull = true;
		
			//Add Code here to call embedded SQL to insert Customer into DB
		
			try {
			
				//Create prepared statement to issue SQL query to the database
				preparedStatement = connect.prepareStatement("INSERT INTO newsagent.customers (customer_name, customer_address, customer_phone, customer_email, is_subscribed) VALUES (?, ?, ?, ?, ?)");
				preparedStatement.setString(1, c.getName());
				preparedStatement.setString(2, c.getAddress());
				preparedStatement.setString(3, c.getPhoneNumber());
				preparedStatement.setString(4, c.getEmail());
				preparedStatement.setBoolean(5, c.getSubscriptionStatus());
				preparedStatement.executeUpdate();
			
		 
			}
			catch (Exception e) {
				insertSucessfull = false; 
				e.printStackTrace(); 
				
			}
		
			return insertSucessfull;
			
		}// end insertCustomerDetailsAccount
	
		public ResultSet retrieveAllCustomerAccounts() {
			
			//Add Code here to call embedded SQL to view Customer Details
		
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.customers");
			
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		
		public boolean updateCustomerDetails(int customerId, String name, String address, String phoneNumber, String email) {
		    boolean updateSuccessful = false;
		    String query = "UPDATE newsagent.customers SET customer_name = ?, customer_address = ?, customer_phone = ?, customer_email = ? WHERE customer_id = ?";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setString(1, name);
		        preparedStatement.setString(2, address);
		        preparedStatement.setString(3, phoneNumber);	
		        preparedStatement.setString(4, email);
		        preparedStatement.setInt(5, customerId);

		        int rowsAffected = preparedStatement.executeUpdate();
		        updateSuccessful = rowsAffected > 0; 
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return updateSuccessful;
		}
		
		public boolean deleteCustomerById(int custID) {
	
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert Customer into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (custID == -99)
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.customers");
				else
					//Delete a particular Customer
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.customers WHERE customer_id = ?");
				preparedStatement.setInt(1, custID);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
	
		public boolean deleteAllCustomers() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.customers");
		        preparedStatement.executeUpdate();
		    } catch (Exception e) {
		        deleteSuccessful = false;
		        e.printStackTrace();  // Log the error for easier debugging
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            if (connect != null) {
		                connect.close();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    return deleteSuccessful;
		}
		
		public boolean insertDeliveryPerson(DeliveryPerson deliveryPerson) {
		    String query = "INSERT INTO delivery_persons (first_name, last_name, delivery_person_id, phone_number, assigned_area, status) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, deliveryPerson.getFirstName());
		        preparedStatement.setString(2, deliveryPerson.getLastName());
		        preparedStatement.setString(3, deliveryPerson.getDeliveryPersonId());
		        preparedStatement.setString(4, deliveryPerson.getPhoneNumber());
		        preparedStatement.setString(5, deliveryPerson.getAssignedArea());
		        preparedStatement.setString(6, deliveryPerson.getStatus());

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0; // Return true if insert was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Return false if there was an error
		    }
		}

		public ResultSet retrieveAllDeliveryPersons() {
		    try {
		        statement = connect.createStatement();
		        resultSet = statement.executeQuery("SELECT * FROM newsagent.delivery_persons");
		    } catch (Exception e) {
		        resultSet = null;
		        e.printStackTrace();  
		    }
		    return resultSet;
		}
		
		public boolean updateDeliveryPersonDetails(DeliveryPerson deliveryPerson) {
	        boolean updateSuccessful = false;
	        String query = "UPDATE delivery_persons SET first_name = ?, last_name = ?, phone_number = ?, assigned_area = ?, status = ? WHERE delivery_person_id = ?";
	        try {
	            preparedStatement = connect.prepareStatement(query);
	            preparedStatement.setString(1, deliveryPerson.getFirstName());
	            preparedStatement.setString(2, deliveryPerson.getLastName());
	            preparedStatement.setString(3, deliveryPerson.getPhoneNumber());
	            preparedStatement.setString(4, deliveryPerson.getAssignedArea());
	            preparedStatement.setString(5, deliveryPerson.getStatus());
	            preparedStatement.setString(6, deliveryPerson.getDeliveryPersonId());

	            int rowsAffected = preparedStatement.executeUpdate();
	            updateSuccessful = rowsAffected > 0; // Returns true if update was successful
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return updateSuccessful;
	    }	

		public boolean deleteDeliveryPersonById(String deleteId) {
			
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert publication into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (deleteId == "DELETEALL")
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.delivery_persons");
				else
					//Delete a particular publication
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.delivery_persons WHERE delivery_person_id = ?");
				preparedStatement.setString(1, deleteId);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
		public boolean deleteAllDeliveryPersons() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.delivery_persons");
		        preparedStatement.executeUpdate();
		        //System.out.println("All good");
		    } catch (Exception e) {
		        deleteSuccessful = false;
		        e.printStackTrace();  // Log the error for easier debugging
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            if (connect != null) {
		                connect.close();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    return deleteSuccessful;
		}

		public boolean insertPublication(Publication publication) {
			String query = "INSERT INTO publications (publication_id, title, number_in_stocks, price, type, delivery_frequency) VALUES (?, ?, ?, ?, ?, ?)";
		    try {
		        preparedStatement = connect.prepareStatement(query);

		        preparedStatement.setString(1, publication.getPublicationId());
		        preparedStatement.setString(2, publication.getTitle());
		        preparedStatement.setInt(3, publication.getNumberInStocks());
		        preparedStatement.setDouble(4, publication.getPrice());
		        preparedStatement.setString(5, publication.getType());
		        preparedStatement.setString(6, publication.getDeliveryFrequency());

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0; // Return true if insert was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Return false if there was an error
		    }
		}
		public ResultSet retrieveAllPublications() {

			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from publications");
			
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		public boolean updatePublicationDetails(Publication publication) {
			boolean updateSuccessful = false;
		    String query = "UPDATE publications SET title = ?, number_in_stocks = ?, price = ?, type = ?, delivery_frequency = ? WHERE publication_id = ?";
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setString(1, publication.getTitle());
		        preparedStatement.setInt(2, publication.getNumberInStocks());  // Corrected to use the proper field
		        preparedStatement.setDouble(3, publication.getPrice());
		        preparedStatement.setString(4, publication.getType());
		        preparedStatement.setString(5, publication.getDeliveryFrequency());
		        preparedStatement.setString(6, publication.getPublicationId());

		        int rowsAffected = preparedStatement.executeUpdate();
		        updateSuccessful = rowsAffected > 0; // Returns true if update was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return updateSuccessful;
	    }
		public boolean deletePublicationById(String publicationId) {
			 boolean deleteSuccessful = true;
			    try {
			        if ("DELETEALL".equals(publicationId)) {
			            preparedStatement = connect.prepareStatement("DELETE FROM publications");  // Corrected table name
			        } else {
			            preparedStatement = connect.prepareStatement("DELETE FROM publications WHERE publication_id = ?");
			            preparedStatement.setString(1, publicationId);
			        }

			        preparedStatement.executeUpdate();
			    } catch (SQLException e) {
			        deleteSuccessful = false;
			        e.printStackTrace();
			    }
			    return deleteSuccessful;
		}
		public boolean deleteAllPublications() {
			 boolean deleteSuccessful = true;
			 try {
			     preparedStatement = connect.prepareStatement("DELETE FROM publications");  // Corrected table name
			     preparedStatement.executeUpdate();
			 } catch (SQLException e) {
			        deleteSuccessful = false;
			        e.printStackTrace();
			 }
			 return deleteSuccessful;
		}
		public boolean insertOrder(Order order) {
		    String query = "INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, order.getOrderId());
		        preparedStatement.setInt(2, order.getCustId());
		        preparedStatement.setString(3, order.getDeliveryAreaId());
		        preparedStatement.setString(4, order.getPublicationId());
		        preparedStatement.setDate(5, Date.valueOf(order.getOrderDate()));
		        preparedStatement.setString(6, order.getOrderStatus().name()); // Store enum as string

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		public ResultSet retrieveAllOrders() {

			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from orders");
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		public boolean updateOrderDetails(Order order) {
			boolean updateSuccessful = false;
		    String query = "UPDATE orders SET cust_id = ?, delivery_id = ?, publication_id = ?, order_date = ?, order_status = ? WHERE order_id = ?";
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setInt(1, order.getCustId());
		        preparedStatement.setString(2, order.getDeliveryAreaId());
		        preparedStatement.setString(3, order.getPublicationId());
		        preparedStatement.setDate(4, Date.valueOf(order.getOrderDate()));
		        preparedStatement.setString(5, order.getOrderStatus().name()); // Update as enum string
		        preparedStatement.setString(6, order.getOrderId());

		        int rowsAffected = preparedStatement.executeUpdate();
		        updateSuccessful = rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return updateSuccessful;
	    }
		public boolean deleteOrderById(String orderId) {
			boolean deleteSuccessful = true;
		    try {
		        // Check if we want to delete all records
		        String query = orderId.equals("DELETEALL") ? "DELETE FROM orders" : "DELETE FROM orders WHERE order_id = ?";
		        preparedStatement = connect.prepareStatement(query);
		        
		        if (!orderId.equals("DELETEALL")) {
		            preparedStatement.setString(1, orderId);
		        }

		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        deleteSuccessful = false;
		        e.printStackTrace();
		    }
		    return deleteSuccessful;
			
		}
		public boolean deleteAllOrders() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM orders");
		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        deleteSuccessful = false;
		        e.printStackTrace();
		    }
		    return deleteSuccessful;
		}
		public boolean insertInvoice(Invoice invoice) {
		    String query = "INSERT INTO invoices (invoice_id, cust_id, payment_method, order_date, total_amount, delivery_docket_id, publication_id, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, invoice.getInvoiceId());
		        preparedStatement.setString(2, invoice.getCustId());
		        preparedStatement.setString(3, invoice.getPaymentMethod().getMethod()); 
		        preparedStatement.setDate(4, Date.valueOf(invoice.getOrderDate()));
		        preparedStatement.setDouble(5, invoice.getTotalAmount());
		        preparedStatement.setString(6, invoice.getDeliveryId());
		        preparedStatement.setString(7, invoice.getPublicationId());
		        preparedStatement.setString(8, invoice.getOrderStatus());

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0; // Return true if insert was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Return false if there was an error
		    }
		}
		
		public ResultSet retrieveAllInvoices() {
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.invoices");
			
			}
			catch (Exception e) {
				e.printStackTrace();
				resultSet = null;
			}
			return resultSet;
		}
		
		public boolean updateInvoiceDetails(Invoice invoice) {
	        boolean updateSuccessful = false;
	        String query = "UPDATE invoices SET cust_id = ?, payment_method = ?, order_date = ?, total_amount = ?, delivery_docket_id = ?, publication_id = ?, order_status = ? WHERE invoice_id = ?";
	        try {
	            preparedStatement = connect.prepareStatement(query);
	            preparedStatement.setString(1, invoice.getCustId());
	            preparedStatement.setString(2, invoice.getPaymentMethod().getMethod());
	            preparedStatement.setDate(3, Date.valueOf(invoice.getOrderDate()));
	            preparedStatement.setDouble(4, invoice.getTotalAmount());
	            preparedStatement.setString(5, invoice.getDeliveryId());
	            preparedStatement.setString(6, invoice.getPublicationId());
	            preparedStatement.setString(7, invoice.getOrderStatus());
	            
	            preparedStatement.setString(8, invoice.getInvoiceId());

	            int rowsAffected = preparedStatement.executeUpdate();
	            updateSuccessful = rowsAffected > 0; // Returns true if update was successful
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return updateSuccessful;
	    }
		public boolean deleteInvoiceById(String invoiceId) {
			String query = "DELETE FROM invoices WHERE invoice_id = ?";
			 try (PreparedStatement ps = connect.prepareStatement(query)) {
		            ps.setString(1, invoiceId);
		            return ps.executeUpdate() > 0;
		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
		        }
		}
		public boolean deleteAllinvoices() {
			String query = "DELETE FROM invoices";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		public boolean insertDeliveryDocket(DeliveryDocket deliveryDocket) {
		    String query = "INSERT INTO delivery_docket (docket_id, order_id, delivery_id, delivery_date, delivery_status, delivery_details) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, deliveryDocket.getDocketId());
		        preparedStatement.setString(2, deliveryDocket.getOrderId());
		        preparedStatement.setString(3, deliveryDocket.getDeliveryPersonId());
		        
		        preparedStatement.setString(4, LocalDate.now().toString());
		        preparedStatement.setString(5, deliveryDocket.getDeliveryStatus());
		        preparedStatement.setString(6, deliveryDocket.getDetails());

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0; // Return true if insert was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Return false if there was an error
		    }
		}
		public ResultSet retrieveAllDeliveryDockets() {
			
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.delivery_docket");
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		public boolean updateDeliveryDocketDetails(DeliveryDocket deliveryDocket) {
	        boolean updateSuccessful = false;
	        String query = "UPDATE delivery_docket SET order_id = ?, delivery_id = ?, delivery_date = ?, delivery_status = ?, delivery_details = ? WHERE docket_id = ?";
	        try {
	            preparedStatement = connect.prepareStatement(query);
	            preparedStatement.setString(1, deliveryDocket.getOrderId());
	            preparedStatement.setString(2, deliveryDocket.getDeliveryPersonId());
	            preparedStatement.setString(3, LocalDate.now().toString());
	            preparedStatement.setString(4, deliveryDocket.getDeliveryStatus());
	            preparedStatement.setString(5, deliveryDocket.getDetails());
	            preparedStatement.setString(6, deliveryDocket.getDocketId());
	            int rowsAffected = preparedStatement.executeUpdate();
	            updateSuccessful = rowsAffected > 0; // Returns true if update was successful
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return updateSuccessful;
	    }
		public boolean deleteDeliveryDocketById(String docketId) {
			
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert order into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (docketId == "DELETEALL")
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.delivery_docket");
				else
					//Delete a particular order
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.delivery_docket WHERE docket_id = ?");
				preparedStatement.setString(1, docketId);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
		public boolean deleteAllDeliveryDockets() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.delivery_docket");
		        preparedStatement.executeUpdate();
		        //System.out.println("All good");
		    } catch (Exception e) {
		        deleteSuccessful = false;
		        e.printStackTrace();  // Log the error for easier debugging
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            if (connect != null) {
		                connect.close();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    return deleteSuccessful;
		}
		
		// Insert Delivery Area
		public boolean insertDeliveryArea(DeliveryArea deliveryArea) {
		    String query = "INSERT INTO delivery_areas (area_id, area_name, delivery_person_id, total_customers) VALUES (?, ?, ?, ?)";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, deliveryArea.getAreaId());
		        ps.setString(2, deliveryArea.getAreaName());
		        ps.setString(3, deliveryArea.getDeliveryPersonId());
		        ps.setInt(4, deliveryArea.getTotalCustomers());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		// Read All Delivery Areas
		public ResultSet getAllDeliveryAreas() {
		    String query = "SELECT * FROM delivery_areas";
		    try  {  
		    	statement = connect.createStatement();
	        	resultSet = statement.executeQuery(query); 
		    } catch (SQLException e) {
		    	resultSet = null;
		    }
	        return resultSet;
		}

		// Update Delivery Area
		public boolean updateDeliveryArea(DeliveryArea deliveryArea) {
		    String query = "UPDATE delivery_areas SET area_name = ?, delivery_person_id = ?, total_customers = ? WHERE area_id = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, deliveryArea.getAreaName());
		        ps.setString(2, deliveryArea.getDeliveryPersonId());
		        ps.setInt(3, deliveryArea.getTotalCustomers());
		        ps.setString(4, deliveryArea.getAreaId());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		// Delete Delivery Area
		public boolean deleteDeliveryArea(String areaId) {
		    String query = "DELETE FROM delivery_areas WHERE area_id = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, areaId);
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		
		
		// Insert Warning Letter
	    public boolean insertWarningLetter(WarningLetter letter) {
	        String query = "INSERT INTO warning_letter (letter_id, order_id, cust_id, reason, due_amount, issue_date) VALUES (?, ?,?, ?, ?, ?)";
	        try (PreparedStatement ps = connect.prepareStatement(query)) {
	        	 ps.setString(1, letter.getLetterId());
	        	 ps.setString(2, letter.getOrderId());
	             ps.setInt(3, letter.getCustId());
	             ps.setString(4, letter.getReason());
	             ps.setDouble(5, letter.getDueAmount());
	             LocalDate issueDate = letter.getIssueDate() != null ? letter.getIssueDate() : LocalDate.now();
	             ps.setDate(6, java.sql.Date.valueOf(issueDate));
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // Retrieve all Warning Letters
	    public ResultSet getAllWarningLetters() {
	        try {
	        	statement = connect.createStatement();
	        	resultSet = statement.executeQuery("SELECT * FROM warning_letter");
	        } catch (SQLException e) {
	        	resultSet = null;
	        }
	        return resultSet;
	    }

	    // Update Warning Letter
	    public boolean updateWarningLetter(WarningLetter letter) {
	        String query = "UPDATE warning_letter SET reason = ?, due_amount = ?, issue_date = ?  WHERE letter_id = ?";
	        try (PreparedStatement ps = connect.prepareStatement(query)) {
//	        	ps.setInt(1, letter.getCustId()); //update customer id not necessary 
	            ps.setString(1, letter.getReason());
	            ps.setDouble(2, letter.getDueAmount());
	            LocalDate issueDate = letter.getIssueDate() != null ? letter.getIssueDate() : LocalDate.now();
	            ps.setDate(3, java.sql.Date.valueOf(issueDate));
	            ps.setString(4, letter.getLetterId());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // Delete Warning Letter
	    public boolean deleteWarningLetter(String letterId) {
	        String query = "DELETE FROM warning_letter WHERE letter_id = ?";
	        try (PreparedStatement ps = connect.prepareStatement(query)) {
	            ps.setString(1, letterId);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
		
		
		// Insert Newsagent
		public boolean insertNewsagent(Newsagent agent) {
		    String query = "INSERT INTO newsagent (agent_name, agent_address, agent_phone, agent_email) VALUES (?, ?, ?, ?)";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, agent.getName());
		        ps.setString(2, agent.getAddress());
		        ps.setString(3, agent.getPhoneNumber());
		        ps.setString(4, agent.getEmail());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		// Retrieve Newsagent 
		public ResultSet getNewsagent() throws CustomerExceptionHandler {
		    String query = "SELECT * FROM newsagent";
		    try {
		        Statement stmt = connect.createStatement();
		        return stmt.executeQuery(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new CustomerExceptionHandler("Error retrieving Newsagent data.");
		    }
		}

		// Update Newsagent
		public boolean updateNewsagent(Newsagent agent) {
		    String query = "UPDATE newsagent SET agent_address = ?, agent_phone = ?, agent_email = ? WHERE agent_name = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, agent.getAddress());
		        ps.setString(2, agent.getPhoneNumber());
		        ps.setString(3, agent.getEmail());
		        ps.setString(4, agent.getName());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		// Delete Newsagent
		public boolean deleteNewsagent(String agentName) {
		    String query = "DELETE FROM newsagent WHERE agent_name = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, agentName);
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		// Insert Storage
		public boolean insertStorage(Storage storage) {
			String query = "INSERT INTO storage (storage_id, publication_id, description_details, capacity, current_stock) VALUES (?, ?, ?, ?, ?)";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, storage.getStorageId());
		        ps.setString(2, storage.getPublicationId());
		        ps.setString(3, storage.getDescription()); // Assuming it's a String
		        ps.setInt(4, storage.getCapacity()); // Added capacity
		        ps.setInt(5, storage.getCurrentStockLevel());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		//Retrieve all storage records
		public ResultSet getAllStorage() {
		    try {  
		    	statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from storage");
		    } catch (Exception e) {
		        resultSet = null;
		    }
		    return resultSet;
		}
		
	    // Update Storage
		public boolean updateStorage(Storage storage) {
			String query = "UPDATE storage SET description_details = ?, capacity = ?, current_stock = ? WHERE storage_id = ?";
		    try (PreparedStatement ps = connect.prepareStatement(query)) {
		        ps.setString(1, storage.getDescription());
		        ps.setInt(2, storage.getCapacity());
		        ps.setInt(3, storage.getCurrentStockLevel());
		        ps.setString(4, storage.getStorageId());
		        return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	    // Delete Storage
	    public boolean deleteStorage(String storageId) {
	        String query = "DELETE FROM storage WHERE storage_id = ?";
	        try (PreparedStatement ps = connect.prepareStatement(query)) {
	            ps.setString(1, storageId);
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}// end Class
	
