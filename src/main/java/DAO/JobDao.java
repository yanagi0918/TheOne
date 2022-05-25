package DAO;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import Bean.Job;

public class JobDao{
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
	public static int insert(Job job){  
        int status=0;  
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
            "insert into JOBS(TITLE,JOB_DESCRIPTION,QUALIFICATION,REQUIRED_NUMBER,SALARY,COMP_ID) values (?,?,?,?,?,?)");  
            ps.setString(1,job.getTitle());  
            ps.setString(2,job.getJob_description());  
            ps.setString(3,job.getQualification());  
            ps.setInt(4,job.getRequired_number());  
            ps.setString(5,job.getSalary()); 
            ps.setString(6,job.getComp_id()); 
              
            status=ps.executeUpdate();
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }
	
	public static int update(Job job){  
        int status=0;
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
            "update JOBS set TITLE=?,JOB_DESCRIPTION=?,QUALIFICATION=?,REQUIRED_NUMBER=?,SALARY=?,COMP_ID=? where JOB_ID=?");  
            ps.setString(1,job.getTitle());  
            ps.setString(2,job.getJob_description());  
            ps.setString(3,job.getQualification());  
            ps.setInt(4,job.getRequired_number());
            ps.setString(5, job.getSalary());
            ps.setString(6, job.getComp_id());
            ps.setInt(7,job.getJob_id());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }
	public static int delete(int job_id){  
        int status=0;  
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from JOBS where job_id=?");  
            ps.setInt(1,job_id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }
	
	public static List<Job> getJobByTitle(String title){  
		List<Job> list = new ArrayList<Job>();
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from JOBS where title like \'%"+ title +"%\'");
            ResultSet rs=ps.executeQuery();  
            while (rs.next()) {
                Job job = new Job();
                job.setJob_id(rs.getInt(1));
                job.setTitle(rs.getString(2));  
            	job.setJob_description(rs.getString(3));  
            	job.setQualification(rs.getString(4));  
            	job.setRequired_number(rs.getInt(5));  
            	job.setSalary(rs.getString(6));
            	job.setComp_id(rs.getString(7));
            	list.add(job);
            } 
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return list;
    }
	public static Job getJobByJobID(int job_id){  
        Job job=new Job();  
          
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from JOBS where JOB_ID=?");  
            ps.setInt(1,job_id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
            	job.setJob_id(rs.getInt(1));  
            	job.setTitle(rs.getString(2));  
            	job.setJob_description(rs.getString(3));  
            	job.setQualification(rs.getString(4));  
            	job.setRequired_number(rs.getInt(5));  
            	job.setSalary(rs.getString(6));
            	job.setComp_id(rs.getString(7));  
            	
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return job;
    }
	
	
	public static List<Job> getAllJobs(){  
        List<Job> list=new ArrayList<Job>();
          
        try{  
            Connection con=JobDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from JOBS");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Job job=new Job();  
                job.setJob_id(rs.getInt(1));  
            	job.setTitle(rs.getString(2));  
            	job.setJob_description(rs.getString(3));  
            	job.setQualification(rs.getString(4));  
            	job.setRequired_number(rs.getInt(5));  
            	job.setSalary(rs.getString(6));
            	job.setComp_id(rs.getString(7));  
                list.add(job);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }
}