package com.priorityqueue;
/**
 * Slot holds the vehicle time and number.
 * @author rajendra
 */
public class Slot implements Comparable<Slot>{
    /**
     * slot number where vehicle is stored.
     */
    private int slotNumber;
    /**
     * array of cars.
     */
    static String[] car;
    /**
     * arrat of intime.
     */
    static Long[] intime;
    /**
     * default constructor.
     */
    public Slot() { }
    /**
     * @param slot where vehicle is parked.
     * @param carNumber
     * @param inTime of vehicle
     */
    public Slot(int slot, String carNumber, long inTime) {
        this.slotNumber = slot;
        Car present = new Car(carNumber);
        car[slot] = present.getCarNumber();
        InTime time = new InTime(inTime);
        intime[slot] = time.getInTime();
    }
    /**
     * @return the slotNumber
     */
    public int getSlotNumber() {
        return slotNumber;
    }
    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    /**
     * @return the string of vehicle details.
     */
    public String toString() {
        return this.getSlotNumber() + " " + Slot.car[this.getSlotNumber()] + " "
                + Slot.intime[this.getSlotNumber()];
    }
	@Override
	public int compareTo(Slot next) {
		
		return 0;
	}
}
