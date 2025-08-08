package service.impl;

import javax.swing.JOptionPane;

import dao.impl.MemberDaoImpl;
import dao.impl.MonthRentDaoImpl;
import model.Member;
import model.MonthRent;
import service.MemberService;
import util.Tool;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {
		//read
//		System.out.println(new MemberServiceImpl().readAmount("JJJ3d33"));

//		//delete
//		new MemberServiceImpl().delete(new MemberDaoImpl().read("aaa"));
	}

	private static MemberDaoImpl memberDI = new MemberDaoImpl();	
	private static MonthRentDaoImpl monthrentDI = new MonthRentDaoImpl();

	@Override
	public boolean create(Member member) {
		Member account = memberDI.readAccount(member.getAccount());
		boolean accountExist = account ==null ? false : true;

		if(!accountExist)
		{
			memberDI.create(member);
		}
		
		return !accountExist;
	}	
	
	@Override
	public int readAmount(String license) {
		String category="";
		int charge=0;
		
		Member member = memberDI.readLicense(license);
		MonthRent monthrent = monthrentDI.readLicense(license);
		
		try {
			category = member.getCategory();
			charge = monthrent.fee;
		
			if (category.equals("身障"))
			{
				charge*=0.5;
			}
			else if(category.equals("里民"))
			{
				charge*=0.8;
			}			
		}
		catch (Exception e)
		{
			Tool.alarmMsg(null,"無此車號，請先加入會員登錄車號。");
		}

		return charge;
	}
	
	@Override
	public Member readAccount(String account) {
		return memberDI.readAccount(account);
	}

	@Override
	public Member login(String account, String password) {
		return memberDI.login(account, password);
	}

	@Override
	public boolean update(Member member) {
		Member account = memberDI.readAccount(member.getAccount());
		boolean accountExist = account ==null ? false : true;

		if(accountExist)
		{
			memberDI.update(member);
		}
		
		return accountExist;
	}

	@Override
	public boolean delete(Member member) {
		Member account = memberDI.readAccount(member.getAccount());
		boolean accountExist = account ==null ? false : true;
		
		if(accountExist)
		{
			memberDI.delete(member);
		}
		
		return accountExist;
	}
}
