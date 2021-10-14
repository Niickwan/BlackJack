package com.company;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardsOnHand = new ArrayList<Card>();
    private boolean hasAce;
    private boolean hasAceUsed;
    private boolean bust;
    private int score;

    public Player() {
        this.hasAce = false;
        this.hasAceUsed = false;
        this.bust = false;
        this.score = 0;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(Card card) {
        this.cardsOnHand.add(card);
    }

    public int checkScore() { // Check / get score, count value for every card on hand
        int add = 0;
        this.score = 0;
        for (int i = 0; i < this.cardsOnHand.size(); i++) {
            add = Card.getScore(this.cardsOnHand.get(i));
            this.score = this.score + add;
            if (add == 11) {
                if (this.score > 21) {
                    this.score = this.score - 10;
                }
            }
        }
        if (this.hasAce && this.score > 21) { // Tjek om Es er taget i brug eller ej, hvis ikke og score
            this.score = this.score - 10; // er mere end 21, ændre Es værdi fra 11 til 1
        }

        if (this.score > 21) {
            this.bust = true;
        }
        return this.score;
    }

    public void reset(){ // Reset deafult values and clear card deck
        this.hasAce = false;
        this.hasAceUsed = false;
        this.bust = false;
        this.score = 0;
        this.cardsOnHand.clear();
    }

    public boolean getBust() {
        return this.bust;
    }

    public void setBust(boolean bust) {
        this.bust = true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

