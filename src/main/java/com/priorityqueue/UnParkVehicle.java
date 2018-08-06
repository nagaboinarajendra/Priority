package com.priorityqueue;
import java.util.Date;
/**
 * @author rajendra
 */
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.priorityqueue.exceptions.CarNotPresentException;
import com.priorityqueue.exceptions.CarNumberInvalidException;
/**
 * unparks the vehicle.
 */
public class UnParkVehicle {
	/**
	 * slot to be removed.
	 */
	public Slot slotToRemove;
	/**
	 * @param carNumber
	 * @param parkingSpace
	 */
	public void unParkCar(String carNumber, ParkingSpace parkingSpace) {
		FileOperation addToLog = new FileOperation();
		try {
		if (isCarNumberValid(carNumber)
				&& isCarPresent(carNumber, parkingSpace)) {
			parkingSpace.queue.remove(slotToRemove);
			parkingSpace.nextSlot.add(slotToRemove.getSlotNumber());
			parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
			long diff = new Date().getTime() - Slot.intime[slotToRemove.getSlotNumber()];
			System.out.println("Unparked successfully! Car parked for duration of:" 
						+ duration(diff));
			addToLog.writeToLogFile(slotToRemove);
		}
		} catch (CarNumberInvalidException message) {
			message.printStackTrace();
		} catch (CarNotPresentException message) {
			message.printStackTrace();
		}
	}
	/**
	 *
	 * @param carNumber
	 * @return
	 * @throws CarNumberInvalidException
	 */
	public boolean isCarNumberValid(String carNumber) 
			throws CarNumberInvalidException {
		boolean isValid = false;
		if (carNumber.length() > 0) {
			String regex = "^[a-zA-z]{2}[0-9]{2}[a-zA-z]{2}[0-9]{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(carNumber);
			isValid =  matcher.matches();
		} else {
			throw new CarNumberInvalidException("Invalid car number!");
		}
		return isValid;
	}
	/**
	 *
	 * @param carNumber
	 * @return
	 */
	public boolean isCarPresent(String carNumber, ParkingSpace parkingSpace)
			throws CarNotPresentException {
		boolean isPresent = false;
		Iterator<Slot> itr = parkingSpace.queue.iterator();
		while (itr.hasNext()) {
			Slot nextslot = itr.next();
			if (Slot.car[nextslot.getSlotNumber()].equals(carNumber)) {
				isPresent = true;
				slotToRemove = nextslot;
			}
		}
		if (isPresent) {
		} else {
			throw new CarNotPresentException("Can't find car in parking space");
		}
		return isPresent;
	}
	private String duration(long diff) {
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000) % 24;
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    System.out.print(diffDays + " days, ");
	    System.out.print(diffHours + " hours, ");
	    System.out.print(diffMinutes + " minutes, ");
	    System.out.print(diffSeconds + " seconds.");
	    System.out.println("\nThankyou For Using Parking Service");
		return null;
	}
}
