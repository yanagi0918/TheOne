package DAO.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Bean.EventBean;
import DAO.EventDao;
import util.HibernateUtils;

public class EventDaoImpl implements EventDao {
	SessionFactory factory;

	public EventDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		EventBean event = (EventBean) session.get(EventBean.class, pk);
		if (event != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(EventBean event) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(event);
		return pk;
	}

	@Override
	public List<EventBean> getAllEvents() {
		Session session = factory.getCurrentSession();
		List<EventBean> events = null;
		String hql = "FROM EventBean";
		events = session.createQuery(hql, EventBean.class).getResultList();
		return events;
	}

	@Override
	public EventBean getEvent(int pk) {
		Session session = factory.getCurrentSession();
		EventBean event = session.get(EventBean.class, pk);
		return event;
	}

	@Override
	public void deleteEvent(int pk) {
		Session session = factory.getCurrentSession();
		EventBean event = new EventBean();
		event.setEventId(pk);
		session.delete(event);
	}

	@Override
	public void updateEvent(EventBean event) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(event);
	}

}
