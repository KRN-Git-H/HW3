package service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.impl.ParkingRecordDaoImpl;
import model.ParkingRecord;
import service.ParkingRecordService;


public class ParkingRecordServiceImpl implements ParkingRecordService {

	public static void main(String[] args) {

	}
	
	private static ParkingRecordDaoImpl parkingRecordDI=new ParkingRecordDaoImpl();

	@Override
	public boolean create(ParkingRecordService monthrent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ParkingRecord> readAll() {
		return parkingRecordDI.readAll();
	}

	@Override
	public List<ParkingRecord> readLicense(String license) {
		return parkingRecordDI.readLicense(license);
	}
}
