
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.naming.*;
import javax.sql.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.Interview;
import DAO.IntvDAO;



@WebServlet("/InterViewServletDS")
public class InterViewServletDS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
    try {
     request.setCharacterEncoding("UTF-8");
		//get connection to DAO
		DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
		IntvDAO intvDAO = new IntvDAO(ds.getConnection()); 

		if (request.getParameter("UpdateId") != null) { //get UpdateId & deliver event to update page
			Interview intvForUpdate = intvDAO.searchByCv_No(Integer.parseInt(request.getParameter("UpdateId")));
			if (intvForUpdate == null) { //check adId exist
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
			request.setAttribute("intvForUpdate", intvForUpdate);
			getServletContext().getRequestDispatcher("/IntvUpdate.jsp").forward(request, response);
		} else if (request.getParameter("DeleteId") != null) { //DeleteId exist & do delete
			processDelete(request, response, intvDAO);
		} else { //Event's index
			showData(request, response, intvDAO);
		}
	} catch (NamingException | SQLException | ParseException e) {
		e.printStackTrace();
	}
}
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// get connection to DAO
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
			IntvDAO intvDAO  = new IntvDAO(ds.getConnection());

			// get upload stream
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304);

			// get fields 
			// {[FieldName1="fieldValue1"],[FieldName2="fieldValue2"],[FieldName3="fieldValue3"]}
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> fieldsIterator = fields.iterator();
			// put data into bean
			Interview interview = new Interview();
			while (fieldsIterator.hasNext()) {
				FileItem fieldItem = (FileItem) fieldsIterator.next();
				String fieldName = fieldItem.getFieldName();
				String fieldValue = fieldItem.getString("UTF-8");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				System.out.println(dtf.format(LocalDateTime.now())); 

				if (!fieldItem.isFormField() && !fieldItem.getName().equals("")) {
				} else if (fieldName.equals("cv_no")) {
					System.out.println("fieldValue: "+fieldValue);
					interview.setCv_No(Integer.parseInt(fieldValue));
				}  else if (fieldName.equals("Int_Time")) {
					interview.setInt_Time(fieldValue);
				} else if (fieldName.equals("Comp_Name")) {
					interview.setComp_Name(fieldValue);
				} else if (fieldName.equals("Job_Name")) {
					interview.setJob_Name(fieldValue);
				} else if (fieldName.equals("Offer")) {
					interview.setOffer(fieldValue);
				} else if (fieldName.equals("Test")) {
					interview.setTest(fieldValue);
				} else if (fieldName.equals("Language")) {
					interview.setLanguage(fieldValue);
				} else if (fieldName.equals("QA")) {
					interview.setQA(fieldValue);
				} else if (fieldName.equals("Share")) {
					interview.setShare(fieldValue);
				} else if (fieldName.equals("Int_Score")) {
					interview.setInt_Score(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("Comp_Score")) {
					interview.setComp_Score(Integer.parseInt(fieldValue));
				} else if (fieldName.equals("USER_ID")) {
					interview.setUSER_ID(fieldValue);								
				} else if (fieldName.equals("Created_Time")) {
					interview.setCreated_Time(dtf.format(LocalDateTime.now()));
				}
			}
			
			System.out.println("cv_no: "+interview.getCv_No());
			if (interview.getCv_No() == 0) { //EventCreate.jsp <input:hidden name="Cv_No" value="0">
				System.out.println("processCreate");
				processCreate(request, response, intvDAO, interview);
			} else {
				System.out.println("processUpdate");
				processUpdate(request, response, intvDAO, interview);
			}

		} catch (NamingException | SQLException | ParseException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showData(HttpServletRequest request, HttpServletResponse response, IntvDAO intvDAO)
			throws SQLException, IOException, ServletException {
		int page = 1; //default page1
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Interview> interviews = intvDAO.searchByPage(page);

		if (interviews != null) {
			request.setAttribute("interviews", interviews);
			request.setAttribute("pageCount", intvDAO.getPageCount());
			getServletContext().getRequestDispatcher("/IntvDashBoard.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		intvDAO.closeConn();
	}
	
	private void processDelete(HttpServletRequest request, HttpServletResponse response, IntvDAO intvDAO)
			throws SQLException, IOException, ParseException, ServletException {
		int deleteId = Integer.parseInt(request.getParameter("DeleteId"));

		if (intvDAO.deleteIntv(deleteId)) {
			System.out.println("DeleteId:" + deleteId + " Delete success");
			response.sendRedirect("./InterViewServletDS");
		} else {
			System.out.println("DeleteId:" + deleteId + " Delete fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		intvDAO.closeConn();
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, IntvDAO intvDAO,
			Interview intv) throws SQLException, IOException, ParseException, ServletException {
		if (intvDAO.createIntv(intv)) {
			System.out.println("Create success");
			response.sendRedirect("./InterViewServletDS");
		} else {
			System.out.println("Create fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		intvDAO.closeConn();
	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, IntvDAO intvDAO,
			Interview intv) throws SQLException, IOException, ParseException, ServletException {
		if (intvDAO.updateInterview(intv)) {
			System.out.println("UpdateId:" + intv.getCv_No() + " Update success");
			response.sendRedirect("./InterViewServletDS");
		} else {
			System.out.println("UpdateId:" + intv.getCv_No() + " Update fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		intvDAO.closeConn();
	}

	
 

}