package com.pluralsight;

import java.util.Scanner;

public class Ledger {
    public void ledgerMenu(Scanner scanner){
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
                    break;
                case "p":
                    break;
                case "l":
                    System.out.println("ll");;
                    break;
                default:
                    return;
            }
        }
    }



}
