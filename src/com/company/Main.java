package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Dealer dealer = new Dealer();
        Users users = new Users();

        boolean manageAccount = true;
        boolean runGame = true;
        boolean blackJack = false;

        System.out.println("----------");
        System.out.println("| Login: |");
        System.out.println("-------------------------------------------");
        System.out.print("| Brugernavn: ");
        String navn = scanner.next();
        System.out.println("-------------------------------------------\n");
        System.out.println("-------------------------------------------");
        users.loginOrCreateUser(navn);
        System.out.println("-------------------------------------------\n");
        System.out.println("-------------------------------------------");
        manageAccount(scanner, users, manageAccount);

        while (runGame) {
            Card.reset();
            player.reset();
            dealer.reset();
            ArrayList<ArrayList> finalDeck = Card.BlackjackDeck();
            boolean playerStay = false;
            boolean dealerStay = false;
            boolean dealCards = true;
            boolean betValid = false;
            double bet = 0;

            System.out.println("\n******************************");
            System.out.println("*----------------------------*");
            System.out.println("*| Velkommen til BlackJack! |*");
            System.out.println("*----------------------------*");
            System.out.println("******************************");
            System.out.println("\n-------------------------------------------");
            while (!betValid) {
                System.out.print("Hvor meget vil du satse?: ");
                bet = scanner.nextDouble();
                if (bet > users.getBalance()) {
                    System.out.println("Du kan ikke satse mere end du har på kontoen!");
                } else if (bet == 0) {
                    System.out.println("Du bliver nød til at satse noget?!");
                } else {
                    betValid = true;
                }
            }
            System.out.println("-------------------------------------------");



            // Del 2 kort til både player og dealer
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) {
                    System.out.print("Dealer deler 1 kort til dig: ");
                    Card card = Card.drawRandomCard(finalDeck);
                    System.out.println(card.toString(card));
                    player.setCardsOnHand(card);
                } else {
                    System.out.print("Dealer deler 1 kort til sig: ");
                    Card card = Card.drawRandomCard(finalDeck);
                    System.out.println(card.toString(card));
                    dealer.setCardsOnHand(card);
                }
            }

            // Vis kort og score
            System.out.println("------------------------------");
            System.out.println("Dine kort: ");
            for (int i = 0; i < player.getCardsOnHand().size(); i++) {
                ASCII.printCard(player.getCardsOnHand().get(i));
            }
            System.out.println("Du har: " + player.checkScore());
            System.out.println("------------------------------");
            System.out.println("Dealers kort: ");
            printAscii(dealer);

            // Tjek om der er blackjack efter de første 4 kort er delt.
            if (player.checkScore() == 21) {
                blackJack = true;
            } else if (dealer.checkScore() == 21) {
                blackJack = true;
            }

            // Skift mellem spiller og dealer får et kort (baseret på om brugeren vil have et kort, og om dealer har under 16)
            while (dealCards) {
                if (!playerStay) {
                    // Draw or stay?
                    System.out.print("Draw or stay? (D/S): ");
                    String playerInput = scanner.next();
                    if (playerInput.equalsIgnoreCase("s")) { // Stay * set playerStay = true
                        playerStay = true;
                    } else if (playerInput.equalsIgnoreCase("d")) { // Draw another card
                        Card card = Card.drawRandomCard(finalDeck); // Draw random card from deck
                        player.setCardsOnHand(card); // Set card in players hand
                        for (int i = 0; i < player.getCardsOnHand().size(); i++) { // Print card ASCII format
                            ASCII.printCard(player.getCardsOnHand().get(i));
                        }
                        System.out.println("You have: " + player.checkScore()); // Players score
                        System.out.println("------------------------------");
                        if (player.checkScore() > 21) {
                            dealCards = false;
                            player.setBust(true);
                        }
                    } else {
                        System.err.println("Forkert input prøv igen!");
                    }
                }
                if (!dealerStay && !player.getBust() && dealer.checkScore() <= 16 && dealCards && playerStay) { // Draw card if under 16
                    Card card = Card.drawRandomCard(finalDeck);
                    dealer.setCardsOnHand(card);
                    printAscii(dealer);
                } else if (dealer.checkScore() >= 16 && dealer.checkScore() <= 21) { // Dealer score between 16-21 = stay
                    dealerStay = true;
                } else if (dealer.checkScore() > 21) { // Dealer score < 21 Skip draw card loop and set busted status to true.
                    dealCards = false;
                    dealer.setBust(true);
                }

                if (player.getBust()) {
                    System.out.println("Du bustede og tabte!");
                    users.betLost(bet);
                    dealCards = false;
                    System.out.println("Din nye saldo er: " + users.getBalance() + "\n\n");;
                }
                if (dealer.getBust()) {
                    System.out.println("Dealer bustede, med: " + dealer.getScore() + ", du vandt!");
                    users.betWon(bet, blackJack);
                    dealCards = false;
                    System.out.println("Din nye saldo er: " + users.getBalance() + "\n\n");
                }

                if (dealerStay && playerStay) { // If both player and dealer stay exit draw card loop
                    dealCards = false;
                }
            }

            if (!player.getBust() && !dealer.getBust()) { // Run if, if player and dealer not busted
                if (player.checkScore() > dealer.checkScore()) {
                    System.out.println("Du vandt!");
                    System.out.print("Du har: " + player.checkScore() + " og dealer har: " + dealer.getScore() + "\n");
                    users.betWon(bet, blackJack);
                    System.out.println("Din nye saldo er: " + users.getBalance() + "\n\n");
                } else if (player.checkScore() < dealer.checkScore()) {
                    System.out.println("Du tabte!");
                    System.out.print("Du har: " + player.checkScore() + " og dealer har: " + dealer.getScore() + "\n");
                    users.betLost(bet);
                    System.out.println("Din nye saldo er: " + users.getBalance() + "\n\n");
                } else if (player.checkScore() == dealer.checkScore()) {
                    System.out.println("Den blev uafgjort!\n\n");
                }
            }
            System.out.print("Vil du prøve igen? (Y/N): ");
            String goAgain = scanner.next();
            if (goAgain.equalsIgnoreCase("n")) {
                runGame = false;
            }
        }
    }

    private static void manageAccount(Scanner scanner, Users users, boolean manageAccount) {
        while (manageAccount) {
            System.out.print("Indebetal eller spil? (I/S): ");
            String depositOrPlay = scanner.next();
            if (depositOrPlay.equalsIgnoreCase("i")) {
                System.out.println("-------------------------------------------");
                System.out.println("Din nuværende balance: " + users.getBalance());
                System.out.print("Hvad vil du ha på din saldo? (Max saldo 500 kr): ");
                int indbetal = scanner.nextInt();
                users.indbetal(indbetal);
                System.out.println("\nDin nye saldo er: " + users.getBalance());
                System.out.println("-------------------------------------------");
            } else if (depositOrPlay.equalsIgnoreCase("s")) {
                manageAccount = false;
                System.out.println("-------------------------------------------");
            } else {
                System.out.println("Forket indput, prøv igen!");
            }
        }
    }

    private static void printAscii(Dealer dealer) {
        for (int i = 0; i < dealer.getCardsOnHand().size(); i++) { // Print card ASCII format
            if (i == 0) {
                ASCII.printCard(dealer.getCardsOnHand().get(i));
            } else {
                ASCIIBlank.printCard(dealer.getCardsOnHand().get(i));
            }
        }
        //System.out.println("Dealer har: " + dealer.checkScore()); // Players score
        //System.out.println("------------------------------");
    }
}
