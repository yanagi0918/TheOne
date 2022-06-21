package tw.team5.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.team5.bean.Job;
import tw.team5.dao.*;
import tw.team5.dao.impl.JobDaoImpl;
import tw.team5.service.JobService;
import tw.team5.util.HibernateUtils;

public class JobServiceImpl implements JobService{
	SessionFactory factory;
	JobDao jobDao;
	
	public JobServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.jobDao = new JobDaoImpl();
	}

	@Override
	public int save(Job job) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = jobDao.save(job);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return n;
	}

	@Override
	public List<Job> getAllJobs() {
		List<Job> jobs = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			jobs = jobDao.getAllJobs();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
			throw new RuntimeException(e);
		}
		return jobs;
	}

	@Override
	public Job getJobByJobID(int pk) {
		Job job = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			job = jobDao.getJobByJobID(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return job;
	}

	@Override
	public void delete(int pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			jobDao.delete(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Job job) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			jobDao.update(job);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = jobDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return result;
	}

}
