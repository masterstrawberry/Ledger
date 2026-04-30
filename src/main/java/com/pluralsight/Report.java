package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Report {
    public static void reportMenu(Scanner scanner, ArrayList<Transaction> arr){
        while (true){
            System.out.println("""
             1) Month To Date
             2) Previous Month
             3) Year To Date
             4) Previous Year
             5) Search by Vendor
             6) Custom Search
             0) Back to Ledger
             """);
            String response = scanner.nextLine();
            switch (response){
                case "1":
                    monthToDate(arr);
                    break;
                case "2":
                    previousMonth(arr);
                    break;
                case "3":
                    yearToDate(arr);
                    break;
                case "4":
                    previousYear(arr);
                    break;
                case "5":
                    byVendor(scanner,arr);
                    break;
                case "6":
                    customSearch(scanner,arr);
                    break;
                default:
                    return;
            }
        }
    }

    public static void monthToDate(ArrayList<Transaction> arr){
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        List<Transaction> filtered = arr.stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth) && !t.getDate().isAfter(today))
                .toList();
        for (Transaction transaction: filtered) {
            System.out.println(transaction.toString());
        }
    }

    public static void previousMonth(ArrayList<Transaction> arr) {
        LocalDate target = LocalDate.now().minusMonths(1);
        List<Transaction> filtered = arr.stream()
                .filter(t -> {
                    LocalDate d = t.getDate();
                    return d.getMonth() == target.getMonth() &&
                            d.getYear() == target.getYear();
                }).toList();

        for (Transaction transaction: filtered) {
            System.out.println(transaction.toString());
        }
    }

    public static void yearToDate(ArrayList<Transaction> arr) {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        List<Transaction> filtered = arr.stream()
                .filter(t -> {
                    LocalDate d = t.getDate();
                    // Check if date is within [January 1st, Today]
                    return !d.isBefore(startOfYear) && !d.isAfter(today);
                }).toList();

        for (Transaction transaction: filtered) {
            System.out.println(transaction.toString());
        }
    }

    public static void previousYear(ArrayList<Transaction> arr) {
        int lastYear = LocalDate.now().minusYears(1).getYear();
        LocalDate startOfLastYear = LocalDate.of(lastYear, 1, 1);
        LocalDate endOfLastYear = LocalDate.of(lastYear, 12, 31);

        List<Transaction> filtered = arr.stream()
                .filter(t -> {
                    LocalDate d = t.getDate();
                    return !d.isBefore(startOfLastYear) && !d.isAfter(endOfLastYear);
                }).toList();

        for (Transaction transaction: filtered) {
            System.out.println(transaction.toString());
        }
    }

    public static void byVendor(Scanner scanner,ArrayList<Transaction> arr){
        System.out.println("Enter vendor: ");
        String vendor = scanner.next().toLowerCase();
        scanner.nextLine();
        for (Transaction transaction : arr) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction.toString());
            }
        }
    }

    public static void customSearch(Scanner scanner, ArrayList<Transaction> arr) {
        scanner.nextLine();

        System.out.println("Enter start date (yyyy-mm-dd) or leave blank: ");
        String startDateInput = scanner.nextLine().trim();
        System.out.println("Enter end date (yyyy-mm-dd) or leave blank: ");
        String endDateInput = scanner.nextLine().trim();
        System.out.println("Enter description or leave blank: ");
        String description = scanner.nextLine().trim();
        System.out.println("Enter vendor or leave blank: ");
        String vendor = scanner.nextLine().trim();
        System.out.println("Enter amount or leave blank: ");
        String amountInput = scanner.nextLine().trim();

        System.out.println("\n--- Search Results ---");
        for (Transaction transaction : arr) {
            if (!startDateInput.isEmpty()) {
                LocalDate start = LocalDate.parse(startDateInput);
                if (transaction.getDate().isBefore(start)) continue;
            }

            if (!endDateInput.isEmpty()) {
                LocalDate end = LocalDate.parse(endDateInput);
                if (transaction.getDate().isAfter(end)) continue;
            }

            if (!description.isEmpty()) {
                if (!transaction.getDescription().toLowerCase().contains(description.toLowerCase())) continue;
            }

            if (!vendor.isEmpty()) {
                if (!transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())) continue;
            }

            if (!amountInput.isEmpty()) {
                double amount = Double.parseDouble(amountInput);
                if (transaction.getAmount() != amount) continue;
            }
            System.out.println(transaction);
        }
    }
    //        6) Custom Search - prompt the user for the following search values.
//        ▪ Start Date
//        ▪ End Date
//        ▪ Description
//        ▪ Vendor
//        ▪ Amount
//        o If the user enters a value for a field you should filter on that field
//        o If the user does not enter a value, you should not filter on that field
//

}
