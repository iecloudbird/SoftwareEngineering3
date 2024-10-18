import junit.framework.TestCase;
//To do
public class InvoiceTest extends TestCase{
	public void testCreateInvoiceSuccess() {
		//Test 1: Create and test the Order object
		try {
			
			// Call method under test
            Invoice invoiceObj = new Invoice(0, 0, "card", null, 0.0);
            
            // Use getters to check for object creation
            assertEquals(0, invoiceObj.getInvoiceId());
            assertEquals(0, invoiceObj.getCustId());
            assertEquals("card", invoiceObj.getPaymentMethod());
            assertEquals(null, invoiceObj.getOrderDate());
            assertEquals(0.0, invoiceObj.getTotalAmount());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
	}
	public void testInvalidPaymentMethod001() {
        try {
            // Call method under test
            Invoice.validatePaymentMethod("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }
}
