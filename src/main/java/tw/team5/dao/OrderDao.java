package tw.team5.dao;

import java.util.List;

import tw.team5.bean.Order;

public interface OrderDao {
	
	boolean isDup(int pk);

	int save(Order order);

	List<Order> getAllOrders();

	Order getOrder(int pk);

	void deleteOrder(int pk);

	void updateOrder(Order order);

}


