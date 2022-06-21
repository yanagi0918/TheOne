package tw.team5.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.team5.bean.Order;
import tw.team5.dao.OrderDao;
import tw.team5.util.HibernateUtils;

public class OrderDaoImpl implements OrderDao {

	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.getCurrentSession();
	
	@Override
	public boolean isDup(int pk) {
		Order order = (Order) session.get(Order.class, pk);
		if (order != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Order order) {
		return (int) session.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = null;
		String hql = "FROM Order";
		orders = session.createQuery(hql, Order.class).getResultList();
		return orders;
	}

	@Override
	public Order getOrder(int pk) {
		Order order = null;
		order = session.get(Order.class, pk);
		return order;
	}

	@Override
	public void deleteOrder(int pk) {
		Order order = session.get(Order.class, pk);
		session.delete(order);
	}

	@Override
	public void updateOrder(Order order) {
		session.saveOrUpdate(order);
	}

}
