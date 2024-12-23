
public class DeliveryPerson {
	private String firstName;
    private String lastName;
    private String deliveryPersonId; // Format: DP003
    private String phoneNumber; // 10 digits
    private String assignedArea;
    private String status; // Options: "Out for delivery", "Returned", "Inactive"

    // Constructor
    public DeliveryPerson(String firstName, String lastName, String deliveryPersonId,
                          String phoneNumber, String assignedArea, String status) throws DeliveryPersonException {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateDeliveryPersonId(deliveryPersonId);
        validatePhoneNumber(phoneNumber);
        validateAssignedArea(assignedArea);
        validateStatus(status);

        this.firstName = firstName;
        this.lastName = lastName;
        this.deliveryPersonId = deliveryPersonId;
        this.phoneNumber = phoneNumber;
        this.assignedArea = assignedArea;
        this.status = status;
    }

    // Getters
    public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
    public String getDeliveryPersonId() { return deliveryPersonId; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAssignedArea() { return assignedArea; }
    public String getStatus() { return status; }

    public void setFirstName(String firstName) {
  		this.firstName = firstName;
  	}

  	public void setLastName(String lastName) {
  		this.lastName = lastName;
  	}

  	public void setDeliveryPersonId(String deliveryPersonId) {
  		this.deliveryPersonId = deliveryPersonId;
  	}

  	public void setPhoneNumber(String phoneNumber) {
  		this.phoneNumber = phoneNumber;
  	}

  	public void setAssignedArea(String assignedArea) {
  		this.assignedArea = assignedArea;
  	}

  	public void setStatus(String status) {
  		this.status = status;
  	}

  	
    // Validation Methods
    public static void validateFirstName(String firstName) throws DeliveryPersonException {
        if (firstName == null || firstName.length() == 0 || firstName.length() > 15) {
            throw new DeliveryPersonException("First name must be between 1 and 15 characters.");
        }
    }

    public static void validateLastName(String lastName) throws DeliveryPersonException {
        if (lastName == null || lastName.length() == 0 || lastName.length() > 25) {
            throw new DeliveryPersonException("Last name must be between 1 and 25 characters.");
        }
    }

    public static void validateDeliveryPersonId(String deliveryPersonId) throws DeliveryPersonException {
        if (deliveryPersonId == null || !deliveryPersonId.matches("DP\\d{3}")) {
            throw new DeliveryPersonException("Delivery Person ID must be in the format DP000.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) throws DeliveryPersonException {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new DeliveryPersonException("Phone number must be exactly 10 digits.");
        }
    }

    public static void validateAssignedArea(String assignedArea) throws DeliveryPersonException {
    	 if (assignedArea == null || !assignedArea.matches("AREA\\d{2}")) {
    	        throw new DeliveryPersonException("Assigned area must be specified in the format AREA00.");
    	    }
    }

    public static void validateStatus(String status) throws DeliveryPersonException {
        if (!status.equals("Out for delivery") && !status.equals("Returned") && !status.equals("Inactive")) {
            throw new DeliveryPersonException("Status must be one of the following: Out for delivery, Returned, Inactive.");
        }
    }
}
