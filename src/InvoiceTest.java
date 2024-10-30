import java.util.Date;
import junit.framework.TestCase;

public class InvoiceTest extends TestCase {

	// Test #: 1
	// Test Objective: Verify successful creation of an Invoice object with valid inputs
	// Inputs: invoiceId = "INV0001", custId = "123", paymentMethod = "card", orderDate = current date, totalAmount = 100.0, deliveryId = "DP001", publicationId = "PUB001", orderStatus = "Pending"
	// Expected Output: Invoice object created with correct details, all fields matching expected values
	public void testCreateInvoiceSuccess() {
		Date orderDate = new Date();
		
		 try {
			 	Invoice invoiceObj = new Invoice("INV0001", "123", "card", orderDate, 100.0, "DP001", "PUB001", "Pending");
		        assertEquals("INV0001", invoiceObj.getInvoiceId());
		        assertEquals("123", invoiceObj.getCustId());
		        assertEquals(PaymentMethod.CARD, invoiceObj.getPaymentMethod());
		        assertTrue("Order dates do not match", 
		                   orderDate.getTime() == invoiceObj.getOrderDate().getTime());
		        assertEquals(100.0, invoiceObj.getTotalAmount(), 0.001); // Use delta for double comparison
		        assertEquals("DP001", invoiceObj.getDeliveryId());
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
    public void testInvalidInvoiceId_ValidFormat() {
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
            assertEquals("Delivery ID must follow the format DP000", e.getMessage());
        }
    }
 // Test #: 21
 // Test Objective: Validate that an invalid Publication ID format throws an exception
 // Inputs: publicationId = "INVALID" (incorrect format)
 // Expected Output: Exception with message "Publication ID must follow the format PUB001"
    public void testInvalidDeliveryId_ValidFormat() {
        try {
            Invoice.validateDeliveryId("DP123"); // Valid
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
 // Expected Output: Exception with message "Order Status must be between 5 and 60 characters"

    public void testInvalidOrderStatus_Invalid() {
        try {
            Invoice.validateOrderStatus("  "); // Invalid: Blank
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
        }
    }

 // Test #: 24
 // Test Objective: Validate that an Order Status shorter than 5 characters throws an exception
 // Inputs: orderStatus = "New" (too short)
 // Expected Output: Exception with message "Order Status must be between 5 and 60 characters"
    public void testInvalidOrderStatus_TooShort() {
        try {
            Invoice.validateOrderStatus("New"); // Invalid: Too short
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
            Invoice.validateOrderStatus("This order status is definitely longer than sixty characters."); // Invalid: Too long
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
        }
    }
 // Test #: 26
 // Test Objective: Verify that a valid Order Status within character limits does not throw an exception
 // Inputs: orderStatus = "Pending" (valid)
 // Expected Output: No exception thrown
    public void testInvalidOrderStatus_Valid() {
        try {
            Invoice.validateOrderStatus("Pending"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
}
