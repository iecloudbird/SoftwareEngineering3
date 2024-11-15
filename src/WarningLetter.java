import java.time.LocalDate;
import java.util.Optional;
public class WarningLetter {
	
	private String letterId; //format : wL001
	private String orderId; //format : ORD0001 
	private int custId;
	private String reason;
	private double dueAmount;
	private LocalDate issueDate; //Date the letter is issued
	
	// Getter and Setter methods
	
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

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
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

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public WarningLetter() {
		
		this.letterId =null;
		this.orderId = null;
		this.custId = 0;
		this.reason = null;
		this.dueAmount = 0;
		this.issueDate = LocalDate.now(); 
	}
	
    // Parameterized Constructor with Validation
	public WarningLetter(String letterId, String orderId, int custId, String reason, double dueAmount,Optional<LocalDate> issueDate) throws CustomerExceptionHandler  {
		
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
	        this.issueDate = issueDate.orElse(LocalDate.now());
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
	}
	
	public WarningLetter(String letterId, String reason, double dueAmount, Optional<LocalDate> issueDate) throws CustomerExceptionHandler {
		try {
			validateLetterId(letterId);
			validateReason(reason);
			validateDueAmount(dueAmount);
			validateIssueDate(issueDate);
			
			 // Set Attributes
			this.letterId = letterId;
	        this.reason = reason;
	        this.dueAmount = dueAmount;
	        this.issueDate = issueDate.orElse(LocalDate.now());
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
	}
	 // Validation methods
	public static void validateLetterId(String letterId) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Letter ID"
		//E.G. Name String must be of format WL001 or WL//d{3}
		
		if (letterId.isBlank() || letterId.isEmpty())
			throw new CustomerExceptionHandler("Warning Letter Id NOT specified");
		else if (letterId.length() < 5) // Check minimum length
	        throw new CustomerExceptionHandler("Warning Letter Id does not meet minimum length requirements");
		else if (!letterId.matches("WL\\d{3}")) {
            throw new CustomerExceptionHandler("Letter Id format is invalid. Expected format: WL001");
        }
	}
	
	public static void validateOrderId(String orderId) throws CustomerExceptionHandler {
			
			//Agree Formating Rules on "Order Id"
			//E.G. Name String must be a minimum of 2 characters and a maximum of 8 characters
			
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
	
	public static void validateCustId(int custId) throws CustomerExceptionHandler {
		if (custId == 0)
		    throw new CustomerExceptionHandler("Customer Id NOT specified");
		else if (custId > 100000)
		    throw new CustomerExceptionHandler("Customer Id exceeds maximum length requirements");
		else if (custId < 0) {
		    throw new CustomerExceptionHandler("Customer Id format is invalid. Expected format: positive integer");
		}
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
	public static void validateIssueDate(Optional<LocalDate> issueDate2) throws CustomerExceptionHandler {
		 if (issueDate2 == null) {
		        throw new CustomerExceptionHandler("Issue date is not specified.");
		    }
		
	}
	
}
