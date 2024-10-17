import java.util.*;

public class Order {
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
	public Boolean orderStatus() {
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
}
