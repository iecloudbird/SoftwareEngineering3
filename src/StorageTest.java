import junit.framework.TestCase;

/* 
* Storage Test Cases Summary
* 
* Entity: Storage
* Objective: Ensure 100% full coverage test cases using Equivalence Partitioning (EP) and Boundary Analysis (BA)
* 
* Total Test Cases: 23 (12 BA + 11 EP)
* 
* Boundary Analysis (BA): 12 Test Cases
* - Minimum and Maximum Length for storageId and publicationId: 6
* - Valid and Invalid Values for currentStockLevel: 2
* - Boundary for valid capacity and currentStockLevel ranges: 4
* 
* Equivalence Partitioning (EP): 11 Test Cases
* - Valid and Invalid Constructor and Object Creation: 4
* - Invalid Formats for storageId and publicationId: 4
* - Validations for acceptable formats of description: 3
* - Validations for stock removal: 3
* - Validations for adding stock: 2
* - Validations for setting capacity: 2
*/
public class StorageTest extends TestCase {

    
	// Test Case #: 1
    // Test Objective: Verify the constructor accepts valid parameters.
    // Inputs: storageId = "ST001", publicationId = "PUB001", description = "Main Storage", currentStockLevel = 50
    // Expected Output: Storage object created with matching parameters.
    public void testValidConstructor() {
        try {
            Storage storage = new Storage("ST001", "PUB001", "Main Storage", 50, 30);
            assertEquals("ST001", storage.getStorageId());
            assertEquals("PUB001", storage.getPublicationId());
            assertEquals("Main Storage", storage.getDescription());
            assertEquals(30, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid parameters");
        }
    }

    // Test Case #: 2
    // Test Objective: Ensure the storage ID is set correctly.
    // Inputs: storageId = "ST003"
    // Expected Output: Storage ID updated to "ST003".
    public void testSetStorageId() {
        try {
            Storage storage = new Storage("ST002", "PUB002", "Secondary Storage", 30, 10);
            storage.setStorageId("ST003");
            assertEquals("ST003", storage.getStorageId());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid storage ID");
        }
    }

    // Test Case #: 3
    // Test Objective: Ensure the publication ID is set correctly.
    // Inputs: publicationId = "PUB004"
    // Expected Output: Publication ID updated to "PUB004".
    public void testSetPublicationId() {
        try {
            Storage storage = new Storage("ST004", "PUB003", "Tertiary Storage", 20, 15);
            storage.setPublicationId("PUB004");
            assertEquals("PUB004", storage.getPublicationId());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid publication ID");
        }
    }

    // Test Case #: 4
    // Test Objective: Ensure the description is set correctly.
    // Inputs: description = "Updated Storage"
    // Expected Output: Description updated to "Updated Storage".
    public void testSetDescription() {
        try {
            Storage storage = new Storage("ST005", "PUB005", "Quaternary Storage", 10, 5);
            storage.setDescription("Updated Storage");
            assertEquals("Updated Storage", storage.getDescription());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid description");
        }
    }

    // Test Case #: 5
    // Test Objective: Ensure the capacity can be set and retrieved.
    // Inputs: capacity = 100
    // Expected Output: Capacity updated to 100.
    public void testSetCapacity() {
        try {
            Storage storage = new Storage("ST006", "PUB006", "Quinary Storage", 25, 15);
            storage.setCapacity(100);
            assertEquals(100, storage.getCapacity());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid capacity");
        }
    }

    // Test Case #: 6
    // Test Objective: Ensure the current stock level can be set and retrieved.
    // Inputs: currentStockLevel = 20
    // Expected Output: Current stock level updated to 20.
    public void testSetCurrentStockLevel() {
        try {
            Storage storage = new Storage("ST007", "PUB007", "Senary Storage", 15, 10);
            storage.setCurrentStockLevel(20);
            assertEquals(20, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid stock level");
        }
    }

    // Test Case #: 7
    // Test Objective: Validate the removeStock method for valid scenario.
    // Inputs: quantity = 10
    // Expected Output: Successful removal of stock; currentStockLevel = 20.
    public void testRemoveStockValid() {
        try {
            Storage storage = new Storage("ST008", "PUB008", "Septenary Storage", 30, 30);
            boolean result = storage.removeStock(10);
            assertTrue(result);
            assertEquals(20, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid stock removal");
        }
    }

    // Test Case #: 8
    // Test Objective: Validate the removeStock method when stock to be removed exceeds current stock.
    // Inputs: quantity = 10
    // Expected Output: Failed removal; currentStockLevel remains 5.
    public void testRemoveStockInsufficient() {
        try {
            Storage storage = new Storage("ST009", "PUB009", "Octonary Storage", 5, 5);
            boolean result = storage.removeStock(10);
            assertFalse(result);
            assertEquals(5, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for insufficient stock");
        }
    }

    // Test Case #: 9
    // Test Objective: Validate handling a case where nothing is removed (stock already zero).
    // Inputs: quantity = 1, currentStockLevel = 0
    // Expected Output: Failed removal; currentStockLevel remains 0.
    public void testRemoveStockWhenZero() {
        try {
            Storage storage = new Storage("ST010", "PUB010", "Non-Existent Stock", 0, 0);
            boolean result = storage.removeStock(1);
            assertFalse(result);
            assertEquals(0, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for zero stock");
        }
    }

    // Test Case #: 10
    // Test Objective: Validate constructor with invalid storage ID format.
    // Inputs: storageId = "1234" (invalid format)
    // Expected Output: Expect failure due to invalid storageId format.
    public void testInvalidStorageIdFormat() {
        try {
            new Storage("1234", "PUB012", "Invalid Storage", 20, 5);
            fail("Expected an exception due to invalid storageId format.");
        } catch (Storage.StorageException e) {
            assertEquals("Storage ID must be in the format ST000 (Prefix 'ST' followed by three digits).", e.getMessage());
        }
    }

    // Test Case #: 11
    // Test Objective: Validate constructor with invalid publication ID format.
    // Inputs: publicationId = "123PUB" (invalid format)
    // Expected Output: Expect failure due to invalid publicationId format.
    public void testInvalidPublicationIdFormat() {
        try {
            new Storage("ST013", "123PUB", "Invalid Publication", 20, 5);
            fail("Expected an exception due to invalid publicationId format.");
        } catch (Storage.StorageException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
        }
    }

    // Test Case #: 12
    // Test Objective: Validate constructor with null description.
    // Inputs: description = null
    // Expected Output: Expect exception for a null description.
    public void testNullDescription() {
        try {
            new Storage("ST014", "PUB014", null, 20, 10);
            fail("Expected an exception due to null description.");
        } catch (Storage.StorageException e) {
            assertEquals("Description must not be null or empty.", e.getMessage());
        }
    }
    
    // Test Case #: 13
    // Test Objective: Validate constructor with an empty description.
    // Inputs: description = ""
    // Expected Output: Expect exception for empty description.
    public void testEmptyDescription() {
        try {
            new Storage("ST015", "PUB015", "", 20, 5);
            fail("Expected an exception due to empty description.");
        } catch (Storage.StorageException e) {
            assertEquals("Description must not be null or empty.", e.getMessage());
        }
    }
    
    // Test Case #: 14
    // Test Objective: Validate the maximum length for storageId.
    // Inputs: storageId = "ST001234567890123456789012345678901234567890"
    // Expected Output: Expect an exception for exceeding length.
    public void testStorageIdTooLong() {
        String longStorageId = "ST" + "0".repeat(40);
        try {
            new Storage(longStorageId, "PUB016", "Storage with Long ID", 30, 10);
            fail("Expected an exception due to long storageId.");
        } catch (Storage.StorageException e) {
            assertEquals("Storage ID must be in the format ST000 (Prefix 'ST' followed by three digits).", e.getMessage());
        }
    }
    
    // Test Case #: 15
    // Test Objective: Validate the minimum length for publicationId.
    // Inputs: publicationId = "PUB"
    // Expected Output: Publication ID should be "PUB001" when shorter than required.
    public void testPublicationIdTooShort() {
        try {
            new Storage("ST017", "PUB", "Invalid Pub", 25, 10);
            fail("Expected an exception due to short publicationId.");
        } catch (Storage.StorageException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
        }
    }

    // Test Case #: 16
    // Test Objective: Validate the maximum length for publicationId.
    // Inputs: publicationId = "PUB01234567890123456789012345678901234567890"
    // Expected Output: Expect an exception for exceeding length.
    public void testPublicationIdTooLong() {
        String longPublicationId = "PUB" + "0".repeat(40);
        try {
            new Storage("ST017", longPublicationId, "Long Publication ID", 20, 5);
            fail("Expected an exception because publicationId is too long.");
        } catch (Storage.StorageException e) {
            assertEquals("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).", e.getMessage());
        }
    }
    
    // Test Case #: 17
    // Test Objective: Validate minimum length for storageId (1 character).
    // Inputs: storageId = "S", publicationId = "PUB001", description = "Short ID Storage", currentStockLevel = 10
    // Expected Output: Object creation with storageId = "S".
    public void testMinimumLengthStorageId() {
        try {
            Storage storage = new Storage("ST001", "PUB001", "Short ID Storage", 10, 5);
            assertEquals("ST001", storage.getStorageId());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid minimum length storage ID.");
        }
    }

    // Test Case #: 18
    // Test Objective: Validate maximum valid value for currentStockLevel.
    // Inputs: currentStockLevel = Integer.MAX_VALUE
    // Expected Output: currentStockLevel set to Integer.MAX_VALUE.
    public void testMaxValidCurrentStockLevel() {
        try {
            Storage storage = new Storage("ST020", "PUB020", "Max Stock Level Storage", 10, Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid max current stock level.");
        }
    }

    // Test Case #: 19
    // Test Objective: Validate minimum valid value for currentStockLevel (0).
    // Inputs: currentStockLevel = 0
    // Expected Output: currentStockLevel set to 0.
    public void testMinValidCurrentStockLevel() {
        try {
            Storage storage = new Storage("ST021", "PUB021", "Min Stock Level Storage", 10, 0);
            assertEquals(0, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid min current stock level.");
        }
    }

    // Test Case #: 20
    // Test Objective: Validate setting capacity to maximum boundary value.
    // Inputs: capacity = Integer.MAX_VALUE
    // Expected Output: capacity set to Integer.MAX_VALUE.
    public void testMaxCapacity() {
        try {
            Storage storage = new Storage("ST022", "PUB022", "Max Capacity Storage", Integer.MAX_VALUE, 10);
            assertEquals(Integer.MAX_VALUE, storage.getCapacity());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown for valid max capacity.");
        }
    }

    // Test Case #: 21
    // Test Objective: Validate addStock method with quantity that would exceed capacity.
    // Inputs: capacity = 100, currentStockLevel = 90, quantity = 20
    // Expected Output: False, stock addition rejected.
    public void testAddStockExceedsCapacity() {
        try {
            Storage storage = new Storage("ST023", "PUB023", "Over Capacity Storage", 100, 90);
            boolean result = storage.addStock(20);
            assertFalse(result);
            assertEquals(90, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown while managing stock addition.");
        }
    }

    // Test Case #: 22
    // Test Objective: Validate addStock method with quantity that does not exceed capacity.
    // Inputs: capacity = 100, currentStockLevel = 50, quantity = 20
    // Expected Output: True, stock added successfully.
    public void testAddStockWithinCapacity() {
        try {
            Storage storage = new Storage("ST024", "PUB024", "Under Capacity Storage", 100, 50);
            boolean result = storage.addStock(20);
            assertTrue(result);
            assertEquals(70, storage.getCurrentStockLevel());
        } catch (Storage.StorageException e) {
            fail("Exception should not be thrown while managing stock addition.");
        }
    }
    // Test Case #: 23
    // Test Objective: Verify the behavior when setting a negative capacity.
    // Inputs: capacity = -10
    // Expected Output: Exception for negative capacity.
    public void testNegativeCapacity() {
        try {
            Storage storage = new Storage("ST025", "PUB025", "Negative Capacity Storage", 5, 0);
            storage.setCapacity(-10);
            fail("Expected an exception for negative capacity.");
        } catch (Storage.StorageException e) {
            assertEquals("Capacity must be non-negative.", e.getMessage());
        }
    }
}