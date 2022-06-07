
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Company;
import Service.CompanyService;
import Service.Impl.CompanyServiceImpl;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			if(request.getParameter("UpdateId") != null) {
				CompanyService companyService = new CompanyServiceImpl();
				Company companyForUpdate = companyService.getCompany(Integer.parseInt(request.getParameter("UpdateId")));
				if (companyForUpdate == null) {
					request.getRequestDispatcher("CompanyUpdate.jsp").forward(request, response);
				}
				request.setAttribute("companyForUpdate", companyForUpdate);
				request.getRequestDispatcher("/CompanyUpdate.jsp").forward(request, response);
				
			}else if (request.getParameter("DeleteId") != null) {
				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
				processDelete(request, response, deleteId);
				
			}else if (request.getParameter("detailForm") != null) {
				processShowDetail(request, response);
			}else {
				showData(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			Company company = new Company();
			company.setComppk(Integer.parseInt(request.getParameter("comppk")));
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
			
			if (company.getComppk() == 0) {
				processCreate(request, response,company);
			}else{
				processUpdate(request, response,company);
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		CompanyService companyService = new CompanyServiceImpl();
		List<Company> companies = companyService.getAllCompanies();
		if (companies != null) {
			System.out.println("get");
		}
			request.setAttribute("companies", companies);
			request.getRequestDispatcher("CompanyDashBoard.jsp").forward(request, response);
	}

	
	private void processCreate(HttpServletRequest request, HttpServletResponse response,Company company)
			throws SQLException, IOException, ParseException, ServletException {
		CompanyService companyService = new CompanyServiceImpl();
		//設定輸入錯誤
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("error", errorMsg);
		//讀取資料
		int compid = (Integer.parseInt(request.getParameter("compid")));
		
		//判斷帳號是否重複
		if(companyService.isDup(compid)) {
			errorMsg.put("compid", "帳號(統編)重複，請重新輸入新帳號");
			request.getRequestDispatcher("CompanyCreate.jsp").forward(request,response);
			return;
		}else {
			companyService.save(company);
			response.sendRedirect("./CompanyServlet");
		}
		
	}
	
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, Company company)
			throws SQLException, IOException, ParseException, ServletException {
		CompanyService companyService = new CompanyServiceImpl();
		companyService.updateCompany(company);
		response.sendRedirect("./CompanyServlet");
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
			throws SQLException, IOException {
		CompanyService companyService = new CompanyServiceImpl();
		companyService.deleteCompany(deleteId);
		response.sendRedirect("./CompanyServlet");
	}

	private void processShowDetail(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException{
		Company company = new Company();		
		CompanyService companyService = new CompanyServiceImpl();
		int comppk = Integer.parseInt(request.getParameter("detailForm"));
		company=companyService.getCompany(comppk);
		request.setAttribute("company", company);
			request.getRequestDispatcher("CompanyDetail.jsp").forward(request, response);
	}
	
}
