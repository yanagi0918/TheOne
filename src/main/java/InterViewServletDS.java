
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Interview;
import Service.InterviewService;
import Service.Impl.InterviewServiceImpl;

@WebServlet("/InterViewServletDS")
public class InterViewServletDS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("InterViewServletDS doGet start...");
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("UpdateId") != null) { // get UpdateId & deliver event to update page
				InterviewService interviewService = new InterviewServiceImpl();
				Interview intvForUpdate = interviewService
						.getInterview(Integer.parseInt(request.getParameter("UpdateId")));
				if (intvForUpdate == null) { // check cv_no exist
					request.getRequestDispatcher("/404.jsp").forward(request, response);
				}

				request.setAttribute("intvForUpdate", intvForUpdate);
				getServletContext().getRequestDispatcher("/IntvUpdate.jsp").forward(request, response);
			} else if (request.getParameter("ShowdateId") != null) { // get UpdateId & deliver event to update page
				InterviewService interviewService = new InterviewServiceImpl();
				Interview intvForShow = interviewService
						.getInterview(Integer.parseInt(request.getParameter("ShowdateId")));
				if (intvForShow == null) { // check cv_no exist
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				}

				request.setAttribute("intvForUpdate", intvForShow);
				getServletContext().getRequestDispatcher("/IntvShow.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) {
				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
				processDelete(request, response, deleteId);
			} else { // Event's index
				showData(request, response);
			}
		} catch (  SQLException | ParseException e) {
			e.printStackTrace();
		}
		System.out.println("InterViewServletDS doGet end...");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("InterViewServletDS doPost start...");
			request.setCharacterEncoding("UTF-8");
			// get connection to DAO
			
//			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
//			IntvDAO intvDAO = new IntvDAO(ds.getConnection());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			Interview interview = new Interview();
			interview.setCvNo(Integer.parseInt(request.getParameter("cvno")));
			interview.setIntTime(request.getParameter("intTime"));
			interview.setCompName(request.getParameter("compName"));
			interview.setJobName(request.getParameter("jobName"));
			interview.setOffer(request.getParameter("offer"));
			interview.setTest(request.getParameter("test"));
			interview.setQA(request.getParameter("qA"));
			interview.setShare(request.getParameter("share"));
			interview.setCompScore(Integer.parseInt(request.getParameter("compScore")));
			interview.setUserId(request.getParameter("userId"));
			interview.setCreatedTime(dtf.format(LocalDateTime.now()));

			if (interview.getCvNo() == 0) { // EventCreate.jsp <input:hidden name="Cv_No" value="0">

				processCreate(request, response,  interview);
			} else {
				processUpdate(request, response,  interview);
			}

		} catch (  SQLException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("InterViewServletDS doPost end...");
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("InterViewServletDS showData start...");
		InterviewService interviewService = new InterviewServiceImpl();
		List<Interview> interviews = interviewService.getAllInterviews();
		request.setAttribute("interviews", interviews);
		request.getRequestDispatcher("IntvDashBoard.jsp").forward(request, response);
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
			throws SQLException, IOException, ParseException, ServletException {
		InterviewService interviewService = new InterviewServiceImpl();
		interviewService.deleteInterview(deleteId);
		response.sendRedirect("./InterViewServletDS");

	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, Interview interview
			) throws SQLException, IOException, ParseException, ServletException {
		InterviewService interviewService = new InterviewServiceImpl();
		interviewService.save(interview);
		response.sendRedirect("./InterViewServletDS");
	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, Interview interview
			) throws SQLException, IOException, ParseException, ServletException {
		InterviewService interviewService = new InterviewServiceImpl();
		interviewService.updateInterview(interview);
		response.sendRedirect("./InterViewServletDS");
	}

}