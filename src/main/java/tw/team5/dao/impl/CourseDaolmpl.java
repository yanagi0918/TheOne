//package tw.team5.dao.impl;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import tw.team5.bean.CourseBean;
//import tw.team5.dao.CourseDao;
//import tw.team5.util.HibernateUtils;
//
//public class CourseDaolmpl implements CourseDao {
//	
//	SessionFactory factory;
//	
//	public CourseDaolmpl() {
//		factory = HibernateUtils.getSessionFactory();
//	}
//
//	@Override
//	public boolean isDup(int pk) {
//		Session session = factory.getCurrentSession();
//		CourseBean courseBean = session.get(CourseBean.class, pk);
//		if (courseBean != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public int save(CourseBean courseBean) {
//		Session session = factory.getCurrentSession();
//		return (int) session.save(courseBean);
//	}
//
//	@Override
//	public List<CourseBean> getAllCourses() {
//		Session session = factory.getCurrentSession();
//		List<CourseBean>  courseBeans = null;
//		String hql = "FROM CourseBean";
//		courseBeans = session.createQuery(hql, CourseBean.class).getResultList();
//		return courseBeans;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CourseBean> getCourseByMultiQuery(String courseCategory, String courseName, String lecturer){
//		Session session = factory.getCurrentSession();
//		List<CourseBean> courseBeans = null;
//		
//		String hql = "FROM CourseBean c WHERE c.courseCategory like :courseCategory "
//				+ "and c.courseName like :courseName and c.lecturer like :lecturer ";
//		
//		courseBeans = session.createQuery(hql)
//				.setParameter("courseCategory", "%"+courseCategory+"%")
//				.setParameter("courseName", "%"+courseName+"%")
//				.setParameter("lecturer", "%"+lecturer+"%")
//				.getResultList();
//
//		return courseBeans;
//	}
//
//	@Override
//	public CourseBean getCourse(int pk) {
//		CourseBean courseBean = null;
//		Session session = factory.getCurrentSession();
//		courseBean = session.get(CourseBean.class, pk);
//		return courseBean;
//	}
//
//	@Override
//	public boolean deleteCourse(int pk) {
//		boolean check = false;
//		Session session = factory.getCurrentSession();
//		CourseBean courseBean = new CourseBean();
//		courseBean.setCourseNo(pk);
//		session.delete(courseBean);
//		check = true;
//		return check;
//	}
//
//	@Override
//	public boolean updateCourse(CourseBean courseBean) {
//		boolean check = false;
//		Session session = factory.getCurrentSession();
//		session.saveOrUpdate(courseBean);
//		check = true;
//		return check;
//	}
//}
