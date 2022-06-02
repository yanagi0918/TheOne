package Service;

import java.util.List;

import Bean.Job;

public interface JobService {
int save(Job job);
	
	List<Job> getAllJobs();
	
	Job getJobByJobID(int pk);

	void delete(int pk);
	
	void update(Job job);
	
	List<Job> getJobByTitle(String title);
}
