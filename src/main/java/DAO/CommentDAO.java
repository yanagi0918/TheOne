package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Bean.Comment;

public class CommentDAO   {
	private Connection conn;

	public CommentDAO(Connection conn) {
		this.conn = conn;
	}

	public List<Comment> listAllComment() {
		List<Comment> listComment = new ArrayList<>();
		String sql = "SELECT * FROM COMMENTS ORDER BY share_id";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int share_id = rs.getInt("share_id");
				java.sql.Date ref_time = rs.getDate("ref_time");
				//String ref_time = rs.getString("ref_time");
				String comp_name = rs.getString("comp_name");
				int comp_score = rs.getInt("comp_score");
				String job_name = rs.getString("job_name");
				int job_score = rs.getInt("job_score");
				String job_description = rs.getString("job_description");
				int std_hour = rs.getInt("std_hour");
				int real_hour = rs.getInt("real_hour");
				int over_freq = rs.getInt("over_freq");
				float seniority = rs.getFloat("seniority");
				float total_seniority = rs.getFloat("total_seniority");
				int monthly_salary = rs.getInt("monthly_salary");
				int yearly_salary = rs.getInt("yearly_salary");
				int bonus_count = rs.getInt("bonus_count");
				String share = rs.getString("share");
				String user_id = rs.getString("user_id");

				Comment comment = new Comment(share_id, ref_time, comp_name, comp_score, job_name, job_score,
						job_description, std_hour, real_hour, over_freq, seniority, total_seniority, monthly_salary,
						yearly_salary, bonus_count, share, user_id);
				listComment.add(comment);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComment;

	}

	public boolean deleteComment(Comment comment) throws SQLException {
		String sql = "DELETE FROM COMMENTS WHERE share_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, comment.getShare_id());

		boolean rowDeleted = pstmt.executeUpdate() > 0;
		pstmt.close();
		return rowDeleted;
	}

	public boolean insertComment(Comment comment) throws SQLException {
		String sql = "INSERT INTO COMMENTS(ref_time,comp_name,comp_score,job_name,job_score,job_description,std_hour,real_hour,over_freq,seniority,total_seniority,monthly_salary,yearly_salary,bonus_count,share,user_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, comment.getRef_time());
		pstmt.setString(2, comment.getComp_name());
		pstmt.setInt(3, comment.getComp_score());
		pstmt.setString(4, comment.getJob_name());
		pstmt.setInt(5, comment.getJob_score());
		pstmt.setString(6, comment.getJob_description());
		pstmt.setInt(7, comment.getStd_hour());
		pstmt.setInt(8, comment.getReal_hour());
		pstmt.setInt(9, comment.getOver_freq());
		pstmt.setFloat(10, comment.getSeniority());
		pstmt.setFloat(11, comment.getTotal_seniority());
		pstmt.setInt(12, comment.getMonthly_salary());
		pstmt.setInt(13, comment.getYearly_salary());
		pstmt.setInt(14, comment.getBonus_count());
		pstmt.setString(15, comment.getShare());
		pstmt.setString(16, comment.getUser_id());
		
		boolean rowInserted = pstmt.executeUpdate() > 0;
		pstmt.close();
		return rowInserted;
	}

	// 搜尋特定ID
	public Comment getComment(int id) throws SQLException {
		String sql = "SELECT * FROM COMMENTS WHERE share_id =?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Comment comment = new Comment();

		if (rs.next()) {
			java.sql.Date ref_time = rs.getDate("ref_time");
			String comp_name = rs.getString("comp_name");
			int comp_score = rs.getInt("comp_score");
			String job_name = rs.getString("job_name");
			int job_score = rs.getInt("job_score");
			String job_description = rs.getString("job_description");
			int std_hour = rs.getInt("std_hour");
			int real_hour = rs.getInt("real_hour");
			int over_freq = rs.getInt("over_freq");
			float seniority = rs.getFloat("seniority");
			float total_seniority = rs.getFloat("total_seniority");
			int monthly_salary = rs.getInt("monthly_salary");
			int yearly_salary = rs.getInt("yearly_salary");
			int bonus_count = rs.getInt("bonus_count");
			String share = rs.getString("share");
			String user_id = rs.getString("user_id");

			comment = new Comment(id, ref_time, comp_name, comp_score, job_name, job_score, job_description, std_hour,
					real_hour, over_freq, seniority, total_seniority, monthly_salary, yearly_salary, bonus_count, share,
					user_id);
		}

		rs.close();
		stmt.close();

		return comment;

	}

	public boolean updateComment(Comment comment) throws SQLException {
		String sql = "UPDATE COMMENTS SET "
				+ "ref_time=?, comp_name=?, comp_score=?, job_name=?, job_score=?, job_description=?, "
				+ "std_hour=?, real_hour=?, over_freq=?, seniority=?, total_seniority=?, monthly_salary=?, "
				+ "yearly_salary=?, bonus_count=?, share=?, user_id=? WHERE share_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, comment.getRef_time());
		pstmt.setString(2, comment.getComp_name());
		pstmt.setInt(3, comment.getComp_score());
		pstmt.setString(4, comment.getJob_name());
		pstmt.setInt(5, comment.getJob_score());
		pstmt.setString(6, comment.getJob_description());
		pstmt.setInt(7, comment.getStd_hour());
		pstmt.setInt(8, comment.getReal_hour());
		pstmt.setInt(9, comment.getOver_freq());
		pstmt.setFloat(10, comment.getSeniority());
		pstmt.setFloat(11, comment.getTotal_seniority());
		pstmt.setInt(12, comment.getMonthly_salary());
		pstmt.setInt(13, comment.getYearly_salary());
		pstmt.setInt(14, comment.getBonus_count());
		pstmt.setString(15, comment.getShare());
		pstmt.setString(16, comment.getUser_id());
		pstmt.setInt(17, comment.getShare_id());

		boolean rowUpdated = pstmt.executeUpdate() > 0;
		pstmt.close();
		return rowUpdated;
	}
	
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
