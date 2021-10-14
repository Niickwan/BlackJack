package com.company;

public class ASCII {



    public static void printCard(Card card){
        System.out.println("┌────────┐");
        if(Card.asciiRank(card)==1)
            System.out.printf("│%s       │","A");
        else if(Card.asciiRank(card) < 10)
            System.out.printf("│%d       │", (Card.asciiRank(card)));
        else if(Card.asciiRank(card) == 10)
            System.out.printf("│%s      │", "10");
        else if(Card.asciiRank(card) == 11)
            System.out.printf("│%s       │", "J");
        else if(Card.asciiRank(card) == 12)
            System.out.printf("│%s       │", "Q");
        else if(Card.asciiRank(card) == 13)
            System.out.printf("│%s       │", "K");
        System.out.printf("\n│        │");
        if(Card.asciiSuit(card) == 1)
            System.out.printf("\n│    %c   │", '\u25C6');
        else if(Card.asciiSuit(card) == 2)
            System.out.printf("\n│    %c   │", '\u2665');
        else if(Card.asciiSuit(card) == 3)
            System.out.printf("\n│    %c   │", '\u2660');
        else
            System.out.printf("\n│    %c   │", '\u2663');
        System.out.println("\n│        │");
        if(Card.asciiRank(card)==1)
            System.out.printf("│       %s│","A");
        else if(Card.asciiRank(card) < 10)
            System.out.printf("│       %d│", (Card.asciiRank(card)));
        else if(Card.asciiRank(card) == 10)
            System.out.printf("│      %s│", "10");
        else if(Card.asciiRank(card) == 11)
            System.out.printf("│       %s│", "J");
        else if(Card.asciiRank(card) == 12)
            System.out.printf("│       %s│", "Q");
        else if(Card.asciiRank(card) == 13)
            System.out.printf("│       %s│", "K");
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