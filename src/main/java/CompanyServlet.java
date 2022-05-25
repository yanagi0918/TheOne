
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Bean.Company;
import DAO.CompanyDAO;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		try {

			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/FindJobDB");
			conn = ds.getConnection();
			CompanyDAO companyDAO = new CompanyDAO(conn);
			
			// 如果dashboard傳回的updateid有值，先查出該id的所有內容，放進updatemember物件
			if(request.getParameter("UpdateId") != null) {
				Company UpdateCompany = companyDAO.findCompany(Integer.parseInt(request.getParameter("UpdateId")));
				if (UpdateCompany == null) {
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				}else {
					   request.setAttribute("UpdateCompany", UpdateCompany); // 把key跟value放進request中，並跳轉至update頁面
					   getServletContext().getRequestDispatcher("/CompanyUpdate.jsp").forward(request, response);
				}
			}else if (request.getParameter("DeleteId") != null) {
					  processDelete(request, response, companyDAO);
			}else {
					showData(request, response, companyDAO);
			}

		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 填入較多資料用doPost
		try {

			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/FindJobDB");
			conn = ds.getConnection();
			CompanyDAO companyDAO = new CompanyDAO(conn);

			// put data into bean
			Company company = new Company();
			company.setCompid(Integer.parseInt(request.getParameter("compid")));
			company.setCompwd(request.getParameter("compwd"));
			company.setCorpname(request.getParameter("corpname"));
			company.setOwner(request.getParameter("owner"));
			company.setIndustry(request.getParameter("industry"));
			company.setContact(request.getParameter("contact"));
			company.setComptele(request.getParameter("comptele"));
			company.setFax(request.getParameter("fax"));
			company.setCompaddress(request.getParameter("compaddress"));
			company.setEmpnumber(Integer.parseInt(request.getParameter("empnumber")));
			company.setWebsite(request.getParameter("website"));
			company.setCapital(request.getParameter("capital"));
			
			
			//
			

			if (request.getParameter("update123") != null ) {
				processUpdate(request, response, companyDAO, company);
	/*		}else if(companyDAO.findAll().contains(request.getParameter("compid"))) {
				getServletContext().getRequestDispatcher("/CompanyCreate.jsp").forward(request, response);*/
			}else{
				processCreate(request, response, companyDAO, company);
			}

		} catch (NamingException | SQLException | NullPointerException | NumberFormatException| ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response, CompanyDAO companyDAO)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		List<Company> company = companyDAO.findAll();

		if (company != null) {
			request.setAttribute("company", company); // 把company放進request，取名叫"company"，讓其他jsp或Servlet可用
			getServletContext().getRequestDispatcher("/CompanyDashBoard.jsp").forward(request, response);
		} else {
			System.out.println("該不會在這裡?");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		companyDAO.closeConn();
	}

	
	// CREATE
	private void processCreate(HttpServletRequest request, HttpServletResponse response, CompanyDAO companyDAO ,Company company)
			throws SQLException, IOException, ParseException, ServletException {
		
		//不能用物件中是否包含物件來檢查
		boolean CheckId= companyDAO.findCompid(company.getCompid());
		
		if (companyDAO.createCompany(company)) {
			response.sendRedirect("./CompanyServlet");
			
		}else if (CheckId) {
			System.out.println("重複的公司統編:" + String.valueOf(company.getCompid()));
			String message = "公司統編不可重複";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/CompanyCreate.jsp").forward(request, response);
			
		}else{
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			}
		
		companyDAO.closeConn();
		}
	
	

	// Update
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, CompanyDAO companyDAO, Company company)
			throws SQLException, IOException, ServletException {
		
		if (companyDAO.updateCompany(company)) { 
			response.sendRedirect("./CompanyServlet");
		} else {
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
		companyDAO.closeConn();
	}

	// DELETE
	private void processDelete(HttpServletRequest request, HttpServletResponse response, CompanyDAO companyDAO)
			throws SQLException, IOException {

		int DeleteId = Integer.parseInt(request.getParameter("DeleteId"));

		if (companyDAO.deleteCompany(DeleteId)) {
			response.sendRedirect("./CompanyServlet");
		}
		companyDAO.closeConn();
	}


	
	
}
