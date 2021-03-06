package Service;

import java.util.List;

import Bean.EventBean;
import Bean.Interview;

public interface InterviewService {
	boolean isDup(int pk);

	int save(Interview interview);

	Interview getInterview(int pk);

	void deleteInterview(int pk);

	void updateInterview(Interview interview);

	List<Interview> getAllInterviews();

}
