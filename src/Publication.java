public class Publication {
    private String publicationId; // Format: PUB003
    private String title;
    private int numberInStocks;
    private double price;
    private String type; // Options: "Newspaper", "Magazine"
    private String deliveryFrequency; // Frequency of delivery

    // Constructor
    public Publication(String publicationId, String title, int numberInStocks, double price,
                       String type, String deliveryFrequency) throws PublicationException {
        validatePublicationId(publicationId);
        validateTitle(title);
        validateNumberInStocks(numberInStocks);
        validatePrice(price);
        validateType(type);
        validateDeliveryFrequency(deliveryFrequency);

        this.publicationId = publicationId;
        this.title = title;
        this.numberInStocks = numberInStocks;
        this.price = price;
        this.type = type;
        this.deliveryFrequency = deliveryFrequency;
    }

    // Getters
    public String getPublicationId() { return publicationId; }
    public String getTitle() { return title; }
    public int getNumberInStocks() { return numberInStocks; }
    public double getPrice() { return price; }
    public String getType() { return type; }
    public String getDeliveryFrequency() { return deliveryFrequency; }

    // Validation Methods
    public static void validatePublicationId(String publicationId) throws PublicationException {
        if (publicationId == null || !publicationId.matches("PUB\\d{3}")) {
            throw new PublicationException("Publication ID must be in the format PUB000 (Prefix 'PUB' followed by three digits).");
        }
    }

    public static void validateTitle(String title) throws PublicationException {
        if (title == null || title.length() == 0 || title.length() > 100) {
            throw new PublicationException("Title must be between 1 and 100 characters.");
        }
    }

    public static void validateNumberInStocks(int numberInStocks) throws PublicationException {
        if (numberInStocks < 0) {
            throw new PublicationException("Number in stocks cannot be negative.");
        }
    }

    public static void validatePrice(double price) throws PublicationException {
        if (price < 0) {
            throw new PublicationException("Price cannot be negative.");
        }
    }

    public static void validateType(String type) throws PublicationException {
        if (!type.equals("Newspaper") && !type.equals("Magazine")) {
            throw new PublicationException("Type must be either 'Newspaper' or 'Magazine'.");
        }
    }

    public static void validateDeliveryFrequency(String deliveryFrequency) throws PublicationException {
    	if (deliveryFrequency == null || deliveryFrequency.length() == 0) {
            throw new PublicationException("Delivery frequency must be specified.");
        }
        if (deliveryFrequency.matches("\\d+")) {
            throw new PublicationException("Delivery frequency must be specified in a valid format.");
        }
        if (deliveryFrequency.length() < 3) {
            throw new PublicationException("Delivery frequency must be at least 3 characters.");
        }
    }
}

// Custom Exception Class
class PublicationException extends Exception {
    public PublicationException(String message) {
        super(message);
    }
}
