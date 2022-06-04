

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Job;
import Service.JobService;
import Service.Impl.JobServiceImpl;
@WebServlet(name = "JobController", urlPatterns = { "/new", "/insert", "/delete", "/edit","/update"})
		
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JobController() {
        super();
    }
    public void init() throws ServletException{}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getServletPath();
			Job job  = new Job();
            switch (action) {
                case "/new":
                    showNewForm(request, response, job);
                    break;
                case "/insert":
                    insertServlet(request, response, job);
                    break;
                case "/delete":
                    deleteJobServlet(request, response, job);
                    break;
                case "/edit":
                    showEditForm(request, response, job);
                    break;
                case "/update":
                    updateJobServlet(request, response, job);
                    break;
                default:
                    allJobs(request, response, job);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
	private void allJobs(HttpServletRequest request, HttpServletResponse response, Job job) throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		JobService jobService = new JobServiceImpl();
		List<Job> jobs = jobService.getAllJobs();
		request.setAttribute("jobs", jobs);
		request.getRequestDispatcher("AllJobs.jsp").forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response, Job job) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("JobDashBoard.jsp");
        dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response, Job job) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		JobService jobService = new JobServiceImpl();
		Job existingJob = jobService.getJobByJobID(job_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JobDashBoard.jsp");
		request.setAttribute("job", existingJob);
		dispatcher.forward(request, response);
	}
	
	private void insertServlet(HttpServletRequest request, HttpServletResponse response, Job job) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("TITLE").trim();
        String JOB_DESCRIPTION = request.getParameter("JOB_DESCRIPTION").trim();
        String QUALIFICATION = request.getParameter("QUALIFICATION").trim();
        String sid = request.getParameter("REQUIRED_NUMBER").trim();
        int REQUIRED_NUMBER = Integer.parseInt(sid);
        String SALARY = request.getParameter("SALARY").trim();
        String COMP_ID = request.getParameter("COMP_ID");
        
        job.setTitle(title);
        job.setJob_description(JOB_DESCRIPTION);
        job.setQualification(QUALIFICATION);
        job.setRequired_number(REQUIRED_NUMBER);
        job.setSalary(SALARY);
        job.setComp_id(COMP_ID);
        JobService jobService = new JobServiceImpl();
        jobService.save(job);
        response.sendRedirect("AllJobs.jsp");
		
	}
	
	private void updateJobServlet(HttpServletRequest request, HttpServletResponse response, Job job) throws ServletException, IOException{
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
        
        job.setJob_id(job_id);
        job.setTitle(title);
        job.setJob_description(JOB_DESCRIPTION);
        job.setQualification(QUALIFICATION);
        job.setRequired_number(REQUIRED_NUMBER);
        job.setSalary(SALARY);
        job.setComp_id(COMP_ID);
        JobService jobService = new JobServiceImpl();
        jobService.update(job);
        request.setAttribute("job", job);
        response.sendRedirect("AllJobs.jsp");
		
	}

	private void deleteJobServlet(HttpServletRequest request, HttpServletResponse response, Job job) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String sid = request.getParameter("job_id");
		int job_id = Integer.parseInt(sid);
		JobService jobService = new JobServiceImpl();
		jobService.delete(job_id);
		response.sendRedirect("AllJobs.jsp");
	}

}
