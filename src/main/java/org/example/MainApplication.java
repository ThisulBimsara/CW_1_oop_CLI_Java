package org.example;

import  java.io.*;
import java.util.Scanner;


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
