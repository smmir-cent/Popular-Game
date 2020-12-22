import java.util.ArrayList;
/**
 * this class is a class for human players.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class HumanPlayer extends Player {
    //the player's cards
    private ArrayList<Card> cards;
    public HumanPlayer() {
        cards=new ArrayList<>();
    }
    /**
     * this method is for adding a card to player's cards
     * @param c one card
     */
    public void addCard(Card c){
        cards.add(c);
    }
    public Card getCard(int index){
        return cards.get(index);
    }

    /**
     * to print human player's cards we can't use printcard method in card class because i want it to print cards in a row.
     */
    public void printCards(){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        //each row has a specific format to print
        for(int i=0;i<5;i++){
            if(i==0 || i==4){
                for (Card c:cards) {
                    String temp;
                    //find out the color
                    switch (c.getColor()){
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
                    if(c instanceof WildDrawCard || c instanceof WildColorCard)
                        System.out.print("|$$$$$$$$$$$$$$$|   ");
                    else
                        System.out.print(temp+"|$$$$$$$$$$$$$$$|   "+ANSI_RESET);
                }
            }
            else if(i==1 || i==3){
                for (Card c:cards) {
                    String temp;
                    //find out the color
                    switch (c.getColor()){
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
                    if(c instanceof WildDrawCard || c instanceof WildColorCard)
                        System.out.print("|               |   ");
                    else
                        System.out.print(temp+"|               |   "+ANSI_RESET);
                }
            }
            else{
                for (Card c:cards) {
                    String temp;
                    //find out the color
                    switch (c.getColor()){
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
                    //each card has different format
                    if( c instanceof SkipCard)
                        System.out.print(temp+"|      skip     |   "+ANSI_RESET);
                    if( c instanceof WildColorCard)
                        System.out.print("|   Wild Color  |   ");
                    if( c instanceof WildDrawCard)
                        System.out.print("|       +4      |   ");
                    if( c instanceof ReverseCard)
                        System.out.print(temp+"|    reverse    |   "+ANSI_RESET);
                    if( c instanceof Draw2Card)
                        System.out.print(temp+"|       +2      |   "+ANSI_RESET);
                    if( c instanceof NumberedCard)
                        System.out.print(temp+"|       "+((NumberedCard)c).getNumber()+"       |   "+ANSI_RESET);
                }
            }
            System.out.println();
        }
    }
    /**
     *
     * @param exCard what card is the last
     * @return true we player can choose at least one card
     */
    public boolean canChooseCard(Card exCard){
        for (Card c:cards)
            if(c.isLegal(exCard,cards))
                return true;
        return false;
    }
    @Override
    public int chooseCard(Game uno,Card exCard,int indexOfArrayList){
        //determine if it is is legal or not
        if(cards.get(indexOfArrayList).isLegal(exCard,cards)){
            uno.addFirstCard(cards.get(indexOfArrayList));
            Card c=cards.remove(indexOfArrayList);
            // to specify what should we do.
            if(c instanceof SkipCard) {
                return 1;
            }
            if(c instanceof ReverseCard) {
                return 2;
            }
            if(c instanceof Draw2Card) {
                return 3;
            }
            if(c instanceof WildDrawCard) {
                return 4;
            }
            if(c instanceof WildColorCard) {
                return 5;
            }
            if(c instanceof NumberedCard){
                return 7;
            }
        }
        //can't choose card
        if(!canChooseCard(exCard)){
            cards.add(uno.getCardRandomly());
            return 6;
        }
        //if it is illegal
        return 0;
    }
    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean contains(Card c) {
        //if c  is a +4 card
        if(c instanceof WildDrawCard)
            for (Card c1:cards)
                if(c1 instanceof WildDrawCard)
                    return true;
        //if c  is a +2 card
        if(c instanceof Draw2Card)
            for (Card c1:cards)
                if(c1 instanceof Draw2Card)
                    return true;
        return false;
    }

    @Override
    public int getScore() {
        int score=0;
        for (Card c:cards) {
            if(c instanceof NumberedCard)
                score+=((NumberedCard) c).getNumber();
            if(c instanceof MarkedCard)
                score+=20;
            if(c instanceof ColorlessCard)
                score+=50;
        }
        return score;
    }
}
