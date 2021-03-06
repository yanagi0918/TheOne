package Service;

import java.util.List;

import Bean.EventBean;

public interface EventService {
	boolean isDup(int pk);

	int save(EventBean event);

	List<EventBean> getAllEvents();

	EventBean getEvent(int pk);

	void deleteEvent(int pk);

	void updateEvent(EventBean event);

}
