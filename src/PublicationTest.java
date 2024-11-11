import junit.framework.TestCase;

/* 
 * Publication Test Cases Summary
 * 
 * Entity: Publication
 * Objective: Ensure 100% full coverage test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
 * 
 * Total Test Cases: 20 (10 BA + 10 EP)
 * 
 * Boundary Analysis (BA): 10 Test Cases
 * - Minimum and Maximum Length for title: 4
 * - Valid Price Range (e.g., zero and positive): 2
 * - Valid and Invalid Values for numberInStocks: 2
 * - Valid Formats for publicationId and type: 4
 * 
 * Equivalence Partitioning (EP): 10 Test Cases
 * - Valid and Invalid Constructor and object creation: 3
 * - Invalid Formats for title, publicationId, type, and deliveryFrequency: 6
 * - Validations for acceptable formats of publicationId and type: 3
 * - Validations for deliveryFrequency specification: 2
 */

public class PublicationTest extends TestCase {

    // Test Case #: 1
    // Test Objective: Verify the constructor accepts valid parameters.
    // Inputs: publicationId = "PUB001", title = "Tech News", numberInStocks = 50, price = 5.99,
    //         type = "Newspaper", deliveryFrequency = "Daily"
    // Expected Output: Publication object created with matching parameters.
    public void testValidConstructor() throws PublicationException {
        Publication pub = new Publication("PUB001", "Tech News", 50, 5.99, "Newspaper", "Daily");

        assertEquals("PUB001", pub.getPublicationId());
        assertEquals("Tech News", pub.getTitle());
        assertEquals(50, pub.getNumberInStocks());
        assertEquals(5.99, pub.getPrice());
        assertEquals("Newspaper", pub.getType());
        assertEquals("Daily", pub.getDeliveryFrequency());
    }

    // Boundary Analysis (BA): Testing minimum and maximum boundaries for `title`.
    // Test Case #: 2
    // Test Objective: Validate minimum title length (1 character).
    // Inputs: title = "A"
    // Expected Output: Publication object created successfully.
    public void testMinimumTitleLength() throws PublicationException {
        Publication pub = new Publication("PUB002", "A", 10, 2.50, "Magazine", "Weekly");
        assertEquals("A", pub.getTitle());
    }

    // Test Case #: 3
    // Test Objective: Validate maximum title length (100 characters).
    // Inputs: title = 100 characters long string
    // Expected Output: Publication object created successfully.
    public void testMaximumTitleLength() throws PublicationException {
        String longTitle = "A".repeat(100);
        Publication pub = new Publication("PUB003", longTitle, 10, 2.50, "Magazine", "Weekly");
        assertEquals(longTitle, pub.getTitle());
    }

    // Equivalence Partitioning (EP): Invalid formats for `publicationId`.
    // Test Case #: 4
    // Test Objective: Validate that invalid publicationId format throws an exception.
    // Inputs: publicationId = "123PUB"
    // Expected Output: PublicationException thrown.
    public void testInvalidPublicationIdFormat() {
        try {
            new Publication("123PUB", "Tech News", 50, 5.99, "Newspaper", "Daily");
            fail("Expected PublicationException for invalid publicationId format.");
        } catch (PublicationException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
        }
    }

    // Boundary Analysis (BA): Validating `numberInStocks`.
    // Test Case #: 5
    // Test Objective: Validate `numberInStocks` accepts zero as a valid input.
    // Inputs: numberInStocks = 0
    // Expected Output: Publication object created successfully.
    public void testZeroNumberInStocks() throws PublicationException {
        Publication pub = new Publication("PUB004", "Science Daily", 0, 3.99, "Newspaper", "Daily");
        assertEquals(0, pub.getNumberInStocks());
    }

    // Test Case #: 6
    // Test Objective: Validate negative `numberInStocks` throws an exception.
    // Inputs: numberInStocks = -1
    // Expected Output: PublicationException thrown.
    public void testNegativeNumberInStocks() {
        try {
            new Publication("PUB005", "Science Daily", -1, 3.99, "Newspaper", "Daily");
            fail("Expected PublicationException for negative numberInStocks.");
        } catch (PublicationException e) {
            assertEquals("Number in stocks cannot be negative.", e.getMessage());
        }
    }

    // Boundary Analysis (BA): Validating `price`.
    // Test Case #: 7
    // Test Objective: Validate `price` accepts zero as a valid input.
    // Inputs: price = 0.0
    // Expected Output: Publication object created successfully.
    public void testZeroPrice() throws PublicationException {
        Publication pub = new Publication("PUB006", "Free Times", 5, 0.0, "Magazine", "Monthly");
        assertEquals(0.0, pub.getPrice());
    }

    // Test Case #: 8
    // Test Objective: Validate negative `price` throws an exception.
    // Inputs: price = -5.00
    // Expected Output: PublicationException thrown.
    public void testNegativePrice() {
        try {
            new Publication("PUB007", "Free Times", 5, -5.00, "Magazine", "Monthly");
            fail("Expected PublicationException for negative price.");
        } catch (PublicationException e) {
            assertEquals("Price cannot be negative.", e.getMessage());
        }
    }

    // Equivalence Partitioning (EP): Validating `type`.
    // Test Case #: 9
    // Test Objective: Validate `type` accepts "Newspaper" and "Magazine" only.
    // Inputs: type = "Journal"
    // Expected Output: PublicationException thrown.
    public void testInvalidType() {
        try {
            new Publication("PUB008", "Tech Daily", 10, 1.50, "Journal", "Weekly");
            fail("Expected PublicationException for invalid type.");
        } catch (PublicationException e) {
            assertEquals("Type must be either 'Newspaper' or 'Magazine'.", e.getMessage());
        }
    }

    // Boundary Analysis (BA): Validating `deliveryFrequency`.
    // Test Case #: 10
    // Test Objective: Validate `deliveryFrequency` must be non-empty.
    // Inputs: deliveryFrequency = ""
    // Expected Output: PublicationException thrown.
    public void testEmptyDeliveryFrequency() {
        try {
            new Publication("PUB009", "Weekly Roundup", 20, 4.99, "Newspaper", "");
            fail("Expected PublicationException for empty deliveryFrequency.");
        } catch (PublicationException e) {
            assertEquals("Delivery frequency must be specified.", e.getMessage());
        }
    }

    // Test Case #: 11
    // Test Objective: Validate `deliveryFrequency` accepts a valid frequency string.
    // Inputs: deliveryFrequency = "Weekly"
    // Expected Output: Publication object created successfully.
    public void testValidDeliveryFrequency() throws PublicationException {
        Publication pub = new Publication("PUB010", "Monthly Digest", 15, 3.50, "Magazine", "Weekly");
        assertEquals("Weekly", pub.getDeliveryFrequency());
    }

    // Additional EP: Test Constructor with Null Values
    // Test Case #: 12
    // Test Objective: Ensure constructor throws exception for null `publicationId`.
    // Inputs: publicationId = null
    // Expected Output: PublicationException thrown.
    public void testNullPublicationId() {
        try {
            new Publication(null, "Null ID Test", 10, 1.99, "Magazine", "Daily");
            fail("Expected PublicationException for null publicationId.");
        } catch (PublicationException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
        }
    }

    // Additional EP: Test Constructor with Null Title
    // Test Case #: 13
    // Test Objective: Ensure constructor throws exception for null `title`.
    // Inputs: title = null
    // Expected Output: PublicationException thrown.
    public void testNullTitle() {
        try {
            new Publication("PUB011", null, 10, 1.99, "Magazine", "Daily");
            fail("Expected PublicationException for null title.");
        } catch (PublicationException e) {
            assertEquals("Title must be between 1 and 100 characters.", e.getMessage());
        }
    }
    
    //BA
    public void testTitleLengthTooShort() {
        try {
            new Publication("PUB012", "", 10, 1.99, "Magazine", "Daily");
            fail("Expected PublicationException for title length less than minimum.");
        } catch (PublicationException e) {
            assertEquals("Title must be between 1 and 100 characters.", e.getMessage());
        }
    }
    
    //BA
    public void testTitleLengthTooLong() {
        String longTitle = "A".repeat(101);
        try {
            new Publication("PUB013", longTitle, 10, 1.99, "Magazine", "Daily");
            fail("Expected PublicationException for title length greater than maximum.");
        } catch (PublicationException e) {
            assertEquals("Title must be between 1 and 100 characters.", e.getMessage());
        }
    }
    public void testInvalidDeliveryFrequencyFormat() {
        try {
            new Publication("PUB014", "Daily News", 10, 1.99, "Newspaper", "1234");
            fail("Expected PublicationException for invalid deliveryFrequency format.");
        } catch (PublicationException e) {
            assertEquals("Delivery frequency must be specified in a valid format.", e.getMessage());
        }
    }
    
    public void testInvalidTypeLowerCase() {
        try {
            new Publication("PUB015", "Tech News", 10, 1.99, "newspaper", "Daily");
            fail("Expected PublicationException for invalid type format.");
        } catch (PublicationException e) {
            assertEquals("Type must be either 'Newspaper' or 'Magazine'.", e.getMessage());
        }
    }
    
    public void testAllNullValues() {
        try {
            new Publication(null, null, -1, -1.00, null, null);
            fail("Expected PublicationException for all null values.");
        } catch (PublicationException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
            // Also check for other messages based on the order of checks in Publication constructor
        }
    }
}
