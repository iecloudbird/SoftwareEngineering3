import java.sql.Date;

public class WarningLetter {
	
	private String letterId;
	private String orderId;
	private String custId;
	private String reason;
	private double dueAmount;
	private Date issueDate;
	
	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public WarningLetter() {
		this.letterId =null;
		this.orderId = null;
		this.custId = null;
		this.reason = null;
		this.dueAmount = 0;
		this.issueDate = null;
	}
	
	public WarningLetter(String letterId, String orderId, String custId, String reason, double dueAmount, Date issueDate) throws CustomerExceptionHandler  {
		
		//id = 0;
		
		// Validate Input
		try {
			
			validateLetterId(letterId);
			validateOrderId(orderId);
			validateCustId(custId);
			validateReason(reason);
			validateDueAmount(dueAmount);
			validateIssueDate(issueDate);
			
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		letterId = letterId;
		orderId = orderId;
		custId = custId;
		reason = reason;
		dueAmount = dueAmount;
		issueDate = issueDate;
		
	}
	
	public static void validateLetterId(String letterId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (letterId.isBlank() || letterId.isEmpty())
			throw new CustomerExceptionHandler("Warning Letter Id NOT specified");
		else if (letterId.length() < 2)
			throw new CustomerExceptionHandler("Warning Letter Id does not meet minimum length requirements");
		else if (letterId.length() > 50)
			throw new CustomerExceptionHandler("Warning Letter Id exceeds maximum length requirements");
		
	}
	
	public static void validateOrderId(String orderId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Address"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (orderId.isBlank() || orderId.isEmpty())
			throw new CustomerExceptionHandler("Order Id NOT specified");
		else if (orderId.length() < 5)
			throw new CustomerExceptionHandler("Order Id does not meet minimum length requirements");
		else if (orderId.length() > 60)
			throw new CustomerExceptionHandler("Order Id exceeds maximum length requirements");
		
	}
	
	public static void validateCustId(String custId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (custId.isBlank() || custId.isEmpty())
			throw new CustomerExceptionHandler("Customer Id NOT specified");
		else if (!custId.matches("\\d+")) {  // Ensure it contains only digits
	        throw new CustomerExceptionHandler("Customer Id does not meet numeric format requirements");
	    }
		else if (custId.length() < 7)
			throw new CustomerExceptionHandler("Customer Id does not meet minimum length requirements");
		else if (custId.length() > 15)
			throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
		
	}

	public static void validateReason(String reason) throws CustomerExceptionHandler {
			
			//Agree Formating Rules on "Customer Email"
			//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
			
		if (reason.isBlank() || reason.isEmpty()) {
	        throw new CustomerExceptionHandler("Customer reason NOT specified");
	    } else if (reason.length() < 5) {
	        throw new CustomerExceptionHandler("Customer reason does not meet minimum length requirements");
	    } else if (reason.length() > 50) {
	        throw new CustomerExceptionHandler("Customer reason exceeds maximum length requirements");
	    }
			
		}
	public static void validateDueAmount(String dueAmount) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Email"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
	if (dueAmount.isBlank() || dueAmount.isEmpty()) {
        throw new CustomerExceptionHandler("Customer dueAmount NOT specified");
    } else if (dueAmount.length() < 5) {
        throw new CustomerExceptionHandler("Customer dueAmount does not meet minimum length requirements");
    } else if (dueAmount.length() > 50) {
        throw new CustomerExceptionHandler("Customer dueAmount exceeds maximum length requirements");
    }
		
	}
	public static void validateIssueDate(String issueDate) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Email"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
	if (issueDate.isBlank() || issueDate.isEmpty()) {
        throw new CustomerExceptionHandler("Customer issueDate NOT specified");
    } else if (issueDate.length() < 5) {
        throw new CustomerExceptionHandler("Customer issueDate does not meet minimum length requirements");
    } else if (issueDate.length() > 50) {
        throw new CustomerExceptionHandler("Customer issueDate exceeds maximum length requirements");
    }
		
	}
	
}
