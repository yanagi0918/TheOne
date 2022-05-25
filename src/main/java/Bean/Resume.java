package Bean;

public class Resume{
	private int resume_id;
	private String edu;
	private String school;
	private String dept;
	private String work_exp;
	private String skills;
	private String user_id;
	public int getResume_id() {
		return resume_id;
	}
	public void setResume_id(int resume_id) {
		this.resume_id = resume_id;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getWork_exp() {
		return work_exp;
	}
	public void setWork_exp(String work_exp) {
		this.work_exp = work_exp;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Resume(int resume_id, String edu, String school, String dept, String work_exp, String skills,
			String user_id) {
		super();
		this.resume_id = resume_id;
		this.edu = edu;
		this.school = school;
		this.dept = dept;
		this.work_exp = work_exp;
		this.skills = skills;
		this.user_id = user_id;
	}
	public Resume() {}
	public Resume(String edu, String school, String dept, String work_exp, String skills, String user_id) {
		super();
		this.edu = edu;
		this.school = school;
		this.dept = dept;
		this.work_exp = work_exp;
		this.skills = skills;
		this.user_id = user_id;
	}
	
}