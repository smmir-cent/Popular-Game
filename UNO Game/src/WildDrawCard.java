import java.util.ArrayList;
/**
 * this class is a class for +4 cards.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class WildDrawCard extends ColorlessCard {

    public WildDrawCard(int nextColor) {
        super(nextColor);
    }


    @Override
    public boolean isLegal(Card exCard, ArrayList<Card> cards) {
        //both of them are +4 cards
        if(exCard instanceof WildDrawCard)
            return true;
        //to check we have another card to choose or not
        for (Card c:cards)
            if(!(c instanceof WildDrawCard) && c.isLegal(exCard,cards))
                return false;
        return true;
    }

    @Override
    void printCard() {
        System.out.println("|$$$$$$$$$$$$$$$|");
        System.out.println("|               |");
        System.out.println("|       +4      |");
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|");

    }
}
