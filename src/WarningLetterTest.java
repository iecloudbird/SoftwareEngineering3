import junit.framework.TestCase;

/*
 * Customer Test Cases
 * 1) Ensure Best Practices is applied to the codes (Meaning names, comments etc...),
 * 2) 
 */

public class WarningLetterTest extends TestCase {
	
	//Test #: 1
	//Test Objective: To create a Customer Account
	//Inputs: custName = "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123", email = "jack@example.com"
	// Expected Output: Customer Object created with id = 0, "Jack Daniels", "Athlone", "087-123123123", "jack@example.com"	//Expected Output: Customer Object created with id = 0, "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
	public void testCreateWarningLetterSuccess() {
		
		//Create the Customer Object
		try {
			
			// Call method under test
            WarningLetter letterObj = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, "2011-01-18");
            
            // Use getters to check for object creation
            assertEquals("923478", letterObj.getLetterId());
            assertEquals("8174239", letterObj.getOrderId());
            assertEquals("82614", letterObj.getCustId());
            assertEquals("Payment Issues", letterObj.getReason());
            assertEquals("90.00", letterObj.getDueAmount());
            assertEquals("2011-01-18", letterObj.getIssueDate());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
		
	}
	
	// Test #: 2
    // Test Objective: To catch an invalid customer name
    // Inputs: custName = "J"
    // Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
    
    public void testInvalidLetterIdLength() {
        try {
            // Call method under test
            WarningLetter.validateLetterId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Letter Id does not meet minimum length requirements", e.getMessage());
        }
    }
    
    // Test #: 3
    // Test Objective: To catch an invalid customer address
    // Inputs: custAddr = "A"
    // Expected Output: Exception Message: "Customer Address does not meet minimum length requirements"
    
    public void testInvalidOrderIdLength() {
        try {
            WarningLetter.validateOrderId("A");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 4
    // Test Objective: To successfully validate a customer address
    // Inputs: custAddr = "Athlone"
    // Expected Output: No exception, address is valid
    
    public void testValidCustomerId() {
        try {
            WarningLetter custId = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, "2011-01-18");
            assertEquals("82614", custId.getCustId());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
    // Test #: 5
    // Test Objective: To catch an invalid customer phone number
    // Inputs: custPhone = "8"
    // Expected Output: Exception Message: "Customer PhoneNumber does not meet minimum length requirements"
    
    public void testInvalidCustomerReasonLength() {
        try {
            WarningLetter.validateReason"8");
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
}

		

