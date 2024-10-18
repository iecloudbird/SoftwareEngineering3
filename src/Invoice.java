import java.util.Date;
//To do
public class Invoice {
	private int invoiceId;
	private int custId;
	private String paymentMethod;
	private Date orderDate;
	private double totalAmount;
	
//	public Invoice(int invoiceId, int custId, String paymentMethod, Date orderDate, double totalAmount) {
//		super();
//		this.invoiceId = invoiceId;
//		this.custId = custId;
//		this.paymentMethod = paymentMethod;
//		this.orderDate = orderDate;
//		this.totalAmount = totalAmount;
//	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Invoice() {
		this.invoiceId =0;
		this.custId = 0;
		this.paymentMethod = "card";
		this.orderDate = null;
		this.totalAmount = 0.0;
	}
	public Invoice(int invoiceId, int custId, String paymentMethod, Date orderDate, double totalAmount) throws CustomerExceptionHandler  {
		
		invoiceId = 0;
		custId = 0;
		orderDate = null;
		totalAmount = 0.0;
		
		// Validate Input
		try {
			//validateOrderId(orderId);
			//validateCustId(custId);
			//validateDeliveryId(deliveryId);
			//validatePublicationId(publicationId);
			//validateOrderDate(orderDate);
			//validateOrderStatus(orderStatus);
			validatePaymentMethod(paymentMethod);
		}//finally{System.out.print("");}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		invoiceId = invoiceId;
		custId = custId;
		paymentMethod = paymentMethod;
		totalAmount = totalAmount;
		orderDate = orderDate;
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
	public static void validatePaymentMethod(String paymentMethod) throws CustomerExceptionHandler {
	
	//Agree Formating Rules on "Order Date"
	//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
	
	if (paymentMethod.isBlank() || paymentMethod.isEmpty())
		throw new CustomerExceptionHandler("Payment Method NOT specified");
	else if (paymentMethod.length() < 5)
		throw new CustomerExceptionHandler("Payment Method does not meet minimum length requirements");
	else if (paymentMethod.length() > 60)
		throw new CustomerExceptionHandler("Payment Method exceeds maximum length requirements");
	
}
}
