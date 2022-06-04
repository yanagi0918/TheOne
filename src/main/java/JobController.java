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
@WebServlet(name = "JobController", urlPatterns = { "/JobController", "/insert", "/delete", "/edit","/update"})
		
public class JobController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    public void init() throws ServletException{
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			request.setCharacterEncoding("UTF-8");
			
			String action = request.getServletPath();
			System.out.println(action);
            switch (action) {
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
                	System.out.println("123");
                    allJobs(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	private void allJobs(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		JobService jobService = new JobServiceImpl();
		List<Job> allJobs = jobService.getAllJobs();
		if(allJobs !=null) {
			System.out.println("get");
			request.setAttribute("allJobs", allJobs);
			request.getRequestDispatcher("AllJobs.jsp").forward(request, response);
		}else {
			System.out.println("none");
		}
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		JobService jobService = new JobServiceImpl();
		Job existingJob = jobService.getJobByJobID(job_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JobDashBoard.jsp");
		request.setAttribute("job", existingJob);
		dispatcher.forward(request, response);
	}
	
	private void insertServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title").trim();
        String JOB_DESCRIPTION = request.getParameter("job_description").trim();
        String QUALIFICATION = request.getParameter("qualification").trim();
        String sid = request.getParameter("required_number").trim();
        int REQUIRED_NUMBER = Integer.parseInt(sid);
        String SALARY = request.getParameter("salary").trim();
        String COMP_ID = request.getParameter("comp_id");
        
        Job job = new Job(title, JOB_DESCRIPTION, QUALIFICATION, REQUIRED_NUMBER, SALARY, COMP_ID);
        JobService jobService = new JobServiceImpl();
        jobService.save(job);
        response.sendRedirect("AllJobs.jsp");
		
	}
	
	private void updateJobServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String sid = request.getParameter("job_id");
        int job_id=Integer.parseInt(sid);
        String title = request.getParameter("TITLE").trim();
        String JOB_DESCRIPTION = request.getParameter("JOB_DESCRIPTION").trim();
        String QUALIFICATION = request.getParameter("QUALIFICATION").trim();
        String sid2 = request.getParameter("REQUIRED_NUMBER").trim();
        int REQUIRED_NUMBER = Integer.parseInt(sid2);
        String SALARY =request.getParameter("SALARY").trim();
        String COMP_ID = request.getParameter("COMP_ID");
        
        Job job = new Job(job_id, title, JOB_DESCRIPTION, QUALIFICATION, REQUIRED_NUMBER, SALARY, COMP_ID);
        JobService jobService = new JobServiceImpl();
        jobService.update(job);
        request.setAttribute("job", job);
        response.sendRedirect("AllJobs.jsp");
		
	}

	private void deleteJobServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		JobService jobService = new JobServiceImpl();
		String sid = request.getParameter("job_id");
		int job_id = Integer.parseInt(sid);
		jobService.delete(job_id);
		response.sendRedirect("AllJobs.jsp");
	}

}
