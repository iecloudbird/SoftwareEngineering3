import java.util.Date;

import junit.framework.TestCase;

/*
 * Customer Test Cases
 * 1) Ensure Best Practices is applied to the codes (Meaning names, comments etc...),
 * 2) 
 */

public class WarningLetterTest extends TestCase {
	
	// Test #: 1
		// Test Objective: Create a WarningLetter with valid inputs.
		// Inputs: letterId = "923478", orderId = "8174239", custId = "82614", reason = "Payment Issues", dueAmount = 90.00, issueDate = null
		// Expected Output: WarningLetter object created with the specified values.
	public void testCreateWarningLetterSuccess() {
		
		// Create the WarningLetter object with valid inputs
		try {
			// Call method under test
            WarningLetter letterObj = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);
            
            /// Validate the object properties with expected values
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
	
	/// Test #: 2
    // Test Objective:  Invalid letterId - Boundary case (1 character)
    // Inputs: letterId = "J"
    // Expected Output: Exception with message "Letter Id does not meet minimum length requirements".
    
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
    // Test Objective: Verify that an exception is thrown for an invalid order ID with insufficient length.
    // Inputs: orderId = "A"
    // Expected Output: Exception with message "Order Id does not meet minimum length requirements".
    public void testInvalidOrderIdLength() {
        try {
            WarningLetter.validateOrderId("A");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 4
    // Test Objective: Verify that a valid customer ID is accepted without exception.
    // Inputs: custId = "82614"
    // Expected Output: No exception, customer ID is valid.
    public void testValidCustomerId() {
        try {
            WarningLetter custId = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);
            assertEquals("82614", custId.getCustId());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
    // Test #: 5
    // Test Objective: Verify that an exception is thrown for a reason that doesn't meet minimum length requirements.
    // Inputs: reason = "P"
    // Expected Output: Exception with message "Reason does not meet minimum length requirements".
    
    public void testInvalidCustomerReasonLength() {
        try {
            WarningLetter.validateReason("P");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Reason does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 6
    // Test Objective: Verify that a valid reason is accepted without exception.
    // Inputs: reason = "Payment Issues"
    // Expected Output: No exception, reason is valid.
    
    public void testValidCustomerReason() {
        try {
            WarningLetter custId = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);
            assertEquals("0874555757", custId.getReason());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 7
    // Test Objective: Verify that an invalid due amount format triggers an exception.
    // Inputs: dueAmount = "a@p"
    // Expected Output: Exception with message "Due Amount does not meet format requirements".   
    public void testInvalidDueAmountFormat() {
        try {
            WarningLetter.validateDueAmount("a@p");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Due Amount does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 8
    // Test Objective: Verify that a valid due amount is accepted without exception.
    // Inputs: dueAmount = 90.00
    // Expected Output: No exception, due amount is valid.
    
    public void testValidDueAmount() {
        try {
            WarningLetter custId = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);
            //WarningLetter.validateDueAmount("90.00");
            assertEquals("90.00", custId.getDueAmount());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
	// Test #: 9
	// Test Objective: Verify the correct handling and formatting of the issue date.
	// Inputs: issueDate = null
	// Expected Output: issueDate is null initially, no exceptions thrown.
	 public void testValidateIssueDateFormat() {
		 try {
		        // Arrange & Act
		        WarningLetter issueDate = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);
		        
		        // Assert
		        int expectedIssueDate = 0; 
		        assertEquals(expectedIssueDate, issueDate.getIssueDate());
		    } catch (CustomerExceptionHandler e) {
		        fail("Exception was not expected.");
		    }
	 }
	 
	// Test #: 10
	// Test Objective: Validate customer name with exactly the minimum required characters.
	// Inputs: custName = "AB"
	// Expected Output: No exception, name is valid.
	public void testCustomerNameMinBoundary() {
	    try {
	        Customer.validateName("AB");
	        assertEquals("AB", "AB");
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 11
	// Test Objective: Validate customer name at maximum length boundary.
	// Inputs: custName = "Alexanderson123" (15 characters)
	// Expected Output: No exception, name is valid.
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
	// Test Objective: Validate customer address at minimum length boundary.
	// Inputs: custAddr = "1glen" (5 characters)
	// Expected Output: No exception, address is valid.
	public void testValidateAddressMinBoundary() {
	    try {
	        Customer.validateAddress("1glen");
	        assertTrue(true);  
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 13
	// Test Objective: Validate customer address at maximum length boundary.
	// Inputs: custAddr = "A".repeat(60) (total 60 characters)
	// Expected Output: No exception, address is valid.
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
	// Test Objective: Catch an invalid phone number containing special characters (dashes).
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

		

