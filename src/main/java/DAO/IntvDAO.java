package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Interview;



public class IntvDAO {
	private Connection conn;

	private static final String GET_ONE_SQL = "SELECT * FROM interview WHERE cv_no = ?";
	private static final String GET_ALL_SQL = "SELECT * FROM interview";
	private static final String CRTAE_SQL = "INSERT INTO interview VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE interview " 
			+ "SET created_Time = ?, "
     	   	+ "int_Time =?, "
     	   	+ "comp_Name = ?, "
     	   	+ "job_Name =?, "
     	   	+ "offer =?, "
     	   	+ "test =?, "
     	   	+ "language =?, "
     	   	+ "qA =?, "
     	   	+ "share =?, "
     	   	+ "int_Score =?, "
     	   	+ "comp_Score =?, "
     	   	+ "user_id =? "
     	    + "WHERE cv_No =?";
	private static final String DELETE_SQL = "DELETE FROM interview WHERE cv_no = ?";
	private static final String GET_BY_PAGE_SQL = "Select * From interview " 
			+ "Order By cv_no " 
			+ "Offset ? Rows "
			+ "Fetch Next 10 Rows Only;";
	private static final String GET_COUNT_SQL = "SELECT COUNT(*) 'counts' FROM interview;";

	public IntvDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean createIntv(Interview interviewdate) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(CRTAE_SQL);
			pstmt.setTimestamp(1, interviewdate.getCreated_Time());
			pstmt.setDate(2, interviewdate.getInt_Time());
			pstmt.setString(3, interviewdate.getComp_Name());
			pstmt.setString(4, interviewdate.getJob_Name());
			pstmt.setString(5, interviewdate.getOffer());
			pstmt.setString(6, interviewdate.getTest());
			pstmt.setString(7, interviewdate.getLanguage());
			pstmt.setString(8, interviewdate.getQA());
			pstmt.setString(9, interviewdate.getShare());
			pstmt.setInt(10, interviewdate.getInt_Score());
			pstmt.setInt(11, interviewdate.getComp_Score());
			pstmt.setString(12, interviewdate.getUSER_ID());
			int createCount = pstmt.executeUpdate();
			pstmt.close();
			if (createCount > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean updateInterview(Interview interviewdate) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setTimestamp(1, interviewdate.getCreated_Time());
			pstmt.setDate(2, interviewdate.getInt_Time());
			pstmt.setString(3, interviewdate.getComp_Name());
			pstmt.setString(4, interviewdate.getJob_Name());
			pstmt.setString(5, interviewdate.getOffer());
			pstmt.setString(6, interviewdate.getTest());
			pstmt.setString(7, interviewdate.getLanguage());
			pstmt.setString(8, interviewdate.getQA());
			pstmt.setString(9, interviewdate.getShare());
			pstmt.setInt(10, interviewdate.getInt_Score());
			pstmt.setInt(11, interviewdate.getComp_Score());
			pstmt.setString(12, interviewdate.getUSER_ID());
			pstmt.setInt(13, interviewdate.getCv_No());
			int updateCount = pstmt.executeUpdate();
			pstmt.close();
			if (updateCount > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean deleteIntv(int cv_no) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, cv_no);
			int deleteCount = pstmt.executeUpdate();
			pstmt.close();
			if (deleteCount > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public Interview searchByCv_No(int cv_No) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(GET_ONE_SQL);
			pstmt.setInt(1, cv_No);
			ResultSet rs = pstmt.executeQuery();
			Interview interviewdate = new Interview();
			if (rs.next()) {
				interviewdate.setCv_No(rs.getInt("cv_no"));
				interviewdate.setCreated_Time(rs.getTimestamp("Created_Time"));
				interviewdate.setInt_Time(rs.getDate("Int_Time"));
				interviewdate.setComp_Name(rs.getString("Comp_Name"));
				interviewdate.setJob_Name(rs.getString("Job_Name"));
				interviewdate.setOffer(rs.getString("Offer"));
				interviewdate.setTest(rs.getString("Test"));
				interviewdate.setLanguage(rs.getString("Language"));
				interviewdate.setQA(rs.getString("QA"));
				interviewdate.setShare(rs.getString("Share"));
				interviewdate.setInt_Score(rs.getInt("Int_Score"));
				interviewdate.setComp_Score(rs.getInt("Comp_Score"));
				interviewdate.setUSER_ID(rs.getString("USER_ID"));
				
			}else {
				rs.close();
				pstmt.close();
				return null;
			}
			rs.close();
			pstmt.close();
			return interviewdate;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Interview> searchAll() {
		try {
			Interview interviewdate = null;
			List<Interview> interviewdates = new ArrayList<Interview>();
			PreparedStatement pstmt = conn.prepareStatement(GET_ALL_SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				interviewdate = new Interview();
				interviewdate.setCv_No(rs.getInt("cv_no"));
				interviewdate.setCreated_Time(rs.getTimestamp("Created_Time"));
				interviewdate.setInt_Time(rs.getDate("Int_Time"));
				interviewdate.setComp_Name(rs.getString("Comp_Name"));
				interviewdate.setJob_Name(rs.getString("Job_Name"));
				interviewdate.setOffer(rs.getString("Offer"));
				interviewdate.setTest(rs.getString("Test"));
				interviewdate.setLanguage(rs.getString("Language"));
				interviewdate.setQA(rs.getString("QA"));
				interviewdate.setShare(rs.getString("Share"));
				interviewdate.setInt_Score(rs.getInt("Int_Score"));
				interviewdate.setComp_Score(rs.getInt("Comp_Score"));
				interviewdate.setUSER_ID(rs.getString("USER_ID"));
				interviewdates.add(interviewdate);
			}
			rs.close();
			pstmt.close();
			return interviewdates;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Interview> searchByPage(int page) {
		try {
			Interview interview = null;
			List<Interview> interviews = new ArrayList<Interview>();
			PreparedStatement pstmt = conn.prepareStatement(GET_BY_PAGE_SQL);
			pstmt.setInt(1, (page - 1) * 10);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				interview = new Interview();
				interview.setCv_No(rs.getInt("cv_no"));
				interview.setCreated_Time(rs.getTimestamp("Created_Time"));
				interview.setInt_Time(rs.getDate("Int_Time"));
				interview.setComp_Name(rs.getString("Comp_Name"));
				interview.setJob_Name(rs.getString("Job_Name"));
				interview.setOffer(rs.getString("Offer"));
				interview.setTest(rs.getString("Test"));
				interview.setLanguage(rs.getString("Language"));
				interview.setQA(rs.getString("QA"));
				interview.setShare(rs.getString("Share"));
				interview.setInt_Score(rs.getInt("Int_Score"));
				interview.setComp_Score(rs.getInt("Comp_Score"));
				interview.setUSER_ID(rs.getString("USER_ID"));
				interviews.add(interview);
			}
			rs.close();
			pstmt.close();
			return interviews;
		} catch (SQLException e) {
			return null;
		}
	}

	public int getPageCount() {
		try {
			int counts = 0;
			PreparedStatement pstmt = conn.prepareStatement(GET_COUNT_SQL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				counts = rs.getInt("counts");
			}
			int pageCount = counts / 10;
			if (counts % 10 != 0) {
				pageCount++;
			}
			rs.close();
			pstmt.close();
			return pageCount;
		} catch (SQLException e) {
			return -1;
		}
	}

	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

}
