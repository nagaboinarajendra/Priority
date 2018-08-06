package com.priorityqueue;

import java.io.IOException;
import java.util.Scanner;

import com.priorityqueue.exceptions.InvalidChoiceException;
import com.priorityqueue.exceptions.ParkingSpaceException;
/**
 * provides facitities to user.
 * @author rajendra
 */
public class App {
    /**
     * method to take the input from the console.
     * @return Scanner object
     */
    public static Scanner inputFromConsole() {
         return new Scanner(System.in);
    }
    /**
     * displays menu to the admin.
     * admin can park, unpark and exit from operations
     * @throws InvalidChoiceException
     * @throws ParkingSpaceException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void proceedToOperations() throws
        InvalidChoiceException, ParkingSpaceException, IOException {
        /**
         * choice of menu.
         */
        int choice;
        /**
         * continue to display menu.
         */
        boolean willingToContinue = true;
        System.out.println("Enter the total number of Parking Slots");
        ParkingSpace parkingSpace =
                new ParkingSpace(inputFromConsole().nextInt());
        /**
         * load cars from transaction file.
         */
        intitializeSlots(parkingSpace);
        while (willingToContinue) {
            System.out.print("Select an option from the menu:\n1.Park Vehicle"
                    + "\n2.Unpark Vehicle\n3.Display parking Space\n4.Exit");
            choice = inputFromConsole().nextInt();
            switch (choice) {
            case 1:
                ParkVehicle park = new ParkVehicle();
                System.out.println("Enter the car Number:");
                if (parkingSpace.getSlotsRemaining() > 0) {
                    park.parkCar(inputFromConsole().next(), parkingSpace);
                } else {
                    throw new ParkingSpaceException("parking Space is full");
                }
                break;
            case 2:
                UnParkVehicle unpark = new UnParkVehicle();
                System.out.println("Enter the car Number");
                unpark.unParkCar(inputFromConsole().next(), parkingSpace);
                break;
            case 3:
                parkingSpace.displayParkingSpace(parkingSpace);
                break;
            case 4:
                willingToContinue = false;
                FileOperation write = new FileOperation();
                write.writeToFile(parkingSpace.queue);
                System.out.println("Thankyou for visiting!!");
                break;
            default: throw new InvalidChoiceException("invalid choice");
            }
        }
    }
    /**
     * @param parkingSpace
     * @throws IOException
     */
    private static void intitializeSlots(ParkingSpace parkingSpace) throws
    IOException {
        Slot.car = new String[parkingSpace.getTotalSlots() + 1];
        Slot.intime = new Long[parkingSpace.getTotalSlots() + 1];
        FileOperation fo = new FileOperation();
        fo.ReadFromFile(parkingSpace);
    }
    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws ClassNotFoundException,
    IOException {
        boolean isAdminValid = false;
        Admin admin = new Admin();
        try {
        System.out.println("enter admin name and password");
        isAdminValid = admin.validateAdmin(inputFromConsole().next()
            , inputFromConsole().next());
        } catch (Exception message) {
            message.printStackTrace();
        }
        if (isAdminValid) {
            try {
            proceedToOperations();
            } catch (InvalidChoiceException message) {
                message.printStackTrace();
            } catch (ParkingSpaceException message) {
                message.printStackTrace();
            }
        }
    }
}
