package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Order;
import DAO.OrderDao;
import DAO.impl.OrderDaoImpl;
import Service.OrderService;
import util.HibernateUtils;

public class OrderServiceImpl  implements OrderService{
	
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.getCurrentSession();
	OrderDao orderDao = new OrderDaoImpl();

	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = orderDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	@Override
	public int save(Order order) {
		int n = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = orderDao.save(order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return n;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orders = orderDao.getAllOrders();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return orders;
	}

	@Override
	public Order getOrder(int pk) {
		Order order = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			order = orderDao.getOrder(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return order;
	}

	@Override
	public void deleteOrder(int pk) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orderDao.deleteOrder(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}
	@Override
	public void updateOrder(Order order) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orderDao.updateOrder(order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
