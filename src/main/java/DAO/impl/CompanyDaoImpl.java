package DAO.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Bean.Company;
import DAO.CompanyDao;
import util.HibernateUtils;

public class CompanyDaoImpl implements CompanyDao{
	SessionFactory factory;
	
	public CompanyDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}
	@Override
	public boolean isDup(int compid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Company cp WHERE cp.compid = :compid";
		List<Company> companies = session.createQuery(hql , Company.class)
				.setParameter("compid", compid)
				.getResultList();
		if (companies.size() >0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Company company) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(company);
		return pk;
	}

	@Override
	public List<Company> getAllCompanies() {
		Session session = factory.getCurrentSession();
		List<Company> companies = null;
		String hql = "FROM Company";
		companies = session.createQuery(hql,Company.class).getResultList();
		return companies;
	}

	@Override
	public Company getCompany(int pk) {
		Session session = factory.getCurrentSession();
		Company company = session.get(Company.class, pk);
		return company;
	}

	@Override
	public void deleteCompany(int pk) {
		Session session = factory.getCurrentSession();
		Company company = new Company();
		company.setComppk(pk);
		session.delete(company);
	}

	@Override
	public void updateCompany(Company company) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(company);
	}


}