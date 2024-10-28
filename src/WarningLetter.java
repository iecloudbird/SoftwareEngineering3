import java.util.Date;

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
		this.issueDate = new Date(); 
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
			
			 // Set Attributes
			this.letterId = letterId;
	        this.orderId = orderId;
	        this.custId = custId;
	        this.reason = reason;
	        this.dueAmount = dueAmount;
	        this.issueDate = (issueDate == null) ? new Date() : issueDate;
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
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
		
		if (custId == null || custId.isBlank())
            throw new CustomerExceptionHandler("Customer Id NOT specified");
        if (!custId.matches("\\d+"))	
            throw new CustomerExceptionHandler("Customer Id does not meet numeric format requirements");
        if (custId.length() < 7)
            throw new CustomerExceptionHandler("Customer Id does not meet minimum length requirements");
        if (custId.length() > 15)
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
	public static void validateDueAmount(double dueAmount) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Email"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (Double.isNaN(dueAmount)) {
	        throw new CustomerExceptionHandler("Due amount is not specified or is invalid.");
	    } else if (dueAmount < 0) {
	        throw new CustomerExceptionHandler("Due amount cannot be negative.");
	    } else if (dueAmount > 1000) {
	        throw new CustomerExceptionHandler("Due amount exceeds the allowed maximum of 1000.");
	    }
		
	}
	public static void validateIssueDate(Date issueDate) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Email"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		 if (issueDate == null) {
		        throw new CustomerExceptionHandler("Issue date is not specified.");
		    }
		
	}
	
}
