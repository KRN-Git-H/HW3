package service.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import dao.impl.MonthRentDaoImpl;
import model.Member;
import model.MonthRent;
import service.MonthRentService;

public class MonthRentServiceImpl implements MonthRentService{

	public static void main(String[] args) {
		
		
		//delete
		
		
//		MonthRent monthrent = new MonthRent();
//		monthrent.setLicense("a");
//		
//		System.out.print(monthrent.getId());
//		new MonthRentDaoImpl().delete(monthrent);

	}
	
	private static MonthRentDaoImpl monthRentDI=new MonthRentDaoImpl();
	
	@Override
	public boolean create(MonthRent monthrent) {
		MonthRent license = monthRentDI.readLicense(monthrent.getLicense());
		boolean licenseExist = license==null ? false : true;

		if(!licenseExist)
		{
			monthRentDI.create(license);
		}
		
		return !licenseExist;
	}

	@Override
	public MonthRent readLicense(String license) {
		return monthRentDI.readLicense(license);
	}

	@Override
	public List<MonthRent> readAll() {
		return monthRentDI.readAll();
	}
	
	@Override
	public List<MonthRent> readMethod(String paymentmethod) {
		return monthRentDI.readMethod(paymentmethod);
	}

	@Override
	public boolean update(MonthRent monthrent) {
		MonthRent license = monthRentDI.readLicense(monthrent.getLicense());
		boolean licenseExist = license==null ? false : true;
		
		if(licenseExist)
		{
			monthRentDI.update(license);
		}
		
		return licenseExist;
	}

	@Override
	public boolean delete(MonthRent monthrent) {
		MonthRent license = monthRentDI.readLicense(monthrent.getLicense());
		boolean licenseExist = license==null ? false : true;

		if(licenseExist)
		{
			monthRentDI.delete(monthrent);
		}
		
		return licenseExist;
	}



}
