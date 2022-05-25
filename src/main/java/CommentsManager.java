
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.CommentDAO;
import Bean.Comment;

@WebServlet(name = "CommentsManager", urlPatterns = { "/CommentsManager", "/CommentEdit", "/CommentNew", "/CommentInsert",
		"/CommentDelete", "/CommentUpdate", "/CommentConfirm", "/CommentDetail" })
public class CommentsManager extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void init() throws ServletException {
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/FindJobDB");

		} catch (NamingException ne) {
			throw new ServletException(ne);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		try {
			conn = ds.getConnection();
			CommentDAO commentDAO = new CommentDAO(conn);

			String action = request.getServletPath();

			switch (action) {
			case "/CommentNew":
				showNewForm(request, response, commentDAO);
				break;
			case "/CommentInsert":
				insertComment(request, response, commentDAO);
				break;
			case "/CommentDelete":
				deleteComment(request, response, commentDAO);
				break;
			case "/CommentEdit":
				showEditForm(request, response, commentDAO);
				break;
			case "/CommentUpdate":
				updateComment(request, response, commentDAO);
				break;
			case "/CommentConfirm":
				showConfirmForm(request, response, commentDAO);
				break;
			case "/CommentDetail":
				showDetailForm(request, response, commentDAO);
				break;
			default:
				listComment(request, response, commentDAO);
				break;
			}

		} catch (SQLException ex) {
			System.out.println("Database Connection Error");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		catch (NamingException ne) {
//			System.out.println("Naming Service Lookup Exception");
//		} 
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Connection Pool Error!");
				}
		}
	}

	private void listComment(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, IOException, ServletException {
		List<Comment> listComment = commentDAO.listAllComment();
		request.getSession(true).setAttribute("listComment", listComment);
		request.getRequestDispatcher("CommentsDashBoard.jsp").forward(request, response);
		commentDAO.closeConn();
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws ServletException, IOException, SQLException {
		request.getSession(true).invalidate();
		request.getRequestDispatcher("CommentForm.jsp").forward(request, response);
		commentDAO.closeConn();
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").trim());
		Comment existingComment = commentDAO.getComment(id);
		request.getSession(true).setAttribute("comment", existingComment);
		request.getRequestDispatcher("CommentForm.jsp").forward(request, response);
		commentDAO.closeConn();
	}
	
	private void showDetailForm(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").trim());
		Comment Comment = commentDAO.getComment(id);
		request.getSession(true).setAttribute("comment", Comment);
		request.getRequestDispatcher("CommentConfirm.jsp").forward(request, response);
		commentDAO.closeConn();
	}
	
	private void insertComment(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, IOException, ParseException, ServletException {
		Comment comment = (Comment) request.getSession(true).getAttribute("comment");
		commentDAO.insertComment(comment);
		response.sendRedirect("./CommentsManager");
		commentDAO.closeConn();
	
	}
	private void updateComment(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, IOException, ParseException, ServletException {

		int share_id = Integer.parseInt(request.getParameter("share_id").trim());
		Date ref_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ref_time").trim()).getTime());
		String comp_name = request.getParameter("comp_name").trim();
		int comp_score = Integer.parseInt(request.getParameter("comp_score").trim());
		String job_name = request.getParameter("job_name").trim();
		int job_score = Integer.parseInt(request.getParameter("job_score").trim());
		String job_description = request.getParameter("job_description").trim();
		int std_hour = Integer.parseInt(request.getParameter("std_hour").trim());
		int real_hour = Integer.parseInt(request.getParameter("real_hour").trim());
		int over_freq = Integer.parseInt(request.getParameter("over_freq").trim());
		float seniority = Float.parseFloat(request.getParameter("seniority").trim());
		float total_seniority = Float.parseFloat(request.getParameter("total_seniority").trim());
		int monthly_salary = Integer.parseInt(request.getParameter("monthly_salary").trim());
		int yearly_salary = Integer.parseInt(request.getParameter("yearly_salary").trim());
		int bonus_count = Integer.parseInt(request.getParameter("bonus_count").trim());
		String share = request.getParameter("share").trim();
		String user_id = request.getParameter("user_id").trim();

		Comment comment = new Comment(share_id, ref_time, comp_name, comp_score, job_name, job_score, job_description,
				std_hour, real_hour, over_freq, seniority, total_seniority, monthly_salary, yearly_salary, bonus_count,
				share, user_id);

		commentDAO.updateComment(comment);
		request.getSession(true).setAttribute("comment", comment);
		response.sendRedirect("./CommentsManager");
		commentDAO.closeConn();

	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id").trim());
		Comment comment = new Comment(id);
		commentDAO.deleteComment(comment);
		request.getRequestDispatcher("CommentsManager").forward(request, response);
		commentDAO.closeConn();
	}
	
	private void showConfirmForm(HttpServletRequest request, HttpServletResponse response, CommentDAO commentDAO)
			throws ServletException, IOException, SQLException, ParseException {
		Date ref_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ref_time").trim()).getTime());
		String comp_name = request.getParameter("comp_name").trim();
		int comp_score = Integer.parseInt(request.getParameter("comp_score").trim());
		String job_name = request.getParameter("job_name").trim();
		int job_score = Integer.parseInt(request.getParameter("job_score").trim());
		String job_description = request.getParameter("job_description").trim();
		int std_hour = Integer.parseInt(request.getParameter("std_hour").trim());
		int real_hour = Integer.parseInt(request.getParameter("real_hour").trim());
		int over_freq = Integer.parseInt(request.getParameter("over_freq").trim());
		float seniority = Float.parseFloat(request.getParameter("seniority").trim());
		float total_seniority = Float.parseFloat(request.getParameter("total_seniority").trim());
		int monthly_salary = Integer.parseInt(request.getParameter("monthly_salary").trim());
		int yearly_salary = Integer.parseInt(request.getParameter("yearly_salary").trim());
		int bonus_count = Integer.parseInt(request.getParameter("bonus_count").trim());
		String share = request.getParameter("share").trim();
		String user_id = request.getParameter("user_id").trim();

		Comment comment = new Comment(ref_time, comp_name, comp_score, job_name, job_score, job_description, std_hour,
				real_hour, over_freq, seniority, total_seniority, monthly_salary, yearly_salary, bonus_count, share,
				user_id);
		request.getSession(true).setAttribute("comment", comment);
		request.getRequestDispatcher("CommentConfirm.jsp").forward(request, response);
		commentDAO.closeConn();
	}

}
