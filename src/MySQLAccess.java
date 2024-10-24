	import java.sql.Connection;
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

	
	
	}// end Class
	
