import java.util.Date;

public class Invoice {
    private String invoiceId;          // Unique identifier for the invoice
    private String custId;             // Unique identifier for the customer
    private PaymentMethod paymentMethod;       // Payment method (e.g., card, cash)
    private Date orderDate;             // Date of the order
    private double totalAmount;         // Total amount of the invoice
    private String deliveryId;          // Unique identifier for the delivery (optional)
    private String publicationId;       // Unique identifier for the publication (optional)
    private String orderStatus;         // Status of the order (optional)

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
    public Invoice(String invoiceId, String custId, String paymentMethod, Date orderDate, double totalAmount,
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
        this.orderDate = orderDate;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) throws CustomerExceptionHandler {
        validateOrderDate(orderDate);
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

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus; // Optional, no validation required
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

    public static void validateOrderDate(Date orderDate) throws CustomerExceptionHandler {
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
        // Optional validation: you can adjust this logic as per your requirements
        if (deliveryId != null && !deliveryId.matches("DP\\d{3}")) {
            throw new CustomerExceptionHandler("Delivery ID must follow the format DP000");
        }
    }

    public static void validatePublicationId(String publicationId) throws CustomerExceptionHandler {
        // Optional validation: you can adjust this logic as per your requirements
        if (publicationId != null && !publicationId.matches("PUB\\d{3}")) {
            throw new CustomerExceptionHandler("Publication ID must follow the format PUB001");
        }
    }

    public static void validateOrderStatus(String orderStatus) throws CustomerExceptionHandler {
        // Optional validation: you can adjust this logic as per your requirements
        if (orderStatus != null && (orderStatus.isBlank() || orderStatus.length() < 5 || orderStatus.length() > 60)) {
            throw new CustomerExceptionHandler("Order Status must be between 5 and 60 characters");
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
