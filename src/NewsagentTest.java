import junit.framework.TestCase;


/*
 * Newsagent Test Cases Summary
 * 
 * Entity: Newsagent
 * Objective: Ensure 100% full coverage test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
 * 
* Total Test Cases: 22
 * 
 * Boundary Analysis (BA): 12 Test Cases
 * - Valid and Invalid Length for name and address: 4
 * - Edge Cases for phoneNumber (minimum and maximum lengths): 5
 * - Edge Cases for email length: 3
 * 
 * Equivalence Partitioning (EP): 10 Test Cases
 * - Valid and Invalid Constructor and object creation: 2
 * - Invalid Formats for name, address, phone number, and email: 6
 * - Validations for valid formats of phone number: 2
 */

public class NewsagentTest extends TestCase {
	
	//Test #: 1
	//Test Objective: Successful Newsagent Creation
	//Inputs: custName = "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123", email = "jack@example.com"
	// Expected Output: Customer Object created with id = 0, "Jack Daniels", "Athlone", "087-123123123", "jack@example.com"	//Expected Output: Customer Object created with id = 0, "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
	 public void testCreateNewsagentSuccess() {
	        try {
	            // Arrange & Act
	            Newsagent newsObj = new Newsagent("Jack Daniels", "Athlone", "0871231233", "jack@example.com");

	            // Assert
	            assertEquals(0, newsObj.getId());
	            assertEquals("Jack Daniels", newsObj.getName());
	            assertEquals("Athlone", newsObj.getAddress());
	            assertEquals("0871231233", newsObj.getPhoneNumber());
	            assertEquals("jack@example.com", newsObj.getEmail());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }
	
	// Test #: 2
    // Test Objective: To catch an invalid name length
    // Inputs: custName = "J"
    // Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"
    
	 public void testInvalidNewsagentNameLength() {
	        try {
	            Newsagent.validateName("J");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Name must be at least 2 characters long.", e.getMessage());
	        }
	    }
    
    // Test #: 3
    // Test Objective: To catch an Invalid Address Length
    // Inputs: custAddr = "A"
    // Expected Output: Exception Message: "Customer Address does not meet minimum length requirements"
    
	 public void testInvalidNewsagentAddressLength() {
	        try {
	            Newsagent.validateAddress("A");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Address must be at least 5 characters long.", e.getMessage());
	        }
	    }
    // Test #: 4
    // Test Objective: To successfully Valid Address
    // Inputs: custAddr = "Athlone"
    // Expected Output: No exception, address is valid
    
	 public void testValidNewsagentAddress() {
	        try {
	            Newsagent newsObj = new Newsagent("John Doe", "Athlone", "0874555757", "john@example.com");
	            assertEquals("Athlone", newsObj.getAddress());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }
    
    // Test #: 5
    // Test Objective: To catch an invalid phone number length
    // Inputs: custPhone = "8"
    // Expected Output: Exception Message: "Customer PhoneNumber does not meet minimum length requirements"
    
	 public void testInvalidNewsagentPhoneNumberLength() {
	        try {
	            Newsagent.validatePhoneNumber("8");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Phone number must be at least 7 digits long.", e.getMessage());
	        }
	    }

    // Test #: 6
    // Test Objective: To successfully validate Phone Number
    // Inputs: custPhone = "0874555757"
    // Expected Output: No exception, phone number is valid
	 public void testValidNewsagentPhoneNumber() {
	        try {
	            Newsagent newsObj = new Newsagent("John Doe", "Athlone", "0874555757", "john@example.com");
	            assertEquals("0874555757", newsObj.getPhoneNumber());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }

    // Test #: 7
    // Test Objective: To catch an invalid customer email
    // Inputs: email = "a@p"
    // Expected Output: Exception Message: "Customer Email does not meet minimum length requirements"
    
	public void testInvalidNewsagentEmailFormat() {
	        try {
	            Newsagent.validateEmail("a@p");
	            fail("Exception expected");
	        } catch (CustomerExceptionHandler e) {
	            assertEquals("Email must be at least 5 characters long.", e.getMessage());
	        }
	    }

    // Test #: 8
    // Test Objective: To successfully validate a customer email
    // Inputs: email = "jack@example.com"
    // Expected Output: No exception, email is valid
    
	 public void testValidNewsagentEmail() {
	        try {
	            Newsagent newsObj = new Newsagent("Jack Daniels", "Athlone", "0874555757", "jack@example.com");
	            assertEquals("jack@example.com", newsObj.getEmail());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }
    
	 // Test #: 9
	 // Test Objective: To validate the format of customer ID (e.g., #C00000)
	 public void testValidateNewsagentIDFormat() {
	        try {
	            Newsagent newsObj = new Newsagent("Jack Daniels", "Athlone", "0871234567", "jack@example.com");
	            assertEquals(0, newsObj.getId());
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception was not expected.");
	        }
	    }
	 
	// Test #: 10
	// Test Objective: Validate name of exactly 1 character
	 public void testNewsagentNameMinBoundary() {
	        try {
	            Newsagent.validateName("AB");
	            assertTrue(true);
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }
	
	// Test #: 11
	// Test Objective: Validate name of exactly 15 characters (boundary case)
	 public void testValidateNameMaxBoundary() {
	        try {
	            String validName = "Alexanderson123456789"; // Example max length
	            Newsagent.validateName(validName);
	            assertTrue(true);
	        } catch (CustomerExceptionHandler e) {
	            fail("Exception not expected");
	        }
	    }
	
	// Test #: 12
	// Test Objective: Validate address of exactly 5 character (boundary case)
	public void testValidateAddressMinBoundary() {
	    try {
	    	Newsagent.validateAddress("1glen");
	        assertTrue(true);  
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}

	// Test #: 13
	// Test Objective: Validate address of exactly 100 characters (assuming max length is 100)
	public void testValidateAddressMaxBoundary() {
	    String longAddress = "A".repeat(60); //total 60 characters
	    try {
	    	Newsagent.validateAddress(longAddress);
	        assertTrue(true); 
	    } catch (CustomerExceptionHandler e) {
	        fail("Exception not expected");
	    }
	}
	
	// Test #: 14
	// Test Objective: Catch invalid customer phone number containing special characters (dashes).
    // Input: custPhone = "087-1234567"
    // Expected Output: Exception with message "Customer PhoneNumber does not meet numeric format requirements".
	public void testValidatePhoneNumberWithDashes() {
        try {
            Newsagent.validatePhoneNumber("087-1234567");
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Phone number must contain only digits.", e.getMessage());
        }
    }
	
	// Test #: 15
    // Test Objective: Catch excessive name length
    // Inputs: name = "A" repeated 51 times
    // Expected Output: Exception Message: "Name must not exceed 50 characters."
    public void testInvalidNewsagentNameMaxLength() {
        try {
            Newsagent.validateName("A".repeat(51)); // 51 characters.
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Name must not exceed 50 characters.", e.getMessage());
        }
    }

    // Test #: 16
    // Test Objective: Validate name exactly 50 characters (max boundary)
    // Inputs: name = "A" repeated 50 times
    // Expected Output: No exception expected, name is valid
    public void testValidNewsagentNameMaximumBoundary() {
        try {
            Newsagent.validateName("A".repeat(50)); // Exactly 50 characters
            assertTrue(true);
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 17
    // Test Objective: Catch excessive phone number length
    // Inputs: phone number = "1234567890123456" (16 characters)
    // Expected Output: Exception Message: "Phone number must not exceed 15 digits."
    public void testInvalidNewsagentPhoneNumberMaxLength() {
        try {
            Newsagent.validatePhoneNumber("1234567890123456"); // 16 characters
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Phone number must not exceed 15 digits.", e.getMessage());
        }
    }

    // Test #: 18
    // Test Objective: Validate maximum valid phone number length (15 characters)
    // Inputs: phone number = "123456789012345" (15 characters)
    // Expected Output: No exception expected, phone number is valid
    public void testValidNewsagentPhoneNumberMaxBoundary() {
        try {
            Newsagent.validatePhoneNumber("123456789012345"); // Exactly 15 characters
            assertTrue(true);
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 19
    // Test Objective: Catch excessive address length
    // Inputs: address = "A" repeated 61 times 
    // Expected Output: Exception Message: "Address must not exceed 60 characters."
    public void testInvalidNewsagentAddressMaxLength() {
        try {
            Newsagent.validateAddress("A".repeat(61)); // 61 characters
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Address must not exceed 60 characters.", e.getMessage());
        }
    }

    // Test #: 20
    // Test Objective: Validate maximum valid address length (60 characters)
    // Inputs: address = "A" repeated 60 times
    // Expected Output: No exception expected, address is valid
    public void testValidNewsagentAddressMaxBoundary() {
        try {
            Newsagent.validateAddress("A".repeat(60)); // Exactly 60 characters
            assertTrue(true);
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 21
    // Test Objective: Validate email of exactly 50 characters before domain
    // Inputs: email = "A" repeated 39 times + "@domain.com" (total 50 char)
    // Expected Output: No exception expected, email is valid
    public void testValidEmailMaximumBoundary() {
        try {
            Newsagent.validateEmail("A".repeat(39) + "@domain.com"); // Length 39 before @
            assertTrue(true);
        } catch (CustomerExceptionHandler e) {
            fail("Exception not expected");
        }
    }

    // Test #: 22
    // Test Objective: Validate excessive email length
    // Inputs: email = "A" repeated 40 times before domain
    // Expected Output: Exception Message: "Email must not exceed 50 characters."
    public void testInvalidEmailTooLong() {
        try {
            Newsagent.validateEmail("A".repeat(40) + "@domain.com"); // TOTAL 51 EXCEEDS UPPER LIMIT
            fail("Exception expected");
        } catch (CustomerExceptionHandler e) {
            assertEquals("Email must not exceed 50 characters.", e.getMessage());
        }
    }

}

		

