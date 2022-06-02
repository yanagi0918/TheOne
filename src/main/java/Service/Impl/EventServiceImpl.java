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
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.getCurrentSession();
	EventDao eventDao = new EventDaoImpl();

	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = eventDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	@Override
	public int save(EventBean event) {
		int n = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = eventDao.save(event);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return n;
	}

	@Override
	public List<EventBean> getAllEvents() {
		List<EventBean> events = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			events = eventDao.getAllEvents();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return events;
	}

	@Override
	public EventBean getEvent(int pk) {
		EventBean event = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			event = eventDao.getEvent(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		return event;
	}

	@Override
	public void deleteEvent(int pk) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			eventDao.deleteEvent(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	@Override
	public void updateEvent(EventBean event) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			eventDao.updateEvent(event);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
