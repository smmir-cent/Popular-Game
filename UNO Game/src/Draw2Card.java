import java.util.ArrayList;
/**
 * this class is a class for +2 cards.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class Draw2Card extends MarkedCard {
    public Draw2Card(int color) {
        super(color);
    }

    @Override
    public boolean isLegal(Card exCard, ArrayList<Card> cards) {
        //both of them are +2 card
        if(exCard instanceof Draw2Card)
            return true;
        //have same color
        return exCard.getColor()==this.getColor();
    }

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
        System.out.println("|       +2      |");
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|"+ANSI_RESET);

    }
}
