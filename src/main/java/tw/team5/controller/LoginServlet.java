package tw.team5.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.team5.bean.Member;
import tw.team5.dao.MemberDao;
import tw.team5.dao.impl.MemberDaoImpl;
import tw.team5.service.MemberService;
import tw.team5.service.impl.MemberServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 後面加上 , String account, String password
		String ACCOUNT = request.getParameter("account");// 取得用戶名
		String PASSWORD = request.getParameter("password");// 取得密碼

		if (ACCOUNT.equals("admin") && PASSWORD.equals("12345")) {
			response.sendRedirect("./MemberServlet");
		}else {
			MemberService memberService = new MemberServiceImpl();
			Member account = memberService.checkAccount(ACCOUNT);  //從memberServiceImpl回來，只會是Member或null

			if (account == null) {
				response.sendRedirect("./Login.jsp");
			} else if (ACCOUNT.equals(account.getUserid()) && PASSWORD.equals(account.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("login", ACCOUNT); // login token，傳到其他頁面，用以標示這個帳戶已登入
				System.out.println("目前登入的Session ID為" + session.getId()); // 取得sessionID
				response.sendRedirect("./MemberServlet");
			} else {
				response.sendRedirect("./Login.jsp");
			}
		}
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
