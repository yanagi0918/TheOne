package tw.team5.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tw.team5.bean.CourseBean;
import tw.team5.service.CourseService;
import tw.team5.service.impl.CourseServicelmpl;

@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("UptdByCourseNO") != null) { // get UptdByCourseNO  and show Update course
				processFindCourseByNo(request, response);

			} else if (request.getParameter("findByNo") != null) { 
				processFindCourseByNo(request, response);

			} else if (request.getParameter("Delete") != null) { // DeleteId exist & do delete
				processDeleteByNo(request, response);

			} else if (request.getParameter("findByMulti") != null) { // findByMulti exist & do multi query
				processMultiQuery(request, response);

			} else if (request.getParameter("detailForm") != null) { // create exist & do create
				processShowDetail(request, response);

			} else {
				processQueryByAll(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			// Create a factory for uploading stream (檔案上傳)
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096); // 設定緩衝區大小，這裡是4kb
			ServletFileUpload upload = new ServletFileUpload(factory); // Create a new file upload handler
			upload.setSizeMax(4194304); // 設定最大檔案尺寸，這裡是4MB

			// 取到所有的檔案(fields 欄位)
			// {[FieldName1="fieldValue1"],[FieldName2="fieldValue2"],[FieldName3="fieldValue3"]}
			List<FileItem> fields = upload.parseRequest(request); // 得到所有的檔案
			Iterator<FileItem> fieldsIterator = fields.iterator();

			// put data into bean
			CourseBean courseBean = new CourseBean();
			String OringinImgURL = null;
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");// 取得value值

				if (!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
					File imageFile = new File(System.currentTimeMillis() + "_" + fieldItem.getName());// 幫檔名前面加上時間戳(毫秒)
					String uploadPath = getServletContext().getRealPath(File.separator + "courseImg");
					File savedFile = new File(uploadPath, imageFile.getName());
					// save Img file in absolute path
					fieldItem.write(savedFile);
					// set ImgURL in relative path
					courseBean.setCoursePicUrl("courseImg" + File.separator + savedFile.getName());
				} else if (fieldName.equals("courseCategory")) {
					courseBean.setCourseCategory(fieldValue);
				} else if (fieldName.equals("courseName")) {
					courseBean.setCourseName(fieldValue);
				} else if (fieldName.equals("courseIntroduction")) {
					courseBean.setCourseIntroduction(fieldValue);
				} else if (fieldName.equals("lecturer")) {
					courseBean.setLecturer(fieldValue);
				} else if (fieldName.equals("date")) {
					courseBean.setDate(Date.valueOf(fieldValue));
				} else if (fieldName.equals("courseVedio")) {
					courseBean.setCourseVedioUrl(fieldValue);
				} else if (fieldName.equals("score")) {
					courseBean.setScore(Double.valueOf(fieldValue));
				} else if (fieldName.equals("price")) {
					courseBean.setPrice(Integer.valueOf(fieldValue));
				} else if (fieldName.equals("courseNo")) {
					courseBean.setCourseNo(Integer.valueOf(fieldValue));
				} else if (fieldName.equals("OringinImgURL")) {
					OringinImgURL = fieldValue;
				}
			}
			if (courseBean.getCoursePicUrl() == null) { // if img did not upload set OringinImgURL
				courseBean.setCoursePicUrl(OringinImgURL);
			}

			if (courseBean.getCourseNo() == 0) { // courseCreate.jsp <input:hidden name="courseNo" value="0">
				processCreate(request, response, courseBean);
			}
			else {
				processUpdate(request, response, courseBean);
			}
			
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, CourseBean course)
			throws SQLException, IOException {

		CourseService courseService = new CourseServicelmpl();
		int coursePk = courseService.save(course);
		try {
			if (coursePk > 0) {
				System.out.println(" Create success ");
				response.sendRedirect("./CourseController");
			} else {
				System.out.println("Create No:" + course.getCourseNo() + " Create \"failed\"");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void processDeleteByNo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String courseNo = request.getParameter("courseNo").trim();
		CourseService courseService = new CourseServicelmpl();
		boolean deletetCheck = courseService.deleteCourse(Integer.valueOf(courseNo));
		try {
			if (deletetCheck) {
				System.out.println("DeleteNo:" + courseNo + " Delete success");
				response.sendRedirect("./CourseController");
			} else {
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	private void processShowDetail(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String courseNo = request.getParameter("courseNo").trim();
		CourseService courseService = new CourseServicelmpl();
		CourseBean courseBean = courseService.getCourse(Integer.valueOf(courseNo));
		try {
			if (courseBean == null) {
				System.out.println("ShowDetail " + courseNo + " failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			} else {
				request.setAttribute("CourseBean", courseBean);
				getServletContext().getRequestDispatcher("/CourseDetail.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processFindCourseByNo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String courseNo = request.getParameter("courseNo").trim();
		System.out.println(courseNo);
		CourseService courseService = new CourseServicelmpl();
		CourseBean courseBean = courseService.getCourse(Integer.valueOf(courseNo));
		try {
			if (courseBean == null) {
				System.out.println("UpdQueryByNo " + courseNo + " failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			} else {
				if (request.getParameter("UptdByCourseNO") != null) {
					request.setAttribute("courseBean", courseBean);
					getServletContext().getRequestDispatcher("/CourseUpdCheck.jsp").forward(request, response);
				} else {
					List<CourseBean> courseList = new ArrayList<CourseBean>();
					courseList.add(courseBean);
					request.setAttribute("courseList", courseList);
					getServletContext().getRequestDispatcher("/CourseQuery.jsp").forward(request, response);
				}
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, CourseBean courseBean)
			throws SQLException, IOException {

		CourseService courseService = new CourseServicelmpl();
		try {
			boolean updateCourseCheck = courseService.updateCourse(courseBean);
			System.out.println(updateCourseCheck);
			System.out.println("Update:" + courseBean.getCourseNo() + " Update success");
			response.sendRedirect("./CourseController");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processQueryByAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		CourseService courseService = new CourseServicelmpl();
		List<CourseBean> courseList = courseService.getAllCourses();
		try {
			if (courseList == null) {
				System.out.println("QueryAll failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			} else {
				request.setAttribute("courseList", courseList);
				getServletContext().getRequestDispatcher("/CourseQuery.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void processMultiQuery(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		String courseCategory = request.getParameter("courseCategory");
		String courseName = request.getParameter("courseName");
		String lecturer = request.getParameter("lecturer");

		System.out.printf("%s|%s|%s%n", courseCategory, courseName, lecturer);

		CourseService courseService = new CourseServicelmpl();
		List<CourseBean> courseList = courseService.getCourseByMultiQuery(courseCategory, courseName, lecturer);
		try {
			if (courseList == null) {
				System.out.println("QueryMulti failed");
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			} else {
				request.setAttribute("courseList", courseList);
				getServletContext().getRequestDispatcher("/CourseQuery.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		File uploadDir = new File(getServletContext().getRealPath(File.separator + "courseImg"));
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		System.out.println(uploadDir.getPath());
	}
}