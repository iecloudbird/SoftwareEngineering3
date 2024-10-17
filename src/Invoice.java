import java.util.Date;
//To do
public class Invoice {
	private int orderId;
	private int custId;
	private int deliveryId;
	private int publicationId;
	private Date orderDate;
	private Boolean orderStatus;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(int publicationId) {
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
		this.orderId =0;
		this.custId = 0;
		this.deliveryId = 0;
		this.publicationId = 0;
		this.orderDate = null;
		this.orderStatus = true;
	}
	public Order(int orderId, int custId, int deliveryId, int publicationId, Date orderDate, Boolean orderStatus) throws CustomerExceptionHandler  {
		
		orderId = 0;
		custId = 0;
		deliveryId = 0;
		publicationId = 0;
		
		// Validate Input
		try {
			//validateOrderId(orderId);
			//validateCustId(custId);
			//validateDeliveryId(deliveryId);
			//validatePublicationId(publicationId);
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
//	public static void validateOrderId(int orderId) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Order Id"
//		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
//		
//		if (orderId.isBlank() || orderId.isEmpty())
//			throw new CustomerExceptionHandler("Order Id NOT specified");
//		else if (orderId.length() < 2)
//			throw new CustomerExceptionHandler("Order Id does not meet minimum length requirements");
//		else if (orderId.length() > 50)
//			throw new CustomerExceptionHandler("Order Id exceeds maximum length requirements");
//		
//	}
//	public static void validateCustId(int custId) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Customer Id"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (custId.isBlank() || custId.isEmpty())
//			throw new CustomerExceptionHandler("Customer Id NOT specified");
//		else if (custId.length() < 5)
//			throw new CustomerExceptionHandler("Customer Id does not meet minimum length requirements");
//		else if (custId.length() > 60)
//			throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
//		
//	}
//	public static void validateDeliveryId(int deliveryId) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Delivery Id"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (deliveryId.isBlank() || deliveryId.isEmpty())
//			throw new CustomerExceptionHandler("Delivery Id NOT specified");
//		else if (deliveryId.length() < 5)
//			throw new CustomerExceptionHandler("Delivery Id does not meet minimum length requirements");
//		else if (deliveryId.length() > 60)
//			throw new CustomerExceptionHandler("Delivery Id exceeds maximum length requirements");
//		
//	}
//	public static void validatePublicationId(int publicationId) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Publication Id"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (publicationId.isBlank() || publicationId.isEmpty())
//			throw new CustomerExceptionHandler("Publication Id NOT specified");
//		else if (publicationId.length() < 5)
//			throw new CustomerExceptionHandler("Publication Id does not meet minimum length requirements");
//		else if (publicationId.length() > 60)
//			throw new CustomerExceptionHandler("Publication Id exceeds maximum length requirements");
//		
//	}
//	public static void validateOrderDate(Date orderDate) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Order Date"
//		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
//		
//		if (orderDate.isBlank() || orderDate.isEmpty())
//			throw new CustomerExceptionHandler("Order Date NOT specified");
//		else if (orderDate.length() < 5)
//			throw new CustomerExceptionHandler(Order Date does not meet minimum length requirements");
//		else if (orderDate.length() > 60)
//			throw new CustomerExceptionHandler("Order Date exceeds maximum length requirements");
//		
//	}
//	public static void validateOrderStatus(Boolean orderStatus) throws CustomerExceptionHandler {
//		
//		//Agree Formating Rules on "Order Date"
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
