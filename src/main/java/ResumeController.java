/*

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.JobDao;
import DAO.ResumeDao;
import Bean.Job;
import Bean.Resume;

@WebServlet(name = "ResumeController", urlPatterns = { "/newResume", "/insertResume", "/deleteResume", "/editResume","/updateResume"})
public class ResumeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ResumeController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
            switch (action) {
                case "/newResume":
                    showNewForm(request, response);
                    break;
                case "/insertResume":
                    insertResumeServlet(request, response);
                    break;
                case "/deleteResume":
                    deleteResumeServlet(request, response);
                    break;
                case "/editResume":
                    showEditForm(request, response);
                    break;
                case "/updateResume":
                    updateResumeServlet(request, response);
                    break;
                default:
                    allResumes(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("ResumeDashBoard.jsp");
        dispatcher.forward(request, response);
		
	}
	private void insertResumeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String EDU = request.getParameter("EDU").trim();
		String SCHOOL = request.getParameter("SCHOOL").trim();
		String DEPT = request.getParameter("DEPT").trim();
		String WORK_EXP = request.getParameter("WORK_EXP");
		String SKILLS = request.getParameter("SKILLS");
		String USER_ID = request.getParameter("USER_ID");
		
		Resume resume = new Resume(EDU,SCHOOL,DEPT,WORK_EXP,SKILLS,USER_ID);
		ResumeDao.insert(resume);
		response.sendRedirect("AllResumes.jsp");
		
	}
	private void deleteResumeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int resume_id = Integer.parseInt(request.getParameter("resume_id"));
		ResumeDao.delete(resume_id);
		response.sendRedirect("AllResumes.jsp");
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int resume_id = Integer.parseInt(request.getParameter("resume_id"));
		Job existingJob = JobDao.getJobByJobID(resume_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ResumeDashBoard.jsp");
		request.setAttribute("resume", existingJob);
		dispatcher.forward(request, response);
	}
	private void updateResumeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int RESUME_ID=Integer.parseInt(request.getParameter("RESUME_ID"));
        String EDU = request.getParameter("EDU").trim();
		String SCHOOL = request.getParameter("SCHOOL").trim();
		String DEPT = request.getParameter("DEPT").trim();
		String WORK_EXP = request.getParameter("WORK_EXP");
		String SKILLS = request.getParameter("SKILLS");
		String USER_ID = request.getParameter("USER_ID").trim();
        
        Resume resume = new Resume(RESUME_ID, EDU, SCHOOL, DEPT, WORK_EXP, SKILLS, USER_ID);
        ResumeDao.update(resume);
        response.sendRedirect("AllResumes.jsp");
		
	}
	private void allResumes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Resume> allResumes = ResumeDao.getAllResumes();
		request.setAttribute("allResumes", allResumes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AllResumes.jsp");
		dispatcher.forward(request, response);
	}

}
*/