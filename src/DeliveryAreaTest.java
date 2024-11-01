import junit.framework.TestCase;

public class DeliveryAreaTest extends TestCase {

    // Test #: 1
    // Test Objective: Verify the constructor accepts valid parameters.
    // Inputs: areaId = "AREA01", areaName = "Central", deliveryPersonId = "DP001", totalCustomers = 10
    // Expected Output: DeliveryArea object created with matching parameters.
    public void testValidConstructor() throws DeliveryAreaException {
        DeliveryArea area = new DeliveryArea("AREA01", "Central", "DP001", 10);
        assertEquals("AREA01", area.getAreaId());
        assertEquals("Central", area.getAreaName());
        assertEquals("DP001", area.getDeliveryPersonId());
        assertEquals(10, area.getTotalCustomers());
    }

    // Test #: 2
    // Test Objective: Verify areaId format validation (minimum length).
    // Inputs: areaId = null
    // Expected Output: DeliveryAreaException thrown.
    public void testNullAreaId() {
        try {
            new DeliveryArea(null, "Central", "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area ID cannot be null.", e.getMessage());
        }
    }

    // Test #: 3
    // Test Objective: Verify areaId format validation (invalid format).
    // Inputs: areaId = "INVALID"
    // Expected Output: DeliveryAreaException thrown.
    public void testInvalidAreaIdFormat() {
        try {
            new DeliveryArea("INVALID", "Central", "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area ID must match the format: AREA00.", e.getMessage());
        }
    }

    // Test #: 4
    // Test Objective: Verify areaName cannot be null.
    // Inputs: areaName = null
    // Expected Output: DeliveryAreaException thrown.
    public void testNullAreaName() {
        try {
            new DeliveryArea("AREA01", null, "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area name cannot be null.", e.getMessage());
        }
    }

    // Test #: 5
    // Test Objective: Verify areaName cannot be empty.
    // Inputs: areaName = ""
    // Expected Output: DeliveryAreaException thrown.
    public void testEmptyAreaName() {
        try {
            new DeliveryArea("AREA01", "", "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area name cannot be empty.", e.getMessage());
        }
    }

    // Test #: 6
    // Test Objective: Verify areaName length limit.
    // Inputs: areaName = "A" * 101
    // Expected Output: DeliveryAreaException thrown.
    public void testAreaNameTooLong() {
        try {
            new DeliveryArea("AREA01", "A".repeat(101), "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area name cannot exceed 100 characters.", e.getMessage());
        }
    }

    // Test #: 7
    // Test Objective: Verify deliveryPersonId format validation (null).
    // Inputs: deliveryPersonId = null
    // Expected Output: DeliveryAreaException thrown.
    public void testNullDeliveryPersonId() {
        try {
            new DeliveryArea("AREA01", "Central", null, 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Delivery Person ID cannot be null.", e.getMessage());
        }
    }

    // Test #: 8
    // Test Objective: Verify deliveryPersonId format validation (invalid format).
    // Inputs: deliveryPersonId = "INVALID"
    // Expected Output: DeliveryAreaException thrown.
    public void testInvalidDeliveryPersonIdFormat() {
        try {
            new DeliveryArea("AREA01", "Central", "INVALID", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Delivery Person ID must match the format: DP000.", e.getMessage());
        }
    }

    // Test #: 9
    // Test Objective: Verify totalCustomers cannot be negative.
    // Inputs: totalCustomers = -1
    // Expected Output: DeliveryAreaException thrown.
    public void testNegativeTotalCustomers() {
        try {
            new DeliveryArea("AREA01", "Central", "DP001", -1);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Total customers cannot be negative.", e.getMessage());
        }
    }

    // Test #: 10
    // Test Objective: Verify totalCustomers can be zero.
    // Inputs: totalCustomers = 0
    // Expected Output: DeliveryArea object created successfully.
    public void testZeroTotalCustomers() throws DeliveryAreaException {
        DeliveryArea area = new DeliveryArea("AREA01", "Central", "DP001", 0);
        assertEquals(0, area.getTotalCustomers());
    }

    // Test #: 11
    // Test Objective: Verify totalCustomers can be a positive value.
    // Inputs: totalCustomers = 1
    // Expected Output: DeliveryArea object created successfully.
    public void testPositiveTotalCustomers() throws DeliveryAreaException {
        DeliveryArea area = new DeliveryArea("AREA01", "Central", "DP001", 1);
        assertEquals(1, area.getTotalCustomers());
    }

    // Test #: 12
    // Test Objective: Verify upper limit for totalCustomers (if applicable).
    // Inputs: totalCustomers = 1000 (assuming this is the upper limit)
    // Expected Output: DeliveryArea object created successfully.
    public void testUpperLimitTotalCustomers() throws DeliveryAreaException {
        DeliveryArea area = new DeliveryArea("AREA01", "Central", "DP001", 1000);
        assertEquals(1000, area.getTotalCustomers());
    }

    // Test #: 13
    // Test Objective: Verify areaId with invalid format.
    // Inputs: areaId = "AREA1" (missing leading zero)
    // Expected Output: DeliveryAreaException thrown.
    public void testInvalidAreaIdMissingZero() {
        try {
            new DeliveryArea("AREA1", "Central", "DP001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Area ID must match the format: AREA00.", e.getMessage());
        }
    }

    // Test #: 14
    // Test Objective: Verify deliveryPersonId with invalid format.
    // Inputs: deliveryPersonId = "D001" (missing leading zero)
    // Expected Output: DeliveryAreaException thrown.
    public void testInvalidDeliveryPersonIdMissingZero() {
        try {
            new DeliveryArea("AREA01", "Central", "D001", 10);
            fail("Exception expected");
        } catch (DeliveryAreaException e) {
            assertEquals("Delivery Person ID must match the format: DP000.", e.getMessage());
        }
    }
}
