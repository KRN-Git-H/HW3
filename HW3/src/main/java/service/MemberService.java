package service;

import model.Member;

public interface MemberService {
	//create
	boolean create(Member member);

	//read
	int readAmount(String license);
	Member readAccount(String account);	
	Member login(String username,String password);
	
	//update
	boolean update(Member member);
	
	//delete
	boolean delete(Member member);
}

