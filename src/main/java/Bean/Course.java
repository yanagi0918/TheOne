package Bean;

import java.io.Serializable;
import java.sql.Date;

public class Course implements Serializable{
	
	/* 封裝屬性 */
	private String courseNo;
	private String courseCategory;
	private String courseName;
	private String courseIntroduction;
	private String lecturer;
	private Date date;
	private String coursePic;
	private String courseVedio;
	private double score;
	private int price;

	/* constructor */
	
	public Course() {
	}
	
	public Course(String courseNo, String courseCategory, String courseName, String courseIntroduction,
			String lecturer, Date date, String coursePic, String courseVedio, double score, int price) {
		this.courseNo = courseNo;
		this.courseCategory = courseCategory;
		this.courseName = courseName;
		this.courseIntroduction = courseIntroduction;
		this.lecturer = lecturer;
		this.date = date;
		this.coursePic = coursePic;
		this.courseVedio = courseVedio;
		this.score = score;
		this.price = price;
	}
	
	public Course(String courseCategory, String courseName, String courseIntroduction,
			String lecturer, Date date, String coursePic, String courseVedio, double score, int price) {
		
		this.courseCategory = courseCategory;
		this.courseName = courseName;
		this.courseIntroduction = courseIntroduction;
		this.lecturer = lecturer;
		this.date = date;
		this.coursePic = coursePic;
		this.courseVedio = courseVedio;
		this.score = score;
		this.price = price;
	}

	@Override
	public String toString() {
		String formatString = String.format(
				"a.課程編號:%s  b.分類:%s  c.課名:%s  d.介紹:%s  e.講師:%s  f.上架時間:%s  g.圖片:%s  h.影片:%s  i.評分:%.1f  k.價錢:%d%n"
				,getCourseNo(),getCourseCategory(),getCourseName(),getCourseIntroduction()
				,getLecturer(),getDate(),getCoursePic(),getCourseVedio(),getScore(),getPrice());
		return formatString;
	}
	

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseIntroduction() {
		return courseIntroduction;
	}

	public void setCourseIntroduction(String courseIntroduction) {
		this.courseIntroduction = courseIntroduction;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCoursePic() {
		return coursePic;
	}

	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}

	public String getCourseVedio() {
		return courseVedio;
	}

	public void setCourseVedio(String courseVedio) {
		this.courseVedio = courseVedio;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
