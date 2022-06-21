package tw.team5.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team5.bean.Resume;
import tw.team5.service.ResumeService;
import tw.team5.service.impl.ResumeServiceImpl;

@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("UpdateId") != null) {
				ResumeService resumeService = new ResumeServiceImpl();
				Resume resumeForUpdate = resumeService.getResume(Integer.parseInt(request.getParameter("UpdateId")));
				if (resumeForUpdate == null) {
					request.getRequestDispatcher("404.jsp").forward(request, response);
				}
				request.setAttribute("resumeForUpdate", resumeForUpdate);
				request.getRequestDispatcher("UpdateResume.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) {
				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
				processDelete(request, response, deleteId);
			} else {
				showData(request, response);
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			Resume resume = new Resume();
			resume.setResume_id(Integer.parseInt(request.getParameter("resume_id")));
			resume.setEdu(request.getParameter("edu"));
			resume.setSchool(request.getParameter("school"));
			resume.setDept(request.getParameter("dept"));
			resume.setWork_exp(request.getParameter("work_exp"));
			resume.setSkills(request.getParameter("skills"));
			resume.setUser_id(request.getParameter("user_id"));
			
			

			if (resume.getResume_id() == 0) {
				processCreate(request, response, resume);
			} else {
				processUpdate(request, response, resume);
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		ResumeService resumeService = new ResumeServiceImpl();
		List<Resume> resumes = resumeService.getAllResumes();
		request.setAttribute("resumes", resumes);
		request.getRequestDispatcher("ResumeDashBoard.jsp").forward(request, response);
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
			throws SQLException, IOException, ParseException, ServletException {
		ResumeService resumeService = new ResumeServiceImpl();
		resumeService.deleteResume(deleteId);
		response.sendRedirect("./ResumeServlet");
	}

	private void processCreate(HttpServletRequest request, HttpServletResponse response, Resume resume)
			throws SQLException, IOException, ParseException, ServletException {
		ResumeService resumeService = new ResumeServiceImpl();
		resumeService.save(resume);
		response.sendRedirect("./ResumeServlet");
	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, Resume resume)
			throws SQLException, IOException, ParseException, ServletException {
		ResumeService resumeService = new ResumeServiceImpl();
		resumeService.updateResume(resume);
		response.sendRedirect("./ResumeServlet");
	}


}
