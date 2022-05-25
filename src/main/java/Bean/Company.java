package Bean;



//Value Object:一個Object代表Member Table一筆Row

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Company implements Serializable{

 private int compid;
 private String compwd;
 private String corpname;
 private String owner;
 private String industry;
 private String contact;
 private String comptele;
 private String fax;
 private String compaddress;
 private int empnumber;
 private String website;
 private String capital;


 //Constructor
 public Company() {}


public Company(int compid, String compwd, String corpname, String owner, String industry, String contact,
		String comptele, String fax, String compaddress, int empnuber, String website, String capital) {
	super();
	this.compid = compid;
	this.compwd = compwd;
	this.corpname = corpname;
	this.owner = owner;
	this.industry = industry;
	this.contact = contact;
	this.comptele = comptele;
	this.fax = fax;
	this.compaddress = compaddress;
	this.empnumber = empnuber;
	this.website = website;
	this.capital = capital;
}


public int getCompid() {
	return compid;
}


public void setCompid(int compid) {
	this.compid = compid;
}


public String getCompwd() {
	return compwd;
}


public void setCompwd(String compwd) {
	this.compwd = compwd;
}


public String getCorpname() {
	return corpname;
}


public void setCorpname(String corpname) {
	this.corpname = corpname;
}


public String getOwner() {
	return owner;
}


public void setOwner(String owner) {
	this.owner = owner;
}


public String getIndustry() {
	return industry;
}


public void setIndustry(String industry) {
	this.industry = industry;
}


public String getContact() {
	return contact;
}


public void setContact(String contact) {
	this.contact = contact;
}


public String getComptele() {
	return comptele;
}


public void setComptele(String comptele) {
	this.comptele = comptele;
}


public String getFax() {
	return fax;
}


public void setFax(String fax) {
	this.fax = fax;
}


public String getCompaddress() {
	return compaddress;
}


public void setCompaddress(String compaddress) {
	this.compaddress = compaddress;
}


public int getEmpnumber() {
	return empnumber;
}


public void setEmpnumber(int empnuber) {
	this.empnumber = empnuber;
}


public String getWebsite() {
	return website;
}


public void setWebsite(String website) {
	this.website = website;
}


public String getCapital() {
	return capital;
}


public void setCapital(String capital) {
	this.capital = capital;
}




	
	
}

 
