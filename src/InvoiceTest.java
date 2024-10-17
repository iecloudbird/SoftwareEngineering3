import junit.framework.TestCase;
//To do
public class InvoiceTest extends TestCase{
	public void testCreateOrderSuccess() {
		//Test 1: Create and test the Order object
		try {
			
			// Call method under test
            Order orderObj = new Order(0, 0, 0, 0, null, true);
            
            // Use getters to check for object creation
            assertEquals(0, orderObj.getOrderId());
            assertEquals(0, orderObj.getCustId());
            assertEquals(0, orderObj.getDeliveryId());
            assertEquals(0, orderObj.getPublicationId());
            assertEquals(null, orderObj.getOrderDate());
            assertEquals(true, orderObj.getOrderStatus());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
	}
	public void testInvalidOrderId() {
        try {
            // Call method under test
            Order.validateOrderId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }
}
