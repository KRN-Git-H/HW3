package dao;

import java.util.List;

import model.MonthRent;
import model.Porder;

public interface MonthRentDao {
	//create
	void create(MonthRent monthrent);
	
	//read
	MonthRent readLicense(String license);
	List<MonthRent> readAll();
	List<MonthRent> readMethod(String Method);
	
	//update
	void update(MonthRent monthrent);
	
	//delete
	void delete(MonthRent monthrent);
}
