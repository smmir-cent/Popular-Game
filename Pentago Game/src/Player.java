/**
 * This class represents a player who wants to play "pentago game".
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class Player {
    // player name
    private String name ;
    // 0 , 1 express color of player in game
    private int color ;
    public Player(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public Player(int color) {
        this.color=color;
    }
    public String getName() {
        return name;
    }
    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        if(color==1)
            return " R ";
        else if(color==2)
            return " B ";
        else
            return " O ";

    }
}
