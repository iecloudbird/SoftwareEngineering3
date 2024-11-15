import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import junit.framework.TestCase;

/*
 * Invoice Test Cases Summary
 * 
 * Entity: Invoice
 * Objective: Ensure 100% full coverage test cases using EP and BA
 * 
 * Total Test Cases: 32
 * 
 * Boundary Analysis (BA): 14 Test Cases
 * - Valid and Invalid Length for invoiceId and customerId: 5
 * - Edge Cases for totalAmount (0 and Integer.MAX_VALUE): 4
 * - Format Validation (date format for orderDate): 2
 * - Valid and Invalid paymentStatus: 4
 * 
 * Equivalence Partitioning (EP): 18 Test Cases
 * - Valid and Invalid Constructor: 1
 * - Invalid Formats for invoiceId, customerId, deliveryId, publicationId: 6
 * - Null and Empty Values for paymentMethod, orderStatus, orderDate: 5
 * - Valid and Invalid Total Amount: 4
 * - Validations for valid formats of Delivery ID and Publication ID: 2
 * - Valid and Invalid Order Status: 6
 */


public class InvoiceTest extends TestCase {

	// Test #: 1
	// Test Objective: Verify successful creation of an Invoice object with valid inputs
	// Inputs: invoiceId = "INV0001", custId = "123", paymentMethod = "card", orderDate = current date, totalAmount = 100.0, deliveryId = "DD00001", publicationId = "PUB001", orderStatus = "Pending"
	// Expected Output: Invoice object created with correct details, all fields matching expected values
	public void testCreateInvoiceSuccess() {
		
		 try {
			 Invoice invoiceObj = new Invoice("INV0001", "123", "card", Optional.empty(), 100.0, "DD00001", "PUB001", "Pending");
			 assertEquals("INV0001", invoiceObj.getInvoiceId());
		     assertEquals("123", invoiceObj.getCustId());
		     assertEquals(PaymentMethod.CARD, invoiceObj.getPaymentMethod());  
		     assertEquals(LocalDate.now(), invoiceObj.getOrderDate());
		     assertEquals(100.0, invoiceObj.getTotalAmount(), 0.001);  
		     assertEquals("DD00001", invoiceObj.getDeliveryId());
		     assertEquals("PUB001", invoiceObj.getPublicationId());
		     assertEquals("Pending", invoiceObj.getOrderStatus());
		    } catch (CustomerExceptionHandler e) {
		    	
		        fail("Exception not expected: " + e.getMessage());
		    }
	}

	// Test #: 2
	// Test Objective: Validate that a null Invoice ID throws an exception
	// Inputs: invoiceId = null
	// Expected Output: Exception with message "Invoice ID NOT specified"

    public void testInvalidInvoiceId_Null() {
        try {
            Invoice.validateInvoiceId(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID NOT specified", e.getMessage());
        }
    }
	 // Test #: 3
	 // Test Objective: Validate that a blank Invoice ID throws an exception
	 // Inputs: invoiceId = "  " (blank string)
	 // Expected Output: Exception with message "Invoice ID NOT specified"
    public void testInvalidInvoiceId_Blank() {
        try {
            Invoice.validateInvoiceId("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID NOT specified", e.getMessage());
        }
    }
    
	 // Test #: 4
	 // Test Objective: Validate that an improperly formatted Invoice ID throws an exception
	 // Inputs: invoiceId = "J" (invalid format)
	 // Expected Output: Exception with message "Invoice ID must follow the format INV0001"
    public void testInvalidInvoiceId_InvalidFormat() {
        try {
            Invoice.validateInvoiceId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID must follow the format INV0001", e.getMessage());
        }
    }
    
	 // Test #: 5
	 // Test Objective: Verify that a correctly formatted Invoice ID does not throw an exception
	 // Inputs: invoiceId = "INV1234" (valid format)
	 // Expected Output: No exception thrown
    public void testValidInvoiceId() {
        try {
            Invoice.validateInvoiceId("INV1234"); // Valid format
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 6
	 // Test Objective: Validate that a null Customer ID throws an exception
	 // Inputs: custId = null
	 // Expected Output: Exception with message "Customer ID NOT specified"
    public void testInvalidCustId_Null() {
        try {
            Invoice.validateCustId(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID NOT specified", e.getMessage());
        }
    }

	 // Test #: 7
	 // Test Objective: Validate that a blank Customer ID throws an exception
	 // Inputs: custId = "  " (blank string)
	 // Expected Output: Exception with message "Customer ID NOT specified"
    public void testInvalidCustId_Blank() {
        try {
            Invoice.validateCustId("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID NOT specified", e.getMessage());
        }
    }
    
	 // Test #: 8
	 // Test Objective: Validate that a non-numeric Customer ID throws an exception
	 // Inputs: custId = "ABC" (non-numeric string)
	 // Expected Output: Exception with message "Customer ID must be a number"
    public void testInvalidCustId_NonNumeric() {
        try {
            Invoice.validateCustId("ABC");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID must be a number", e.getMessage());
        }
    }
 // Test #: 9
 // Test Objective: Verify that a numeric Customer ID does not throw an exception
 // Inputs: custId = "123" (valid numeric ID)
 // Expected Output: No exception thrown
    public void testInvalidCustId_ValidFormat() {
        try {
            Invoice.validateCustId("123"); // Valid numeric ID
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

 // Test #: 10
 // Test Objective: Validate that a null Payment Method throws an exception
 // Inputs: paymentMethod = null
 // Expected Output: Exception with message "Payment Method NOT specified"
    public void testInvalidPaymentMethod_Null() {
        try {
            Invoice.validatePaymentMethod(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method NOT specified", e.getMessage());
        }
    }

 // Test #: 11
 // Test Objective: Validate that a blank Payment Method throws an exception
 // Inputs: paymentMethod = "  " (blank string)
 // Expected Output: Exception with message "Payment Method NOT specified"
    public void testInvalidPaymentMethod_Blank() {
        try {
            Invoice.validatePaymentMethod("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method NOT specified", e.getMessage());
        }
    }
 // Test #: 12
 // Test Objective: Validate that an invalid Payment Method value throws an exception
 // Inputs: paymentMethod = "check" (invalid payment method)
 // Expected Output: Exception with message "Invalid payment method. Accepted values: card, cash"
    public void testInvalidPaymentMethod_Unknown() {
        try {
            Invoice.validatePaymentMethod("check");  // Invalid payment method for this enum
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invalid payment method. Accepted values: card, cash", e.getMessage());
        }
    }
 // Test #: 13
 // Test Objective: Verify that "card" is a valid Payment Method
 // Inputs: paymentMethod = "card" (valid method)
 // Expected Output: No exception thrown
    public void testValidPaymentMethod_Card() {
        try {
            Invoice.validatePaymentMethod("card");  // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
 // Test #: 14
 // Test Objective: Verify that "cash" is a valid Payment Method
 // Inputs: paymentMethod = "cash" (valid method)
 // Expected Output: No exception thrown
    public void testValidPaymentMethod_Cash() {
        try {
            Invoice.validatePaymentMethod("cash");  // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

 // Test #: 15
 // Test Objective: Validate that a null Order Date throws an exception
 // Inputs: orderDate = null
 // Expected Output: Exception with message "Order Date NOT specified"
    public void testInvalidOrderDate_Null() {
        try {
            Invoice.validateOrderDate(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Date NOT specified", e.getMessage());
        }
    }

 // Test #: 16
 // Test Objective: Validate that a negative Total Amount throws an exception
 // Inputs: totalAmount = -10.0 (negative)
 // Expected Output: Exception with message "Total Amount cannot be negative"
    public void testInvalidTotalAmount_Negative() {
        try {
            Invoice.validateTotalAmount(-10.0);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Total Amount cannot be negative", e.getMessage());
        }
    }
 // Test #: 17
 // Test Objective: Verify that zero as a Total Amount is allowed
 // Inputs: totalAmount = 0.0 (zero)
 // Expected Output: No exception thrown
    public void testInvalidTotalAmount_Zero() {
        try {
            Invoice.validateTotalAmount(0.0); // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
 // Test #: 18
 // Test Objective: Verify that a positive Total Amount is allowed
 // Inputs: totalAmount = 10.0 (positive)
 // Expected Output: No exception thrown
    public void testInvalidTotalAmount_Positive() {
        try {
            Invoice.validateTotalAmount(10.0); // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

 // Test #: 19
 // Test Objective: Validate that an invalid Delivery ID format throws an exception
 // Inputs: deliveryId = "INVALID" (incorrect format)
 // Expected Output: Exception with message "Delivery ID must follow the format DP000"
    public void testInvalidDeliveryId_InvalidFormat() {
        try {
            Invoice.validateDeliveryId("INVALID");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery ID must follow the format DD00000", e.getMessage());
        }
    }
 // Test #: 21
 // Test Objective: Validate that an invalid Publication ID format throws an exception
 // Inputs: deliveryId = "DD00001"" (incorrect format)
 // Expected Output: Exception with message "Publication ID must follow the format PUB001"
    public void testInvalidDeliveryId_ValidFormat() {
        try {
            Invoice.validateDeliveryId("DD00001"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

 // Test #: 21
 // Test Objective: Validate that an invalid Publication ID format throws an exception
 // Inputs: publicationId = "INVALID" (incorrect format)
 // Expected Output: Exception with message "Publication ID must follow the format PUB001"
    public void testInvalidPublicationId_InvalidFormat() {
        try {
            Invoice.validatePublicationId("INVALID");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication ID must follow the format PUB001", e.getMessage());
        }
    }
 // Test #: 22
 // Test Objective: Verify that a correctly formatted Publication ID does not throw an exception
 // Inputs: publicationId = "PUB123" (valid format)
 // Expected Output: No exception thrown
    public void testInvalidPublicationId_ValidFormat() {
        try {
            Invoice.validatePublicationId("PUB123"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

	 // Test #: 23
	 // Test Objective: Validate that a blank Order Status throws an exception
	 // Inputs: orderStatus = "  " (blank string)
	 // Expected Output: Exception with message "Order Status NOT specified"
	 public void testInvalidOrderStatus_Blank() {
	     try {
	         Invoice.validateOrderStatus("  "); // Invalid: Blank
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Order Status NOT specified", e.getMessage());
	     }
	 }
	
	 // Test #: 24
	 // Test Objective: Validate that an Order Status shorter than 5 characters throws an exception
	 // Inputs: orderStatus = "New" (too short)
	 // Expected Output: Exception with message "Order Status must be between 5 and 60 characters"
	 public void testInvalidOrderStatus_TooShort() {
	     try {
	         Invoice.validateOrderStatus("New");
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
	     }
	 }
	
	 // Test #: 25
	 // Test Objective: Validate that an Order Status longer than 60 characters throws an exception
	 // Inputs: orderStatus = "This order status is definitely longer than sixty characters." (too long)
	 // Expected Output: Exception with message "Order Status must be between 5 and 60 characters"
	 public void testInvalidOrderStatus_TooLong() {
	     try {
	         Invoice.validateOrderStatus("This order status is definitely longer than sixty characters.");
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
	     }
	 }
	
	 // Test #: 26
	 // Test Objective: Verify that an invalid Order Status outside of acceptable values throws an exception
	 // Inputs: orderStatus = "Processing" (invalid status)
	 // Expected Output: Exception with message "Invalid Order Status. Accepted values are: Pending, Paid, Cancelled, Refunded"
	 public void testInvalidOrderStatus_InvalidValue() {
	     try {
	         Invoice.validateOrderStatus("Processing"); // Invalid: Not in accepted values
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Invalid Order Status. Accepted values are: Pending, Paid, Cancelled, Refunded", e.getMessage());
	     }
	 }
	
	 // Test #: 27
	 // Test Objective: Verify that a valid Order Status within character limits does not throw an exception
	 // Inputs: orderStatus = "Pending" 
	 // Expected Output: No exception thrown
	 public void testValidOrderStatus() {
	     try {
	         Invoice.validateOrderStatus("Pending"); // Valid
	     } catch (CustomerExceptionHandler e) {
	         fail("Exception not expected");
	     }
	 }

	// Test #: 28
	// Test Objective: Verify that a valid Order Status "Paid" does not throw an exception
	// Inputs: orderStatus = "Paid"
	// Expected Output: No exception thrown
	public void testValidOrderStatus_Paid() {
	    try {
	        Invoice.validateOrderStatus("Paid"); // Valid With boundary 4 characters
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 29
	// Test Objective: Verify that a valid Order Status "Cancelled" does not throw an exception
	// Inputs: orderStatus = "Cancelled"
	// Expected Output: No exception thrown
	public void testValidOrderStatus_Cancelled() {
	    try {
	        Invoice.validateOrderStatus("Cancelled"); // Valid
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 30
	// Test Objective: Verify that a valid Order Status "Refunded" does not throw an exception
	// Inputs: orderStatus = "Refunded"
	// Expected Output: No exception thrown
	public void testValidOrderStatus_Refunded() {
	    try {
	        Invoice.validateOrderStatus("Refunded"); // Valid
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	//No Max boundary due to limited choice of order status, therefore tested all possibilities.
	
	 // Test #: 31
	 // Test Objective: Verify that an invoice can be created with a total amount of Integer.MAX_VALUE
	 // Inputs: totalAmount = Integer.MAX_VALUE
	 // Expected Output: Invoice object is created with totalAmount = Integer.MAX_VALUE
	 public void testMaxTotalAmount() {
	     try {
	         Invoice invoiceObj = new Invoice("INV1001", "123", "card", Optional.empty(), Integer.MAX_VALUE, "DD00001", "PUB001", "Pending");
	         assertEquals(Integer.MAX_VALUE, invoiceObj.getTotalAmount(), 0.001);
	     } catch (CustomerExceptionHandler e) {
	         fail("Exception not expected: " + e.getMessage());
	     }
	 }
	
	 // Test #: 32
	 // Test Objective: Validate the order date format for a valid date
	 // Inputs: orderDate = "2024-11-09" (valid format)
	 // Expected Output: No exception thrown
	 public void testValidOrderDateFormat() {
	     try {
	    	 Optional<LocalDate> validOrderDate = Optional.of(LocalDate.parse("2024-11-09"));
	         Invoice.validateOrderDate(validOrderDate); // Valid format
	     } catch (CustomerExceptionHandler e) {
	         fail("Exception not expected");
	     }
	 }

}
