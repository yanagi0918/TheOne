package DAO.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Bean.EventBean;
import DAO.EventDao;
import util.HibernateUtils;

public class EventDaoImpl implements EventDao {
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.getCurrentSession();

	@Override
	public boolean isDup(int pk) {
		EventBean event = (EventBean) session.get(EventBean.class, pk);
		if (event != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(EventBean event) {
		return (int) session.save(event);
	}

	@Override
	public List<EventBean> getAllEvents() {
		List<EventBean> events = null;
		String hql = "FROM EventBean";
		events = session.createQuery(hql, EventBean.class).getResultList();
		return events;
	}

	@Override
	public EventBean getEvent(int pk) {
		EventBean event = null;
		event = session.get(EventBean.class, pk);
		return event;
	}

	@Override
	public void deleteEvent(int pk) {
		EventBean event = session.get(EventBean.class, pk);
		session.delete(event);
	}

	@Override
	public void updateEvent(EventBean event) {
		session.saveOrUpdate(event);
	}

}
