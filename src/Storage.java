public class Storage {
	private String storageId;    
    private String publicationId;       
    private String description;       
    private int capacity;            
    private int currentStockLevel;    

    public Storage(String storageId, String publicationId, String description, int capacity, int currentStockLevel) throws StorageException {
        validateStorageId(storageId);
        validatePublicationId(publicationId);
        validateDescription(description);
        validateCapacity(capacity);
        validateCurrentStockLevel(currentStockLevel);

        this.storageId = storageId;
        this.publicationId = publicationId;
        this.description = description;
        this.capacity = capacity;
        this.currentStockLevel = currentStockLevel;
    }

    // Getters and Setters
    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws StorageException {
    	validateCapacity(capacity);
        this.capacity = capacity;
    }

    public int getCurrentStockLevel() {
        return currentStockLevel;
    }

    public void setCurrentStockLevel(int currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }
    
    public static void validateStorageId(String storageId) throws StorageException {
        if (storageId == null || !storageId.matches("ST\\d{3}")) {
            throw new StorageException("Storage ID must be in the format ST000 (Prefix 'ST' followed by three digits).");
        }
    }

    public static void validatePublicationId(String publicationId) throws StorageException {
        if (publicationId == null || !publicationId.matches("PUB\\d{3}")) {
            throw new StorageException("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).");
        }
    }

    public static void validateDescription(String description) throws StorageException {
        if (description == null || description.isEmpty()) {
            throw new StorageException("Description must not be null or empty.");
        }
        if (description.length() < 3) {
            throw new StorageException("Description must be at least 3 characters long.");
        }
    }

    public static void validateCapacity(int capacity) throws StorageException {
        if (capacity < 0) {
            throw new StorageException("Capacity must be non-negative.");
        }
    }

    public static void validateCurrentStockLevel(int currentStockLevel) throws StorageException {
        if (currentStockLevel < 0) {
            throw new StorageException("Current stock level cannot be negative.");
        }
    }
    
    public static class StorageException extends Exception {
        public StorageException(String message) {
            super(message);
        }
    }

    // Method to add stock to the storage
    public boolean addStock(int quantity) {
        if (currentStockLevel + quantity <= capacity) {
            currentStockLevel += quantity;
            return true;
        } else {
            return false;
        }
    }

    // Method to remove stock from the storage
    public boolean removeStock(int quantity) {
        if (currentStockLevel >= quantity) {
            currentStockLevel -= quantity;
            return true;
        } else {
            System.out.println("Insufficient stock to remove.");
            return false;
        }
    }

    // Display storage information
    public void displayStorageInfo() {
        System.out.println("Storage ID: " + storageId);
        System.out.println("Publication ID: " + publicationId);
        System.out.println("Description: " + description);
//        System.out.println("Capacity: " + capacity);
        System.out.println("Current Stock Level: " + currentStockLevel);
    }
}
