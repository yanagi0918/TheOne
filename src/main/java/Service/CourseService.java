package Service;

import java.util.List;

import Bean.CourseBean;

public interface CourseService {

	boolean isDup(int pk);

	int save(CourseBean courseBean);

	List<CourseBean> getAllCourses();
	
	List<CourseBean> getCourseByMultiQuery(String courseCategory, String courseName, String lecturer);

	CourseBean getCourse(int pk);

	boolean deleteCourse(int pk);

	boolean updateCourse(CourseBean courseBean);
}