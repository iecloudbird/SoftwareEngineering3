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
	private String custId;
	private String deliveryId; //deliveryPersonID format : DP/000
	private String publicationId;
	private Date orderDate;
	private OrderStatus orderStatus;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
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
		this.custId = null;
		this.deliveryId = null ;
		this.publicationId = null;
		this.orderDate = new Date();
        this.orderStatus = OrderStatus.PENDING;
	}
	
	public Order(String orderId, String custId, String deliveryId, String publicationId, Date orderDate, OrderStatus orderStatus) throws CustomerExceptionHandler  {
		
		// Validate Input
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
	        this.deliveryId = deliveryId;
	        this.publicationId = publicationId;
	        this.orderDate = orderDate != null ? orderDate : new Date();
	        this.orderStatus =  orderStatus;
		}catch (CustomerExceptionHandler e) {
			throw e;
		}
		
	}
	public static void validateOrderId(String orderId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Order Id"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (orderId.isBlank() || orderId.isEmpty())
			throw new CustomerExceptionHandler("Order Id NOT specified");
		else if (orderId.length() < 2)
			throw new CustomerExceptionHandler("Order Id does not meet minimum length requirements");
		else if (orderId.length() > 8)
			throw new CustomerExceptionHandler("Order Id exceeds maximum length requirements");
		else if (!orderId.matches("ORD\\d{4}")) {
            throw new CustomerExceptionHandler("Order Id format is invalid. Expected format: ORD0001");
        }
	}
	public static void validateCustId(String custId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (custId.isBlank() || custId.isEmpty())
			throw new CustomerExceptionHandler("Customer Id NOT specified");
		else if (custId.length() < 5)
			throw new CustomerExceptionHandler("Customer Id does not meet minimum length requirements");
		else if (custId.length() > 10)
			throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
		else if (!custId.matches("\\d+")) {
            throw new CustomerExceptionHandler("Customer Id format is invalid. Expected format: integer");
        }
	}
	public static void validateDeliveryId(String deliveryId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Delivery Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (deliveryId.isBlank() || deliveryId.isEmpty())
			throw new CustomerExceptionHandler("Delivery Id NOT specified");
		else if (deliveryId.length() < 5)
			throw new CustomerExceptionHandler("Delivery Id does not meet minimum length requirements");
		else if (deliveryId.length() > 8)
			throw new CustomerExceptionHandler("Delivery Id exceeds maximum length requirements");
		else if (!deliveryId.matches("DP\\d{3}")) {
            throw new CustomerExceptionHandler("Delivery Id format is invalid. Expected format: DP000");
        }
	}
	public static void validatePublicationId(String publicationId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Publication Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (publicationId.isBlank() || publicationId.isEmpty())
			throw new CustomerExceptionHandler("Publication Id NOT specified");
		else if (publicationId.length() < 5)
			throw new CustomerExceptionHandler("Publication Id does not meet minimum length requirements");
		else if (publicationId.length() > 7)
			throw new CustomerExceptionHandler("Publication Id exceeds maximum length requirements");
		else if (!publicationId.matches("PUB\\d{4}")) {
	         throw new CustomerExceptionHandler("Publication Id format is invalid. Expected format: PUB0001");
	       }
	}
	 public static void validateOrderDate(Date orderDate) throws CustomerExceptionHandler {
	        if (orderDate == null) {
	            throw new CustomerExceptionHandler("Order Date NOT specified");
	        }
	        
	        Date currentDate = new Date();
	        if (orderDate.before(currentDate)) {
	            throw new CustomerExceptionHandler("Order Date cannot be in the past");
	        }
	    }

	 public static void validateOrderStatus(OrderStatus orderStatus) throws CustomerExceptionHandler {
		    if (orderStatus == null) {
		        throw new CustomerExceptionHandler("Order Status NOT specified");
		    }
		}
}
