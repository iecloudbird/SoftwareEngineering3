import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import junit.framework.TestCase;
	
/*
 * DeliveryDocket Test Cases Summary
 * 
 * Entity: DeliveryDocket
 * Objective: Ensure 100%/full coverage test cases using EP and BA
 * BA (Boundary Analysis): 10 Test Cases
 * EP (Equivalence Partitioning): 6 Test Cases
 * 
 * Boundary Analysis (BA): 10 Test Cases
 * - Valid and Invalid Length for docketId and orderId: 4
 * - Edge Cases for totalCustomers (0 and Integer.MAX_VALUE): 2
 * - Format Validation (date format): 2
 * - Valid and Invalid deliveryStatus: 2
 * 
 * Equivalence Partitioning (EP): 6 Test Cases
 * - Valid and Invalid Constructor: 1
 * - Invalid Formats (Area ID, Delivery Person ID): 2
 * - Null and Empty Values for details: 2
 * - Uppercase/Lowercase Validation: 1
 * 
 */

public class DeliveryDocketTest extends TestCase{
	   // Test #: 1
    // Test Objective: Verify the constructor accepts valid parameters.
    // Inputs: docketId = "DD00001", orderId = "ORD001", deliveryPersonId = "DP001",
    //         deliveryDate = "30/10/2024", deliveryStatus = "Out for delivery", details = "Test delivery"
    // Expected Output: DeliveryDocket object created with matching parameters.
    public void testValidConstructor() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");

        assertEquals("DD00001", docket.getDocketId());
        assertEquals("ORD001", docket.getOrderId());
        assertEquals("DP001", docket.getDeliveryPersonId());
        assertEquals(LocalDate.parse("30/10/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), docket.getDeliveryDate());
        assertEquals("Out for delivery", docket.getDeliveryStatus());
    }

    // Test #: 2
    // Test Objective: Boundary analysis for valid docketId format.
    // Inputs: docketId = "DD00000", other valid parameters.
    // Expected Output: DeliveryDocket object created with docketId = "DD00000".
    public void testDocketIdMinLength() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00000", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
        assertEquals("DD00000", docket.getDocketId());
    }

    // Test #: 3
    // Test Objective: Boundary analysis for valid docketId format (maximum length).
    // Inputs: docketId = "DD99999", other valid parameters.
    // Expected Output: DeliveryDocket object created with docketId = "DD99999".
    public void testDocketIdMaxLength() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD99999", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
        assertEquals("DD99999", docket.getDocketId());
    }

    // Test #: 4
    // Test Objective: Equivalence partitioning for invalid docketId format.
    // Inputs: docketId = "XX00001", other valid parameters.
    // Expected Output: DeliveryDocketException thrown.
    public void testInvalidDocketIdFormat() {
        try {
            new DeliveryDocket("XX00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid docket ID format. Expected format: DD00000", e.getMessage());
        }
    }

    // Test #: 5
    // Test Objective: Boundary analysis for valid orderId format.
    // Inputs: orderId = "ORD000", other valid parameters.
    // Expected Output: DeliveryDocket object created with orderId = "ORD000".
    public void testOrderIdMinLength() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD000", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
        assertEquals("ORD000", docket.getOrderId());
    }

    // Test #: 6
    // Test Objective: Equivalence partitioning for invalid orderId format.
    // Inputs: orderId = "XYZ001", other valid parameters.
    // Expected Output: DeliveryDocketException thrown.
    public void testInvalidOrderIdFormat() {
        try {
            new DeliveryDocket("DD00001", "XYZ001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid Order ID format. Expected format: ORD000", e.getMessage());
        }
    }

    // Test #: 7
    // Test Objective: Boundary analysis for valid deliveryPersonId format.
    // Inputs: deliveryPersonId = "DP000", other valid parameters.
    // Expected Output: DeliveryDocket object created with deliveryPersonId = "DP000".
    public void testValidDeliveryPersonId() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP000", "30/10/2024", "Out for delivery", "Test delivery");
        assertEquals("DP000", docket.getDeliveryPersonId());
    }

    // Test #: 8
    // Test Objective: Equivalence partitioning for invalid deliveryPersonId format.
    // Inputs: deliveryPersonId = "D001", other valid parameters.
    // Expected Output: DeliveryDocketException thrown.
    public void testInvalidDeliveryPersonIdFormat() {
        try {
            new DeliveryDocket("DD00001", "ORD001", "D001", "30/10/2024", "Out for delivery", "Test delivery");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid Delivery Person ID format. Expected format: DP000", e.getMessage());
        }
    }

    // Test #: 9
    // Test Objective: Boundary analysis for valid deliveryDate format.
    // Inputs: deliveryDate = "01/01/2024", other valid parameters.
    // Expected Output: DeliveryDocket object created with deliveryDate = "01/01/2024".
    public void testValidDeliveryDate() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "01/01/2024", "Out for delivery", "Test delivery");
        assertEquals(LocalDate.parse("01/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")), docket.getDeliveryDate());
    }

    // Test #: 10
    // Test Objective: Equivalence partitioning for invalid deliveryDate format.
    // Inputs: deliveryDate = "31-12-2024", other valid parameters.
    // Expected Output: DeliveryDocketException thrown.
    public void testInvalidDeliveryDateFormat() {
        try {
            new DeliveryDocket("DD00001", "ORD001", "DP001", "31-12-2024", "Out for delivery", "Test delivery");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid delivery date format. Expected format: dd/MM/yyyy", e.getMessage());
        }
    }

    // Test #: 11
    // Test Objective: Valid delivery status.
    // Inputs: deliveryStatus = "Delivered", other valid parameters.
    // Expected Output: DeliveryDocket object created with deliveryStatus = "Delivered".
    public void testValidDeliveryStatus() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Delivered", "Test delivery");
        assertEquals("Delivered", docket.getDeliveryStatus());
    }

    // Test #: 12
    // Test Objective: Equivalence partitioning for invalid delivery status.
    // Inputs: deliveryStatus = "Unknown", other valid parameters.
    // Expected Output: DeliveryDocketException thrown.
    public void testInvalidDeliveryStatus() {
        try {
            new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Unknown", "Test delivery");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid delivery status. Valid options: Delivered, Out for delivery, Not delivered", e.getMessage());
        }
    }

    // Test #: 13
    // Test Objective: Update delivery status with a valid status.
    // Inputs: newDeliveryStatus = "Not delivered".
    // Expected Output: Updated delivery status.
    public void testUpdateDeliveryStatus() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
        docket.updateDeliveryStatus("Not delivered");
        assertEquals("Not delivered", docket.getDeliveryStatus());
    }

    // Test #: 14
    // Test Objective: Test for updating delivery status to an invalid option.
    // Inputs: new deliveryStatus = "InvalidStatus"
    // Expected Output: DeliveryDocketException thrown with message "Invalid delivery status. Valid options: Delivered, Out for delivery, Not delivered".
    public void testUpdateDeliveryStatusInvalid() {
        try {
            DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
            docket.updateDeliveryStatus("InvalidStatus");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid delivery status. Valid options: Delivered, Out for delivery, Not delivered", e.getMessage());
        }
    }
    
    // Test #: 15
    // Test Objective: Test details field handling (non-null).
    // Inputs: details = "Test delivery details"
    // Expected Output: details field is set to "Test delivery details".
    public void testDetailsField() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery details");
        assertEquals("Test delivery details", docket.getDetails());
    }

    // Test #: 16
    // Test Objective: Test that null details field does not throw an exception.
    // Inputs: details = null
    // Expected Output: details field is set to null.
    public void testNullDetailsField() throws DeliveryDocketException {
        DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", null);
        assertNull(docket.getDetails());
    }
}
