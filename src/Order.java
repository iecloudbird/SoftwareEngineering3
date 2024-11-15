import java.time.LocalDate;
import java.util.*;

enum OrderStatus {
    PENDING,    
    CONFIRMED,    
    DISPATCHED,   
    DELIVERED, 
    CANCELLED,   
    POSTPONED
}

public class Order {
	private String orderId; //format : ORD0001
	private int custId;
	private String deliveryAreaId; //format AREA00
	private String publicationId;  // Format: PUB003
	private LocalDate orderDate;
	private OrderStatus orderStatus; 
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getDeliveryAreaId() {
		return deliveryAreaId;
	}
	public void setDeliveryAreaId(String deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
	}
	public String getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getOrderStatus() {
	    return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
	public Order() {
		this.orderId = null;
		this.custId = 0;
		this.deliveryAreaId = null ;
		this.publicationId = null;
		this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.PENDING;
	}
	
	public Order(String orderId, int custId, String deliveryId, String publicationId, Optional<LocalDate> orderDate, OrderStatus orderStatus) throws CustomerExceptionHandler  {
        try {
            validateOrderId(orderId);
            validateCustId(custId);
            validateDeliveryId(deliveryId);
            validatePublicationId(publicationId);
            validateOrderDate(orderDate);
            validateOrderStatus(orderStatus);
            
            // Set Attributes
            this.orderId = orderId;
            this.custId = custId;    
            this.deliveryAreaId = deliveryId;
            this.publicationId = publicationId;
            this.orderDate = orderDate.orElse(LocalDate.now());
            this.orderStatus = orderStatus;
        } catch (CustomerExceptionHandler e) {
            throw e;
        }
    }
	public static void validateOrderId(String orderId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Order Id"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (orderId.isBlank() || orderId.isEmpty())
			throw new CustomerExceptionHandler("Order Id NOT specified");
		else if (!orderId.matches("ORD\\d{4}")) {
            throw new CustomerExceptionHandler("Order Id format is invalid. Expected format: ORD0001");
        }
	}
	public static void validateCustId(int custId) throws CustomerExceptionHandler {
		if (custId == 0)
		    throw new CustomerExceptionHandler("Customer Id NOT specified");
		else if (custId > 100000)
		    throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
		else if (custId < 0) {
		    throw new CustomerExceptionHandler("Customer Id format is invalid. Expected format: positive integer");
		}
	}
	public static void validateDeliveryId(String areaId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Delivery Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (areaId == null) {
            throw new CustomerExceptionHandler("Area ID cannot be null.");
        }
        if (!areaId.matches("AREA\\d{2}")) {
            throw new CustomerExceptionHandler("Area ID must match the format: AREA00.");
        }
	}
	public static void validatePublicationId(String publicationId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Publication Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (publicationId.isBlank() || publicationId.isEmpty())
			throw new CustomerExceptionHandler("Publication Id NOT specified");
		else if (publicationId == null || !publicationId.matches("PUB\\d{3}")) {
            throw new CustomerExceptionHandler("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).");
        }
	}
	 public static void validateOrderDate(Optional<LocalDate> orderDate) throws CustomerExceptionHandler {
		 if (orderDate == null) {
		        throw new CustomerExceptionHandler("Order Date NOT specified.");
		    }

	 }
	 
	 
	 public static void validateOrderStatus(OrderStatus orderStatus) throws CustomerExceptionHandler {
	        if (orderStatus == null) {
	            throw new CustomerExceptionHandler("Order Status NOT specified");
	        }
	  }
	
}
