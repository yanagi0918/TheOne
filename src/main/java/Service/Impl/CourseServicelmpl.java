package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.CourseBean;
import DAO.CourseDao;
import DAO.impl.CourseDaolmpl;
import Service.CourseService;
import util.HibernateUtils;

public class CourseServicelmpl implements CourseService {

	SessionFactory factory;
	Session session;
	CourseDao courseDao;
	
	public CourseServicelmpl() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
		courseDao = new CourseDaolmpl();
	}

	@Override
	public boolean isDup(int pk) {
		boolean checkResult = false;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			checkResult = courseDao.isDup(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return checkResult;
	}

	@Override
	public int save(CourseBean courseBean) {
		int n = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n  = courseDao.save(courseBean);
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
	public List<CourseBean> getAllCourses() {
		List<CourseBean> courseBeans = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseBeans = courseDao.getAllCourses();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return courseBeans;
	}

	@Override
	public List<CourseBean> getCourseByMultiQuery(String courseNo, String courseCategory, String courseName,
			String lecturer, String dateMonth, String date) {
		List<CourseBean> courseBeans = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseBeans = courseDao.getCourseByMultiQuery(courseNo, courseCategory, courseName, lecturer, dateMonth, date);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return courseBeans;
	}

	@Override
	public CourseBean getCourse(int pk) {
		CourseBean courseBean = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseBean = courseDao.getCourse(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return courseBean;
	}

	@Override
	public void deleteCourse(int pk) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseDao.deleteCourse(pk);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateCourse(CourseBean courseBean) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseDao.updateCourse(courseBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}
}
