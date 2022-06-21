package tw.team5.service;

import java.util.List;

import tw.team5.bean.CommentBean;

public interface CommentService {

	List<CommentBean> getAllComments();

	CommentBean getCommentById(int pk);

	void deleteComment(int pk);

	int save(CommentBean comment);

	boolean isDup(int pk);

	void updateComment(CommentBean comment);

}
