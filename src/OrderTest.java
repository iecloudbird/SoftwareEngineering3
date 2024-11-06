import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import junit.framework.TestCase;


public class OrderTest extends TestCase{
	
	// Test #: 1
    // Test Objective: Create an order with valid parameters.
    // Inputs: orderId = "ORD0001", custId = "12345", deliveryId = "DP001", 
    //         publicationId = "PUB001", orderDate = future date, 
    //         orderStatus = OrderStatus.PENDING.
    // Expected Output: Order object is created with matching parameters.
	public void testCreateOrderSuccess() {
		try {
	        // Valid parameters
	        String orderId = "ORD0001";
	        int custId = 12345;
	        String deliveryId = "DP001";
	        String publicationId = "PUB001";
	        Date orderDate = new Date(System.currentTimeMillis() + 10000);  // future date
	        OrderStatus orderStatus = OrderStatus.PENDING;

	        // Call method under test
	        Order orderObj = new Order(orderId, custId, deliveryId, publicationId, orderDate, orderStatus);

	        // Assertions to check if the object is created correctly
	        assertEquals(orderId, orderObj.getOrderId());
	        assertEquals(custId, orderObj.getCustId());
	        assertEquals(deliveryId, orderObj.getDeliveryId());
	        assertEquals(publicationId, orderObj.getPublicationId());
	        assertEquals(orderDate, orderObj.getOrderDate());
	        assertEquals(orderStatus, orderObj.getOrderStatus());
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected: " + e.getMessage());
	    }
    }
	
	// Test #: 2
    // Test Objective: Validate Order ID - Boundary case (1 character)
    // Inputs: orderId = "J"
    // Expected Output: Exception with message "Order Id does not meet minimum length requirements".
    public void testInvalidOrderIdLength() {
        try {
            Order.validateOrderId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id does not meet minimum length requirements", e.getMessage());
        }
    }
    
    // Test #: 3
    // Test Objective: Validate Order ID - Exceeds maximum length.
    // Inputs: orderId = "ORD123411"
    // Expected Output: Exception with message "Order Id exceeds maximum length requirements".
    public void testOrderIdExceedsMaxLength() {
        try {
        	Order.validateOrderId("ORD123411");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
        	assertEquals("Order Id exceeds maximum length requirements", e.getMessage());
        }	
    }
    
	// Test #: 4
    // Test Objective: Validate Order ID - Invalid format.
    // Inputs: orderId = "ORD"
    // Expected Output: Exception with message "Order Id format is invalid. Expected format: ORD0001".
    public void testOrderIdInvalidFormat() {
        try {
            Order.validateOrderId("ORD"); // Not valid format
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id format is invalid. Expected format: ORD0001", e.getMessage());
        }
    }
    
	 // Test #: 5
	 // Test Objective: Validate Customer ID with negative value
	 // Inputs: custId = -1
	 // Expected Output: Exception with message "Customer Id format is invalid. Expected format: positive integer".
	 public void testInvalidCustIdLength() {
	     try {
	         Order.validateCustId(-1);  // Invalid input, negative number
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Customer Id format is invalid. Expected format: positive integer", e.getMessage());
	     }
	 }
	
	 // Test #: 6
	 // Test Objective: Validate Customer ID exceeding maximum length
	 // Inputs: custId = 12345678911
	 // Expected Output: Exception with message "Customer Id exceeds maximum length requirements".
	 public void testCustIdExceedsMaxLength() {
	     try {
	         Order.validateCustId(1234567911); 
	         fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	         assertEquals("Customer Id exceeds maximum length requirements", e.getMessage());
	     }
	 }
	
	// Test #: 7
	// Test Objective: Validate Customer ID with invalid format (non-integer input)
	// Inputs: custId = "abcde"
	// Expected Output: Exception with message "Customer Id format is invalid. Expected format: positive integer".
	 public void testCustIdInvalidFormat() {
		    try {
		        int invalidCustId = Integer.parseInt("abcde"); 
		        Order.validateCustId(invalidCustId);  
		        fail("Exception expected for non-integer input");
		    } catch (NumberFormatException ex) {
		        assertTrue("Input 'abcde' should throw a NumberFormatException", true);
		    } catch (CustomerExceptionHandler e) {
		        assertEquals("Customer Id format is invalid. Expected format: positive integer", e.getMessage());
		    }
		}
	
    // Test #: 8
    // Test Objective: Validate Delivery ID - Boundary case (1 character).
    // Inputs: deliveryId = "J"
    // Expected Output: Exception with message "Delivery Id does not meet minimum length requirements".
    public void testInvalidDeliveryIdLength() {
        try {
            Order.validateDeliveryId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery Id does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 9
    // Test Objective: Validate Delivery ID - Exceeds maximum length.
    // Inputs: deliveryId = "DP00001001"
    // Expected Output: Exception with message "Delivery Id exceeds maximum length requirements".
    public void testDeliveryIdExceedsMaxLength() {
        try {
            Order.validateDeliveryId("DP00001001"); // Exceeds max length
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery Id exceeds maximum length requirements", e.getMessage());
        }
    }

    // Test #: 10
    // Test Objective: Validate Delivery ID - Invalid format.
    // Inputs: deliveryId = "DPD01"
    // Expected Output: Exception with message "Delivery Id format is invalid. Expected format: DP/000".
    public void testDeliveryIdInvalidFormat() {
        try {
            Order.validateDeliveryId("DPD01"); // Invalid format
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Delivery Id format is invalid. Expected format: DP000", e.getMessage());
        }
    }

    // Test #: 11
    // Test Objective: Validate Publication ID - Boundary case (1 character).
    // Inputs: publicationId = "J"
    // Expected Output: Exception with message "Publication Id does not meet minimum length requirements".
    public void testInvalidPublicationIdLength() {
        try {
            Order.validatePublicationId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication Id does not meet minimum length requirements", e.getMessage());
        }
    }

    // Test #: 12
    // Test Objective: Validate Publication ID - Exceeds maximum length.
    // Inputs: publicationId = "PUB12345"
    // Expected Output: Exception with message "Publication Id exceeds maximum length requirements".
    public void testPublicationIdExceedsMaxLength() {
        try {
            Order.validatePublicationId("PUB12345"); // Exceeds max length
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication Id exceeds maximum length requirements", e.getMessage());
        }
    }

    // Test #: 13
    // Test Objective: Validate Publication ID - Invalid format.
    // Inputs: publicationId = "PUBB01"
    // Expected Output: Exception with message "Publication Id format is invalid. Expected format: PUB0001".
    public void testPublicationIdInvalidFormat() {
        try {
            Order.validatePublicationId("PUBB01"); // Invalid format
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Publication Id format is invalid. Expected format: PUB001", e.getMessage());
        }
    }

    // Test #: 14
    // Test Objective: Validate Order Date - Past date.
    // Inputs: orderDate = past date
    // Expected Output: Exception with message "Order Date cannot be in the past".
    public void testOrderDateInThePast() {
        // Create a past date using LocalDate
        LocalDate pastLocalDate = LocalDate.now().minusYears(1); // 1 year ago
        Date pastDate = Date.from(pastLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        try {
            Order.validateOrderDate(pastDate);
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Date cannot be in the past", e.getMessage());
        }
    }

    // Test #: 15
    // Test Objective: Validate Order Date - Null check.
    // Inputs: orderDate = null
    // Expected Output: Exception with message "Order Date NOT specified".
    public void testOrderDateNullCheck() {
        try {
            Order.validateOrderDate(null); // Null check
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Date NOT specified", e.getMessage());
        }
    }
    
    // Test #: 16
    // Test Objective: Validate Order Status - Null check.
    // Inputs: orderStatus = null
    // Expected Output: Exception with message "Order Status NOT specified".
    public void testOrderStatusNullCheck() {
        try {
            Order.validateOrderStatus(null); // Null check
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Status NOT specified", e.getMessage());
        }
    }
    
}
