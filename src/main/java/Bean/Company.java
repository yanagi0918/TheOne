package Bean;
import java.io.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Company_Table")
public class Company implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer comppk;
 private Integer compid;
 private String compwd;
 private String corpname;
 private String owner;
 private String industry;
 private String contact;
 private String comptele;
 private String fax;
 private String compaddress;
 private Integer empnumber;
 private String website;
 private String capital;


 public Company() {}



public Company(Integer comppk, Integer compid, String compwd, String corpname, String owner, String industry,
		String contact, String comptele, String fax, String compaddress, Integer empnumber, String website,
		String capital) {
	super();
	this.comppk = comppk;
	this.compid = compid;
	this.compwd = compwd;
	this.corpname = corpname;
	this.owner = owner;
	this.industry = industry;
	this.contact = contact;
	this.comptele = comptele;
	this.fax = fax;
	this.compaddress = compaddress;
	this.empnumber = empnumber;
	this.website = website;
	this.capital = capital;
}

public Integer getComppk() {
	return comppk;
}

public void setComppk(Integer comppk) {
	this.comppk = comppk;
}

public Integer getCompid() {
	return compid;
}


public void setCompid(Integer compid) {
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

public Integer getEmpnumber() {
	return empnumber;
}

public void setEmpnumber(Integer empnuber) {
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

 
