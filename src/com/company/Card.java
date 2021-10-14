package com.company;
import java.util.ArrayList;
import java.util.Random;

public class Card {
    private final int rank;
    private final int suit;
    private int value;
    private static int deckSize= 51;

    public Card(int rank, int suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public static final String[] RANKS = {
            null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public static final String[] SUITS = {
            "Clubs", "Diamonds", "hearts", "Spades"};

    public String toString() {
        return RANKS[this.rank] + " of " + SUITS[this.suit] + " " + this.value;
    }

    public String toString(Card card){
        return Card.RANKS[this.rank] + " of " + Card.SUITS[this.suit];
    }

    public static int asciiRank(Card card) {
        return card.rank;
    }

    public static int asciiSuit(Card card) {
        return card.suit;
    }

    public static ArrayList<Card> makeDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        int value = 1;
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                if (rank == 1) {
                    value = 11; // Set ace value to 11
                    deck.add(new Card(rank, suit, value));
                    value = 2; // Set number after 1.... 2
                } else if (rank <= 9) {
                    deck.add(new Card(rank, suit, value));
                    value++;
                } else if (rank > 9) {
                    value = 10;
                    deck.add(new Card(rank, suit, value));
                }

            }
            value = 1;
        }
        return deck;
    }

    public static ArrayList<ArrayList> BlackjackDeck() {
        ArrayList<Card> deck1 = new ArrayList<>();
        ArrayList<Card> deck2 = new ArrayList<>();
        ArrayList<Card> deck3 = new ArrayList<>();
        ArrayList<Card> deck4 = new ArrayList<>();
        ArrayList<ArrayList> finalDeck = new ArrayList<>();

        int value = 1;
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                if (rank == 1) {
                    value = 11;
                    deck1.add(new Card(rank, suit, value));
                    deck2.add(new Card(rank, suit, value));
                    deck3.add(new Card(rank, suit, value));
                    deck4.add(new Card(rank, suit, value));
                    value = 2;
                } else if (rank <= 9) {
                    deck1.add(new Card(rank, suit, value));
                    deck2.add(new Card(rank, suit, value));
                    deck3.add(new Card(rank, suit, value));
                    deck4.add(new Card(rank, suit, value));
                    value++;
                } else if (rank > 9) {
                    value = 10;
                    deck1.add(new Card(rank, suit, value));
                    deck2.add(new Card(rank, suit, value));
                    deck3.add(new Card(rank, suit, value));
                    deck4.add(new Card(rank, suit, value));
                }
            }
            value = 1;
        }
        finalDeck.add(deck1);
        finalDeck.add(deck2);
        finalDeck.add(deck3);
        finalDeck.add(deck4);
        return finalDeck;
    }

    public static Card drawRandomCard(ArrayList finalDeck){
        Random random = new Random();
        int singleDeckIndex = random.nextInt(3);
        int singleCardIndex = random.nextInt(deckSize);
        ArrayList singleDeck = (ArrayList) finalDeck.get(singleDeckIndex);
        Card singleCard = (Card) singleDeck.get(singleCardIndex);
        singleDeck.remove(singleCard);
        deckSize--;
        return singleCard;
    }

    public ArrayList<Card> getCardsOnHand() {
        Player player = new Player();
        ArrayList<Card> cardsOnHand = player.getCardsOnHand();
        return cardsOnHand;
    }

    public static int checkCard(Card Card) {
        return Card.rank;
    }

    public static int getScore(Card card) {
        return card.value;
    }

    public static void reset() {
        deckSize = 51;
    }
}