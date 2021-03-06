package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.EventBean;
import DAO.EventDao;
import DAO.impl.EventDaoImpl;
import Service.EventService;
import util.HibernateUtils;

public class EventServiceImpl implements EventService {
	SessionFactory factory;
	EventDao eventDao;

	public EventServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.eventDao = new EventDaoImpl();
	}

	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = eventDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public int save(EventBean event) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = eventDao.save(event);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public List<EventBean> getAllEvents() {
		List<EventBean> events = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			events = eventDao.getAllEvents();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return events;
	}

	@Override
	public EventBean getEvent(int pk) {
		EventBean event = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			event = eventDao.getEvent(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return event;
	}

	@Override
	public void deleteEvent(int pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			eventDao.deleteEvent(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateEvent(EventBean event) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			eventDao.updateEvent(event);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

}
