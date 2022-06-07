package DAO.impl;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Bean.Job;
import DAO.JobDao;
import util.HibernateUtils;

public class JobDaoImpl implements JobDao{
	SessionFactory factory;
	public JobDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}
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
	public Job getJobByJobID(int pk) {
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