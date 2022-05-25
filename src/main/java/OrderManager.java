import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import DAO.OrderDAO;
import Bean.Order;

@WebServlet("/OrderManager")
public class OrderManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			//get connection to DAO
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
			OrderDAO orderDAO = new OrderDAO(ds.getConnection());

			if (request.getParameter("UpdateId") != null) { //get UpdateId & deliver event to update page
				Order orderForUpdate = orderDAO.get1Order(Integer.parseInt(request.getParameter("UpdateId")));
				if (orderForUpdate == null) { //check adId exist
					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				}
				request.setAttribute("orderForUpdate", orderForUpdate);
				getServletContext().getRequestDispatcher("/OrderUpdate.jsp").forward(request, response);
			} else if (request.getParameter("DeleteId") != null) { //DeleteId exist & do delete
				processDelete(request, response, orderDAO);
			} else { //Event's index
				showData(request, response, orderDAO);
			}
		} catch (NamingException | SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			// get connection to DAO
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
			OrderDAO orderDAO = new OrderDAO(ds.getConnection());
			// put data into bean
			Order order = new Order();
			order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
			order.setUserId(request.getParameter("userId"));
			order.setProductId(request.getParameter("productId"));
			order.setTotalPrice(Integer.parseInt(request.getParameter("totalPrice")));
			Date orderDate = Date.valueOf(request.getParameter("orderDate"));
			order.setOrderDate(orderDate);
			order.setState(request.getParameter("state"));
			
			

			if (order.getOrderId() == 0) { //EventCreate.jsp <input:hidden name="adId" value="0">
				processCreate(request, response, orderDAO, order);
			} else {
				processUpdate(request, response, orderDAO, order);
			}

		} catch (NamingException | SQLException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showData(HttpServletRequest request, HttpServletResponse response, OrderDAO orderDAO)
			throws SQLException, IOException, ServletException {
//		int page = 1; //default page1
//		if (request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		}
		List<Order> orders = orderDAO.getAllOrder();

		if (orders != null) {
			request.setAttribute("orders", orders);
//			request.setAttribute("pageCount", eventDAO.getPageCount());
			getServletContext().getRequestDispatcher("/OrderDashBoard.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
//		eventDAO.closeConn();
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response, OrderDAO orderDAO)
			throws SQLException, IOException, ParseException, ServletException {
		int deleteId = Integer.parseInt(request.getParameter("DeleteId"));

		if (orderDAO.DeleteOrder(deleteId)) {
			System.out.println("DeleteId:" + deleteId + " Delete success");
			response.sendRedirect("./OrderManager");
		} else {
			System.out.println("DeleteId:" + deleteId + " Delete fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}


	private void processCreate(HttpServletRequest request, HttpServletResponse response, OrderDAO orderDAO,
			Order order) throws SQLException, IOException, ParseException, ServletException {
		if (orderDAO.insertOrder(order)) {
			System.out.println("Create success");
			response.sendRedirect("./OrderManager");
		} else {
			System.out.println("Create fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}

	
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, OrderDAO orderDAO,
			Order order) throws SQLException, IOException, ParseException, ServletException {
		if (orderDAO.updateOrder(order)) {
			System.out.println("Update success");
			response.sendRedirect("./OrderManager");
		} else {
			System.out.println("Update fail");
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}


}
