package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Bean.Order;

public class OrderDAO {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDAO(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order order) {

		boolean result = false;

		try {

			query = "insert into orders values(?,?,?,?,?)";

			pst = this.con.prepareStatement(query);
			pst.setString(1, order.getUserId());
			pst.setString(2, order.getProductId());
			pst.setInt(3, order.getTotalPrice());
			pst.setDate(4, order.getOrderDate());
			pst.setString(5, order.getState());
			pst.executeUpdate();
			result = true; 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean DeleteOrder(int deleteId) {

		boolean result = false;

		try {

			query = "DELETE FROM orders WHERE order_id = ?";

			pst = this.con.prepareStatement(query);
			pst.setInt(1, deleteId);
			pst.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Order> getAllOrder() {
		List<Order> list = new ArrayList<>();
		try {

			query = "SELECT * FROM orders";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setProductId(rs.getString("product_ID"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setState(rs.getString("state"));
				list.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}

	public Order get1Order(int orderId) {
		try {

			query = "SELECT * FROM orders WHERE order_id = ?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, orderId);
			rs = pst.executeQuery();
			Order order = new Order();
			if (rs.next()) {

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setProductId(rs.getString("product_ID"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setState(rs.getString("state"));
			}
			return order;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public boolean updateOrder(Order order) {
		try {

			query = "UPDATE orders " 
			+ "SET user_id = ?, " 
			+ "product_ID = ?, " 
			+ "total_price = ?, "
			+ "order_date = ?, "
			+ "state = ? "
			+ "WHERE order_id = ?";

			pst = this.con.prepareStatement(query);
			pst.setString(1, order.getUserId());
			pst.setString(2, order.getProductId());
			pst.setInt(3, order.getTotalPrice());
			pst.setDate(4, order.getOrderDate());
			pst.setString(5, order.getState());
			pst.setInt(6, order.getOrderId());
			pst.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
