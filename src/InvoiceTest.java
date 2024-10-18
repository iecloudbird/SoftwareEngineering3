import junit.framework.TestCase;
//To do
public class InvoiceTest extends TestCase{
	public void testCreateInvoiceSuccess() {
		//Test 1: Create and test the Order object
		try {
			
			// Call method under test
            Invoice invoiceObj = new Invoice(null, null, "card", null, 0.0);
            
            // Use getters to check for object creation
            assertEquals(null, invoiceObj.getInvoiceId());
            assertEquals(null, invoiceObj.getCustId());
            assertEquals("card", invoiceObj.getPaymentMethod());
            assertEquals(null, invoiceObj.getOrderDate());
            assertEquals(0.0, invoiceObj.getTotalAmount());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
	}
	public void testInvalidInvoiceId001() {
        try {
            // Call method under test
            Invoice.validateInvoiceId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }
	public void testInvalidCustId001() {
        try {
            // Call method under test
            Invoice.validateCustId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }
	public void testInvalidPaymentMethod001() {
        try {
            // Call method under test
            Invoice.validatePaymentMethod("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Payment Method does not meet minimum length requirements", e.getMessage());
        }
    }
//	public void testInvalidOrderDate001() {
//        try {
//            // Call method under test
//            Invoice.validateOrderDate("J");
//            fail("Exception expected");
//        } catch (CustomerExceptionHandler e) {
//            assertEquals("Order Date does not meet minimum length requirements", e.getMessage());
//        }
//    }
//	public void testInvalidTotalAmount001() {
//        try {
//            // Call method under test
//            Invoice.validateTotalAmount("J");
//            fail("Exception expected");
//        } catch (CustomerExceptionHandler e) {
//            assertEquals("Total Amount does not meet minimum length requirements", e.getMessage());
//        }
//    }
}
