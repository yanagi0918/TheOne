package DAO;

//DAO: Database Access Object


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListDataEvent;

import Bean.Company;

public class CompanyDAO {

	private Connection conn;

	private static final String SQL_QueryAll = "SELECT * FROM MEMBER_COMP";
	private static final String SQL_Query = "SELECT * FROM MEMBER_COMP WHERE comp_id =? ";
	private static final String SQL_Create = "INSERT INTO MEMBER_COMP VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_Update = " UPDATE MEMBER_COMP SET pwd=?,corp_name=?,owner=?,corp_industry=?,contact=?,tele=?,fax=?,[address]=?,emp_numbers=?,website=?,capital=? WHERE comp_id=? ";
	private static final String SQL_Delete = " DELETE FROM MEMBER_COMP WHERE comp_id = ? ";

	
	// 建構子
	public CompanyDAO(Connection conn) {
		this.conn = conn;
	}

	
	//確認id是否重複
	public boolean findCompid(int compid) {
		 
		boolean CheckId =false;   //預設checkid為false
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Query);
			pstmt.setInt(1,compid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {      //如果前端傳入的值在資料庫蒐的到
				
				CheckId=true;     //Checkid改true
			}
				
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CheckId;
		
	}
	
	
	//QueryAll
	public List<Company> findAll() {
		try {
			List<Company> list = new ArrayList<Company>();
			PreparedStatement pstmt = conn.prepareStatement(SQL_QueryAll);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setCompid(rs.getInt("comp_id"));
				company.setCompwd(rs.getString("pwd"));
				company.setCorpname(rs.getString("corp_name"));
				company.setOwner(rs.getString("owner"));
				company.setIndustry(rs.getString("corp_industry"));
				company.setContact(rs.getString("contact"));
				company.setComptele(rs.getString("tele"));
				company.setFax(rs.getString("fax"));
				company.setCompaddress(rs.getString("address"));
				company.setEmpnumber(rs.getInt("emp_numbers"));
				company.setWebsite(rs.getString("website"));
				company.setCapital(rs.getString("capital"));
				list.add(company);
			}

			rs.close();
			pstmt.close();
			return list;

		} catch (Exception e) {
			System.err.println("尋找會員資料時發生錯誤:" + e);
			return null;
		}

	}

	//QueryOne
	public Company findCompany(int id) {

		Company company = new Company();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				company.setCompid(rs.getInt("comp_id"));
				company.setCompwd(rs.getString("pwd"));
				company.setCorpname(rs.getString("corp_name"));
				company.setOwner(rs.getString("owner"));
				company.setIndustry(rs.getString("corp_industry"));
				company.setContact(rs.getString("contact"));
				company.setComptele(rs.getString("tele"));
				company.setFax(rs.getString("fax"));
				company.setCompaddress(rs.getString("address"));
				company.setEmpnumber(rs.getInt("emp_numbers"));
				company.setWebsite(rs.getString("website"));
				company.setCapital(rs.getString("capital"));
			}
			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	//Create
	public boolean createCompany(Company company) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Create);
			pstmt.setInt(1, company.getCompid());
			pstmt.setString(2, company.getCompwd());
			pstmt.setString(3, company.getCorpname());
			pstmt.setString(4, company.getOwner());
			pstmt.setString(5, company.getIndustry());
			pstmt.setString(6, company.getContact());
			pstmt.setString(7, company.getComptele());
			pstmt.setString(8, company.getFax());
			pstmt.setString(9, company.getCompaddress());
			pstmt.setInt(10, company.getEmpnumber());
			pstmt.setString(11, company.getWebsite());
			pstmt.setString(12, company.getCapital());
			int insertamount = pstmt.executeUpdate();
			pstmt.close();
			
			if(insertamount >= 1 ) {
				return true;
			}else {
				return false;
			} 
			
		} catch (Exception e) {
			System.err.println("新增會員時發生錯誤:" + e);
			return false;
		}
	}

	//Update
	public boolean updateCompany(Company company) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Update);
			pstmt.setString(1, company.getCompwd());
			pstmt.setString(2, company.getCorpname());
			pstmt.setString(3, company.getOwner());
			pstmt.setString(4, company.getIndustry());
			pstmt.setString(5, company.getContact());
			pstmt.setString(6, company.getComptele());
			pstmt.setString(7, company.getFax());
			pstmt.setString(8, company.getCompaddress());
			pstmt.setInt(9, company.getEmpnumber());
			pstmt.setString(10, company.getWebsite());
			pstmt.setString(11, company.getCapital());
			pstmt.setInt(12, company.getCompid());
			pstmt.addBatch();
			pstmt.executeBatch();
			int updateamount = pstmt.executeUpdate();
			pstmt.close();
			
			if(updateamount >0 ) {
				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			System.err.println("更新部門資料時發生錯誤:" + e);
			return false;
		}
	}

	//Delete
	public boolean deleteCompany(int id) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Delete);
			pstmt.setInt(1, id );
			int deleteamount = pstmt.executeUpdate();
			pstmt.close();
			
			if(deleteamount > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			System.err.println("刪除部門時發生錯誤: " + e);
			return false;
		}
	}
	
	//close the connection
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	

}