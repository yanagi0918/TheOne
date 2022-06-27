package tw.team5.dao.impl;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.team5.bean.Job;
import tw.team5.dao.JobDao;
@Repository
public class JobDaoImpl implements JobDao{
	@Autowired
	private SessionFactory factory;
	
	@Override
	public int save(Job job) {
		Session session = factory.getCurrentSession();
		int n = 0;
		n  =(int)session.save(job );
		return n ;
	}

	@Override
	public List<Job> getAllJobs() {
		Session session = factory.getCurrentSession();
		List<Job> jobs = null;
		String hql = "FROM Job";
		jobs = session.createQuery(hql, Job.class).getResultList();
		return jobs;
	}

	@Override
	public Job getJob(int pk) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class,pk);
		return job;
	}

	@Override
	public void delete(int pk) {
		Session session = factory.getCurrentSession();
		Job job = new Job();
		job.setJob_id(pk);
		session.delete(job);
		}

	@Override
	public void update(Job job) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(job);
	}

	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		Job job = (Job) session.get(Job.class, pk);
		if(job !=null) {
			return true;
		}else {
			return false;
		}
	}
	
}