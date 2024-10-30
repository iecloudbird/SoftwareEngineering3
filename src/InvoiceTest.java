import java.util.Date;
import junit.framework.TestCase;

public class InvoiceTest extends TestCase {

    // Test for successful creation of an Invoice object
	public void testCreateInvoiceSuccess() {
		Date orderDate = new Date();
		
		 try {
			 	Invoice invoiceObj = new Invoice("INV0001", "123", "card", orderDate, 100.0, "DP001", "PUB001", "Pending");
			 	System.out.println("Invoice Details: " + invoiceObj.toString());
		        assertEquals("INV0001", invoiceObj.getInvoiceId());
		        assertEquals("123", invoiceObj.getCustId());
		        assertEquals("card", invoiceObj.getPaymentMethod());
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

    // Test cases for invalid Invoice ID
    public void testInvalidInvoiceId_Null() {
        try {
            Invoice.validateInvoiceId(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID NOT specified", e.getMessage());
        }
    }

    public void testInvalidInvoiceId_Blank() {
        try {
            Invoice.validateInvoiceId("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID NOT specified", e.getMessage());
        }
    }

    public void testInvalidInvoiceId_InvalidFormat() {
        try {
            Invoice.validateInvoiceId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Invoice ID must follow the format INV0001", e.getMessage());
        }
    }

    public void testInvalidInvoiceId_ValidFormat() {
        try {
            Invoice.validateInvoiceId("INV1234"); // Valid format
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for invalid Customer ID
    public void testInvalidCustId_Null() {
        try {
            Invoice.validateCustId(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID NOT specified", e.getMessage());
        }
    }

    public void testInvalidCustId_Blank() {
        try {
            Invoice.validateCustId("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID NOT specified", e.getMessage());
        }
    }

    public void testInvalidCustId_NonNumeric() {
        try {
            Invoice.validateCustId("ABC");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer ID must be a number", e.getMessage());
        }
    }

    public void testInvalidCustId_ValidFormat() {
        try {
            Invoice.validateCustId("123"); // Valid numeric ID
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for invalid Payment Method
    public void testInvalidPaymentMethod_Null() {
        try {
            Invoice.validatePaymentMethod(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method NOT specified", e.getMessage());
        }
    }

    public void testInvalidPaymentMethod_Blank() {
        try {
            Invoice.validatePaymentMethod("  ");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method NOT specified", e.getMessage());
        }
    }

    public void testInvalidPaymentMethod_TooShort() {
        try {
            Invoice.validatePaymentMethod("cash");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method must be at least 5 characters long", e.getMessage());
        }
    }

    public void testInvalidPaymentMethod_TooLong() {
        try {
            Invoice.validatePaymentMethod("This payment method is definitely longer than sixty characters.");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method must not exceed 60 characters", e.getMessage());
        }
    }

    public void testInvalidPaymentMethod_Valid() {
        try {
            Invoice.validatePaymentMethod("credit card"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for invalid Order Date
    public void testInvalidOrderDate_Null() {
        try {
            Invoice.validateOrderDate(null);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Date NOT specified", e.getMessage());
        }
    }

    // Test cases for invalid Total Amount
    public void testInvalidTotalAmount_Negative() {
        try {
            Invoice.validateTotalAmount(-10.0);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Total Amount cannot be negative", e.getMessage());
        }
    }

    public void testInvalidTotalAmount_Zero() {
        try {
            Invoice.validateTotalAmount(0.0); // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    public void testInvalidTotalAmount_Positive() {
        try {
            Invoice.validateTotalAmount(10.0); // Valid case
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for optional Delivery ID
    public void testInvalidDeliveryId_InvalidFormat() {
        try {
            Invoice.validateDeliveryId("INVALID");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery ID must follow the format DP000", e.getMessage());
        }
    }

    public void testInvalidDeliveryId_ValidFormat() {
        try {
            Invoice.validateDeliveryId("DP123"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for optional Publication ID
    public void testInvalidPublicationId_InvalidFormat() {
        try {
            Invoice.validatePublicationId("INVALID");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication ID must follow the format PUB001", e.getMessage());
        }
    }

    public void testInvalidPublicationId_ValidFormat() {
        try {
            Invoice.validatePublicationId("PUB123"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test cases for optional Order Status
    public void testInvalidOrderStatus_Invalid() {
        try {
            Invoice.validateOrderStatus("  "); // Invalid: Blank
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
        }
    }

    public void testInvalidOrderStatus_TooShort() {
        try {
            Invoice.validateOrderStatus("New"); // Invalid: Too short
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
        }
    }

    public void testInvalidOrderStatus_TooLong() {
        try {
            Invoice.validateOrderStatus("This order status is definitely longer than sixty characters."); // Invalid: Too long
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status must be between 5 and 60 characters", e.getMessage());
        }
    }

    public void testInvalidOrderStatus_Valid() {
        try {
            Invoice.validateOrderStatus("Pending"); // Valid
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }
}
