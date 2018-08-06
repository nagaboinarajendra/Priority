package com.priorityqueue;

import java.io.IOException;

import junit.framework.TestCase;

public class TestFileOperation extends TestCase {
public void testWriteToFileOperation() throws IOException {
	ParkingSpace parkingSpace = new ParkingSpace(30);
	Slot.car = new String[parkingSpace.getTotalSlots() + 1];
	Slot.intime =new Long[parkingSpace.getTotalSlots() + 1];
	ParkVehicle park = new ParkVehicle();
	park.parkCar("ap29cb1118", parkingSpace);
	FileOperation fo = new FileOperation();
	fo.writeToFile(parkingSpace.queue);
	fo.ReadFromFile(parkingSpace);
}
public void testReadFromFileOperation() {
	
}
}
