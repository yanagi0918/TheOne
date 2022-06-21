package tw.team5.dao;
import java.util.List;

import tw.team5.bean.Company;
public interface CompanyDao {
	boolean isDup(int compid);
	
	int save(Company company);
	
	List<Company> getAllCompanies();
	
	Company getCompany(int pk);
	
	void deleteCompany(int pk);
	
	void updateCompany(Company company);
}
