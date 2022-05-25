package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bean.Course;

/*CRUD相關method*/
public class CourseDAO {

	private Connection conn;

	public CourseDAO() {
	}

	public CourseDAO(Connection conn) {
		this.conn = conn;
	}

	public void printCourse(Course course) {
		System.out.println(course);
	}
	
	public List<Course> findCourseByNo(String courseNo) {
		List<Course> list = new ArrayList<Course>();
		String sqlSelectByNo = "SELECT * FROM COURSE WHERE courseNo=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByNo);
			pstmt.setString(1, courseNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
						rs.getInt(10));

				list.add(course);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*-------SELECT-------*/
	public List<Course> findCourseAll() {
		List<Course> list = new ArrayList<Course>();
		String sqlSelectAll = "SELECT * FROM COURSE";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlSelectAll);) {
			while (rs.next()) {
				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
						rs.getDouble(9), rs.getInt(10));
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Course> findCourseMulti(String courseNo, String courseCategory,String courseName,String lecturer,String dateMonth,String date) {
		List<Course> list = new ArrayList<Course>();
		String sqlSelectByMulti = "select * from course where courseNo like ? and courseCategory like ? and courseName like ? and lecturer like ? and date like ? and date like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByMulti);
			
			if (courseNo.length()==1) {
				pstmt.setString(1,courseNo);
			}else  {
				pstmt.setString(1,"%"+courseNo+"%");
			}
			pstmt.setString(2,"%"+courseCategory+"%");
			pstmt.setString(3,"%"+courseName+"%");
			pstmt.setString(4,"%"+lecturer+"%");
			pstmt.setString(5,"%-%"+dateMonth+"-%");
			pstmt.setString(6,"%"+date+"%");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
						rs.getDouble(9), rs.getInt(10));
				list.add(course);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

	
	/*-------INSERT-------*/
	public boolean createCourse(Course course) throws SQLException {
		boolean insertCheck =false;
		String sqlCreateCourse = "INSERT INTO COURSE VALUES(?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement psmt = conn.prepareStatement(sqlCreateCourse);) {

//			psmt.setString(1, course.getCourseNo());
			psmt.setString(1, course.getCourseCategory());
			psmt.setString(2, course.getCourseName());
			psmt.setString(3, course.getCourseIntroduction());
			psmt.setString(4, course.getLecturer());
			psmt.setDate(5, course.getDate());
			psmt.setString(6, course.getCoursePic());
			psmt.setString(7, course.getCourseVedio());
			psmt.setDouble(8, course.getScore());
			psmt.setInt(9, course.getPrice());

			int count = psmt.executeUpdate();
			if (count > 0) {
				System.out.println("成功新增" + count + "筆");
				insertCheck=true;
			} else {
				System.out.println("新增失敗 =..=");
			}
			System.out.println("---------------------\r\n");
			return insertCheck;
		}
	}

	/*-------DELETE-------*/
	public boolean deletetCourseByNo(int courseNo) throws SQLException {
		Boolean boolean1 =false;
		String sqlDeletetCourseByNo = "DELETE FROM COURSE WHERE courseNo =" + courseNo;
		try (Statement stmt = conn.createStatement();) {
			int count = stmt.executeUpdate(sqlDeletetCourseByNo);
			if (count > 0) {
				System.out.println("成功刪除" + count + "筆");
				boolean1=true;
			} else {
				System.out.println("刪除失敗 =..=");
			}
			System.out.println("---------------------\r\n");
		}
		return boolean1;
	}

	/*-------UPDATE-------*/
	public boolean updatetCourse(Course course) {
		String updateByNo = "UPDATE COURSE SET courseCategory=?,courseName=?,courseIntroduction=?,"
				+ "lecturer=?,date=?,coursePic=?,courseVedio=?,score=?,price=? "
				+ "WHERE courseNo=?";
		try (PreparedStatement pstmt = conn.prepareStatement(updateByNo);
				) {
			pstmt.setString(1, course.getCourseCategory());
			pstmt.setString(2, course.getCourseName());
			pstmt.setString(3, course.getCourseIntroduction());
			pstmt.setString(4, course.getLecturer());
			pstmt.setDate(5, course.getDate());
			pstmt.setString(6, course.getCoursePic());
			pstmt.setString(7, course.getCourseVedio());
			pstmt.setDouble(8, course.getScore());
			pstmt.setInt(9, course.getPrice());
			pstmt.setString(10, course.getCourseNo());

			int updateCount = pstmt.executeUpdate();
			if (updateCount > 0) {
				 System.out.println("成功更新" + updateCount + "筆");
				return true;
			} else {
				 System.out.println("更新失敗 =..=");
				return false;
			}
		} catch (Exception e) {
			System.err.println("更新部門資料時發生錯誤:" + e);
			e.printStackTrace();
			return false;
		}
	}


//
//	public List<Course> findCourseByCategory(String courseCategory) {
//		List<Course> list = new ArrayList<Course>();
//		String sqlSelectByCategory = "SELECT * FROM COURSE WHERE courseCategory=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByCategory);
//			pstmt.setString(1, courseCategory);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
//						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
//						rs.getDouble(9), rs.getInt(10));
//				list.add(course);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<Course> findCourseByName(String courseName) {
//		List<Course> list = new ArrayList<Course>();
//		String sqlSelectByName = "SELECT * FROM COURSE WHERE courseName LIKE ?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByName);
//			pstmt.setString(1,"%"+courseName+"%");
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
//						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
//						rs.getDouble(9), rs.getInt(10));
//				list.add(course);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<Course> findCourseByDate(String date) {
//		List<Course> list = new ArrayList<Course>();
//		String sqlSelectByDate = "SELECT * FROM COURSE WHERE date=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByDate);
//			pstmt.setString(1, date);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
//						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
//						rs.getDouble(9), rs.getInt(10));
//				list.add(course);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public List<Course> findCourseByLecturer(String lecturer) {
//		List<Course> list = new ArrayList<Course>();
//		String sqlSelectByLecturer = "SELECT * FROM COURSE WHERE lecturer=?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByLecturer);
//			pstmt.setString(1, lecturer);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				Course course = new Course(rs.getString(1), rs.getString(2), rs.getString(3),
//						rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8),
//						rs.getDouble(9), rs.getInt(10));
//				list.add(course);
//			}
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}
