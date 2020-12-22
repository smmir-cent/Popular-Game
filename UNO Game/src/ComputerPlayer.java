import java.util.ArrayList;
import java.util.Random;
/**
 * this class is a class for computer players.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class ComputerPlayer extends Player {
    //the player's cards
    private ArrayList<Card> cards;

    public ComputerPlayer() {
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
        //if we can't choose one card
        if(!canChooseCard(exCard)){
            cards.add(uno.getCardRandomly());
            return 6;
        }
        //obtain all possible cards
        ArrayList<Card> possible = new ArrayList<>();
        for (Card c:cards)
            if(c.isLegal(exCard,cards)) {
                possible.add(c);
            }
        //choose one of them randomly
        Random random=new Random();
        int index=random.nextInt(possible.size());
        Card c=possible.get(index);
        //add to repository
        uno.addFirstCard(c);
        //remove from cards
        cards.remove(c);
        //if random card is a skip card
        if(c instanceof SkipCard) {
            return 1;
        }
        //if random card is a Reverse card
        if(c instanceof ReverseCard) {
            return 2;
        }
        //if random card is a +2 card
        if(c instanceof Draw2Card) {
            return 3;
        }
        //if random card is a +4 card
        if(c instanceof WildDrawCard) {
            return 4;
        }
        //if random card is a wild color card
        if(c instanceof WildColorCard) {
            return 5;
        }
        //if random card is a Numbered card
        if(c instanceof NumberedCard){
            return 7;
        }
        return 0;
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
    public int getSize() {
        return cards.size();
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
