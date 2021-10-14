package com.company;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Users {
    private String user;
    private double balance;

    public void loginOrCreateUser(String navn) {
        this.user = navn;
        this.balance = 500;

        try {
            File users = new File("./src/com/company/users/" + this.user + ".txt"); // Create individual users file
            if (users.createNewFile()) {
                System.out.println("Velkommen til: " + this.user + ", vi har oprettet dig en bruger!");
                try {
                    FileWriter usersWrite = new FileWriter("./src/com/company/users/" + this.user + ".txt");
                    usersWrite.write("500"); // Write 500 "kr" til brugeres fil
                    usersWrite.close();
                    System.out.println("500 kr blev tilføjet til din konto!");
                } catch (IOException e) {
                    System.err.println("Der skete en fejl");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Velkommen tilbage: " + this.user);
                Scanner userReader = new Scanner(users);
                while (userReader.hasNextLine()) {
                    this.balance = Double.parseDouble(userReader.nextLine());
                    System.out.println("Din saldo er: " + this.balance);
                }
                userReader.close();
            }
        } catch (IOException e) {
            System.err.println("Der skete en fejl");
            e.printStackTrace();
        }
    }

    public double betLost (double bet) {
        double newBalance = this.balance - bet;
        return this.balance = writeBalance(newBalance);
    }

    public double betWon (double bet, boolean blackjack) {
        if (!blackjack) {
            double newBalance = this.balance + bet;
            this.balance = writeBalance(newBalance);
        } else if (blackjack) {
            double newBalance = this.balance + (bet * 1.5);
            this.balance = writeBalance(newBalance);
        }
        return this.balance;
    }

    private double writeBalance(double newBalance) {
        try { // Write new balance to individual users file
            FileWriter usersWrite = new FileWriter("./src/com/company/users/" + this.user + ".txt");
            usersWrite.write(String.valueOf(newBalance));
            usersWrite.close();
        } catch (IOException e) {
            System.err.println("Der skete en fejl");
            e.printStackTrace();
        }
        return newBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void indbetal(int indbetal) {
        if (indbetal <= 500 && indbetal > this.balance) {
            this.balance = writeBalance(indbetal);
        } else if (indbetal < this.balance){
            System.out.println("Du har allerede penge på kontoen");
        } else if (indbetal > 500) {
            System.out.println("Du kan ikke indbetale mere end 500 kr!");
        }
    }
}
