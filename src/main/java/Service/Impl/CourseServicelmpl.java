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
	CourseDao courseDao;
	
	public CourseServicelmpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.courseDao = new CourseDaolmpl();
	}

	@Override
	public boolean isDup(int pk) {
		boolean checkResult = false;
		Session session = factory.getCurrentSession();
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
		Session session = factory.getCurrentSession();
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
		Session session = factory.getCurrentSession();
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
	public List<CourseBean> getCourseByMultiQuery(String courseCategory, String courseName,
			String lecturer) {
		List<CourseBean> courseBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			courseBeans = courseDao.getCourseByMultiQuery(courseCategory, courseName, lecturer );
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
		Session session = factory.getCurrentSession();
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
	public boolean deleteCourse(int pk) {
		boolean checkResult = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			checkResult = courseDao.deleteCourse(pk);
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
	public boolean updateCourse(CourseBean courseBean) {
		boolean checkResult = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			checkResult = courseDao.updateCourse(courseBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return checkResult;
	}
}
