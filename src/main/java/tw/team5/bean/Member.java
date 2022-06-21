package tw.team5.bean;



//Value Object:一個Object代表Member Table一筆Row

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.event.ListDataEvent;

@Entity
@Table(name = "Member_Table")
public class Member implements Serializable{
 //@OneToMany(cascade = CascadeType.ALL)
 //@JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
 //private Set<Resume> resumes = new LinkedHashSet<>();
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer idNumber;
 
 @Column(unique=true)
 private String userid;
 
 private String pwd;
 private String username;
 private String gender;
 @Column(columnDefinition = "Date")
 private Date birth;  
 private String tele;
 private String phone;
 private String address;
 private String email;
 private Integer point;
 private String image;


 //Constructor
 public Member() {}
 
 
 //建立一個帳號密碼驗證的建構子
 public Member(String userid, String pwd) {
		super();
		this.userid = userid;
		this.pwd = pwd;
	}


/*public Set<Resume> getResumes() {
	return resumes;
}

public void setResumes(Set<Resume> resumes) {
	this.resumes = resumes;
}
*/
public Integer getIdNumber() {
	return idNumber;
}


public void setIdNumber(Integer idNumber) {
	this.idNumber = idNumber;
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

public void setBirth(Date birth) {
	this.birth = birth;
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

public Integer getPoint() {
	return point;
}

public void setPoint(Integer point) {
	this.point = point;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}



	public static java.sql.Date strToDate(String strDate) { 
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

 
