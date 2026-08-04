class solutionBytes {
    public static void main(String[] args) {
        int card1Val = 10;
        int card1Suit = 0;
        byte card1 = (byte) (((card1Suit & 0x03) << 4) | (card1Val & 0x0F));
        int card2Val = 12;
        int card2Suit = 3;
        byte card2 = (byte) (((card2Suit & 0x03) << 4) | (card2Val & 0x0F));

        System.out.println(highest(card1, card2));
        System.out.println(sameSuit(card1, card2));
    }

    // 0: same
    // 1: card1 > card2
    // -1: card1 < card2
    public static int highest(byte card1, byte card2) {
        return (card1 & 0x0F) == (card2 & 0x0F) ? 0 : (card1 & 0x0F) > (card2 & 0x0F) ? 1 : -1;
    }

    public static boolean sameSuit(byte card1, byte card2) {
        return (card1 & 0x30) == (card2 & 0x30);
    }
}
