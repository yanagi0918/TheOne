package tw.team5.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team5.bean.CommentBean;
import tw.team5.service.CommentService;
import tw.team5.service.impl.CommentServiceImpl;

@WebServlet(name = "CommentsManager", urlPatterns = { "/CommentsManager", "/CommentEdit", "/CommentNew", "/CommentInsert",
		"/CommentDelete", "/CommentUpdate", "/CommentConfirm", "/CommentDetail" })
public class CommentsManager extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void init() throws ServletException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getServletPath();
			CommentBean comment = new CommentBean();

			switch (action) {
			case "/CommentNew":
				showNewForm(request, response, comment);
				break;
			case "/CommentInsert":
				insertComment(request, response, comment);
				break;
			case "/CommentDelete":
				deleteComment(request, response, comment);
				break;
			case "/CommentEdit":
				showEditForm(request, response, comment);
				break;
			case "/CommentUpdate":
				updateComment(request, response, comment);
				break;
			case "/CommentConfirm":
				showConfirmForm(request, response, comment);
				break;
			case "/CommentDetail":
				showDetailForm(request, response, comment);
				break;
			default:
				listComment(request, response, comment);
				break;
			}

		} catch (SQLException ex) {
			System.out.println("Database Connection Error");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void listComment(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, IOException, ServletException {
		CommentService commentService = new CommentServiceImpl();
		List<CommentBean> listComment = commentService.getAllComments();
		request.getSession(true).setAttribute("listComment", listComment);
		request.getRequestDispatcher("CommentsDashBoard.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws ServletException, IOException, SQLException {
		request.getSession(true).invalidate();
		request.getRequestDispatcher("CommentForm.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, ServletException, IOException {
		CommentService commentService = new CommentServiceImpl();
		int id = Integer.parseInt(request.getParameter("id").trim());
		CommentBean existingComment = commentService.getCommentById(id);
		request.getSession(true).setAttribute("comment", existingComment);
		request.getRequestDispatcher("CommentForm.jsp").forward(request, response);
	}
	
	private void showDetailForm(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, ServletException, IOException {
		CommentService commentService = new CommentServiceImpl();
		int pk = Integer.parseInt(request.getParameter("id").trim());
		comment = commentService.getCommentById(pk);
		request.getSession(true).setAttribute("comment", comment);
		request.getRequestDispatcher("CommentConfirm.jsp").forward(request, response);
	}
	
	private void insertComment(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, IOException, ParseException, ServletException {
		CommentService commentService = new CommentServiceImpl();
		comment = (CommentBean) request.getSession(true).getAttribute("comment");
		commentService.save(comment);
		response.sendRedirect("./CommentsManager");
	
	}
	private void updateComment(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, IOException, ParseException, ServletException {

		int share_id = Integer.parseInt(request.getParameter("share_id").trim());
		Date ref_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ref_time").trim()).getTime());
		Date create_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("create_time").trim()).getTime());
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

		comment = new CommentBean(share_id, ref_time, create_time, comp_name, comp_score, job_name, job_score, job_description,
				std_hour, real_hour, over_freq, seniority, total_seniority, monthly_salary, yearly_salary, bonus_count,
				share, user_id);
		CommentService commentService = new CommentServiceImpl();
		commentService.updateComment(comment);
		request.getSession(true).setAttribute("comment", comment);
		response.sendRedirect("./CommentsManager");
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws SQLException, IOException, ServletException {
		CommentService commentService = new CommentServiceImpl();
		int id = Integer.parseInt(request.getParameter("id").trim());
		comment = new CommentBean(id);
		commentService.deleteComment(id);
		request.getRequestDispatcher("CommentsManager").forward(request, response);
	}
	
	private void showConfirmForm(HttpServletRequest request, HttpServletResponse response, CommentBean comment)
			throws ServletException, IOException, SQLException, ParseException {
		Date ref_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ref_time").trim()).getTime());
		Date create_time = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("create_time").trim()).getTime());
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

		comment = new CommentBean(ref_time, create_time, comp_name, comp_score, job_name, job_score, job_description, std_hour,
				real_hour, over_freq, seniority, total_seniority, monthly_salary, yearly_salary, bonus_count, share,
				user_id);
		request.getSession(true).setAttribute("comment", comment);
		request.getRequestDispatcher("CommentConfirm.jsp").forward(request, response);
	}

}
