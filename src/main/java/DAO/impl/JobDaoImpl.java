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
		List<Job> job = null;
		String hql = "FROM Job";
		job = session.createQuery(hql,Job.class)
					.getResultList();
		return job;
	}

	@Override
	public Job getJobByJobID(int job_id) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class,job_id);
		return job;
	}

	@Override
	public void delete(int job_id) {
		Session session = factory.getCurrentSession();
		Job job = new Job();
		job.setJob_id(job_id);
		session.delete(job);
		}

	@Override
	public void update(Job job) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(job);
	}

	@Override
	public List<Job> getJobByTitle(String title) {
		Session session = factory.getCurrentSession();
		List<Job> job = null;
		String hql = "FROM Job WHERE title=:title";
		job = session.createQuery(hql,Job.class)
				.getResultList();
		return job;
	}
	
}