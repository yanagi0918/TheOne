package DAO;

import java.util.List;

import Bean.Order;

public interface OrderDao {
	
	boolean isDup(int pk);

	int save(Order order);

	List<Order> getAllOrders();

	Order getOrder(int pk);

	void deleteOrder(int pk);

	void updateOrder(Order order);

}


