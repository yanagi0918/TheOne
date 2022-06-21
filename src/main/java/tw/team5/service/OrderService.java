package tw.team5.service;

import java.util.List;

import tw.team5.bean.Order;

public interface OrderService {
	boolean isDup(int pk);

	int save(Order order);

	List<Order> getAllOrders();

	Order getOrder(int pk);

	void deleteOrder(int pk);

	void updateOrder(Order order);

}
