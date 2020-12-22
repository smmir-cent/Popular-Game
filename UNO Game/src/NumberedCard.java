import java.util.ArrayList;
/**
 * this class is a class for numbered cards.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class NumberedCard extends ColoredCard{
    //number of card
    private int number;

    /**
     *
     * @param color color of card
     * @param number number of card
     */
    public NumberedCard(int color ,int number) {
        super(color);
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean isLegal(Card exCard, ArrayList<Card> cards) {
        //number of cards must be equal
        if(exCard instanceof NumberedCard)
            if (((NumberedCard) exCard).getNumber()==this.getNumber())
                return true;
        //or color of them
        return exCard.getColor()==this.getColor();

    }
    /**
     * this method prints  number of cards in beautiful format.
     */
    @Override
    void printCard() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String temp;
        switch (this.getColor()){
            case 1:
                temp=ANSI_RED;
                break;
            case 2:
                temp=ANSI_YELLOW;
                break;
            case 3:
                temp=ANSI_GREEN;
                break;
            default:
                temp=ANSI_BLUE;
                break;
        }
        System.out.println(temp+"|$$$$$$$$$$$$$$$|");
        System.out.println("|               |");
        System.out.println("|       "+this.getNumber()+"       |");
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|"+ANSI_RESET);

    }
}
