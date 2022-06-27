//package tw.team5.service.impl;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import tw.team5.bean.CommentBean;
//import tw.team5.dao.CommentDao;
//import tw.team5.dao.impl.CommentDaoImpl;
//import tw.team5.service.CommentService;
//import tw.team5.util.HibernateUtils;
//
//public class CommentServiceImpl implements CommentService {
//
//	SessionFactory factory;
//	CommentDao commentDao;
//
//	public CommentServiceImpl() {
//		this.factory = HibernateUtils.getSessionFactory();
//		this.commentDao = new CommentDaoImpl();
//	}
//
//	@Override
//	public boolean isDup(int pk) {
//		boolean result = false;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			result = commentDao.isDup(pk);
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
//	public int save(CommentBean comment) {
//		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			n = commentDao.save(comment);
//			tx.commit();
//
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);
//		}
//		return n;
//	}
//
//	@Override
//	public void updateComment(CommentBean comment) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			commentDao.updateComment(comment);
//			tx.commit();
//
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);
//		}
//	}
//
//	@Override
//	public CommentBean getCommentById(int pk) {
//		CommentBean comment = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			comment = commentDao.getCommentById(pk);
//			tx.commit();
//
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);
//		}
//		return comment;
//	}
//
//	@Override
//	public List<CommentBean> getAllComments() {
//		List<CommentBean> comments = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			comments = commentDao.getAllComments();
//			tx.commit();
//
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);
//		}
//		return comments;
//	}
//
//	@Override
//	public void deleteComment(int pk) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			commentDao.deleteComment(pk);
//			tx.commit();
//
//		} catch (Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex);
//		}
//	}
//
//}
