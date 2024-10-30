import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import junit.framework.TestCase;
	

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
        docket.updateDeliveryStatus("Delivered");
        assertEquals("Delivered", docket.getDeliveryStatus());
    }

    // Test #: 14
    // Test Objective: Update delivery status with an invalid status.
    // Inputs: newDeliveryStatus = "Pending".
    // Expected Output: DeliveryDocketException thrown.
    public void testUpdateDeliveryStatusInvalid() {
        try {
        	 DeliveryDocket docket = new DeliveryDocket("DD00001", "ORD001", "DP001", "30/10/2024", "Out for delivery", "Test delivery");
            docket.updateDeliveryStatus("Pending");
            fail("Exception expected");
        } catch (DeliveryDocketException e) {
            assertEquals("Invalid delivery status. Valid options: Delivered, Out for delivery, Not delivered", e.getMessage());
        }
    }
}
