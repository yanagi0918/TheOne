package tw.team5.dao;

import java.util.List;

import tw.team5.bean.CommentBean;


public interface CommentDao {

	void updateComment(CommentBean commentBean);

	CommentBean getCommentById(int pk);

	List<CommentBean> getAllComments();

	void deleteComment(int pk);

	boolean isDup(int pk);

	int save(CommentBean comment);

}