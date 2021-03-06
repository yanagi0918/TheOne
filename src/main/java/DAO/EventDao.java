package DAO;

import java.util.List;

import Bean.EventBean;

public interface EventDao {
	boolean isDup(int pk);

	int save(EventBean event);

	List<EventBean> getAllEvents();

	EventBean getEvent(int pk);

	void deleteEvent(int pk);

	void updateEvent(EventBean event);

}
