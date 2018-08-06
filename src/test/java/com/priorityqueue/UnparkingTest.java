package com.priorityqueue;

import com.priorityqueue.exceptions.CarNumberInvalidException;

import junit.framework.TestCase;

public class UnparkingTest extends TestCase {
	
	public void testParkCar() {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new String[parkingSpace.getTotalSlots() + 1];
		Slot.intime =new Long[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		park.parkCar("ap29cb1118", parkingSpace);
		park.parkCar("ap29cb1112", parkingSpace);
		park.parkCar("ap29cb1118", parkingSpace);
		UnParkVehicle unpark = new UnParkVehicle();
		unpark.unParkCar("ap29cb11118", parkingSpace);
	}
	public void carAvailabilityTest() throws CarNumberInvalidException {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new String[parkingSpace.getTotalSlots() + 1];
		Slot.intime = new Long[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		park.parkCar("ap29cb1118", parkingSpace);
		UnParkVehicle unpark = new UnParkVehicle();
		unpark.isCarNumberValid("ap29cb1118");
	}
}