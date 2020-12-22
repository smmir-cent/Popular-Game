import java.util.ArrayList;
import java.util.Random;
/**
 * this class is a class to hold repository.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class Game {
    //repository
    private ArrayList<Card> repository;
    public Game(){
        repository=new ArrayList<>();
        for(int i=1;i<=4;i++){
            //adding numbered color
            for(int j=0;j<10;j++)
                repository.add(new NumberedCard(i,j));
            //adding numbered color
            for(int j=1;j<10;j++)
                repository.add(new NumberedCard(i,j));
            //adding marked color
            repository.add(new SkipCard(i));
            repository.add(new SkipCard(i));
            repository.add(new ReverseCard(i));
            repository.add(new ReverseCard(i));
            repository.add(new Draw2Card(i));
            repository.add(new Draw2Card(i));
        }
        //adding wild card
        for(int i=0;i<4;i++)
            repository.add(new WildColorCard(0));
        for(int i=0;i<4;i++)
            repository.add(new WildDrawCard(0));
    }

    /**
     *
     * @param human how many player are human
     * @param computer how many player are computer
     * @return array of players
     */
    public Player[] firstInitial(int human,int computer){
        Player[] player=new Player[human+computer];
        Random random=new Random();
        int i=0;
        //instance of players
        for(;i<human;i++)
            player[i]=new HumanPlayer();
        for(;i<human+computer;i++)
            player[i]=new ComputerPlayer();
        //get cards randomly
        for(i=0;i<human;i++)
            for(int j=0;j<7;j++)
                ((HumanPlayer)player[i]).addCard(repository.remove(random.nextInt(repository.size())));
        for(;i<computer+human;i++)
            for(int j=0;j<7;j++)
                ((ComputerPlayer)player[i]).addCard(repository.remove(random.nextInt(repository.size())));

        return player;
    }

    /**
     * get the card randomly
     * @return card from repository randomly
     */
    public Card getCardRandomly(){
        Random random=new Random();
        return repository.remove(random.nextInt(repository.size()-3)+3);
    }

    /**
     * add a card to the index=0 of array list
     * @param c what card
     */
    public void addFirstCard(Card c){
        repository.add(0,c);
    }

    /**
     * get the index=0 of array list
     * @return index=0 of array list
     */
    public Card getFirstCard(){
        return repository.get(0);
    }

}
