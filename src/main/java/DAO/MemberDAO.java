package DAO;


//DAO: Database Access Object
//專責與Dept Table之新增,修改,刪除與查詢

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Bean.Member;


public class MemberDAO {

	private Connection conn;

	private static final String SQL_QueryAll = "SELECT * FROM MEMBER_USER";
	private static final String SQL_Query = "SELECT * FROM MEMBER_USER WHERE [user_id] = ?";
	private static final String SQL_CreateMember = "INSERT INTO MEMBER_USER VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_Update = " UPDATE MEMBER_USER SET pwd=?,user_name=?,gender=?,birthday=?,tele=?,phone=?,address=?,email=?,point=?,image=? WHERE [user_id] = ? ";
	private static final String SQL_Delete = " DELETE FROM MEMBER_USER WHERE [user_id] = ? ";

	// 建構子
	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	//判斷會員id是否重複
	public boolean findUserid(String id) {
		boolean Checkid = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Query);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {      //如果前端傳入的值在資料庫蒐的到
				Checkid=true;     //Checkid改true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Checkid;
	}
	
	
	//搜尋所有會員資料，回傳list裡面裝member
	public List<Member> findAll() {
		try {
			List<Member> list = new ArrayList<Member>(); 
			PreparedStatement pstmt = conn.prepareStatement(SQL_QueryAll);
			ResultSet rs = pstmt.executeQuery();
			// 把抓到的資料set進member裡面
			while (rs.next()) {
				Member member = new Member();
				member.setUserid(rs.getString("user_id"));
				member.setPwd(rs.getString("pwd"));
				member.setUsername(rs.getString("user_name"));
				member.setGender(rs.getString("gender"));
				member.setBirth(rs.getDate("birthday"));
				member.setTele(rs.getString("tele"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setPoint(rs.getInt("point"));
				member.setImage(rs.getString("image"));
				list.add(member);
			}

			rs.close();
			pstmt.close();
			return list;

		} catch (Exception e) {
			System.err.println("尋找會員資料時發生錯誤:" + e);
			return null;
		}

	}

	public Member findMember(String id) {
		try {

			PreparedStatement pstmt = conn.prepareStatement(SQL_Query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			Member member = new Member();

			if (rs.next()) {

				member.setUserid(rs.getString("user_id"));
				member.setPwd(rs.getString("pwd"));
				member.setUsername(rs.getString("user_name"));
				member.setGender(rs.getString("gender"));
				member.setBirth(rs.getDate("birthday"));
				member.setTele(rs.getString("tele"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setEmail(rs.getString("email"));
				member.setPoint(rs.getInt("point"));
				member.setImage(rs.getString("image"));

			} else {
				return null;
			}

			rs.close();
			pstmt.close();
			return member;

		} catch (Exception e) {
			System.err.println("尋找會員資料時發生錯誤:" + e);
			return null;
		}
	}

	// 新增新的會員資訊
	public boolean createMember(Member member) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_CreateMember);
			// 把前面傳回的資料set進資料庫
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getGender());
		//	pstmt.setDate(5, new java.sql.Date(member.getBirth().getTime()));
			pstmt.setDate(5, member.getBirth());
			pstmt.setString(6, member.getTele());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getEmail());
			pstmt.setInt(10, member.getPoint());
			pstmt.setString(11, member.getImage());

			int createCount = pstmt.executeUpdate();
			pstmt.close();
			if(createCount >= 1 ) {
				return true;
				
			}else {
				return false;
			}

		} catch (Exception e) {
			System.err.println("新增會員時發生錯誤:" + e);
			return false;
		}
	}

	// 更新會員資料		     
	public  boolean updateMember(Member member) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL_Update);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getUsername());
			pstmt.setString(3, member.getGender());
	//		pstmt.setDate(4, new java.sql.Date(mem.getBirth().getTime()));
			pstmt.setDate(4, member.getBirth());
			pstmt.setString(5, member.getTele());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getEmail());
			pstmt.setInt(9, member.getPoint());
			pstmt.setString(10, member.getImage());
			//最後一個?是where user_id = ?
			pstmt.setString(11, member.getUserid());
			pstmt.addBatch();
			pstmt.executeBatch();
			
			int updateCount = pstmt.executeUpdate();
			pstmt.close();
			if (updateCount > 0) {
				return true;
			}
			

		} catch (Exception e) {
			System.err.println("更新部門資料時發生錯誤:" + e);
			return false;
		}
		return false;
	}

	
	  //刪除會員資料 
	public boolean deleteMember(String userid) { 
		try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL_Delete); 
			  pstmt.setString(1, userid);
			  int deletecount = pstmt.executeUpdate(); 
			  pstmt.close();
			  
			  if (deletecount >= 1) {
				  return true; 
			  }else {
				  return false; 
			  }
		} catch (Exception e) {
			  System.err.println("刪除部門時發生錯誤: "+ e); 
			  return false; 
			  } 
		}
	  
	//關閉連線，到時候controller呼叫方法一次關
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
	

}