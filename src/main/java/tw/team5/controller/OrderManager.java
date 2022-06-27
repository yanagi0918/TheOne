//package tw.team5.controller;
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import tw.team5.bean.Order;
//import tw.team5.service.OrderService;
//import tw.team5.service.impl.OrderServiceImpl;
//
//@WebServlet("/OrderManager")
//public class OrderManager extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			request.setCharacterEncoding("UTF-8");
//			//get connection to DAO
////			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
////			OrderDao orderDAO = new OrderDao(ds.getConnection());
//
//			if (request.getParameter("UpdateId") != null) { //get UpdateId & deliver order to update page
//				OrderService orderService = new OrderServiceImpl();
//				Order orderForUpdate = orderService.getOrder(Integer.parseInt(request.getParameter("UpdateId")));
//				if (orderForUpdate == null) { //check adId exist
//					getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
//				}
//				request.setAttribute("orderForUpdate", orderForUpdate);
//				getServletContext().getRequestDispatcher("/OrderUpdate.jsp").forward(request, response);
//			} else if (request.getParameter("DeleteId") != null) { //DeleteId exist & do delete
//				int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
//				processDelete(request, response, deleteId);
//			} else {
//				showData(request, response);
//			}
//		} catch (SQLException | ParseException e) {
//			e.printStackTrace();
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		try {
//			request.setCharacterEncoding("UTF-8");
//			// get connection to DAO
////			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FindJobDB");
////			OrderDao orderDAO = new OrderDao(ds.getConnection());
//			// put data into bean
//			Order order = new Order();
//			order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
//			order.setUserId(request.getParameter("userId"));
//			order.setProductId(request.getParameter("productId"));
//			order.setTotalPrice(Integer.parseInt(request.getParameter("totalPrice")));
//			Date orderDate = Date.valueOf(request.getParameter("orderDate"));
//			order.setOrderDate(orderDate);
//			order.setState(request.getParameter("state"));
//			
//			
//
//			if (order.getOrderId() == 0) { 
//				processCreate(request, response, order);
//			} else {
//				processUpdate(request, response, order);
//			}
//
//		} catch (SQLException | ParseException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void showData(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
////		List<Order> orders = orderDAO.getAllOrder();
//		OrderService orderService = new OrderServiceImpl();
//		List<Order> orders = orderService.getAllOrders();
//		if(orders == null) {
//			System.out.println("123");
//		}
//		request.setAttribute("orders", orders);
//		request.getRequestDispatcher("OrderDashBoard.jsp").forward(request, response);
//		
////		if (orders != null) {
////			request.setAttribute("orders", orders);
////			getServletContext().getRequestDispatcher("/OrderDashBoard.jsp").forward(request, response);
////		} else {
////			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
////		}
//	}
//
//	private void processDelete(HttpServletRequest request, HttpServletResponse response, int deleteId)
//			throws SQLException, IOException, ParseException, ServletException {
//		
//		OrderService orderService = new OrderServiceImpl();
//		orderService.deleteOrder(deleteId);
//		response.sendRedirect("./OrderManager");
//		
////		int deleteId = Integer.parseInt(request.getParameter("DeleteId"));
////
////		if (orderDAO.DeleteOrder(deleteId)) {
////			System.out.println("DeleteId:" + deleteId + " Delete success");
////			response.sendRedirect("./OrderManager");
////		} else {
////			System.out.println("DeleteId:" + deleteId + " Delete fail");
////			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
////		}
//	}
//
//
//	private void processCreate(HttpServletRequest request, HttpServletResponse response, Order order) throws SQLException, IOException, ParseException, ServletException {
//		OrderService orderService = new OrderServiceImpl();
//		orderService.save(order);
//		response.sendRedirect("./OrderManager");
//		
////		if (orderDAO.insertOrder(order)) {
////			System.out.println("Create success");
////			response.sendRedirect("./OrderManager");
////		} else {
////			System.out.println("Create fail");
////			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
////		}
//	}
//
//	
//	private void processUpdate(HttpServletRequest request, HttpServletResponse response, Order order) throws SQLException, IOException, ParseException, ServletException {
//
//		OrderService orderService = new OrderServiceImpl();
//		orderService.updateOrder(order);
//		response.sendRedirect("./OrderManager");
//		
////		if (orderDAO.updateOrder(order)) {
////			System.out.println("Update success");
////			response.sendRedirect("./OrderManager");
////		} else {
////			System.out.println("Update fail");
////			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
////		}
////	}
//
//
//	}
//}
