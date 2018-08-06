package com.priorityqueue;
/**
 * @author rajendra
 */
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.priorityqueue.exceptions.CarNumberInvalidException;
/**
 * parks the vehicle into parking space.
 */
public class ParkVehicle {
    /**
     * if car is valid and not present in parking space,
     * then it is placed into slot.
     * @param carNumber
     * @param parkingSpace
     */
    public void parkCar(String carNumber, ParkingSpace parkingSpace) {
        try {
        if (isCarNumberValid(carNumber) &&
                isCarNotPresent(carNumber, parkingSpace)) {
        if (!isEmptySlotsAvailable(parkingSpace)) {
            int emptySlot = parkingSpace.nextSlot.poll();
            Slot slot = new Slot(emptySlot, carNumber, new Date().getTime());
            parkingSpace.queue.add(slot);
            System.out.println("Parked successfully at slot " + emptySlot);
        } else {
            Slot slot = new Slot(parkingSpace.getNextSlotNumber(), 
                    carNumber, new Date().getTime());
            parkingSpace.queue.add(slot);
            System.out.println("Parked successfully at slot "
                    + parkingSpace.getNextSlotNumber());
            parkingSpace.setSlotNumber(parkingSpace.getNextSlotNumber() + 1);
        }
        parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
        }
        } catch (CarNumberInvalidException message) {
            message.printStackTrace();
        }
    }
    /**
     * checks for empty slots.
     * @param parkingSpace
     * @return
     */
    private boolean isEmptySlotsAvailable(ParkingSpace parkingSpace) {
        return parkingSpace.nextSlot.isEmpty();
    }
    /**
     * validates the car numnber.
     * @param carNumber
     * @return true if car number is valid else false
     * @throws CarNumberInvalidException
     */
    private boolean isCarNumberValid(String carNumber)
            throws CarNumberInvalidException{
        int len = carNumber.length();
        boolean isValid = false;
        if (len == 10) {
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
     * checks for presence of car.
     * @param carNumber
     * @return
     */
    private boolean isCarNotPresent(String carNumber, ParkingSpace parkingSpace) {
        boolean isNotPresent = true;
        Iterator<Slot> itr = parkingSpace.queue.iterator();
        while (itr.hasNext()) {  
            Slot nextslot = itr.next();
            if (Slot.car[nextslot.getSlotNumber()].equals(carNumber)) {
                isNotPresent = false;
            }
        }
        return isNotPresent;
    }
}

