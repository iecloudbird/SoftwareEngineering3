public class Storage {
	private String storageId;    
    private String publicationId;       
    private String description;       
    private int capacity;            
    private int currentStockLevel;    

    public Storage(String storageId, String publicationId, String description, int currentStockLevel) {
        this.storageId = storageId;
        this.publicationId = publicationId;
        this.description = description;
//        this.capacity = capacity;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentStockLevel() {
        return currentStockLevel;
    }

    public void setCurrentStockLevel(int currentStockLevel) {
        this.currentStockLevel = currentStockLevel;
    }

    // Method to add stock to the storage
//    public boolean addStock(int quantity) {
//        if (currentStockLevel + quantity <= capacity) {
//            currentStockLevel += quantity;
//            return true;
//        } else {
//            System.out.println("Cannot add stock: Exceeds capacity.");
//            return false;
//        }
//    }

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
