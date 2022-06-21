package tw.team5.service;

import java.util.List;

import tw.team5.bean.Job;

public interface JobService {
	boolean isDup(int pk);
	
	int save(Job job);
	
	List<Job> getAllJobs();
	
	Job getJobByJobID(int pk);

	void delete(int pk);
	
	void update(Job job);
	
}
