package DAO.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Bean.Member;
import DAO.MemberDao;
import util.HibernateUtils;

public class MemberDaoImpl implements MemberDao {
	SessionFactory factory;
	
	public MemberDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public boolean isDup(int pk) {
		Session session = factory.getCurrentSession();
		Member member = (Member) session.get(Member.class, pk);
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(Member member) {
		Session session = factory.getCurrentSession();
		int pk = 0;
		pk = (int) session.save(member);
		return pk;
	}

	@Override
	public List<Member> getAllMembers() {
		Session session = factory.getCurrentSession();
		List<Member> members = null;
		String hql = "FROM Member";
		members = session.createQuery(hql, Member.class).getResultList();
		return members;
		
	}

	@Override
	public Member getMember(int pk) {
		Session session = factory.getCurrentSession();
		Member member = session.get(Member.class, pk);
		return member;
		
	}

	@Override
	public Member checkAccount(String userid) {
		Session session = factory.getCurrentSession();
		Member account = session.get(Member.class, userid);
		return account;
		
	}
	
	@Override
	public Member checkPassword(String pwd) {
		Session session = factory.getCurrentSession();
		Member password = session.get(Member.class, pwd);
		return password;
		
	}
	
	
	
	
	@Override
	public void deleteMember(int pk) {
		Session session = factory.getCurrentSession();
		Member member = new Member();
		member.setIdNumber(pk);
		session.delete(member);
		
		
	}

	@Override
	public void updateMember(Member member) {

		Session session = factory.getCurrentSession();
		session.saveOrUpdate(member);
		
	}

}
