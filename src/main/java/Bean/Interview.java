
package Bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Interview_Table")
public class Interview implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cvNo;
	
	private java.sql.Timestamp createdTime;
	private Date intTime;
	private String compName;
	private String jobName;
	private String offer;
	private String test;
	private String QA;
	private String share;
	private Integer compScore;
	private String userId;

	public Interview() {
		
	}

	public Integer getCvNo() {
		return cvNo;
	}

	public void setCvNo(Integer cvNo) {
		this.cvNo = cvNo;
	}

	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatedTime(String createdTime) throws ParseException {
		this.createdTime = stringToSQLDateSs(createdTime);
	}
	public Date getIntTime() {
		return intTime;
	}

	public void setIntTime(Date intTime) {
		this.intTime = intTime;
	}
	
	public void setIntTime(String intTime) throws ParseException {
		this.intTime = stringToSQLDate(intTime);
	}
	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getQA() {
		return QA;
	}

	public void setQA(String qA) {
		QA = qA;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public Integer getCompScore() {
		return compScore;
	}

	public void setCompScore(Integer compScore) {
		this.compScore = compScore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Date stringToSQLDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = df.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	private java.sql.Timestamp stringToSQLDateSs(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date utilDate = df.parse(date);
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
		return sqlDate;
	}

	@Override
	public String toString() {
		return "Interview [cvNo=" + cvNo + ", createdTime=" + createdTime + ", intTime=" + intTime + ", compName="
				+ compName + ", jobName=" + jobName + ", offer=" + offer + ", test=" + test + ", QA=" + QA + ", share="
				+ share + ", compScore=" + compScore + ", userId=" + userId + "]";
	}

	public void setInterviewId(int pk) {
		// TODO Auto-generated method stub
		
	}
	
	



	

}