import java.util.*;

public class Order {
	private String orderId;
	private String custId;
	private String deliveryId;
	private String publicationId;
	private Date orderDate;
	private Boolean orderStatus;
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
	public Boolean getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Boolean orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Order() {
		this.orderId = null;
		this.custId = null;
		this.deliveryId = null ;
		this.publicationId = null;
		this.orderDate = null;
		this.orderStatus = true;
	}
	public Order(String orderId, String custId, String deliveryId, String publicationId, Date orderDate, Boolean orderStatus) throws CustomerExceptionHandler  {
		
		orderId = null;
		custId = null;
		deliveryId = null;
		publicationId = null;
		
		// Validate Input
		try {
			validateOrderId(orderId);
			validateCustId(custId);
			validateDeliveryId(deliveryId);
			validatePublicationId(publicationId);
			//validateOrderDate(orderDate);
			//validateOrderStatus(orderStatus);
		}finally{System.out.print("");}
//		catch (CustomerExceptionHandler e) {
//			throw e;
//		}
		
		// Set Attributes
		orderId = orderId;
		custId = custId;
		deliveryId = deliveryId;
		publicationId = publicationId;
		orderDate = orderDate;
		orderStatus = orderStatus;
	}
	public static void validateOrderId(String orderId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Order Id"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (orderId.isBlank() || orderId.isEmpty())
			throw new CustomerExceptionHandler("Order Id NOT specified");
		else if (orderId.length() < 2)
			throw new CustomerExceptionHandler("Order Id does not meet minimum length requirements");
		else if (orderId.length() > 50)
			throw new CustomerExceptionHandler("Order Id exceeds maximum length requirements");
		
	}
	public static void validateCustId(String custId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (custId.isBlank() || custId.isEmpty())
			throw new CustomerExceptionHandler("Customer Id NOT specified");
		else if (custId.length() < 5)
			throw new CustomerExceptionHandler("Customer Id does not meet minimum length requirements");
		else if (custId.length() > 60)
			throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
		
	}
	public static void validateDeliveryId(String deliveryId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Delivery Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (deliveryId.isBlank() || deliveryId.isEmpty())
			throw new CustomerExceptionHandler("Delivery Id NOT specified");
		else if (deliveryId.length() < 5)
			throw new CustomerExceptionHandler("Delivery Id does not meet minimum length requirements");
		else if (deliveryId.length() > 60)
			throw new CustomerExceptionHandler("Delivery Id exceeds maximum length requirements");
		
	}
	public static void validatePublicationId(String publicationId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Publication Id"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (publicationId.isBlank() || publicationId.isEmpty())
			throw new CustomerExceptionHandler("Publication Id NOT specified");
		else if (publicationId.length() < 5)
			throw new CustomerExceptionHandler("Publication Id does not meet minimum length requirements");
		else if (publicationId.length() > 60)
			throw new CustomerExceptionHandler("Publication Id exceeds maximum length requirements");
		
	}
//	public static void validateOrderDate(Date orderDate) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Order Date"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (orderDate == null)
//			throw new CustomerExceptionHandler("Order Date NOT specified");
//		else if (orderDate.length() < 5)
//			throw new CustomerExceptionHandler("Order Date does not meet minimum length requirements");
//		else if (orderDate.length() > 60)
//			throw new CustomerExceptionHandler("Order Date exceeds maximum length requirements");
//		
//	}
//	public static void validateOrderStatus(Boolean orderStatus) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Order Status"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (orderStatus.isBlank() || orderStatus.isEmpty())
//			throw new CustomerExceptionHandler("Publication Id NOT specified");
//		else if (orderStatus.length() < 5)
//			throw new CustomerExceptionHandler("Publication Id does not meet minimum length requirements");
//		else if (orderStatus.length() > 60)
//			throw new CustomerExceptionHandler("Publication Id exceeds maximum length requirements");
//		
//	}
}
