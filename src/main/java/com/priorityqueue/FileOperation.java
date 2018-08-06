package com.priorityqueue;
/**
 * @author rajendra
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
/**
 * read and write operations on file.
 */
public class FileOperation {
    /**
     * write the car info into file.
     * @param queue
     */
    public void writeToFile(PriorityQueue<Slot> queue) {
        try (BufferedWriter bw = new BufferedWriter (
                new FileWriter("Transaction.txt"))) {
        for (Object line : queue) {
            bw.write(line + "\n");
        }
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * read information from file.
     * @param parkingSpace
     * @throws IOException
     */
    public void ReadFromFile(ParkingSpace parkingSpace) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("Transaction.txt"));
        int lastSlot = 0;
        int[] temp = new int[parkingSpace.getTotalSlots()];
        while ((line = br.readLine()) != null) {
            String cvsSplitBy = " ";
            String[] record = line.split(cvsSplitBy);
            parkingSpace.queue.add(new Slot(Integer.parseInt(record[0]),
                    record[1], Long.valueOf(record[2])));
            parkingSpace.setSlotNumber(parkingSpace.getNextSlotNumber() + 1);
            parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
            temp[Integer.parseInt(record[0])] = Integer.parseInt(record[0]);
            lastSlot  = Integer.parseInt(record[0]);
        }
        if ((line = br.readLine()) == null) {
            updateQueue(lastSlot,temp, parkingSpace);
            parkingSpace.setSlotNumber(lastSlot + 1);
        }
         br.close();
    }
    /**
     * updates the queue with empty slots.
     * @param lastSlot
     * @param temp
     * @param parkingSpace
     */
    public void updateQueue(int lastSlot, int temp[], ParkingSpace parkingSpace) {
        for (int slot = 1; slot <= lastSlot; slot++) {
            if (temp[slot] == 0) {
                parkingSpace.nextSlot.add((slot));
            }
        }
    }
    public void writeToLogFile(Slot slot) {
    	try (BufferedWriter bw = new BufferedWriter (
                new FileWriter("LogFile.txt"))) {
        Object line = slot;
        bw.write(line + "\n");
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
