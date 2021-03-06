package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Interview;
import DAO.InterviewDao;
import DAO.impl.InterviewDaoImpl;
import Service.InterviewService;
import util.HibernateUtils;

public class InterviewServiceImpl implements InterviewService {
	SessionFactory factory;
	InterviewDao interviewdao;

	public InterviewServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.interviewdao = new InterviewDaoImpl();
	}

	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = interviewdao.isDup(pk);
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
	public int save(Interview interview) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = interviewdao.save(interview);
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
	public List<Interview> getAllInterviews() {
		List<Interview> interviews = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			interviews = interviewdao.getAllInterviews();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return interviews;
	}

	@Override
	public Interview getInterview(int pk) {
		Interview interview = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			interview = interviewdao.getInterview(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return interview;
	}

	@Override
	public void deleteInterview(int pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			interviewdao.deleteInterview(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateInterview(Interview interview) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			interviewdao.updateInterview(interview);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

}
