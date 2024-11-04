	import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.Statement;
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
	        String query = "UPDATE delivery_person SET first_name = ?, last_name = ?, phone_number = ?, assigned_area = ?, status = ? WHERE delivery_person_id = ?";
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
		
		//Invoices
		public boolean insertInvoice(Invoice invoice) {
		    String query = "INSERT INTO Invoices (invoice_id, cust_id, payment_method, order_date, total_amount) VALUES (?, ?, ?, ?, ?)";
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setString(1, invoice.getInvoiceId()); // Use getInvoiceId() for invoice ID
		        preparedStatement.setString(2, invoice.getCustId()); // Use getCustId() for customer ID
		        preparedStatement.setString(3, invoice.getPaymentMethod().getMethod()); // Use getPaymentMethod() to retrieve the payment method
		        preparedStatement.setDate(4, new java.sql.Date(invoice.getOrderDate().getTime())); // Use getOrderDate() for order date
		        preparedStatement.setDouble(5, invoice.getTotalAmount()); // Use getTotalAmount() for the total amount

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; 
		    }
		}

		public ResultSet retrieveAllInvoices() {
		    try {
		        statement = connect.createStatement();
		        resultSet = statement.executeQuery("SELECT * FROM invoices");
		    } catch (Exception e) {
		        resultSet = null;
		        e.printStackTrace();
		    }
		    return resultSet;
		}

		public Invoice retrieveInvoiceById(String invoiceId) {
		    Invoice invoice = null; // Initialize the Invoice object to null
		    String query = "SELECT * FROM Invoices WHERE invoice_id = ?";
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setString(1, invoiceId); 
		        resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) { // Check if there is a result
		            invoice = new Invoice();
		            try {
		                invoice.setInvoiceId(resultSet.getString("invoice_id"));
		                invoice.setCustId(resultSet.getString("cust_id"));
		                invoice.setPaymentMethod(resultSet.getString("payment_method")); // Assuming paymentMethod is stored as a string
		                invoice.setOrderDate(resultSet.getDate("order_date")); // Get order date
		                invoice.setTotalAmount(resultSet.getDouble("total_amount")); // Get total amount
		            } catch (CustomerExceptionHandler e) {
		                
		                e.printStackTrace();
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); // Log the SQL error for easier debugging
		    } finally {
		        // Close resources to avoid memory leaks
		        try {
		            if (resultSet != null) resultSet.close();
		            if (preparedStatement != null) preparedStatement.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return invoice; // Return the retrieved Invoice object or null if not found
		}

		public boolean updateInvoiceDetails(Invoice invoice) {
		    boolean updateSuccessful = false;
		    String query = "UPDATE Invoices SET cust_id = ?, payment_method = ?, order_date = ?, total_amount = ?, delivery_id = ?, publication_id = ?, order_status = ? WHERE invoice_id = ?";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        preparedStatement.setString(1, invoice.getCustId());
		        preparedStatement.setString(2, invoice.getPaymentMethod().getMethod());
		        preparedStatement.setDate(3, new java.sql.Date(invoice.getOrderDate().getTime()));
		        preparedStatement.setDouble(4, invoice.getTotalAmount());
		        
		        // Set optional fields, null if they were not provided
		        if (invoice.getDeliveryId() != null && !invoice.getDeliveryId().isEmpty()) {
		            preparedStatement.setString(5, invoice.getDeliveryId());
		        } else {
		            preparedStatement.setNull(5, java.sql.Types.VARCHAR); // or appropriate SQL type
		        }
		        
		        if (invoice.getPublicationId() != null && !invoice.getPublicationId().isEmpty()) {
		            preparedStatement.setString(6, invoice.getPublicationId());
		        } else {
		            preparedStatement.setNull(6, java.sql.Types.VARCHAR);
		        }
		        
		        preparedStatement.setString(7, invoice.getOrderStatus());
		        preparedStatement.setString(8, invoice.getInvoiceId());

		        int rowsAffected = preparedStatement.executeUpdate();
		        updateSuccessful = rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return updateSuccessful;
		}

		public boolean deleteInvoiceById(String invoiceId) {
		    boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM Invoices WHERE invoice_id = ?");
		        preparedStatement.setString(1, invoiceId);
		        int rowsAffected = preparedStatement.executeUpdate();
		        deleteSuccessful = rowsAffected > 0; 
		    } catch (SQLException e) {
		        deleteSuccessful = false; 
		        e.printStackTrace(); 
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return deleteSuccessful; 
		}

		public boolean deleteAllInvoices() {
		    boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM invoices");
		        preparedStatement.executeUpdate();
		    } catch (Exception e) {
		        deleteSuccessful = false;
		        e.printStackTrace(); // Log the error for easier debugging
		    }
		    return deleteSuccessful;
		}
		
		public boolean insertPublication(Publication publication) {
		    String query = "INSERT INTO publications (publication_id, publication_name, stock_number, publication_price, publication_type, publication_frequency) VALUES (?, ?, ?, ?, ?, ?)";
		    
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
			
			//Add Code here to call embedded SQL to view Customer Details
		
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.publications");
			
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		public boolean updatePublicationDetails(Publication publication) {
	        boolean updateSuccessful = false;
	        String query = "UPDATE publications SET publication_name = ?, stock_number = ?, publication_price = ?, publication_type = ?, publication_frequency = ? WHERE publication_id = ?";
	        try {
	            preparedStatement = connect.prepareStatement(query);
	            preparedStatement.setString(1, publication.getTitle());
	            preparedStatement.setInt(2, publication.getNumberInStocks());
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
			
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert publication into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (publicationId == "DELETEALL")
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.publications");
				else
					//Delete a particular publication
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.publications WHERE publication_id = ?");
				preparedStatement.setString(1, publicationId);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
		public boolean deleteAllPublications() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.publications");
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
		public boolean insertOrder(Order order) {
		    String query = "INSERT INTO orders (order_id, cust_id, delivery_id, publication_id, order_date, order_status) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, order.getOrderId());
		        preparedStatement.setInt(2, order.getCustId());
		        preparedStatement.setString(3, order.getDeliveryId());
		        preparedStatement.setString(4, order.getPublicationId());
		        preparedStatement.setDate(5, (Date) order.getOrderDate());
		        preparedStatement.setObject(6, order.getOrderStatus());

		        int rowsAffected = preparedStatement.executeUpdate();
		        return rowsAffected > 0; // Return true if insert was successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Return false if there was an error
		    }
		}
		public ResultSet retrieveAllOrders() {
			
			//Add Code here to call embedded SQL to view Customer Details
		
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.orders");
			
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
	            preparedStatement.setString(2, order.getDeliveryId());
	            preparedStatement.setString(3, order.getPublicationId());
	            preparedStatement.setDate(4, (Date)order.getOrderDate());
	            preparedStatement.setObject(5, order.getOrderStatus());
	            preparedStatement.setString(6, order.getOrderId());

	            int rowsAffected = preparedStatement.executeUpdate();
	            updateSuccessful = rowsAffected > 0; // Returns true if update was successful
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return updateSuccessful;
	    }
		public boolean deleteOrderById(String orderId) {
			
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert publication into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (orderId == "DELETEALL")
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.orders");
				else
					//Delete a particular publication
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.orders WHERE order_id = ?");
				preparedStatement.setString(1, orderId);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
		public boolean deleteAllOrders() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.orders");
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
		public boolean insertInvoice(Invoice invoice) {
		    String query = "INSERT INTO invoices (invoice_id, cust_id, payment_method, order_date, total_amount, delivery_persons, publication_id, order_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    try {
		        preparedStatement = connect.prepareStatement(query);
		        
		        preparedStatement.setString(1, invoice.getInvoiceId());
		        preparedStatement.setString(2, invoice.getCustId());
		        preparedStatement.setObject(3, invoice.getPaymentMethod());
		        preparedStatement.setDate(4, (Date) invoice.getOrderDate());
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
			
			//Add Code here to call embedded SQL to view Customer Details
		
			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.invoices");
			
			}
			catch (Exception e) {
				resultSet = null;
			}
			return resultSet;
		}
		public boolean updateInvoiceDetails(Invoice invoice) {
	        boolean updateSuccessful = false;
	        String query = "UPDATE invoices SET cust_id = ?, payment_method = ?, order_date = ?, total_amount = ?, delivery_persons, publication_id, order_status WHERE invoice_id = ?";
	        try {
	            preparedStatement = connect.prepareStatement(query);
	            preparedStatement.setString(1, invoice.getCustId());
	            preparedStatement.setObject(2, invoice.getPaymentMethod());
	            preparedStatement.setDate(3,(Date) invoice.getOrderDate());
	            preparedStatement.setDouble(4, invoice.getTotalAmount());
	            preparedStatement.setString(5, invoice.getDeliveryId());
	            preparedStatement.setString(6, invoice.getPublicationId());
	            preparedStatement.setString(7, invoice.getOrderStatus());

	            int rowsAffected = preparedStatement.executeUpdate();
	            updateSuccessful = rowsAffected > 0; // Returns true if update was successful
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return updateSuccessful;
	    }
		public boolean deleteInvoiceById(String invoiceId) {
			
			boolean deleteSucessfull = true;
			
			//Add Code here to call embedded SQL to insert order into DB
			
			try {
				
				//Create prepared statement to issue SQL query to the database
				if (invoiceId == "DELETEALL")
					//Delete all entries in Table
					preparedStatement = connect.prepareStatement("delete from newsagent.invoices");
				else
					//Delete a particular order
					preparedStatement = connect.prepareStatement("DELETE FROM newsagent.invoices WHERE invoice_id = ?");
				preparedStatement.setString(1, invoiceId);
	
				preparedStatement.executeUpdate();
			 
			}
			catch (Exception e) {
				deleteSucessfull = false;
			}
			
			return deleteSucessfull;
			
		}
		public boolean deleteAllinvoices() {
			boolean deleteSuccessful = true;
		    try {
		        preparedStatement = connect.prepareStatement("DELETE FROM newsagent.invoices");
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
	}// end Class
	
