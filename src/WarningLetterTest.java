import java.time.LocalDate;
import java.util.Optional;

import junit.framework.TestCase;

/**
 * WarningLetterTest Summary
 * 
 * Entity: WarningLetter
 * Objective: Ensure 100% full coverage test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
 * 
 * Total Test Cases: 20
 * 
 * Boundary Analysis (BA): 10 Test Cases
 * - Minimum and Maximum Length for letterId, orderId, customer name, and address.
 * - Valid and Invalid Values for dueAmount and issueDate handling.
 * - Valid and Invalid Customer Phone Numbers.
 * 
 * Equivalence Partitioning (EP): 10 Test Cases
 * - Valid and Invalid Formats for letterId, orderId, customer ID, reason, and phone number.
 * - Validations for object creation and default settings.
 */

public class WarningLetterTest extends TestCase {
	
	// Test #: 1
	// Test Objective
	// Test Objective: Create a WarningLetter with valid inputs.
	// Inputs: letterId = "WL0001", orderId = "ORD001", custId = "82614", reason = "Payment Issues", dueAmount = 90.00, issueDate = null
    // Expected Output: WarningLetter object created with specified values.
	public void testCreateWarningLetterSuccess() {
        try {
        	WarningLetter letter = new WarningLetter("WL001", "ORD0001", 82614, "Payment Issues", 90.00, Optional.empty());
            assertEquals("WL001", letter.getLetterId());
            assertEquals("ORD0001", letter.getOrderId());
            assertEquals(82614, letter.getCustId());
            assertEquals("Payment Issues", letter.getReason());
            assertEquals(90.00, letter.getDueAmount());
            assertEquals(LocalDate.now(), letter.getIssueDate());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
	
	/// Test #: 2
    // Test Objective:  Invalid letterId - Boundary case (1 character)
    // Inputs: letterId = "J"
    // Expected Output: Exception with message "Letter Id does not meet minimum length requirements".
	public void testInvalidLetterIdLength() {
	        try {
	            WarningLetter.validateLetterId("J");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Warning Letter Id does not meet minimum length requirements", e.getMessage());
	        }
	    }
    
	// Test #: 3
    // Test Objective: Invalid letterId - Invalid format
    // Inputs: letterId = "WL00A"
    // Expected Output: Exception with message "Letter Id format is invalid. Expected format: WL001".
	public void testInvalidLetterIdFormat() {
	        try {
	            WarningLetter.validateLetterId("WL00A");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Letter Id format is invalid. Expected format: WL001", e.getMessage());
	        }
	    }
	 
	// Test #: 4
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
	
	// Test #: 5
    // Test Objective: Invalid orderId - Invalid format
    // Inputs: orderId = "ORDABC"
    // Expected Output: Exception with message "Order Id format is invalid. Expected format: ORD0001".
	public void testInvalidOrderIdFormat() {
        try {
            WarningLetter.validateOrderId("ORDABC");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id format is invalid. Expected format: ORD0001", e.getMessage());
        }
    }

	// Test #: 6
    // Test Objective: Valid customer ID format
    // Inputs: custId = 82614
    // Expected Output: No exception, customer ID is valid.
	public void testValidCustomerId() {
        try {
            WarningLetter letter = new WarningLetter("WL001", "ORD0001", 82614, "Payment Issues", 90.00, Optional.empty());
            assertEquals(82614, letter.getCustId());
        } catch (CustomerExceptionHandler e) {

        }
    }
	
	// Test #: 7
    // Test Objective: Verify that customer ID cannot be zero.
    // Inputs: custId = 0
    // Expected Output: Exception with message "Customer Id NOT specified".
	public void testInvalidCustomerIdZero() {
        try {
            WarningLetter.validateCustId(0);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer Id NOT specified", e.getMessage());
        }
    }
    
	// Test #: 8
    // Test Objective: Invalid reason length (Boundary - too short)
    // Inputs: reason = "P"
    // Expected Output: Exception with message "Customer reason does not meet minimum length requirements".
	public void testInvalidCustomerReasonLength() {
        try {
            WarningLetter.validateReason("P");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer reason does not meet minimum length requirements", e.getMessage());
        }
    }

	// Test #: 9
    // Test Objective: Valid reason length
    // Inputs: reason = "Payment Issues"
    // Expected Output: No exception, reason is valid.
	public void testValidCustomerReason() {
        try {
            WarningLetter letter = new WarningLetter("WL001", "ORD0001", 82614, "Payment Issues", 90.00, Optional.empty());
            assertEquals("Payment Issues", letter.getReason());
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

	// Test #: 10
    // Test Objective: Check for negative due amount triggering an exception.
    // Inputs: dueAmount = -50.00
    // Expected Output: Exception with message "Due amount cannot be negative."
	public void testInvalidNegativeDueAmount() {
        try {
            WarningLetter.validateDueAmount(-50.00);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Due amount cannot be negative.", e.getMessage());
        }
    }
	
	// Test #: 11
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
	 
	 // Test #: 12
	 // Test Objective: Ensure due amount can't exceed the maximum allowed value.
	 // Inputs: dueAmount = 1500.00
	 // Expected Output: Exception with message "Due amount exceeds the allowed maximum of 1000."
	 public void testInvalidDueAmountExceedsMax() {
	        try {
	            WarningLetter.validateDueAmount(1500.00);
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Due amount exceeds the allowed maximum of 1000.", e.getMessage());
	        }
	    }
    
	 // Test #: 13
	 // Test Objective: Verify handling and formatting of the issue date when it is null.
	 // Inputs: issueDate = null
	 // Expected Output: issueDate is set to the current date.
	 public void testIssueDateDefaultsToCurrentDate() {
	        try {
	        	LocalDate today = LocalDate.now(); 
	            WarningLetter letter = new WarningLetter("WL001", "ORD0001", 82614, "Payment Issues", 90.00, Optional.empty());
	            assertTrue(today.isEqual(letter.getIssueDate()));
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception was not expected.");
	        }
	    }
	// Test #: 14
	// Test Objective: Verify the correct handling of a provided issue date.
	// Inputs: issueDate = LocalDate.of(2023, 1, 1)
	// Expected Output: issueDate is set to the provided date.
	 public void testIssueDateProvided() {
	        LocalDate specificDate = LocalDate.of(2023, 1, 1);
	        try {
	        	WarningLetter letter = new WarningLetter("WL001", "ORD0001", 82614, "Payment Issues", 90.00, Optional.of(specificDate));
	            assertEquals(specificDate, letter.getIssueDate());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception was not expected.");
	        }
	    }
	 
	// Test #: 15
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
	
    // Test #: 16
    // Test Objective: Maximum length check for customer name
    // Inputs: custName = "Alexanderson123" (15 characters)
    // Expected Output: No exception, name is valid.
	public void testCustomerNameMaxBoundary() {
        try {
            Customer.validateName("Alexanderson123");
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
	
	// Test #: 17
    // Test Objective: Valid maximum length for letterId
    // Inputs: letterId = "WL999"
    // Expected Output: No exception, letterId is valid.
    public void testMaxLengthLetterId() {
        try {
            WarningLetter.validateLetterId("WL999");
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
	
    // Test #: 18
    // Test Objective: Minimum length check for customer address
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

	 // Test #: 19
    // Test Objective: Maximum length check for customer address
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
	
	// Test #: 19
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

		

