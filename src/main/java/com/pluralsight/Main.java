package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> arr = initialize();
        mainMenu(scanner, arr);
//        System.out.println(init.get(0).getVendor());

    }

    public static void mainMenu(Scanner scanner, ArrayList<Transaction> arr){
        while (true){
            System.out.println("""
                    
                    Menu below:
                    D) Add Deposit\s
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                    Enter your option:\s""");
            String response = scanner.nextLine().toLowerCase();
            switch (response){
                case "d":
                    addDeposit(scanner, arr);
                    break;
                case "p":
                    makePayment(scanner,arr);
                    break;
                case "l":
//                    ledgerMenu(scanner);
                    break;
                default:
                    return;
            }
        }
    }


    public static void addDeposit(Scanner scanner, ArrayList<Transaction> arr){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./transactions.csv",true));
            System.out.println("Enter description:");
            String desc = scanner.nextLine();
            System.out.println("Enter vendor:");
            String vendor = scanner.nextLine();
            System.out.println("Enter amount:");
            Double deposit = scanner.nextDouble();
            scanner.nextLine();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
            arr.add(new Transaction(date, time, desc, vendor, deposit));
            bufferedWriter.write(date+"|"+time+"|"+desc+"|"+vendor+"|"+deposit);
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void makePayment(Scanner scanner, ArrayList<Transaction> arr){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./transactions.csv",true));
            System.out.println("Enter description:");
            String desc = scanner.nextLine();
            System.out.println("Enter vendor:");
            String vendor = scanner.nextLine();
            System.out.println("Enter amount:");
            Double deposit = -scanner.nextDouble();
            scanner.nextLine();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
            arr.add(new Transaction(date, time, desc, vendor, deposit));
            bufferedWriter.write(date+"|"+time+"|"+desc+"|"+vendor+"|"+deposit);
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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