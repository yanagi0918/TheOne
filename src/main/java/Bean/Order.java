package Bean;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
	private int orderId;
	private String userId;
	private String productId;
	private int totalPrice;
	private Date orderDate;
	private String state;

	
	public Order() {	}
	
	public Order(int orderId, String userId, String productId, int totalPrice, Date orderDate, String state) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.state = state;
	}


	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}
