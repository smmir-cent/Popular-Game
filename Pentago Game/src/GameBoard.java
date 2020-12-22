/**
 * This class includes players coordinates information and methods that are for the player's turn.
 * @author Mahdi Mirfendereski
 * @version 0.0
 */
public class GameBoard {
    // to keep data of player's coordinates
    private Player[][] board ;

    /**
     * init of 2d-Array with the player that has color=0 means empty
     */
    public GameBoard(){
        board=new Player[6][6];
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                board[i][j]=new Player(0);
            }
        }
    }

    /**
     * this method give the block of 2d array that rotate
     * @param typeOfRotation we want rotate 90* or -90*
     * @param block which block must rotate
     * @return the 2d array rotated block
     */
    public Player[][] rotatedBlock(int typeOfRotation,int block){
        //this several lines must be here to find out start of x and y
        int startX=0;
        int startY=0;
        if (block == 2)
            startX=3;
        else if(block==3){
            startY=3;
        }
        else if(block==4){
            startX=3;
            startY=3;
        }
        //copy the block to array
        Player[][] temp=new Player[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                temp[i][j]=board[startY+i][startX+j];

        //here is the algorithm of 90* rotation
        if(typeOfRotation==90){
            for (int i = 0; i < 3 / 2; i++) {
                // Consider elements in group of 4 in
                // current square
                for (int j = i; j < 3 - i - 1; j++) {
                    // store current cell in temp variable
                    Player player = temp[i][j];

                    // move values from right to top
                    temp[i][j] = temp[j][3 - 1 - i];

                    // move values from bottom to right
                    temp[j][3 - 1 - i] = temp[3 - 1 - i][3 - 1 - j];

                    // move values from left to bottom
                    temp[3 - 1 - i][3 - 1 - j] = temp[3 - 1 - j][i];

                    // assign temp to left
                    temp[3 - 1 - j][i] = player;
                }

            }
        }
        //here is the algorithm of -90* rotation
        if(typeOfRotation==-90){
            for (int i = 0; i < 3 / 2; i++)
            {
                for (int j = i; j < 3 - i - 1; j++)
                {

                    // Swap elements of each cycle
                    // in clockwise direction
                    Player player = temp[i][j];
                    temp[i][j] = temp[3 - 1 - j][i];
                    temp[3 - 1 - j][i] = temp[3 - 1 - i][3 - 1 - j];
                    temp[3 - 1 - i][3 - 1 - j] = temp[j][3 - 1 - i];
                    temp[j][3 - 1 - i] = player;
                }
            }
        }
        return temp;
    }

    /**
     * just a simple formatted board to display it
     */
    public void printBoard(){
        for(int i =0;i<6;i++){
            for(int j=0;j<6;j++){
                System.out.print(board[i][j].toString()+" ");
                if(j==2)
                    System.out.print("| ");
            }
            System.out.println();
            if(i==2)
                System.out.println("-------------------------");
        }
    }

    /**
     *
     * @param i the row of selected cell
     * @param j the column of selected cell
     * @return true if selected cell is empty
     */
    public boolean isLegal(int i ,int j){
        return board[i][j].getColor()==0;
    }

    /**
     * rotate the block of 2d array.
     * @param typeOfRotation  we want rotate 90* or -90*
     * @param block which block must rotate
     */
    public void rotation(int typeOfRotation,int block){
        //this several lines must be here to find out start of x and y
        int startX=0;
        int startY=0;
        if (block == 2)
            startX=3;
        else if(block==3){
            startY=3;
        }
        else if(block==4){
            startX=3;
            startY=3;
        }
        Player[][] temp=rotatedBlock(typeOfRotation,block);
        //copy the returned array to board
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[startY+i][startX+j]=temp[i][j];
    }

    /**
     * this method is written to find out we can pass without rotation or not.
     * @return true if we can
     */
    public boolean canPassWithoutRotation(){
        Player[][] temp ;
        int startX;
        int startY;
        boolean flag;
        for(int block=1;block<=4;block++){
            //this several lines must be here to find out start of x and y
            startX=0;
            startY=0;
            switch (block){
                case 2:
                    startX=3;break;
                case 3:
                    startY=3;break;
                case 4:
                    startX=3;startY=3;break;
            }
            //if rotated block is the same block in board return true
            temp =rotatedBlock(90,block);
            flag=true;
            outer:for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board[startY+i][startX+j].getColor()!=(temp[i][j]).getColor()){
                        //System.out.println(block+" : 90 "+i+" "+j);
                        flag=false;
                        break outer;
                    }
                }
            }
            if(flag)
                return true;
            //if rotated block is the same block in board return true
            temp =rotatedBlock(-90,block);
            flag=true;
            outer:for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board[startY+i][startX+j].getColor()!=(temp[i][j]).getColor()){
                        //System.out.println(block+" : -90 "+i+" "+j);
                        flag=false;
                        break outer;
                    }

                }
            }
            if(flag)
                return true;
        }
        return false;
    }

    /**
     * update the board with cell and player
     * @param player which player choose a cell
     * @param i the row of selected cell
     * @param j the row of selected cell
     * @return true if we can accept it as legal choice
     */
    public boolean updateBoard(Player player,int i,int j){
        if(!isLegal(i,j))
            return false;
        board[i][j]=player;
        return true;
    }

    /**
     * find out end of game
     * @param player which player that we want to know
     * @return true if player is winner
     */
    public boolean isWinner(Player player){
        //in rows
        //do we have 5 cells existed Consecutively
        boolean flag;
        for(int i=0;i<6;i++){
            flag=true;
            if(board[i][0].equals(player)){
                for(int j=1;j<5;j++){
                    if(!board[i][j].equals(player)){
                        flag=false;
                        break;
                    }
                }
            }
            else{
                for(int j=1;j<6;j++){
                    if(!board[i][j].equals(player)){
                        flag=false;
                        break;
                    }
                }
            }
            if(flag)
                return true;
        }
        //in columns
        //do we have 5 cells existed Consecutively
        for(int i=0;i<6;i++){
            flag=true;
            if(board[0][i].equals(player)){
                for(int j=1;j<5;j++){
                    if(!board[j][i].equals(player)){
                        flag=false;
                        break;
                    }
                }
            }
            else{
                for(int j=1;j<6;j++){
                    if(!board[j][i].equals(player)){
                        flag=false;
                        break;
                    }
                }
            }
            if(flag)
                return true;
        }
        //in diameter
        //do we have 5 cells existed Consecutively
        for(int i=0;i<2;i++){
            flag=true;
            int startX=0;
            int startY=0;
            if(i==0)
                startX=1;
            if(i==1)
                startY=1;
            for(int j=0;j<5;j++){
                if(!board[startX+j][startY+j].equals(player)){
                    flag=false;
                    break;
                }
            }
            if(flag)
                return true ;
        }
        for(int i=0;i<2;i++){
            flag=true;
            int startX=0;
            int startY=5;
            if(i==0)
                startX=1;
            if(i==1)
                startY=4;
            for(int j=0;j<5;j++){
                if(!board[startX+j][startY-j].equals(player)){
                    flag=false;
                    break;
                }
            }
            if(flag)
                return true ;
        }
        flag=true;
        //here is for main diameter
        if(board[0][0].equals(player)) {
            for (int i = 1; i < 5; i++)
                if (!board[i][i].equals(player)) {
                    flag = false;
                    break;
                }
            if (flag)
                return true;
        }
        else{
            for(int i=1;i<6;i++)
                if(!board[i][i].equals(player)){
                    flag=false;
                    break;
                }
            if(flag)
                return true ;
        }
        //here is for Sub-diameter
        flag=true;
        if(board[0][5].equals(player)){
            for(int i=1;i<5;i++)
                if(!board[i][5-i].equals(player)){
                    flag=false;
                    break;
                }
            return flag;
        }
        else{
            for(int i=1;i<6;i++)
                if(!board[i][5-i].equals(player)){
                    flag=false;
                    break;
                }
            return flag;
        }
    }

    /**
     * one of condition that is for end of game.
     * @return true if board has at least 1 empty cell
     */
    public boolean hasEmptyCell(){
        int sum=0;
        for(int i=0;i<6;i++)
            for(int j=0;j<6;j++)
                if(board[i][j].getColor()==0)
                    return true;
         return false;
    }
}
