import java.util.ArrayList;
/**
 * this class is a class for wild color cards.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class WildColorCard extends ColorlessCard {
    public WildColorCard(int nextColor) {
        super(nextColor);
    }

    @Override
    public boolean isLegal(Card exCard, ArrayList<Card> cards) {
        //always we van use it but we must set the next color manually
        return true;
    }

    @Override
    void printCard() {
        System.out.println("|$$$$$$$$$$$$$$$|");
        System.out.println("|               |");
        System.out.println("|   Wild Color  |");
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|");
    }
}
