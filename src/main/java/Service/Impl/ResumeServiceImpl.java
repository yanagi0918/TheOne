package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Resume;
import DAO.ResumeDao;
import DAO.impl.ResumeDaoImpl;
import Service.ResumeService;
import util.HibernateUtils;

public class ResumeServiceImpl implements ResumeService {

	SessionFactory factory;
	ResumeDao resumeDao;
	
	
	public ResumeServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.resumeDao = new ResumeDaoImpl();
	}
	
	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = resumeDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public int save(Resume resume) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = resumeDao.save(resume);
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
	public List<Resume> getAllResumes() {
		List<Resume> resumes = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			resumes = resumeDao.getAllResumes();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return resumes;
	}

	@Override
	public Resume getResume(int pk) {
		Resume resume = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			resume = resumeDao.getResume(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return resume;
	}

	@Override
	public void deleteResume(int pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			resumeDao.deleteResume(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateResume(Resume resume) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			resumeDao.updateResume(resume);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
}
