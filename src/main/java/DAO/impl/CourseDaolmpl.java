package DAO.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Bean.CourseBean;
import DAO.CourseDao;
import util.HibernateUtils;

public class CourseDaolmpl implements CourseDao {
	
	SessionFactory factory;
	Session session;
	
	public CourseDaolmpl() {
		factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
	}

	@Override
	public boolean isDup(int pk) {
		CourseBean courseBean = session.get(CourseBean.class, pk);
		if (courseBean != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(CourseBean courseBean) {
		return (int) session.save(courseBean);
	}

	@Override
	public List<CourseBean> getAllCourses() {
		List<CourseBean>  courseBeans = null;
		String hql = "FROM CourseBean";
		courseBeans = session.createQuery(hql, CourseBean.class).getResultList();
		return courseBeans;
	}
	
	@Override
	public List<CourseBean> getCourseByMultiQuery(String courseNo, String courseCategory, String courseName,
			String lecturer, String dateMonth, String date) {
		List<CourseBean> courseBeans = null;
		String hql = "FROM CourseBean c WHERE c.courseNo like :courseNo "
				+ "and c.courseCategory like :courseCategory "
				+ "and c.courseName like :courseName and c.lecturer like :lecturer "
				+ "and c.dateMonth like :dateMonth and c.date like :date";
		
//		String sqlSelectByMulti ="from course where courseNo like ? and courseCategory like ? "
//				+ "and courseName like ? and lecturer like ? and date like ? and date like ?";
//		if (courseNo.length() == 1) {
//			pstmt.setString(1, courseNo);
//		} else {
//			pstmt.setString(1, "%" + courseNo + "%");
//		}
//		pstmt.setString(2, "%" + courseCategory + "%");
//		pstmt.setString(3, "%" + courseName + "%");
//		pstmt.setString(4, "%" + lecturer + "%");
//		pstmt.setString(5, "%-%" + dateMonth + "-%");
//		pstmt.setString(6, "%" + date + "%");
		
		courseBeans = session.createQuery(hql, CourseBean.class)
								.setParameter("courseNo","%" + courseNo + "%" )
								.setParameter("courseCategory","%" + courseCategory + "%" )
								.setParameter("courseName","%" + courseName + "%" )
								.setParameter("lecturer","%" + lecturer + "%" )
								.setParameter("dateMonth","%-%" + dateMonth + "-%" )
								.setParameter("date","%" + date + "%" )
									.getResultList();
		return courseBeans;
	}

	@Override
	public CourseBean getCourse(int pk) {
		CourseBean courseBean = null;
		courseBean = session.get(CourseBean.class, pk);
		return courseBean;
	}

	@Override
	public void deleteCourse(int pk) {
		CourseBean courseBean = new CourseBean();
		courseBean.setCourseNo(pk);
		session.delete(courseBean);
	}

	@Override
	public void updateCourse(CourseBean courseBean) {
		session.saveOrUpdate(courseBean);
	}
}
