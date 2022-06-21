package tw.team5.service;

import java.util.List;

import tw.team5.bean.CourseBean;

public interface CourseService {

	boolean isDup(int pk);

	int save(CourseBean courseBean);

	List<CourseBean> getAllCourses();
	
	List<CourseBean> getCourseByMultiQuery(String courseCategory, String courseName, String lecturer);

	CourseBean getCourse(int pk);

	boolean deleteCourse(int pk);

	boolean updateCourse(CourseBean courseBean);
}