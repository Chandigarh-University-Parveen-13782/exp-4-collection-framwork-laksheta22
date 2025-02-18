import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class Card {
    String rank;
    String suit;
    
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String toString() {
        return rank + " of " + suit;
    }
}

public class CardDeck {
    private static Collection<Card> deck = new ArrayList<>();
    
    public static void initializeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }
    
    public static void displayCardsBySuit(String suit) {
        boolean found = false;
        for (Card card : deck) {
            if (card.getSuit().equalsIgnoreCase(suit)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for the suit: " + suit);
        }
    }
    public static void main(String[] args) {
        initializeDeck();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the suit to find the cards (Hearts, Diamonds, Clubs, Spades): ");
        String suit = scanner.nextLine();
                displayCardsBySuit(suit);
    }
}
