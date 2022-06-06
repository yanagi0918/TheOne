package DAO;

import java.util.List;

import Bean.Member;

public interface MemberDao {

	boolean isDup(int pk);

	int save(Member member);

	List<Member> getAllMembers();

	Member getMember(int pk);

	void deleteMember(int pk);

	void updateMember(Member member);

	
	Member checkAccount(String userid);


	Member checkPassword(String pwd);



	
}
