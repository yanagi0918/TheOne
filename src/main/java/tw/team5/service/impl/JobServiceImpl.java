package tw.team5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.team5.bean.Job;
import tw.team5.dao.*;
import tw.team5.dao.impl.JobDaoImpl;
import tw.team5.service.JobService;
@Transactional
@Service
public class JobServiceImpl implements JobService{
	@Autowired
	private JobDao jobDao;
	
	public JobServiceImpl() {
		this.jobDao = new JobDaoImpl();
	}

	@Override
	public int save(Job job) {
		
		return jobDao.save(job);
	}

	@Override
	public List<Job> getAllJobs() {
		
		return jobDao.getAllJobs();
	}

	@Override
	public Job getJob(int pk) {
		
		return jobDao.getJob(pk);
	}

	@Override
	public void delete(int pk) {

		jobDao.delete(pk);
	}

	@Override
	public void update(Job job) {
		
		jobDao.update(job);
	}

	@Override
	public boolean isDup(int pk) {
		
		return jobDao.isDup(pk);
	}

}
