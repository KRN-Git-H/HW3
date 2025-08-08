package dao;

import java.util.List;
import model.ParkingRecord;

public interface ParkingRecordDao {
	//create
	void add(ParkingRecord parkingrecord);
	
	//read
	List<ParkingRecord> readAll();	
	List<ParkingRecord> readLicense(String license);

	//update
	
	//delete

}
