package tw.team5.dao;

import java.util.List;

import tw.team5.bean.EventBean;

public interface EventDao {
	boolean isDup(int pk);

	int save(EventBean event);

	List<EventBean> getAllEvents();

	EventBean getEvent(int pk);

	void deleteEvent(int pk);

	void updateEvent(EventBean event);

}
