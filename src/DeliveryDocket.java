import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DeliveryDocket {

    private String docketId;  // Format: DD00000
    private String orderId;		// Format: ORD001
    private String deliveryPersonId;  // Format: DP000
    private LocalDate deliveryDate; // Format: dd/MM/yyyy
    private String deliveryStatus; // "Delivered", "Out for delivery", "Not delivered"
    private String details;

    // Regular expression for valid docket ID
    private static final String DOCKET_ID_PATTERN = "DD\\d{5}"; // Updated pattern to match the specified format
    private static final String ORDER_ID_PATTERN = "ORD\\d{3}";
    private static final String DELIVERY_PERSON_ID_PATTERN = "DP\\d{3}"; 
    // Valid delivery statuses
    private static final String[] VALID_STATUSES = {"Delivered", "Out for delivery", "Not delivered"};

    public DeliveryDocket() {
        this.docketId = null;
        this.orderId = null;
        this.deliveryPersonId = null;
        this.deliveryDate = null;
        this.deliveryStatus = null;
        this.details = null;
    }

    public DeliveryDocket(String docketId, String orderId,String deliveryPersonId, String deliveryDate, String deliveryStatus, String details) throws DeliveryDocketException {
        try {
            validateDocketId(docketId);
            validateOrderId(orderId);
            validateDeliveryDate(deliveryDate);
            validateDeliveryStatus(deliveryStatus);
            validateDeliveryPersonId(deliveryPersonId); 
            
            this.docketId = docketId;
            this.orderId = orderId;
            this.deliveryPersonId = deliveryPersonId; 
            this.deliveryDate = LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.deliveryStatus = deliveryStatus;
            this.details = details;
        } catch (DeliveryDocketException e) {
            throw e;
        }
    }

    public String getDocketId() {
        return docketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDeliveryPersonId() {
        return deliveryPersonId; 
    }
    
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDocketId(String docketId) {
		this.docketId = docketId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setDeliveryPersonId(String deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void updateDeliveryStatus(String deliveryStatus) throws DeliveryDocketException {
        validateDeliveryStatus(deliveryStatus);
        this.deliveryStatus = deliveryStatus;
    }

    // Validation methods
    public static void validateDocketId(String docketId) throws DeliveryDocketException {
        if (!isValidDocketId(docketId)) {
            throw new DeliveryDocketException("Invalid docket ID format. Expected format: DD00000");
        }
    }

    public static void validateOrderId(String orderId) throws DeliveryDocketException {
        if (!isValidOrderId(orderId)) {
            throw new DeliveryDocketException("Invalid Order ID format. Expected format: ORD000");
        }
    }
    
    public static void validateDeliveryPersonId(String deliveryPersonId) throws DeliveryDocketException {
        if (!isValidDeliveryPersonId(deliveryPersonId)) {
            throw new DeliveryDocketException("Invalid Delivery Person ID format. Expected format: DP000");
        }
    }

    public static void validateDeliveryDate(String deliveryDate) throws DeliveryDocketException {
        try {
            LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            throw new DeliveryDocketException("Invalid delivery date format. Expected format: dd/MM/yyyy");
        }
    }

    public static void validateDeliveryStatus(String deliveryStatus) throws DeliveryDocketException {
        if (!isValidDeliveryStatus(deliveryStatus)) {
            throw new DeliveryDocketException("Invalid delivery status. Valid options: Delivered, Out for delivery, Not delivered");
        }
    }

    public static boolean isValidDocketId(String docketId) {
        return Pattern.matches(DOCKET_ID_PATTERN, docketId);
    }

    public static boolean isValidOrderId(String orderId) {
        return Pattern.matches(ORDER_ID_PATTERN, orderId);
    }

    public static boolean isValidDeliveryPersonId(String deliveryPersonId) {
        return Pattern.matches(DELIVERY_PERSON_ID_PATTERN, deliveryPersonId);
    }
    
    public static boolean isValidDeliveryStatus(String status) {
        for (String validStatus : VALID_STATUSES) {
            if (validStatus.equals(status)) {
                return true;
            }
        }
        return false;
    }

}

class DeliveryDocketException extends Exception {
    public DeliveryDocketException(String message) {
        super(message);
    }
}
