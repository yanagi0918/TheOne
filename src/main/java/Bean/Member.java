package Bean;



//Value Object:一個Object代表Member Table一筆Row

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Member implements Serializable{

 private String userid;
 private String pwd;
 private String username;
 private String gender;
 private Date birth;  
 private String tele;
 private String phone;
 private String address;
 private String email;
 private int point;
 private String image;


 //Constructor
 public Member() {}
 
 
	public Member(String userid, String pwd, String username, String gender, Date birth, String tele, String phone,
			String address, String email, int point, String image) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.username = username;
		this.gender = gender;
		this.birth = birth;
		this.tele = tele;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.point = point;
		this.image = image;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date date) {
		this.birth = date;
	}


	public String getTele() {
		return tele;
	}


	public void setTele(String tele) {
		this.tele = tele;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
 

	
	
	
	
	public void setBirth(String birth) throws ParseException {
		this.birth = strToDate(birth);
	}
	
	
	
	
	public java.sql.Date strToDate(String strDate) { 
		String str = strDate; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date d = null; 
		
		try { 
			  d = format.parse(str); 
		} catch (Exception e) { 
			  e.printStackTrace(); 
		} 
		java.sql.Date date = new java.sql.Date(d.getTime()); 
		return date; 
		}
	
	
}

 
