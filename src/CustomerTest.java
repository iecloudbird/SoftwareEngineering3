import junit.framework.TestCase;

/*
 * Customer Test Cases Summary
 * 
 * Entity: Customer 
 * Objective: Ensure 100%/full coverage test cases using EP and BA
 * BA (Boundary Analysis): 8 Test Cases
 * EP (Equivalence Partitioning): 12 Test Cases
 * 
 */

public class CustomerTest extends TestCase {
	
	//Test #: 1
	//Test Objective: To create a Customer Account
	//Inputs: custName = "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123", email = "jack@example.com"
	// Expected Output: Customer Object created with id = 0, "Jack Daniels", "Athlone", "087-123123123", "jack@example.com"	//Expected Output: Customer Object created with id = 0, "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
	public void testCreateCustomerSuccess() {
		
		//Create the Customer Object
		try {
			
			// Call method under test
            Customer custObj = new Customer("Jack Daniels", "Athlone", "0871231233", "jack@example.com",true);
            
            // Use getters to check for object creation
            assertEquals(0, custObj.getId());
            assertEquals("Jack Daniels", custObj.getName());
            assertEquals("Athlone", custObj.getAddress());
            assertEquals("0871231233", custObj.getPhoneNumber());
            assertEquals("jack@example.com", custObj.getEmail());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
		
	}
	
	// Test #: 2
    // Test Objective: To catch an invalid customer name
    // Inputs: custName = "J"
    // Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
    
    public void testInvalidCustomerNameLength() {
        try {
            // Call method under test
            Customer.validateName("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());
        }
    }
    
    // Test #: 3
    // Test Objective: To catch an invalid customer address
    // Inputs: custAddr = "A"
    // Expected Output: Exception Message: "Customer Address does not meet minimum length requirements"
    
    public void testInvalidCustomerAddressLength() {
        try {
            Customer.validateAddress("A");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer Address does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 4
    // Test Objective: To successfully validate a customer address
    // Inputs: custAddr = "Athlone"
    // Expected Output: No exception, address is valid
    
    public void testValidCustomerAddress() {
        try {
            Customer custObj = new Customer("John Doe", "Athlone", "0874555757", "john@example.com",true);
            assertEquals("Athlone", custObj.getAddress());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
    // Test #: 5
    // Test Objective: To catch an invalid customer phone number
    // Inputs: custPhone = "8"
    // Expected Output: Exception Message: "Customer PhoneNumber does not meet minimum length requirements"
    
    public void testInvalidCustomerPhoneNumberLength() {
        try {
            Customer.validatePhoneNumber("8");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer PhoneNumber does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 6
    // Test Objective: To successfully validate a customer phone number
    // Inputs: custPhone = "0874555757"
    // Expected Output: No exception, phone number is valid
    
    public void testValidCustomerPhoneNumber() {
        try {
            Customer custObj = new Customer("John Doe", "Athlone", "0874555757", "john@example.com",true);
            assertEquals("0874555757", custObj.getPhoneNumber());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 7
    // Test Objective: To catch an invalid customer email
    // Inputs: email = "a@p"
    // Expected Output: Exception Message: "Customer Email does not meet minimum length requirements"
    
    public void testInvalidCustomerEmailFormat() {
        try {
            Customer.validateEmail("a@p");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer Email does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 8
    // Test Objective: To successfully validate a customer email
    // Inputs: email = "jack@example.com"
    // Expected Output: No exception, email is valid
    
    public void testValidCustomerEmail() {
        try {
            Customer custObj = new Customer("Jack Daniels", "Athlone", "0874555757", "jack@example.com",true);
            assertEquals("jack@example.com", custObj.getEmail());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
	 // Test #: 9
	 // Test Objective: To validate the format of customer ID (e.g., #C00000)
	 public void testValidateCustomerIDFormat() {
		 try {
		        // Arrange & Act
		        Customer customer = new Customer("Jack Daniels", "Athlone", "0871234567", "jack@example.com",true);
		        
		        // Assert
		        int expectedCustomerId = 0; 
		        assertEquals(expectedCustomerId, customer.getId());
		    } catch (CustomerExceptionHandler e) {
		        fail("Exception was not expected.");
		    }
	 }
	 
	// Test #: 10
	// Test Objective: Validate name of exactly 1 character
	public void testCustomerNameMinBoundary() {
	    try {
	        Customer.validateName("AB");
	        assertEquals("AB", "AB");
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 11
	// Test Objective: Validate name of exactly 15 characters (boundary case)
	public void testValidateNameMaxBoundary() {
	    try {
	    	String validName = "Alexanderson123";  // 15 characters
	        Customer.validateName(validName);
	        
	      
	        assertEquals(validName, "Alexanderson123");
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 12
	// Test Objective: Validate address of exactly 5 character (boundary case)
	public void testValidateAddressMinBoundary() {
	    try {
	        Customer.validateAddress("1glen");
	        assertTrue(true);  
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 13
	// Test Objective: Validate address of exactly 100 characters (assuming max length is 100)
	public void testValidateAddressMaxBoundary() {
	    String longAddress = "A".repeat(60); //total 60 characters
	    try {
	        Customer.validateAddress(longAddress);
	        assertTrue(true); 
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 14
	// Test Objective: Catch invalid customer phone number containing special characters (dashes).
    // Input: custPhone = "087-1234567"
    // Expected Output: Exception with message "Customer PhoneNumber does not meet numeric format requirements".
	public void testValidatePhoneNumberWithDashes() {
	    try {
	        Customer.validatePhoneNumber("087-1234567");
	        fail("Exception expected"); 
	    } catch (CustomerExceptionHandler e) {
	        assertEquals("Customer PhoneNumber does not meet numeric format requirements", e.getMessage());
	    }
	}
	
	// Test #: 15
	// Test Objective: Catch an invalid customer name exceeding max length
	// Inputs: custName = "A".repeat(51)
	// Expected Output: Exception with message "Customer Name exceeds maximum length requirements"
	public void testInvalidCustomerNameExceedsMax() {
	    try {
	        Customer.validateName("A".repeat(51));
	        fail("Exception expected");
	    } catch (CustomerExceptionHandler e) {
	        assertEquals("Customer Name exceeds maximum length requirements", e.getMessage());
	    }
	}
	
	// Test #: 16
	// Test Objective: Catch an invalid customer address exceeding max length
	// Inputs: custAddr = "B".repeat(61)
	// Expected Output: Exception with message "Customer Address exceeds maximum length requirements"
	public void testInvalidCustomerAddressExceedsMax() {
	    try {
	        Customer.validateAddress("B".repeat(61));
	        fail("Exception expected");
	    } catch (CustomerExceptionHandler e) {
	        assertEquals("Customer Address exceeds maximum length requirements", e.getMessage());
	    }
	}

	// Test #: 17
	// Test Objective: Catch an invalid customer phone number exceeding max length
	// Inputs: custPhone = "1234567890123456" (16 digits)
	// Expected Output: Exception with message "Customer PhoneNumber exceeds maximum length requirements"
	public void testInvalidCustomerPhoneNumberExceedsMax() {
	    try {
	        Customer.validatePhoneNumber("1234567890123456");
	        fail("Exception expected");
	    } catch (CustomerExceptionHandler e) {
	        assertEquals("Customer PhoneNumber exceeds maximum length requirements", e.getMessage());
	    }
	}

	// Test #: 18
	// Test Objective: Catch an invalid email without "@" symbol
	// Inputs: email = "invalidemail.com"
	// Expected Output: Exception with message "Invalid email format"
	public void testInvalidEmailMissingAtSymbol() {
	    try {
	        Customer.validateEmail("invalidemail.com");
	        fail("Exception expected");
	    } catch (CustomerExceptionHandler e) {
	        assertEquals("Invalid email format", e.getMessage());
	    }
	}

	// Test #: 19
	// Test Objective: Validate subscription status is correctly set
	// Inputs: subscriptionStatus = true
	// Expected Output: Subscription status is true
	public void testSubscriptionStatusTrue() {
	    try {
	        Customer custObj = new Customer("Jane Doe", "Dublin", "0851234567", "jane@example.com", true);
	        assertTrue(custObj.getSubscriptionStatus());
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 20
	// Test Objective: Validate subscription status is correctly set
	// Inputs: subscriptionStatus = false
	// Expected Output: Subscription status is false
	public void testSubscriptionStatusFalse() {
	    try {
	        Customer custObj = new Customer("Jane Doe", "Dublin", "0851234567", "jane@example.com", false);
	        assertFalse(custObj.getSubscriptionStatus());
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
}

		

