package tw.team5.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.team5.bean.EventBean;
import tw.team5.bean.Interview;
import tw.team5.dao.EventDao;
import tw.team5.dao.InterviewDao;
import tw.team5.util.HibernateUtils;

public class InterviewDaoImpl implements InterviewDao {
	SessionFactory factory;

	public InterviewDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		Interview interview = (Interview) session.get(Interview.class, pk);
		if (interview != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Interview interview) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(interview);
		return pk;
	}

	@Override
	public List<Interview> getAllInterviews() {
		Session session = factory.getCurrentSession();
		List<Interview> interviews = null;
		String hql = "FROM Interview";
		interviews = session.createQuery(hql, Interview.class).getResultList();
		return interviews;
	}

	@Override
	public Interview getInterview(int pk) {
		Session session = factory.getCurrentSession();
		Interview interview = session.get(Interview.class, pk);
		return interview;
	}

	@Override
	public void deleteInterview(int pk) {
		Session session = factory.getCurrentSession();
		Interview interview = new Interview();
		interview.setCvNo(pk);
		session.delete(interview);
	}

	@Override
	public void updateInterview(Interview interview) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(interview);
	}

}
