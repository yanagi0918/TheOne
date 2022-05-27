

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.JobDao;
import Bean.Job;
@WebServlet(name = "JobController", urlPatterns = { "/new", "/insert", "/delete", "/edit","/update"})
		
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JobController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			//test123
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertServlet(request, response);
                    break;
                case "/delete":
                    deleteJobServlet(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateJobServlet(request, response);
                    break;
                default:
                    allJobs(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
	private void allJobs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Job> allJobs = JobDao.getAllJobs();
		request.setAttribute("allJobs", allJobs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AllJobs.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("JobDashBoard.jsp");
        dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		Job existingJob = JobDao.getJobByJobID(job_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JobDashBoard.jsp");
		request.setAttribute("job", existingJob);
		dispatcher.forward(request, response);
	}
	
	private void insertServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("TITLE").trim();
        String JOB_DESCRIPTION = request.getParameter("JOB_DESCRIPTION").trim();
        String QUALIFICATION = request.getParameter("QUALIFICATION").trim();
        String sid = request.getParameter("REQUIRED_NUMBER").trim();
        int REQUIRED_NUMBER = Integer.parseInt(sid);
        String SALARY = request.getParameter("SALARY").trim();
        String COMP_ID = request.getParameter("COMP_ID");
        
        Job job = new Job();
        job.setTitle(title);
        job.setJob_description(JOB_DESCRIPTION);
        job.setQualification(QUALIFICATION);
        job.setRequired_number(REQUIRED_NUMBER);
        job.setSalary(SALARY);
        job.setComp_id(COMP_ID);
        JobDao.insert(job);
        response.sendRedirect("AllJobs.jsp");
		
	}
	
	private void updateJobServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sid = request.getParameter("job_id");
        int job_id=Integer.parseInt(sid);
        String title = request.getParameter("TITLE").trim();
        String JOB_DESCRIPTION = request.getParameter("JOB_DESCRIPTION").trim();
        String QUALIFICATION = request.getParameter("QUALIFICATION").trim();
        String sid2 = request.getParameter("REQUIRED_NUMBER").trim();
        int REQUIRED_NUMBER = Integer.parseInt(sid2);
        String SALARY =request.getParameter("SALARY").trim();
        String COMP_ID = request.getParameter("COMP_ID");
        
        Job job = new Job();
        job.setJob_id(job_id);
        job.setTitle(title);
        job.setJob_description(JOB_DESCRIPTION);
        job.setQualification(QUALIFICATION);
        job.setRequired_number(REQUIRED_NUMBER);
        job.setSalary(SALARY);
        job.setComp_id(COMP_ID);
        JobDao.update(job);
        response.sendRedirect("AllJobs.jsp");
		
	}

	private void deleteJobServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sid = request.getParameter("job_id");
		int job_id = Integer.parseInt(sid);
		JobDao.delete(job_id);
		response.sendRedirect("AllJobs.jsp");
	}

}
