package org.example;

import  java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


class ConfigData {
    private int totalTicketsAvailable;
    private int ticketAddRate;
    private int customerPurchaseRate;
    private int maxStorage;

    // Constructor to initialize values
    public ConfigData(int totalTicketsAvailable, int ticketAddRate, int customerPurchaseRate, int maxStorage) {
        this.totalTicketsAvailable = totalTicketsAvailable;
        this.ticketAddRate = ticketAddRate;
        this.customerPurchaseRate = customerPurchaseRate;
        this.maxStorage = maxStorage;
    }

    // Default constructor for flexibility
    public ConfigData() {}

    // Getters and Setters
    public int getTotalTicketsAvailable() {
        return totalTicketsAvailable;
    }
    public void setTotalTicketsAvailable(int totalTicketsAvailable) {
        this.totalTicketsAvailable = totalTicketsAvailable;
    }
    public int getTicketAddRate() {
        return ticketAddRate;
    }
    public void setTicketAddRate(int ticketAddRate) {
        this.ticketAddRate = ticketAddRate;
    }
    public int getCustomerPurchaseRate() {
        return customerPurchaseRate;
    }
    public void setCustomerPurchaseRate(int customerPurchaseRate) {
        this.customerPurchaseRate = customerPurchaseRate;
    }
    public int getMaxStorage() {
        return maxStorage;
    }
    public void setMaxStorage(int maxStorage) {
        this.maxStorage = maxStorage;
    }

    @Override
    public String toString() {
        return String.format("ConfigData { Total Tickets Available = %d, Ticket Add Rate = %d, Customer Purchase Rate = %d, Max Storage = %d }",
                totalTicketsAvailable, ticketAddRate, customerPurchaseRate, maxStorage);
    }
}

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }
    //Load settings in file

}
class TicketBox {
    private final List<Integer> ticketsList;

    public TicketBox(int initialTicketCount) {
        ticketsList = Collections.synchronizedList(new Vector<>());
        for (int i = 0; i < initialTicketCount; i++) {
            ticketsList.add(1); // Each entry represents a ticket
        }
    }

    public synchronized void addTickets(int number) {
        for (int i = 0; i < number; i++) {
            ticketsList.add(1);
        }
        System.out.println(number + " tickets added. Total available tickets: " + ticketsList.size());
    }

    public synchronized boolean buyTicket() {
        if (ticketsList.size() > 0) {
            ticketsList.remove(0);
            System.out.println("Ticket purchased. Total available tickets: " + ticketsList.size());
            return true;
        } else {
            System.out.println("No tickets available to buy.");
            return false;
        }
    }

    public synchronized int getAvailableTicketsCount() {
        return ticketsList.size();
    }
}

class TicketManager {
    private final ConfigData settings;
    private final TicketBox ticketBox;
    private volatile boolean isActive = false;

    public TicketManager(ConfigData settings) {
        this.settings = settings;
        this.ticketBox = new TicketBox(settings.getTotalTicketsAvailable());
    }

    public void runSystem() {
        isActive = true;
        new Thread(new TicketAdder()).start();
        new Thread(new CustomerBuyer()).start();
    }

    public void stopSystem() {
        isActive = false;
    }

    private class TicketAdder implements Runnable {
        @Override
        public void run() {
            while (isActive) {
                synchronized (ticketBox) {
                    if (ticketBox.getAvailableTicketsCount() < settings.getMaxStorage()) {
                        ticketBox.addTickets(1); // Add one ticket at a time
                    }
                }
                try {
                    Thread.sleep(1000 / settings.getTicketAddRate());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private class CustomerBuyer implements Runnable {
        @Override
        public void run() {
            while (isActive) {
                synchronized (ticketBox) {
                    ticketBox.buyTicket();
                }
                try {
                    Thread.sleep(1000 / settings.getCustomerPurchaseRate());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
