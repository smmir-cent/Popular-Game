import java.util.ArrayList;

/**
 * this class is super class for all kind of cards.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public abstract class Card {
    // the current color for colored card or next color for colorless card
    private int color;
    public Card(int color) {
        this.color = color;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    /**
     * this method show this card can choose or not according to the card the we have.
     * @param exCard last card that other player choose
     * @param cards list of our cards
     * @return true if we can choose it
     */
    abstract boolean isLegal(Card exCard, ArrayList<Card> cards);

    /**
     * this method prints cards in beautiful format.
     */
    abstract void printCard();
}
