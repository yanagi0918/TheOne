//package tw.team5.service.impl;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import tw.team5.bean.Member;
//import tw.team5.dao.MemberDao;
//import tw.team5.dao.impl.MemberDaoImpl;
//import tw.team5.service.MemberService;
//import tw.team5.util.HibernateUtils;
//
//public class MemberServiceImpl implements MemberService {
//	SessionFactory factory;
//	MemberDao memberDao;
//	
//	public MemberServiceImpl() {
//		this.factory = HibernateUtils.getSessionFactory();
//		this.memberDao = new MemberDaoImpl();
//	}
//	
//		
//	@Override
//	public boolean isDup(int pk) {
//		boolean result = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			result = memberDao.isDup(pk);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//		return result;
//	}
//
//	@Override
//	public int save(Member member) {
//		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			n = memberDao.save(member);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//		return n;
//	}
//
//	@Override
//	public List<Member> getAllMembers() {
//		List<Member> members = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			members = memberDao.getAllMembers();
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//		return members;
//	}
//
//	@Override
//	public Member getMember(int pk) {
//		Member member = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			member = memberDao.getMember(pk);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//		return member;
//	}
//
//	public Member checkAccount(String userid) {
//		Member account = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			account = memberDao.checkAccount(userid);  //呼叫memberDao.checkAccount得到他的回傳值(Member)
//			tx.commit();
//			
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			return null;  //如果memberDao.checkAccount得到例外，這邊就會return null
//		}
//		return account;
//	}
//	
//	public Member checkPassword(String pwd) {
//		Member password = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			password = memberDao.checkPassword(pwd);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//		return password;
//	}
//	
//	
//	
//	
//	
//	@Override
//	public void deleteMember(int pk) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			memberDao.deleteMember(pk);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	public void updateMember(Member member) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			memberDao.updateMember(member);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(e);
//		}
//	}
//
//}
