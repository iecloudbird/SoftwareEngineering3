import java.time.LocalDate;
import java.util.Optional;

public class Invoice {
    private String invoiceId;          // Unique identifier for the invoice
    private String custId;             // Unique identifier for the customer
    private PaymentMethod paymentMethod;       // Payment method (e.g., card, cash)
    private LocalDate orderDate;             // Date of the order
    private double totalAmount;         // Total amount of the invoice
    private String deliveryId;          // Delivery Docket (DD00000)
    private String publicationId;       //
    private String orderStatus;         // Accept: Pending,Paid,Cancelled,Refunded

    // Default constructor
    public Invoice() {
        this.invoiceId = null;
        this.custId = null;
        this.paymentMethod = PaymentMethod.CARD; 
        this.orderDate = null;
        this.totalAmount = 0.0;
        this.deliveryId = null;  
        this.publicationId = null; 
        this.orderStatus = "Pending"; 
    }

    // Parameterized constructor
    public Invoice(String invoiceId, String custId, String paymentMethod, Optional<LocalDate> orderDate, double totalAmount,
                   String deliveryId, String publicationId, String orderStatus) throws CustomerExceptionHandler {
        
        validateInvoiceId(invoiceId);
        validateCustId(custId);
        validatePaymentMethod(paymentMethod);
        validateOrderDate(orderDate);
        validateTotalAmount(totalAmount);
        validateDeliveryId(deliveryId); // Optional validation
        validatePublicationId(publicationId); // Optional validation
        validateOrderStatus(orderStatus); // Optional validation

        this.invoiceId = invoiceId;
        this.custId = custId;
        this.paymentMethod = PaymentMethod.fromString(paymentMethod);  
        this.orderDate = orderDate.orElse(LocalDate.now());
        this.totalAmount = totalAmount;
        this.deliveryId = deliveryId;
        this.publicationId = publicationId;
        this.orderStatus = orderStatus;
    }

    // Getter and Setter methods
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) throws CustomerExceptionHandler {
        validateInvoiceId(invoiceId);
        this.invoiceId = invoiceId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) throws CustomerExceptionHandler {
        validateCustId(custId);
        this.custId = custId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) throws CustomerExceptionHandler {
        this.paymentMethod = PaymentMethod.fromString(paymentMethod); // Use enum validation
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) throws CustomerExceptionHandler {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) throws CustomerExceptionHandler {
        validateTotalAmount(totalAmount);
        this.totalAmount = totalAmount;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId; // Optional, no validation required
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId; // Optional, no validation required
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) throws CustomerExceptionHandler {
    	validateOrderStatus(orderStatus);
        this.orderStatus = orderStatus; 
    }

    // Validation methods
    public static void validateInvoiceId(String invoiceId) throws CustomerExceptionHandler {
        if (invoiceId == null || invoiceId.isBlank()) {
            throw new CustomerExceptionHandler("Invoice ID NOT specified");
        } else if (!invoiceId.matches("INV\\d{4}")) {
            throw new CustomerExceptionHandler("Invoice ID must follow the format INV0001");
        }
    }

    public static void validateCustId(String custId) throws CustomerExceptionHandler {
        if (custId == null || custId.isBlank()) {
            throw new CustomerExceptionHandler("Customer ID NOT specified");
        } else if (!custId.matches("\\d+")) {
            throw new CustomerExceptionHandler("Customer ID must be a number");
        }
    }

    public static void validatePaymentMethod(String paymentMethod) throws CustomerExceptionHandler {
    	if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new CustomerExceptionHandler("Payment Method NOT specified");
        }
       
        PaymentMethod.fromString(paymentMethod);
    }

    public static void validateOrderDate(Optional<LocalDate> orderDate) throws CustomerExceptionHandler {
        if (orderDate == null) {
            throw new CustomerExceptionHandler("Order Date NOT specified");
        }
    }

    public static void validateTotalAmount(double totalAmount) throws CustomerExceptionHandler {
        if (totalAmount < 0) {
            throw new CustomerExceptionHandler("Total Amount cannot be negative");
        }
    }

    public static void validateDeliveryId(String deliveryId) throws CustomerExceptionHandler {
        // Optional validation: Adjusted logic to match Delivery Docket ID format
        if (deliveryId != null && !deliveryId.matches("DD\\d{5}")) {
            throw new CustomerExceptionHandler("Delivery ID must follow the format DD00000");
        }
    }

    public static void validatePublicationId(String publicationId) throws CustomerExceptionHandler {
        // Optional validation: you can adjust this logic as per your requirements
        if (publicationId != null && !publicationId.matches("PUB\\d{3}")) {
            throw new CustomerExceptionHandler("Publication ID must follow the format PUB001");
        }
    }

    public static void validateOrderStatus(String orderStatus) throws CustomerExceptionHandler {
    	 if (orderStatus == null || orderStatus.isBlank()) {
    	       throw new CustomerExceptionHandler("Order Status NOT specified");
    	    }
    	    
    	 if (orderStatus.length() < 4 || orderStatus.length() > 60) {
    	      throw new CustomerExceptionHandler("Order Status must be between 5 and 60 characters");
    	   }
    	    
    	 if (!orderStatus.equalsIgnoreCase("Pending") &&
    	      !orderStatus.equalsIgnoreCase("Paid") &&
    	      !orderStatus.equalsIgnoreCase("Cancelled") &&
    	      !orderStatus.equalsIgnoreCase("Refunded")) {
    	      throw new CustomerExceptionHandler("Invalid Order Status. Accepted values are: Pending, Paid, Cancelled, Refunded");
    	   }
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", custId='" + custId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", deliveryId='" + deliveryId + '\'' +
                ", publicationId='" + publicationId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}

// Custom exception class for handling validation errors
class CustomerExceptionHandler extends Exception {
    public CustomerExceptionHandler(String message) {
        super(message);
    }
}

 enum PaymentMethod {
    CARD("card"),
    CASH("cash");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public static PaymentMethod fromString(String method) throws CustomerExceptionHandler {
        for (PaymentMethod pm : PaymentMethod.values()) {
            if (pm.getMethod().equalsIgnoreCase(method)) {
                return pm;
            }
        }
        throw new CustomerExceptionHandler("Invalid payment method. Accepted values: card, cash");
    }
}
