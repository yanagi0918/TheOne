package Service;

import java.util.List;

import Bean.Member;

public interface MemberService {
	boolean isDup(int pk);

	int save(Member member);

	List<Member> getAllMembers();

	Member getMember(int pk);
	
	Member checkAccount(String account);
	Member checkPassword(String password);
	

	void deleteMember(int pk);

	void updateMember(Member member);

}
