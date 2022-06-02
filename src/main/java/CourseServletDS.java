import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.CourseBean;
import DAO.CourseDAO;

@WebServlet("/CourseServletDS")
public class CourseServletDS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//test github by Vincet Lian
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/FindJobDB");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			CourseDAO courseDAO = new CourseDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");
			
			if (request.getParameter("UptdByCourseNO") != null) { // get UptdByCourseNO & deliver event to update page and show Update course
				processUpdQueryByNo(request, response, courseDAO);

			} else if (request.getParameter("updateConfirm") != null) { // updateConfirm exist & do Update
				processUpdate(request, response, courseDAO);

			} else if (request.getParameter("DELETE") != null) { // DeleteId exist & do delete
				processDeleteByNo(request, response, courseDAO);

			} else if (request.getParameter("findByMulti") != null) { // findByMulti exist & do multi query
				processMultiQuery(request, response, courseDAO);

//			} else if (request.getParameter("inset") != null) { // create exist & do create
//				doPost(request, response);
			
			} else if (request.getParameter("detailForm") != null) { // create exist & do create
				processShowDetail(request, response, courseDAO);

			} else {
				processQueryByAll(request, response, courseDAO);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}
		//doPost(request, response);
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/FindJobDB");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			CourseDAO courseDAO = new CourseDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");
			
			// Create a factory for uploading stream (檔案上傳)
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096); // 設定緩衝區大小，這裡是4kb
			ServletFileUpload upload = new ServletFileUpload(factory); // Create a new file upload handler
			upload.setSizeMax(4194304); // 設定最大檔案尺寸，這裡是4MB
			
			// 取到所有的檔案(fields 欄位)
			// {[FieldName1="fieldValue1"],[FieldName2="fieldValue2"],[FieldName3="fieldValue3"]}
			List<FileItem> fields = upload.parseRequest(request); //得到所有的檔案
			Iterator<FileItem> fieldsIterator = fields.iterator();
			
			// put data into bean
			CourseBean course =new CourseBean();
			String OringinImgURL = null;
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");//取得value值
				
				if(!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
					File imageFile = new File(System.currentTimeMillis() + "_" + fieldItem.getName());//幫檔名前面加上時間戳(毫秒)
					String uploadPath = getServletContext().getRealPath(File.separator + "courseImg");
					File savedFile = new File(uploadPath, imageFile.getName());
					//save Img file in absolute path
					fieldItem.write(savedFile);
					//set ImgURL in relative path
					course.setCoursePicUrl("courseImg" + File.separator + savedFile.getName());
				}else if (fieldName.equals("courseCategory")) {
					course.setCourseCategory(fieldValue);
				}else if (fieldName.equals("courseName")) {
					course.setCourseName(fieldValue);	
				}else if (fieldName.equals("courseIntroduction")) {
					course.setCourseIntroduction(fieldValue);
				}else if (fieldName.equals("lecturer")) {
					course.setLecturer(fieldValue);
				}else if (fieldName.equals("date")) {
					course.setDate(Date.valueOf(fieldValue));	
				}else if (fieldName.equals("courseVedio")) {
					course.setCourseVedioUrl(fieldValue);	
				}else if (fieldName.equals("score")) {
					course.setScore(Double.valueOf(fieldValue));
				}else if (fieldName.equals("price")) {
					course.setPrice(Integer.valueOf(fieldValue));
				}else if (fieldName.equals("OringinImgURL")) {
					OringinImgURL = fieldValue;
				}
				
			}
			if (course.getCoursePicUrl() == null) { //if img did not upload set OringinImgURL
				course.setCoursePicUrl(OringinImgURL);
			}

			if (course.getCourseName()!=null) { //EventCreate.jsp <input:hidden name="adId" value="0">
				processCreate(request, response, courseDAO, course);
				System.out.println(course.getCourseName());
				//course.getCourseName();
			}
//			else{
//				processUpdate(request, response, courseDAO, course);
//			}
			
			
//			if (request.getParameter("UptdByCourseNO") != null) { // get UptdByCourseNO & deliver event to update page and show Update course
//				processUpdQueryByNo(request, response, courseDAO);
//
//			} else if (request.getParameter("updateConfirm") != null) { // updateConfirm exist & do Update
//				processUpdate(request, response, courseDAO);
//
//			} else if (request.getParameter("DELETE") != null) { // DeleteId exist & do delete
//				processDeleteByNo(request, response, courseDAO);
//
//			} else if (request.getParameter("findByMulti") != null) { // findByMulti exist & do multi query
//				processMultiQuery(request, response, courseDAO);
//
//			} else if (request.getParameter("inset") != null) { // create exist & do create
//				processCreate(request, response, courseDAO);
//			
//			} else if (request.getParameter("detailForm") != null) { // create exist & do create
//				processShowDetail(request, response, courseDAO);
//
//			} else {
//				processQueryByAll(request, response, courseDAO);
//			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO, CourseBean course)
			throws SQLException, IOException {
		
		boolean createCheck = courseDAO.createCourse(course);
		try {
			if (createCheck) {
				System.out.println(" Create success");
				response.sendRedirect("./CourseServletDS");

			} else {
				System.out.println("Create No:" + course.getCourseNo() + " Create \"failed\"");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);

			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void processDeleteByNo(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {

		String courseNo = request.getParameter("courseNo");
		// 透過DAO元件Access course delete

		boolean deletetCheck = courseDAO.deletetCourseByNo(Integer.valueOf(courseNo));

		try {
			if (deletetCheck) {
				System.out.println("DeleteId:" + courseNo + " Delete success");
				response.sendRedirect("./CourseServletDS");
			} else {
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	private void processShowDetail(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {
		// 讀取client端傳來的課程號碼
		String courseNo = request.getParameter("courseNo");

		// 透過DAO元件Access course List
		List<CourseBean> courseList = courseDAO.findCourseByNo(courseNo);
		for (CourseBean course : courseList) {
			try {
				if (course == null) {
					System.out.println("ShowDetail " + courseNo + " failed");
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				} else {
					request.setAttribute("course", course);
					getServletContext().getRequestDispatcher("/CourseDetail.jsp").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processUpdQueryByNo(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {
		// 讀取client端傳來的課程號碼
		String courseNo = request.getParameter("courseNo");
		
		// 透過DAO元件Access course List
		List<CourseBean> courseList = courseDAO.findCourseByNo(courseNo);
		for (CourseBean course : courseList) {
			try {
				if (course == null) {
					System.out.println("UpdQueryByNo " + courseNo + " failed");
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				} else {
					request.setAttribute("course", course);
					getServletContext().getRequestDispatcher("/CourseUpdCheck.jsp").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

//	private void processUpdate(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO, Course course) 
//			throws SQLException, IOException {
//
//		if (courseDAO.updatetCourse(course)) {
//			System.out.println("Update:" + course.getCourseNo() + " Update success");
//			response.sendRedirect("./CourseServletDS");
//
//		} else {
//			System.out.println("Update:" + course.getCourseNo() + " Update failed");
//			try {
//				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {
		String courseNo = request.getParameter("courseNo");
		String courseCategory = request.getParameter("courseCategory");
		String courseName = request.getParameter("courseName");
		String courseIntroduction = request.getParameter("courseIntroduction");
		String lecturer = request.getParameter("lecturer");
		String date = request.getParameter("date");
		String coursePic = request.getParameter("coursePic");
		String courseVedio = request.getParameter("courseVedio");
		String score = request.getParameter("score");
		String price = request.getParameter("price");
		
		List<CourseBean> courseList = courseDAO.findCourseByNo(courseNo);
		try {
			if (courseList == null) {
				System.out.println("UpdateQ:" + courseNo + " UpdateQ failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
			else {
				for (CourseBean course : courseList) {
					course.setCourseCategory(courseCategory);
					course.setCourseName(courseName);
					course.setCourseIntroduction(courseIntroduction);
					course.setLecturer(lecturer);
					course.setDate(Date.valueOf(date));
					course.setCoursePicUrl(coursePic);
					course.setCourseVedioUrl(courseVedio);
					course.setScore(Double.valueOf(score));
					course.setPrice(Integer.valueOf(price));
					
					if (courseDAO.updatetCourse(course)) {
						System.out.println("Update:" + courseNo + " Update success");
						response.sendRedirect("./CourseServletDS");
						
					} else {
						System.out.println("Update:" + courseNo + " Update failed");
						getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
						
					}
				}
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void processQueryByAll(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {

		// 透過DAO元件Access course List
		List<CourseBean> courseList = courseDAO.findCourseAll();

		if (courseList == null)
			try {
				System.out.println("QueryAll failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else
			try {
				request.setAttribute("courseList", courseList);
				getServletContext().getRequestDispatcher("/CourseQuery.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

	}

	private void processMultiQuery(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
			throws SQLException, IOException {
		String courseNo = request.getParameter("courseNo");
		String courseCategory = request.getParameter("courseCategory");
		String courseName = request.getParameter("courseName");
		String lecturer = request.getParameter("lecturer");
		String dateMonth = request.getParameter("dateMonth");
		String date = request.getParameter("date");

		System.out.printf("%s|%s|%s|%s|%s|%s%n", courseNo, courseCategory, courseName, lecturer, dateMonth, date);

		List<CourseBean> courseList = courseDAO.findCourseMulti(courseNo, courseCategory, courseName, lecturer, dateMonth,
				date);
		if (courseList == null)
			try {
				System.out.println("QueryMulti failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		else
			try {
				request.setAttribute("courseList", courseList);
				request.getRequestDispatcher("/CourseQuery.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
	}

	public void init() throws ServletException {
		File uploadDir = new File(getServletContext().getRealPath(File.separator + "courseImg"));
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}

//	private void processQueryByNo(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
//			throws SQLException, IOException {
//		// 讀取client端傳來的課程號碼
//		String courseNo = request.getParameter("courseNo");
//
//		// 透過DAO元件Access course List
//		List<CourseBean> courseList = courseDAO.findCourseByNo(courseNo);
//		if (courseList == null) {
//			try {
//				request.setAttribute("courseList", " 找不到此編號課程 ");
//				request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			} // showError(response, " can not findCourseByNo ");
//		}
//
//		else {
//			try {
//				request.setAttribute("courseList", courseList);
//				request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			} // showForm(response, courseList);
//		}
//	}

//	private void showError(HttpServletResponse response, String message) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<HTML>");
//		out.println("<HEAD>");
//		out.println("<TITLE>CourseBean Form</TITLE>");
//		out.println(" <meta charset=\"UTF-8\">");
//
//		out.println("</HEAD>");
//		out.println("<BODY BGCOLOR='#FDF5E6'>");
//		out.println("message:" + message);
//		out.println("<p><a  href=\"courseForm.html\"><button>回到查詢頁</button></a></p>");
//		out.println("</BODY>");
//		out.println("</HTML>");
//		out.close();
//	}
//
//	private void showExecuteSucess(HttpServletResponse response, String message) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<HTML>");
//		out.println("<HEAD>");
//		out.println("<TITLE>CourseBean Form</TITLE>");
//		out.println(" <meta charset=\"UTF-8\">");
//		out.println("</HEAD>");
//		out.println("<BODY BGCOLOR='#FDF5E6'>");
//		out.println("message:" + message);
//		out.println("<p><a  href=\"courseForm.html\"><button>回到查詢頁</button></a></p>");
//		out.println("</BODY>");
//		out.println("</HTML>");
//		out.close();
//	}

//	private void processQueryByCategory(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
//			throws SQLException, IOException {
//				// 讀取client端傳來的課程分類
//		String courseCategory = request.getParameter("courseCategory");
//
//				// 透過DAO元件Access course List
//		List<CourseBean> courseList = courseDAO.findCourseByCategory(courseCategory);
//		if (courseList == null)
//			showError(response, " can not findCourseByCategory  ");
//		else
//			request.setAttribute("courseList", courseList);
//		try {
//			request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
//				//	showForm(response, courseList);
//	}

//	private void processQueryByName(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
//			throws SQLException, IOException {
//		// 讀取client端傳來的課程名稱
//		String courseName = request.getParameter("courseName");
//
//		// 透過DAO元件Access course List
//		List<CourseBean> courseList = courseDAO.findCourseByName(courseName);
//		if (courseList == null)
//			showError(response, " can not findCourseByName");
//		else
//			request.setAttribute("courseList", courseList);
//		try {
//			request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
//		// showForm(response, courseList);
//	}

//	private void processQueryByDate(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
//			throws SQLException, IOException {
//		// 讀取client端傳來的上架日期
//		String date = request.getParameter("date");
//
//		// 透過DAO元件Access course List
//		List<CourseBean> courseList = courseDAO.findCourseByDate(date);
//		if (courseList == null)
//			showError(response, " can not findCourseByLecturer");
//		else
//			request.setAttribute("courseList", courseList);
//		try {
//			request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
//		// howForm(response, courseList);
//
//	}

//	private void processQueryByLecturer(HttpServletRequest request, HttpServletResponse response, CourseDAO courseDAO)
//			throws SQLException, IOException {
//		// 讀取client端傳來的講師
//		String lecturer = request.getParameter("lecturer");
//
//		// 透過DAO元件Access course List
//		List<CourseBean> courseList = courseDAO.findCourseByLecturer(lecturer);
//		if (courseList == null)
//			showError(response, " can not findCourseByLecturer");
//		else
//			request.setAttribute("courseList", courseList);
//
//		try {
//			request.getRequestDispatcher("/showForm.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
//		// showForm(response, courseList);
//	}
}