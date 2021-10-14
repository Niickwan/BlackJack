package com.company;

public class ASCIIBlank {



    public static void printCard(Card card){
        System.out.println("┌────────┐");
        if(Card.asciiRank(card)==1)
            System.out.print("│        │");
        else if(Card.asciiRank(card) < 10)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 10)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 11)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 12)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 13)
            System.out.print("│        │");
        System.out.print("\n│        │");
        if(Card.asciiSuit(card) == 1)
            System.out.print("\n│        │");
        else if(Card.asciiSuit(card) == 2)
            System.out.print("\n│        │");
        else if(Card.asciiSuit(card) == 3)
            System.out.print("\n│        │");
        else
            System.out.print("\n│        │");
        System.out.println("\n│        │");
        if(Card.asciiRank(card)==1)
            System.out.print("│        │");
        else if(Card.asciiRank(card) < 10)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 10)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 11)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 12)
            System.out.print("│        │");
        else if(Card.asciiRank(card) == 13)
            System.out.print("│        │");
        System.out.println("\n└────────┘");
    }

}

/*
 System.out.println("\u2663");    //CLUBS
        System.out.println("\u2665");    //HEARTS
        System.out.println("\u2660");    //SPADES
        System.out.println("\u25C6");    //DIAMONDS


        System.out.println("┌──────────────┐");
        System.out.println("│ \u2663            │");
        System.out.println("│              │");
        System.out.println("│              │");
        System.out.println("│      5       │");
        System.out.println("│              │");
        System.out.println("│              │");
        System.out.println("│            \u2663 │");
        System.out.println("└──────────────┘");

*/