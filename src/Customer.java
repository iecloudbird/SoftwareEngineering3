
public class Customer {
	
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Boolean subscriptionStatus;


	public Boolean getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(Boolean subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	void setId(int custId) {
		id = custId;
	}
	
	void setName(String custName) {
		name = custName;
	}
	
	void setAddress(String custAddr) {
		address = custAddr;
	}
	
	void setPhoneNumber(String custPhone) throws CustomerExceptionHandler {
		validatePhoneNumber(custPhone);
		phoneNumber = custPhone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws CustomerExceptionHandler {
		validateEmail(email);  
		this.email = email;
	}

	int getId() {
		return id;
	}
	
	String getName() {
		return name;
	}
	
	String getAddress() {
		return address;
	}
	
	String getPhoneNumber() {
		return phoneNumber;
		
	}
	
	public Customer() {
		this.address =null;
		this.name = null;
		this.phoneNumber = null;
		this.email = null;
		this.subscriptionStatus = null;
	}
	
	public Customer(String custName, String custAddr, String custPhone, String custEmail, Boolean custSubscriptionStatus) throws CustomerExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			validateName(custName);
			validateAddress(custAddr);
			validatePhoneNumber(custPhone);
			validateEmail(custEmail);
			
			// Set attributes if valid
			name = custName;
			address = custAddr;
			phoneNumber = custPhone;
			email = custEmail;
			subscriptionStatus = custSubscriptionStatus;
			
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
	}
	
	public static void validateName(String custName) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (custName.isBlank() || custName.isEmpty())
			throw new CustomerExceptionHandler("Customer Name NOT specified");
		else if (custName.length() < 2)
			throw new CustomerExceptionHandler("Customer Name does not meet minimum length requirements");
		else if (custName.length() > 50)
			throw new CustomerExceptionHandler("Customer Name exceeds maximum length requirements");
		
	}
	
	public static void validateAddress(String custAddr) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Address"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (custAddr.isBlank() || custAddr.isEmpty())
			throw new CustomerExceptionHandler("Customer Address NOT specified");
		else if (custAddr.length() < 5)
			throw new CustomerExceptionHandler("Customer Address does not meet minimum length requirements");
		else if (custAddr.length() > 60)
			throw new CustomerExceptionHandler("Customer Address exceeds maximum length requirements");
		
	}
	
	public static void validatePhoneNumber(String custPhone) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (custPhone.isBlank() || custPhone.isEmpty())
			throw new CustomerExceptionHandler("Customer PhoneNumber NOT specified");
		else if (!custPhone.matches("\\d+")) {  // Ensure it contains only digits
	        throw new CustomerExceptionHandler("Customer PhoneNumber does not meet numeric format requirements");
	    }
		else if (custPhone.length() < 7)
			throw new CustomerExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");
		else if (custPhone.length() > 15)
			throw new CustomerExceptionHandler("Customer PhoneNumber exceeds maximum length requirements");
		
	}

	public static void validateEmail(String email) throws CustomerExceptionHandler {
			
			//Agree Formating Rules on "Customer Email"
			//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
			
		if (email.isBlank() || email.isEmpty()) {
	        throw new CustomerExceptionHandler("Customer Email NOT specified");
	    }
		else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			throw new CustomerExceptionHandler("Invalid email format");
		}
		else if (email.length() < 5) {
	        throw new CustomerExceptionHandler("Customer Email does not meet minimum length requirements");
	    } else if (email.length() > 50) {
	        throw new CustomerExceptionHandler("Customer Email exceeds maximum length requirements");
	    }
			
		}
}
