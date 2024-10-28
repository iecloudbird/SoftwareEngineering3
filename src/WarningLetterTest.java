import java.time.LocalDate;


import junit.framework.TestCase;

/*
 * Customer Test Cases
 * 1) Ensure Best Practices is applied to the codes (Meaning names, comments etc...),
 * 2) 
 */

public class WarningLetterTest extends TestCase {
	
	// Test #: 1
	// Test Objective: Create a WarningLetter with valid inputs.
	// Inputs: letterId = "WL0001", orderId = "ORD001", custId = "82614", reason = "Payment Issues", dueAmount = 90.00, issueDate = null
    // Expected Output: WarningLetter object created with specified values.
	public void testCreateWarningLetterSuccess() {
		
		// Create the WarningLetter object with valid inputs
		try {
			// Call method under test
			 WarningLetter letter = new WarningLetter("WL0001", "ORD001", "82614", "Payment Issues", 90.00, null);
            
            /// Validate the object properties with expected values
			 assertEquals("WL0001", letter.getLetterId());
	         assertEquals("ORD001", letter.getOrderId());
	         assertEquals("82614", letter.getCustId());
	         assertEquals("Payment Issues", letter.getReason());
	         assertEquals(90.00, letter.getDueAmount());
	         assertNull(letter.getIssueDate());
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
            assertEquals("Warning Letter Id does not meet minimum length requirements", e.getMessage());
        }
    }
    
 // Test #: 3
    // Test Objective: Invalid orderId (Boundary - too short)
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
    // Test Objective: Valid customer ID format
    // Inputs: custId = "82614"
    // Expected Output: No exception, customer ID is valid.
    public void testValidCustomerId() {
        try {
        	 WarningLetter letter = new WarningLetter("WL0001", "ORD001", "82614", "Payment Issues", 90.00, null);
        	 assertEquals("82614", letter.getCustId());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
    
    // Test #: 5
    // Test Objective: Invalid reason length (Boundary - too short)
    // Inputs: reason = "P"
    // Expected Output: Exception with message "Reason does not meet minimum length requirements".
    
    public void testInvalidCustomerReasonLength() {
        try {
            WarningLetter.validateReason("P");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer reason does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 6
    // Test Objective: Valid reason length
    // Inputs: reason = "Payment Issues"
    // Expected Output: No exception, reason is valid.
    
    public void testValidCustomerReason() {
        try {
        	WarningLetter letter = new WarningLetter("WL0001", "ORD001", "82614", "Payment Issues", 90.00, null);
        	assertEquals("Payment Issues", letter.getReason());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

	 // Test #: 7
	 // Test Objective: Verify that a negative due amount triggers an exception.
	 // Inputs: dueAmount = -50.00
	 // Expected Output: Exception with message "Due Amount must be positive".
	 public void testInvalidNegativeDueAmount() {
	     try {
	         WarningLetter.validateDueAmount(-50.00);
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Due Amount must be positive", e.getMessage());
	     }
	 }
	
	 // Test #: 8
	 // Test Objective: Verify that a valid due amount is accepted without exception.
	 // Inputs: dueAmount = 90.00
	 // Expected Output: No exception, due amount is valid.
	 public void testValidDueAmount() {
	     try {
	         WarningLetter.validateDueAmount(90.00);
	     } catch (CustomerExceptionHandler e) {
	         fail("Exception not expected");
	     }
	 }
    
	// Test #: 9
	// Test Objective: Verify the correct handling and formatting of the issue date.
	// Inputs: issueDate = null
	// Expected Output: issueDate is set to the current date.
	 public void testIssueDateDefaultsToCurrentDate() {
		    try {
		        WarningLetter letter = new WarningLetter("923478", "8174239", "82614", "Payment Issues", 90.00, null);

		        // Check that issueDate is approximately the current date
		        LocalDate today = LocalDate.now();
		        assertEquals(today, letter.getIssueDate());
		    } catch (CustomerExceptionHandler e) {
		        fail("Exception was not expected.");
		    }
		}
	 
	// Test #: 10
	// Test Objective: Minimum length customer name
	// Inputs: custName = "AB"
	// Expected Output: No exception, name is valid.
	public void testCustomerNameMinBoundary() {
	    try {
	        Customer.validateName("AB");
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 11
	// Test Objective:Maximum length customer name
	// Inputs: custName = "Alexanderson123" (15 characters)
	// Expected Output: No exception, name is valid.
	public void testCustomerNameMaxBoundary() {
        try {
            Customer.validateName("Alexanderson123");
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
	
	// Test #: 12
	// Test Objective: Minimum length customer address 
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
	// Test Objective: Maximum length customer address
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

		

