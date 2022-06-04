package Service;

import java.util.List;

import Bean.Company;

public interface CompanyService {
boolean isDup(int pk);
	
	int save(Company company);
	
	List<Company> getAllCompanies();
	
	Company getCompany(int pk);
	
	void deleteCompany(int pk);
	
	void updateCompany(Company company);
}
