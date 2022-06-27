//package tw.team5.dao.impl;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import tw.team5.bean.CommentBean;
//import tw.team5.bean.EventBean;
//import tw.team5.dao.CommentDao;
//import tw.team5.util.HibernateUtils;
//
//public class CommentDaoImpl implements CommentDao {
//	
//	SessionFactory factory;
//	
//	public CommentDaoImpl() {
//		factory = HibernateUtils.getSessionFactory();
//	}
//
//	@Override
//	public boolean isDup(int pk) {
////		Session session = factory.getCurrentSession();
//		EventBean event = (EventBean) session.get(EventBean.class, pk);
//		if (event != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	@Override
//	public int save(CommentBean comment) {
//		
//		Session session = factory.getCurrentSession();
//		int pk = 0;
//		pk = (int) session.save(comment);
//		return pk;
//	}
//	
//	@Override
//	public void updateComment(CommentBean comment) {
//		
//		Session session = factory.getCurrentSession();
//		session.saveOrUpdate(comment);
//		
//	}
//	
//	@Override
//	public CommentBean getCommentById(int pk) {
//		Session session = factory.getCurrentSession();
//		CommentBean commentBean = session.get(CommentBean.class, pk);
//		
//		return commentBean;
//		
//	}
//	
//	@Override
//	public List<CommentBean> getAllComments() {
//		Session session = factory.getCurrentSession();
//		List<CommentBean> comments = null;
//		String hql = "FROM CommentBean";
//		comments = session.createQuery(hql, CommentBean.class).getResultList();
//		
//		return comments;
//		
//	}
//	
//	@Override
//	public void deleteComment(int pk) {
//		
//		Session session = factory.getCurrentSession();
//		CommentBean comment = new CommentBean();
//		comment.setShare_id(pk);
//		session.delete(comment);
//		
//	}
//
//}
