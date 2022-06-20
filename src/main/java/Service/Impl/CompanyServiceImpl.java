package Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Bean.Company;
import DAO.CompanyDao;
import DAO.impl.CompanyDaoImpl;
import Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	CompanyDao companyDao;
	
	public CompanyServiceImpl() {
		this.companyDao = new CompanyDaoImpl();
	}
	
	@Transactional
	@Override
	public boolean isDup(int compid) {
		
		return companyDao.isDup(compid);
	}
	@Transactional
	@Override
	public int save(Company company) {
		
		return companyDao.save(company);
	}
	
	@Transactional
	@Override
	public List<Company> getAllCompanies() {
		
		return companyDao.getAllCompanies();
	}
	
	@Transactional
	@Override
	public Company getCompany(int pk) {
		
		return companyDao.getCompany(pk);
	}
	
	@Transactional
	@Override
	public void deleteCompany(int pk) {
		
		companyDao.deleteCompany(pk);
	}
	
	@Transactional
	@Override
	public void updateCompany(Company company) {
		
		companyDao.updateCompany(company);
	}

}
