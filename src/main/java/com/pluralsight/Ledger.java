package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.Report.reportMenu;

public class Ledger {
    public static void ledgerMenu(Scanner scanner, ArrayList<Transaction> arr){
        while (true){
            System.out.println("""
             A) All
             D) Deposits
             P) Payments
             R) Reports
             H) Home
             """);
            String response = scanner.nextLine().toLowerCase();
            switch (response){
                case "a":
                    allEntries(arr);
                    break;
                case "d":
                    allDeposits(arr);
                    break;
                case "p":
                    allPayments(arr);
                    break;
                case "r":
                    reportMenu(scanner, arr);
                    break;
                default:
                    return;
            }
        }
    }

    public static void allEntries(ArrayList<Transaction> arr){
        for (Transaction transaction : arr) {
            System.out.println(transaction.toString());
        }
    }

    public static void allDeposits(ArrayList<Transaction> arr){
        for (Transaction transaction : arr) {
            if (transaction.getAmount() >= 0) {
                System.out.println(transaction.toString());
            }
        }
    }

    public static void allPayments(ArrayList<Transaction> arr){
        for (Transaction transaction : arr) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.toString());
            }
        }
    }
}
