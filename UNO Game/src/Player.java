/**
 * this class is super class for all kind players.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public abstract class Player {
    /**
     *
     * @param uno the main game
     * @param exCard what card is tha last
     * @param indexOfArrayList it is for human to choose cards from their list
     * @return the the number to specify what do we do on players
     */
    public abstract int chooseCard(Game uno,Card exCard,int indexOfArrayList);

    /**
     * to obtain how many cards does it have
     * @return number of cards
     */
    public abstract int getSize();

    /**
     * this method is for +4 and +2 cards for next player.
     * @param c the last card that is we use kind of it
     * @return true if next player has +4 and +2 cards.
     */
    public abstract boolean contains(Card c);

    /**
     * get score's player.
     * @return scores that is calculate in special formula.
     */
    public abstract int getScore();
}
