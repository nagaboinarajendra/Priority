package com.priorityqueue;

import junit.framework.TestCase;

public class ParkingTest extends TestCase {
	public void testParkCar() {
		ParkingSpace parkingSpace = new ParkingSpace(50);
		Slot.car = new String[parkingSpace.getTotalSlots() + 1];
		Slot.intime =new Long[parkingSpace.getTotalSlots() + 1];
		ParkVehicle park = new ParkVehicle();
		park.parkCar("ap29cb1118", parkingSpace);
		park.parkCar("ap29cb1112", parkingSpace);
		park.parkCar("ap29cb1118", parkingSpace);
		
	}
}
