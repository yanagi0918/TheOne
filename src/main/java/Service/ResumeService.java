package Service;

import java.util.List;
import Bean.Resume;

public interface ResumeService {

	boolean isDup(int pk);  //判斷是否重複

	int save(Resume resume); //新增Resume物件到資料庫

	List<Resume> getAllResumes(); //呈現所有履歷

	Resume getResume(int pk); //取得單筆履歷

	void deleteResume(int pk); //刪除履歷

	void updateResume(Resume resume);  //更新履歷
	
}
