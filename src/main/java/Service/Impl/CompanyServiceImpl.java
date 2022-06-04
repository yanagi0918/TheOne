package Service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Company;
import DAO.CompanyDao;
import DAO.impl.CompanyDaoImpl;
import Service.CompanyService;
import util.HibernateUtils;

public class CompanyServiceImpl implements CompanyService{
	SessionFactory factory;
	CompanyDao companyDao;
	public CompanyServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.companyDao = new CompanyDaoImpl();
	}
	@Override
	public boolean isDup(int pk) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			result = companyDao.isDup(pk);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return result;
	}
	@Override
	public int save(Company company) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			n = companyDao.save(company);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return n;
	}
		
	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			companies = companyDao.getAllCompanies();
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return companies;
	}
	@Override
	public Company getCompany(int pk) {
		Company company = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			company = companyDao.getCompany(pk);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return company;
	}
	@Override
	public void deleteCompany(int pk) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			companyDao.deleteCompany(pk);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
	}
	@Override
	public void updateCompany(Company company) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			companyDao.updateCompany(company);
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		
	}

}
