import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DeliveryDocket {

    private String docketId;
    private String orderId;
    private LocalDate deliveryDate;
    private String deliveryStatus; // "Delivered", "Out for delivery", "Not delivered"
    private String details;

    // Regular expression for valid docket ID
    private static final String DOCKET_ID_PATTERN = "D\\d{5}";

    // Valid delivery statuses
    private static final String[] VALID_STATUSES = {"Delivered", "Out for delivery", "Not delivered"};

    public DeliveryDocket() {
        this.docketId = null;
        this.orderId = null;
        this.deliveryDate = null;
        this.deliveryStatus = null;
        this.details = null;
    }

    public DeliveryDocket(String docketId, String orderId, String deliveryDate, String deliveryStatus, String details) throws DeliveryDocketException {
        try {
            validateDocketId(docketId);
            validateOrderId(orderId);
            validateDeliveryDate(deliveryDate);
            validateDeliveryStatus(deliveryStatus);
        } catch (DeliveryDocketException e) {
            throw e;
        }

        this.docketId = docketId;
        this.orderId = orderId;
        this.deliveryDate = LocalDate.parse(deliveryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.deliveryStatus = deliveryStatus;
        this.details = details;
    }

    public String getDocketId() {
        return docketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void updateDeliveryStatus(String deliveryStatus) throws DeliveryDocketException {
        validateDeliveryStatus(deliveryStatus);
        this.deliveryStatus = deliveryStatus;
    }

    // Validation methods
    public static void validateDocketId(String docketId) throws DeliveryDocketException {
        if (!isValidDocketId(docketId)) {
            throw new DeliveryDocketException("Invalid docket ID format");
        }
    }

    public static void validateOrderId(String orderId) throws DeliveryDocketException {
        if (orderId == null || orderId.isEmpty()) {
            throw new DeliveryDocketException("Order ID cannot be null or empty");
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
            throw new DeliveryDocketException("Invalid delivery status");
        }
    }

    public static boolean isValidDocketId(String docketId) {
        return Pattern.matches(DOCKET_ID_PATTERN, docketId);
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
