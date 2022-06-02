package Service;

import java.util.List;

import Bean.CourseBean;

public interface CourseService {

	boolean isDup(int pk);

	int save(CourseBean courseBean);

	List<CourseBean> getAllCourses();
	
	List<CourseBean> getCourseByMultiQuery(String courseNo, String courseCategory, String courseName, String lecturer,
			String dateMonth, String date);

	CourseBean getCourse(int pk);

	void deleteCourse(int pk);

	void updateCourse(CourseBean courseBean);
}