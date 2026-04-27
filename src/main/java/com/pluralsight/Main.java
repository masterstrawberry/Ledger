package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> init = initialize();
        System.out.println(init.get(0).getVendor());

    }

    public void mainMenu(){

    }

    public void ledgerMenu(){

    }

    public static ArrayList<Transaction> initialize() {
        ArrayList<Transaction> ledger;
        try {
            ledger = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./transactions.csv"));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\\|");
                ledger.add(new Transaction(LocalDate.parse(arr[0]), LocalTime.parse(arr[1]), arr[2], arr[3], Double.parseDouble(arr[4])));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ledger;
    }

}