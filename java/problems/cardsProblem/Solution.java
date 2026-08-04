import java.util.Scanner;

class Card {
    public int value;
    public String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

}

class Solution {
    public static void main() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("value for card1: ");
            int value1 = sc.nextInt();
            sc.nextLine();

            System.out.print("suit for card1: ");
            String suit1 = sc.nextLine();
            Card card1 = new Card(value1, suit1);
            System.out.println("");

            System.out.print("value for card2: ");
            int value2 = sc.nextInt();
            sc.nextLine();

            System.out.print("suit for card2: ");
            String suit2 = sc.nextLine();
            Card card2 = new Card(value2, suit2);

            System.out.println("value: " + lightest(card1, card2));
            System.out.println("same suit: " + sameSuit(card1, card2));
        }
    }

    public static int lightest(Card card1, Card card2) {
        if (card1.value == card2.value)
            return 0;
        if (card1.value > card2.value)
            return 1;
        return -1;
    }

    public static boolean sameSuit(Card card1, Card card2) {
        return card1.suit.equals(card2.suit);
    }
}
