import junit.framework.TestCase;
	
/*
 * Delivery Person Test Cases Summary
 * 
 * Entity: DeliveryPerson
 * Objective: Ensure complete coverage of test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
 * 
 * Total Test Cases: 15
 * 
 * Boundary Analysis (BA): 8 Test Cases
 * - Minimum and Maximum Length for firstName: 3
 * - Minimum and Maximum Length for lastName: 3
 * - Valid Phone Number Length: 2
 * - Valid Assigned Area Format: 1
 * 
 * Equivalence Partitioning (EP): 7 Test Cases
 * - Valid and Invalid Constructor: 1
 * - Invalid Formats for firstName, lastName, deliveryPersonId, phoneNumber, assignedArea, and status: 6
 */

public class DeliveryPersonTest extends TestCase{

	// Test #: 1
    // Test Objective: Verify the constructor accepts valid parameters.
    // Inputs: firstName = "John", lastName = "Doe", deliveryPersonId = "DP123",
    //         phoneNumber = "1234567890", assignedArea = "AREA01", status = "Out for delivery"
    // Expected Output: DeliveryPerson object created with matching parameters.
    public void testValidConstructor() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "Doe", "DP123", "1234567890", "AREA01", "Out for delivery");
        
        assertEquals("John", dp.getFirstName());
        assertEquals("Doe", dp.getLastName());
        assertEquals("DP123", dp.getDeliveryPersonId());
        assertEquals("1234567890", dp.getPhoneNumber());
        assertEquals("AREA01", dp.getAssignedArea());
        assertEquals("Out for delivery", dp.getStatus());
    }

    
    // Test #: 2
    // Test Objective: Boundary analysis for the minimum length of firstName (1 character).
    // Inputs: firstName = "A", other valid parameters.
    // Expected Output: DeliveryPerson object created with firstName = "A".
    public void testFirstNameMinLength() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("A", "Doe", "DP123", "1234567890", "AREA01", "Out for delivery");
        assertEquals("A", dp.getFirstName());
    }

    // Test #: 3
    // Test Objective: Boundary analysis for the maximum length of firstName (15 characters).
    // Inputs: firstName = "Alexandersonson", other valid parameters.
    // Expected Output: DeliveryPerson object created with firstName = "Alexandersonson".
    public void testFirstNameMaxLength() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("Alexandersonson", "Doe", "DP123", "1234567890", "AREA01", "Out for delivery");
        assertEquals("Alexandersonson", dp.getFirstName());
    }

    // Test #: 4
    // Test Objective: Equivalence partitioning for null value of firstName.
    // Inputs: firstName = null, other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testFirstNameNull() {
    	try {
            new DeliveryPerson(null, "Doe", "DP123", "1234567890", "AREA01", "Out for delivery");
            fail("Exception expected");
        } catch (DeliveryPersonException e) {
        	 assertEquals("First name must be between 1 and 15 characters.", e.getMessage());
        }
    }


    // Test #: 5
    // Test Objective: Boundary analysis for the minimum length of lastName (1 character).
    // Inputs: lastName = "D", other valid parameters.
    // Expected Output: DeliveryPerson object created with lastName = "D".
    public void testLastNameMinLength() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "D", "DP123", "1234567890", "AREA01", "Out for delivery");
        assertEquals("D", dp.getLastName());
    }

    // Test #: 6
    // Test Objective: Boundary analysis for the maximum length of lastName (25 characters).
    // Inputs: lastName = "Alexandersonsonsonsonsonson", other valid parameters.
    // Expected Output: DeliveryPerson object created with lastName = "Alexandersonsonsonsonsonson".
    public void testLastNameMaxLength() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "Alexandersonsonsonson", "DP123", "1234567890", "AREA01", "Out for delivery");
        assertEquals("Alexandersonsonsonson", dp.getLastName());
    }

    // Test #: 7
    // Test Objective: Verify valid format of deliveryPersonId "DP000".
    // Inputs: deliveryPersonId = "DP001", other valid parameters.
    // Expected Output: DeliveryPerson object created with deliveryPersonId = "DP001".
    public void testValidDeliveryPersonId() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "Doe", "DP001", "1234567890", "AREA01", "Out for delivery");
        assertEquals("DP001", dp.getDeliveryPersonId());
    }

    // Test #: 8
    // Test Objective: Equivalence partitioning for invalid format of deliveryPersonId.
    // Inputs: deliveryPersonId = "DP1A3", other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testInvalidDeliveryPersonIdFormat() {
    	 try {
             new DeliveryPerson("John", "Doe", "DP1A3", "1234567890", "AREA01", "Out for delivery");
             fail("Exception expected");
         } catch (DeliveryPersonException e) {
             assertEquals("Delivery Person ID must be in the format DP000.", e.getMessage());
         }
    }

    // Test #: 9
    // Test Objective: Boundary analysis for valid 10-digit phone number.
    // Inputs: phoneNumber = "0987654321", other valid parameters.
    // Expected Output: DeliveryPerson object created with phoneNumber = "0987654321".
    public void testValidPhoneNumber() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "Doe", "DP123", "0987654321", "AREA01", "Out for delivery");
        assertEquals("0987654321", dp.getPhoneNumber());
    }

    // Test #: 10
    // Test Objective: Equivalence partitioning for a phone number less than 10 digits.
    // Inputs: phoneNumber = "12345678", other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testPhoneNumberTooShort() {
    	try {
             new DeliveryPerson("John", "Doe", "DP123", "12345678", "AREA01", "Out for delivery");
             fail("Exception expected");
         } catch (DeliveryPersonException e) {
             assertEquals("Phone number must be exactly 10 digits.", e.getMessage());
         }
    }

    // Test #: 11
    // Test Objective: Equivalence partitioning for a phone number more than 10 digits.
    // Inputs: phoneNumber = "12345678901", other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testPhoneNumberTooLong() {
    	try {
            new DeliveryPerson("John", "Doe", "DP123", "12345678901", "AREA01", "Out for delivery");
            fail("Exception expected");
        } catch (DeliveryPersonException e) {
            assertEquals("Phone number must be exactly 10 digits.", e.getMessage());
        }
    }

    // Test #: 12
    // Test Objective: Verify assignedArea with valid format "AREA00".
    // Inputs: assignedArea = "AREA05", other valid parameters.
    // Expected Output: DeliveryPerson object created with assignedArea = "AREA05".
    public void testValidAssignedArea() throws DeliveryPersonException {
        DeliveryPerson dp = new DeliveryPerson("John", "Doe", "DP123", "1234567890", "AREA05", "Out for delivery");
        assertEquals("AREA05", dp.getAssignedArea());
    }

    // Test #: 13
    // Test Objective: Equivalence partitioning for invalid format of assignedArea.
    // Inputs: assignedArea = "AR001", other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testInvalidAssignedAreaFormat() {
    	try {
            new DeliveryPerson("John", "Doe", "DP123", "1234567890", "AR001", "Out for delivery");
            fail("Exception expected");
        } catch (DeliveryPersonException e) {
            assertEquals("Assigned area must be specified in the format AREA00.", e.getMessage());
        }
    }

    // Test #: 14
    // Test Objective: Verify valid status options.
    // Inputs: status = "Out for delivery", "Returned", "Inactive"
    // Expected Output: DeliveryPerson objects created with each valid status.
    public void testValidStatusOptions() throws DeliveryPersonException {
        DeliveryPerson dp1 = new DeliveryPerson("John", "Doe", "DP123", "1234567890", "AREA01", "Out for delivery");
        assertEquals("Out for delivery", dp1.getStatus());

        DeliveryPerson dp2 = new DeliveryPerson("Jane", "Doe", "DP124", "0987654321", "AREA02", "Returned");
        assertEquals("Returned", dp2.getStatus());

        DeliveryPerson dp3 = new DeliveryPerson("Jake", "Smith", "DP125", "1112223334", "AREA03", "Inactive");
        assertEquals("Inactive", dp3.getStatus());
    }

    // Test #: 15
    // Test Objective: Equivalence partitioning for invalid status.
    // Inputs: status = "Delivering", other valid parameters.
    // Expected Output: DeliveryPersonException thrown.
    public void testInvalidStatus() {
        try {
            new DeliveryPerson("John", "Doe", "DP123", "1234567890", "AREA01", "Delivering");
            fail("Exception expected");
        } catch (DeliveryPersonException e) {
            assertEquals("Status must be one of the following: Out for delivery, Returned, Inactive.", e.getMessage());
        }
    }
}
