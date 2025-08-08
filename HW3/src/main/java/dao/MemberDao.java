package dao;

import model.Member;
import model.Porder;

public interface MemberDao {
	//create
	void create(Member member);
	
	//read
	Member readLicense(String license);
	Member readAccount(String account);
	Member login(String account,String password);

	//update
	void update(Member member);
	
	//delete
	void delete(Member member);
}
