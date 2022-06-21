package tw.team5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.team5.bean.Company;
import tw.team5.dao.CompanyDao;
import tw.team5.dao.impl.CompanyDaoImpl;
import tw.team5.service.CompanyService;
@Transactional
@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyDao companyDao;
	
	public CompanyServiceImpl() {
		this.companyDao = new CompanyDaoImpl();
	}
	
	@Override
	public boolean isDup(int compid) {
		
		return companyDao.isDup(compid);
	}
	@Override
	public int save(Company company) {
		
		return companyDao.save(company);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		
		return companyDao.getAllCompanies();
	}
	
	@Override
	public Company getCompany(int pk) {
		
		return companyDao.getCompany(pk);
	}
	
	@Override
	public void deleteCompany(int pk) {
		
		companyDao.deleteCompany(pk);
	}
	
	@Override
	public void updateCompany(Company company) {
		
		companyDao.updateCompany(company);
	}

}
