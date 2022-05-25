package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Bean.Resume;

public class ResumeDao{
	private static Connection con;
	public static Connection getConnection(){ 
		try{
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/FindJobDB");
            con = ds.getConnection();
        }catch (NamingException e){
            System.out.println(e);
        }catch (SQLException e){
            System.out.println(e);
        }
        return con;
    }
	public static int insert(Resume resume){  
        int status=0;  
        try{  
            Connection con=ResumeDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
            "insert into RESUME(EDU,SCHOOL,DEPT,WORK_EXP,SKILLS,USER_ID) values (?,?,?,?,?,?)");  
            ps.setString(1,resume.getEdu());  
            ps.setString(2,resume.getSchool());  
            ps.setString(3,resume.getDept());  
            ps.setString(4,resume.getWork_exp());  
            ps.setString(5,resume.getSkills()); 
            ps.setString(6,resume.getUser_id()); 
              
            status=ps.executeUpdate();
              
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        	}  
          
        return status;  
    }
	public static int update(Resume resume){  
        int status=0;
        try{  
            Connection con=ResumeDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
            "update RESUME set EDU=?,SCHOOL=?,DEPT=?,WORK_EXP=?,SKILLS=?,USER_ID=? where RESUME_ID=?");  
            ps.setString(1,resume.getEdu());  
            ps.setString(2,resume.getSchool());  
            ps.setString(3,resume.getDept());  
            ps.setString(4,resume.getWork_exp());  
            ps.setString(5,resume.getSkills()); 
            ps.setString(6,resume.getUser_id());
            ps.setInt(7,resume.getResume_id());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        	}  
          
        return status;  
    }
	public static int delete(int resume_id){  
        int status=0;  
        try{  
            Connection con=ResumeDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from RESUME where resume_id=?");  
            ps.setInt(1,resume_id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){
        	e.printStackTrace();
        	}  
          
        return status;  
    }
	public static Resume getResumeByResumeID(int resume_id){  
        Resume resume=new Resume();
          
        try{  
            Connection con=ResumeDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from RESUME where RESUME_ID=?");  
            ps.setInt(1,resume_id);  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	resume.setResume_id(rs.getInt(1));  
            	resume.setEdu(rs.getString(2));  
            	resume.setSchool(rs.getString(3));  
            	resume.setDept(rs.getString(4));  
            	resume.setWork_exp(rs.getString(5));  
            	resume.setSkills(rs.getString(6));
            	resume.setUser_id(rs.getString(7));  
            	
            }  
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        	}  
          
        return resume;
    }
	public static List<Resume> getAllResumes(){  
        List<Resume> list=new ArrayList<Resume>();
          
        try{
            Connection con=ResumeDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from RESUME");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Resume resume=new Resume();  
            	resume.setResume_id(rs.getInt(1));  
            	resume.setEdu(rs.getString(2));  
            	resume.setSchool(rs.getString(3));  
            	resume.setDept(rs.getString(4));  
            	resume.setWork_exp(rs.getString(5));  
            	resume.setSkills(rs.getString(6));
            	resume.setUser_id(rs.getString(7));   
                list.add(resume);  
            }  
            con.close();  
        }catch(Exception e){
        	e.printStackTrace();
        	}  
          
        return list;  
    }
}