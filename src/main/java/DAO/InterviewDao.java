package DAO;

import java.util.List;

import Bean.Interview;

public interface InterviewDao {
	boolean isDup(int pk);

	int save(Interview interview);

	List<Interview> getAllInterviews();

	Interview getInterview(int pk);

	void deleteInterview(int pk);

	void updateInterview(Interview interview);

}
