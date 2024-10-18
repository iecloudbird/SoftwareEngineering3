import junit.framework.TestCase;
public class OrderTest extends TestCase{
	public void testCreateOrderSuccess() {
		//Test 1: Create and test the Order object
		try {
			
			// Call method under test
            Order orderObj = new Order(null, null, null, null, null, true);
            
            // Use getters to check for object creation
            assertEquals(null, orderObj.getOrderId());
            assertEquals(null, orderObj.getCustId());
            assertEquals(null, orderObj.getDeliveryId());
            assertEquals(null, orderObj.getPublicationId());
            assertEquals(null, orderObj.getOrderDate());
            assertEquals("true", orderObj.getOrderStatus().toString());
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
	public void testInvalidCustId() {
        try {
            // Call method under test
            Order.validateCustId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Customer Id does not meet minimum length requirements", e.getMessage());
        }
    }
	public void testInvalidDeliveryId() {
        try {
            // Call method under test
            Order.validateDeliveryId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery Id does not meet minimum length requirements", e.getMessage());
        }
	}
	public void testInvalidPublicationId() {
        try {
            // Call method under test
            Order.validatePublicationId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication Id does not meet minimum length requirements", e.getMessage());
        }
	}
//	public void testInvalidOrderDate() {
//        try {
//            // Call method under test
//            Order.validateOrderDate("J");
//            fail("Exception expected");
//        } catch (CustomerExceptionHandler e) {
//            assertEquals("Order Date does not meet minimum length requirements", e.getMessage());
//        }
//	}
//	public void testInvalidOrderStatus() {
//        try {
//            // Call method under test
//            Order.validateOrderStatus("J");
//            fail("Exception expected");
//        } catch (CustomerExceptionHandler e) {
//            assertEquals("Order Status does not meet minimum length requirements", e.getMessage());
//        }
//	}
}
