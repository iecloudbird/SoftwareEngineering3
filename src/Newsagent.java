
public class Newsagent {
	
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;

	void setId(int custId) {
		id = custId;
	}
	
	void setName(String custName) {
		name = custName;
	}
	
	void setAddress(String custAddr) {
		address = custAddr;
	}
	
	void setPhoneNumber(String custPhone) {
		phoneNumber = custPhone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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
	
	public Newsagent() {
		this.address =null;
		this.name = null;
		this.phoneNumber = null;
		this.email = null;
	}
	
	public Newsagent(String name, String address, String phoneNumber, String email) throws CustomerExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			// Validate input
	        validateName(name);
	        validateAddress(address);
	        validatePhoneNumber(phoneNumber);
	        validateEmail(email);

	        // Set attributes
	        this.name = name;
	        this.address = address;
	        this.phoneNumber = phoneNumber;
	        this.email = email;
			
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
	
	}
	
	public static void validateName(String name) throws CustomerExceptionHandler {
        if (name == null || name.isBlank()) {
            throw new CustomerExceptionHandler("Name is not specified.");
        } else if (name.length() < 2) {
            throw new CustomerExceptionHandler("Name must be at least 2 characters long.");
        } else if (name.length() > 50) {
            throw new CustomerExceptionHandler("Name must not exceed 50 characters.");
        }
    }
	
	public static void validateAddress(String address) throws CustomerExceptionHandler {
        if (address == null || address.isBlank()) {
            throw new CustomerExceptionHandler("Address is not specified.");
        } else if (address.length() < 5) {
            throw new CustomerExceptionHandler("Address must be at least 5 characters long.");
        } else if (address.length() > 60) {
            throw new CustomerExceptionHandler("Address must not exceed 60 characters.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) throws CustomerExceptionHandler {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new CustomerExceptionHandler("Phone number is not specified.");
        } else if (!phoneNumber.matches("\\d+")) {
            throw new CustomerExceptionHandler("Phone number must contain only digits.");
        } else if (phoneNumber.length() < 7) {
            throw new CustomerExceptionHandler("Phone number must be at least 7 digits long.");
        } else if (phoneNumber.length() > 15) {
            throw new CustomerExceptionHandler("Phone number must not exceed 15 digits.");
        }
    }

    public static void validateEmail(String email) throws CustomerExceptionHandler {
        if (email == null || email.isBlank()) {
            throw new CustomerExceptionHandler("Email is not specified.");
        } else if (email.length() < 5) {
            throw new CustomerExceptionHandler("Email must be at least 5 characters long.");
        } else if (email.length() > 50) {
            throw new CustomerExceptionHandler("Email must not exceed 50 characters.");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new CustomerExceptionHandler("Email format is invalid.");
        }
    }
}
