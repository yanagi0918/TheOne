package tw.team5.dao;

import java.util.List;

import tw.team5.bean.Interview;

public interface InterviewDao {
	boolean isDup(int pk);

	int save(Interview interview);

	List<Interview> getAllInterviews();

	Interview getInterview(int pk);

	void deleteInterview(int pk);

	void updateInterview(Interview interview);

}
