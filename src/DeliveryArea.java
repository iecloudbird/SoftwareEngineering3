public class DeliveryArea {

    private String areaId;         // format: AREA00
    private String areaName;    
    private String deliveryPersonId; // format: DP000
    private int totalCustomers;      

    public DeliveryArea() {
        this.areaId = null;
        this.areaName = null;
        this.deliveryPersonId = null;
        this.totalCustomers = 0;
    }

    public DeliveryArea(String areaId, String areaName, String deliveryPersonId, int totalCustomers) throws DeliveryAreaException {
        setAreaId(areaId);
        setAreaName(areaName);
        setDeliveryPersonId(deliveryPersonId);
        setTotalCustomers(totalCustomers);
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) throws DeliveryAreaException {
        validateAreaId(areaId);
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) throws DeliveryAreaException {
        validateAreaName(areaName);
        this.areaName = areaName;
    }

    public String getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(String deliveryPersonId) throws DeliveryAreaException {
        validateDeliveryPersonId(deliveryPersonId);
        this.deliveryPersonId = deliveryPersonId;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) throws DeliveryAreaException { // Add throws clause
        validateTotalCustomers(totalCustomers); // Validate before setting
        this.totalCustomers = totalCustomers;
    }

    private void validateAreaId(String areaId) throws DeliveryAreaException {
        if (areaId == null) {
            throw new DeliveryAreaException("Area ID cannot be null.");
        }
        if (!areaId.matches("AREA\\d{2}")) {
            throw new DeliveryAreaException("Area ID must match the format: AREA00.");
        }
    }
    
    private void validateAreaName(String areaName) throws DeliveryAreaException {
        if (areaName == null) {
            throw new DeliveryAreaException("Area name cannot be null.");
        }
        if (areaName.trim().isEmpty()) {
            throw new DeliveryAreaException("Area name cannot be empty.");
        }
        if (areaName.length() > 100) {
            throw new DeliveryAreaException("Area name cannot exceed 100 characters.");
        }
    }

    private void validateDeliveryPersonId(String deliveryPersonId) throws DeliveryAreaException {
        if (deliveryPersonId == null) {
            throw new DeliveryAreaException("Delivery Person ID cannot be null.");
        }
        if (!deliveryPersonId.matches("DP\\d{3}")) {
            throw new DeliveryAreaException("Delivery Person ID must match the format: DP000.");
        }
    }

    private void validateTotalCustomers(int totalCustomers) throws DeliveryAreaException {
        if (totalCustomers < 0) {
            throw new DeliveryAreaException("Total customers cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "DeliveryArea{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", deliveryPersonId='" + deliveryPersonId + '\'' +
                ", totalCustomers=" + totalCustomers +
                '}';
    }
}

class DeliveryAreaException extends Exception {
    public DeliveryAreaException(String message) {
        super(message);
    }
}
