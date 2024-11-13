import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import junit.framework.TestCase;

/* 
 * Order Test Cases Summary
 * 
 * Entity: Order
 * Objective: Ensure 100% full coverage test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
 * 
 * Total Test Cases: 20 (10 BA + 10 EP)
 * 
 * Boundary Analysis (BA): 10 Test Cases
 * - Max and Min Length for OrderId,CustId, deliveryArea Id and publication ID
 * 
 * Equivalence Partitioning (EP): 10 Test Cases
 * - Successful Creation of Order
 * - Invalid and Valid Order Status, orderDate, custId
 */

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
	        String deliveryAreaId = "AREA01";
	        String publicationId = "PUB001";
	        LocalDate orderDate = LocalDate.now();
	        OrderStatus orderStatus = OrderStatus.PENDING;

	        // Call method under test
	        Order orderObj = new Order(orderId, custId, deliveryAreaId, publicationId, Optional.of(orderDate), orderStatus);

	        // Assertions to check if the object is created correctly
	        assertEquals(orderId, orderObj.getOrderId());
            assertEquals(custId, orderObj.getCustId());
            assertEquals(deliveryAreaId, orderObj.getDeliveryAreaId());
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
    // Expected Output: Exception with message "Order Id format is invalid. Expected format: ORD0001".
    public void testInvalidOrderIdLength() {
        try {
            Order.validateOrderId("J");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Order Id format is invalid. Expected format: ORD0001", e.getMessage());
        }
    }
    
    // Test #: 3
    // Test Objective: Validate Order ID - Exceeds maximum length.
    // Inputs: orderId = "ORD123411"
    // Expected Output: Exception with message "Order Id format is invalid. Expected format: ORD0001".
    public void testOrderIdExceedsMaxLength() {
        try {
        	Order.validateOrderId("ORD123411");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
        	assertEquals("Order Id format is invalid. Expected format: ORD0001", e.getMessage());
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
	 // Test Objective: Validate Delivery ID - Invalid length (1 character).
	 // Inputs: Delivery Id = J
	 // Expected Output: Exception with message "Delivery Id format is invalid. Expected format: AREA00.".
	 public void testInvalidDeliveryAreaIdLength() {
	     try {
	         Order.validateDeliveryId("J");
	          fail("Exception expected");
	     } catch (CustomerExceptionHandler e) {
	          assertEquals("Area ID must match the format: AREA00.", e.getMessage());
	     }
	   }
	
    // Test #: 9
    // Test Objective: Validate Delivery ID - Exceeds maximum length.
    // Inputs: deliveryId = "AREA0001"
    // Expected Output: Exception with message "Area ID must match the format: AREA00.".
	 public void testDeliveryAreaIdExceedsMaxLength() {
	        try {
	            Order.validateDeliveryId("AREA0001"); // Exceeds max length
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Area ID must match the format: AREA00.", e.getMessage());
	        }
	    }

    // Test #: 10
    // Test Objective: Validate Delivery ID - Invalid format.
    // Inputs: deliveryId = "DPD01"
    // Expected Output: Exception with message "Delivery Id format is invalid. Expected format: DP/000".
	 public void testDeliveryAreaIdInvalidFormat() {
	        try {
	            Order.validateDeliveryId("DP01"); // Invalid format
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Area ID must match the format: AREA00.", e.getMessage());
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
	            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
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
	            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
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
	            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
	        }
	    }

    // Test #: 14
    // Test Objective: Validate Order Date - Past date.
    // Inputs: orderDate = past date
    // Expected Output: Exception with message "Order Date cannot be in the past".
	 public void testOrderDateInThePast() {
	        // Create a past date using LocalDate
	        LocalDate pastLocalDate = LocalDate.now().minusYears(1); // 1 year ago

	        try {
	            Order.validateOrderDate(Optional.of(pastLocalDate)); // Past date
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Order Date cannot be in the past.", e.getMessage());
	        }
	    }

    // Test #: 15
    // Test Objective: Validate Order Date - Null check.
    // Inputs: orderDate = null
    // Expected Output: Exception with message "Order Date NOT specified".
	 public void testOrderDateNullCheck() {
	        try {
	            Order.validateOrderDate(Optional.empty()); // Null check
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Order Date NOT specified.", e.getMessage());
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
	 
	// Test #: 16
	    // Test Objective: Validate Order Status - Valid status PENDING.
	    public void testValidOrderStatusPending() {
	        try {
	            Order.validateOrderStatus(OrderStatus.PENDING); // Valid input
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected: " + e.getMessage());
	        }
	    }

	    // Test #: 17
	    // Test Objective: Validate Order Status - Valid status CANCELLED.
	    public void testValidOrderStatusCancelled() {
	        try {
	            Order.validateOrderStatus(OrderStatus.CANCELLED); // Valid input
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected: " + e.getMessage());
	        }
	    }

	    // Test #: 18
	    // Test Objective: Validate Order Status - Invalid status. 
	    public void testOrderStatusInvalidEnum() {
	        try {
	            OrderStatus invalidStatus = null; // Simulate invalid status
	            Order.validateOrderStatus(invalidStatus);
	            fail("Exception expected for invalid order status");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Order Status NOT specified", e.getMessage());
	        }
	    }

	    
	    // Test #: 19
	    // Test Objective: Validate creation of Order with future date.
	    public void testOrderWithFutureDate() {
	        try {
	            String orderId = "ORD0002";
	            int custId = 999;
	            String deliveryAreaId = "AREA02";
	            String publicationId = "PUB002";
	            LocalDate futureDate = LocalDate.now().plusDays(1); // valid future date
	            OrderStatus orderStatus = OrderStatus.CONFIRMED;

	            Order order = new Order(orderId, custId, deliveryAreaId, publicationId, Optional.of(futureDate), orderStatus);

	            // Verify that the order is created correctly
	            assertEquals(orderId, order.getOrderId());
	            assertEquals(custId, order.getCustId());
	            assertEquals(deliveryAreaId, order.getDeliveryAreaId());
	            assertEquals(publicationId, order.getPublicationId());
	            assertEquals(futureDate, order.getOrderDate());
	            assertEquals(orderStatus, order.getOrderStatus());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected: " + e.getMessage());
	        }
	    }

	    // Test #: 20
	    // Test Objective: Validate creation of Order with today's date.
	    public void testOrderWithTodayDate() {
	        try {
	            String orderId = "ORD0003";
	            int custId = 1001;
	            String deliveryAreaId = "AREA03";
	            String publicationId = "PUB003";
	            LocalDate todayDate = LocalDate.now(); // today's date
	            OrderStatus orderStatus = OrderStatus.POSTPONED;

	            Order order = new Order(orderId, custId, deliveryAreaId, publicationId, Optional.of(todayDate), orderStatus);

	            // Verify that the order is created correctly
	            assertEquals(orderId, order.getOrderId());
	            assertEquals(custId, order.getCustId());
	            assertEquals(deliveryAreaId, order.getDeliveryAreaId());
	            assertEquals(publicationId, order.getPublicationId());
	            assertEquals(todayDate, order.getOrderDate());
	            assertEquals(orderStatus, order.getOrderStatus());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected: " + e.getMessage());
	        }
	    }
    
}
