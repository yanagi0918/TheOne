package tw.team5.dao.impl;
//package DAO.impl;
//package DAO.impl;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import Bean.CourseBean;
//
//public class CourseDaolmpl2 implements CourseDao {
//
//	private Connection conn;
//
//	public CourseDaolmpl2() {
//	}
//
//	public CourseDaolmpl2(Connection conn) {
//		this.conn = conn;
//	}
//
//
//	@Override
//	public List<CourseBean> findCourseByNo(String courseNo) {
//		List<CourseBean> list = new ArrayList<CourseBean>();
//		String sqlSelectByNo = "SELECT * FROM COURSE WHERE courseNo = ?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByNo);
//			pstmt.setString(1, courseNo);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				CourseBean course = new CourseBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
//						rs.getInt(10));
//
//				list.add(course);
//			}
//			rs.close();
//			pstmt.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	/*-------SELECT-------*/
//	@Override
//	public List<CourseBean> findCourseAll() {
//		List<CourseBean> list = new ArrayList<CourseBean>();
//		String sqlSelectAll = "SELECT * FROM COURSE";
//		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlSelectAll);) {
//			while (rs.next()) {
//				CourseBean course = new CourseBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
//						rs.getInt(10));
//				list.add(course);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	@Override
//	public List<CourseBean> findCourseMulti(String courseNo, String courseCategory, String courseName, String lecturer,
//			String dateMonth, String date) {
//		List<CourseBean> list = new ArrayList<CourseBean>();
//		String sqlSelectByMulti = "select * from course where courseNo like ? and courseCategory like ? and courseName like ? and lecturer like ? and date like ? and date like ?";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sqlSelectByMulti);
//
//			if (courseNo.length() == 1) {
//				pstmt.setString(1, courseNo);
//			} else {
//				pstmt.setString(1, "%" + courseNo + "%");
//			}
//			pstmt.setString(2, "%" + courseCategory + "%");
//			pstmt.setString(3, "%" + courseName + "%");
//			pstmt.setString(4, "%" + lecturer + "%");
//			pstmt.setString(5, "%-%" + dateMonth + "-%");
//			pstmt.setString(6, "%" + date + "%");
//
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				CourseBean course = new CourseBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
//						rs.getInt(10));
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
//	/*-------INSERT-------*/
//	@Override
//	public boolean createCourse(CourseBean course) throws SQLException {
//		boolean insertCheck = false;
//		String sqlCreateCourse = "INSERT INTO COURSE VALUES(?,?,?,?,?,?,?,?,?)";
//		try (PreparedStatement psmt = conn.prepareStatement(sqlCreateCourse);) {
//
//			psmt.setString(1, course.getCourseCategory());
//			psmt.setString(2, course.getCourseName());
//			psmt.setString(3, course.getCourseIntroduction());
//			psmt.setString(4, course.getLecturer());
//			psmt.setDate(5, course.getDate());
//			psmt.setString(6, course.getCoursePicUrl());
//			psmt.setString(7, course.getCourseVedioUrl());
//			psmt.setDouble(8, course.getScore());
//			psmt.setInt(9, course.getPrice());
//
//			int count = psmt.executeUpdate();
//			if (count > 0) {
//				System.out.println("成功新增" + count + "筆");
//				insertCheck = true;
//			} else {
//				System.out.println("新增失敗 =..=");
//			}
//			System.out.println("---------------------\r\n");
//			return insertCheck;
//		}
//	}
//
//	/*-------DELETE-------*/
//	@Override
//	public boolean deletetCourseByNo(int courseNo) throws SQLException {
//		Boolean boolean1 = false;
//		String sqlDeletetCourseByNo = "DELETE FROM COURSE WHERE courseNo =" + courseNo;
//		try (Statement stmt = conn.createStatement();) {
//			int count = stmt.executeUpdate(sqlDeletetCourseByNo);
//			if (count > 0) {
//				System.out.println("成功刪除" + count + "筆");
//				boolean1 = true;
//			} else {
//				System.out.println("刪除失敗 =..=");
//			}
//			System.out.println("---------------------\r\n");
//		}
//		return boolean1;
//	}
//
//	/*-------UPDATE-------*/
//	@Override
//	public boolean updatetCourse(CourseBean course) {
//		String updateByNo = "UPDATE COURSE SET courseCategory=?,courseName=?,courseIntroduction=?,"
//				+ "lecturer=?,date=?,coursePic=?,courseVedio=?,score=?,price=? " + "WHERE courseNo=?";
//		try (PreparedStatement pstmt = conn.prepareStatement(updateByNo);) {
//			pstmt.setString(1, course.getCourseCategory());
//			pstmt.setString(2, course.getCourseName());
//			pstmt.setString(3, course.getCourseIntroduction());
//			pstmt.setString(4, course.getLecturer());
//			pstmt.setDate(5, course.getDate());
//			pstmt.setString(6, course.getCoursePicUrl());
//			pstmt.setString(7, course.getCourseVedioUrl());
//			pstmt.setDouble(8, course.getScore());
//			pstmt.setInt(9, course.getPrice());
//			pstmt.setInt(10, course.getCourseNo());
//
//			int updateCount = pstmt.executeUpdate();
//			if (updateCount > 0) {
//				System.out.println("成功更新" + updateCount + "筆");
//				return true;
//			} else {
//				System.out.println("更新失敗 =..=");
//				return false;
//			}
//		} catch (Exception e) {
//			System.err.println("更新部門資料時發生錯誤:" + e);
//			e.printStackTrace();
//			return false;
//		}
//	}
//}
