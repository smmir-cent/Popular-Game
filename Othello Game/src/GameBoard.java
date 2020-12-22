/**
 * This class represent a otello board game.
 * @author Mahdi Mirfendereski
 * @version 1.0
 */
public class GameBoard {
    //Create a two-dimensional array to identify map cells that are empty or filled with which player
    private String[][] board ;
    /**
     * first initial of board with 5 spaces("     ") except 4 cells : 2 of "  1  " and 2 of "  2  "
     */
    public GameBoard(){
        board=new String[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)
                board[i][j]="     ";
        }
        board[3][3]="  1  ";
        board[3][4]="  2  ";
        board[4][3]="  2  ";
        board[4][4]="  1  ";
    }
    /**
     * just for displaying a board in console
     */
    public void print(){
        int i,j,k;
        System.out.println("    1     2     3     4     5     6     7     8   ");
        for(i=0;i<8;i++){
            for(k=0;k<50;k++)
                System.out.print('_');
            System.out.println();
            System.out.print((char)('A'+i));
            for(j=0;j<8;j++)
                System.out.print("|"+board[i][j]);

            System.out.println("|");
        }
        for(k=0;k<50;k++)
            System.out.print('_');
    }
    /**
     * this is method is for checking the legal choice.
     * @param i1 the x variable(which row) of selected coordinate
     * @param j1 the y variable(which column) of selected coordinate
     * @param i2 the x variable(which row) of target coordinate
     * @param j2 the y variable(which column) of target coordinate
     * @return true if between selected and target existed another player's coordinates and they must be in  one row or one column or skewed.
     * */
    public boolean isPossible(int i1 , int j1 , int i2,int j2){
        double distance=Math.sqrt((i1-i2)*(i1-i2) + (j1-j2)*(j1-j2) );
        if(distance<1.5)
            return false;
        boolean flag=true ;
        //this is skewed.
        if(Math.abs(j1-j2)==Math.abs(i1-i2)){
            if((j1-j2)*(i1-i2)<0){
                int starty = Math.max(i1, i2);
                int startx =(i1>i2)?j1:j2;
                for (int i=1;i<Math.abs(i1-i2);i++)
                    if (!board[starty - i][startx + i].equals((board[i2][j2].equals("  1  "))? ("  2  " ):("  1  "))) {
                        flag = false;
                        break;
                    }
            }
            else{
                int starty = Math.min(i1, i2);
                int startx =(i1>i2)?j2:j1;
                //to check cells that are in between selected and target
                for (int i=1;i<Math.abs(i1-i2);i++)
                    if (!board[starty + i][startx + i].equals((board[i2][j2].equals("  1  ")) ? "  2  " :"  1  ")) {
                        flag = false;

                        break;
                    }
            }
        }
        //in one column
        else if(j1==j2){
            int start = Math.min(i1, i2);
            //to check cells that are in between selected and target
            for(int i=1;i<Math.abs(i1-i2);i++)
                if (!board[start + i][j1].equals((board[i2][j2].equals("  1  ") )? "  2  " :"  1  ")) {
                    flag = false;
                    break;
                }
        }
        //in one row
        else if(i1==i2){
            int start = Math.min(j1, j2);
            //to check cells that are in between selected and target
            for(int i=1;i<Math.abs(j1-j2);i++)
                if(!board[i1][start+i].equals((board[i2][j2].equals("  1  "))?"  2  ":"  1  ")){
                    flag=false;

                    break;
                }
        }
        else
            flag=false;


        return flag;
    }

    /**
     * checking end of game for which player
     * @param player which player
     * @return true if player can choice on coordinates on board.
     */
    public boolean endGame(String player){
        int[][]temp=new int[64][2];
        int flag=0;
        //find the player's cells
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(board[i][j].equals(player)){
                   temp[flag][0]=i;
                   temp[flag++][1]=j;
                }
        int k=0;
        boolean check=true;
        //to check player can choose at least one empty cell
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(board[i][j].equals("     "))
                    for (k=0;k<flag;k++)
                        if(isPossible(i,j,temp[k][0],temp[k][1]))
                            return false;
        System.out.println("Pass");
        return check;
    }
    /**
     * updating the board with specific coordinate and player.
     * @param i1 the x variable(which row) of selected coordinate
     * @param j1 the y variable(which column) of selected coordinate
     * @param player selected coordinate for which player
     * @return true if selected coordinate is legal choice and update completed
     */
    public boolean updateBoard(int i1 ,int j1,String player){
        if(!board[i1][j1].equals("     "))
            return false;
            int sum =0;
        //find player's cells
        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                //to check is it legal for selected coordinate
                if(board[i][j].equals(player) && isPossible(i1,j1,i,j)){
                    //if selected coordinate and player's cell are skewed.
                    if(Math.abs(j-j1)==Math.abs(i-i1)){
                        //with positive slope
                        if((j-j1)*(i-i1)<0){
                            int starty = Math.max(i, i1);
                            int startx =(i>i1)?j:j1;
                            for (int k=1;k<Math.abs(i-i1);k++){
                                board[starty-k][startx+k]=player;
                                sum++;
                            }
                        }
                        //with negative slope
                        else{
                            int starty = Math.min(i, i1);
                            int startx =(i>i1)?j1:j;
                            for (int k=1;k<Math.abs(i-i1);k++){
                                board[starty+k][startx+k]=player;
                                sum++;
                            }
                        }
                    }
                    //if selected coordinate and player's cell are in one column.
                    else if(j==j1){
                        int start = Math.min(i, i1);
                        for(int k=1;k<Math.abs(i-i1);k++){
                            board[start+k][j1]=player;
                            sum++;
                        }
                    }
                    //if selected coordinate and player's cell are in one row.
                    else if(i==i1){
                        int start = Math.min(j, j1);
                        for(int k=1;k<Math.abs(j1-j);k++){
                            board[i1][start+k]=player;
                            sum++;
                        }
                    }
                }
            }
        }
        if(sum!=0)
            board[i1][j1]=player;
        return sum != 0;
    }

    /**
     * This method is for single player and automatically choice coordinate with the high impact to win the game.
     */
    public void computerChoice(){
        int[][] emptyCell=new int[64][2];
        int flag=0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(board[i][j].equals("     ")){
                    emptyCell[flag][0]=i;
                    emptyCell[flag++][1]=j;
                }
        int max=0;
        int finali=emptyCell[0][0] ;
        int finalj=emptyCell[0][1] ;
        for(int k=0;k<flag;k++){
            int temp=convertedNumber(emptyCell[k][0],emptyCell[k][1],"  2  ");
            if(temp>max){
                finali=emptyCell[k][0];
                finalj=emptyCell[k][1];
                max=temp;
            }
        }
        System.out.println("Computer chose : "+(char)('A'+finali)+" "+(finalj+1));
        updateBoard(finali,finalj,"  2  ");
    }
    /**
     * This method is for determine how the selected coordinates influence on other player
     * @param i1 the x variable(which row) of selected coordinate
     * @param j1 the y variable(which column) of selected coordinate
     * @param player selected coordinate for which player
     * @return how many cell converted to this player with this choice
     */
    public int convertedNumber(int i1 ,int j1,String player){
        int sum =0;
        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                //to find player's cell
                if(board[i][j].equals(player) && isPossible(i1,j1,i,j)){
                    if(Math.abs(j-j1)==Math.abs(i-i1)){
                        //skewed
                        if((j-j1)*(i-i1)<0){
                            //counting the cells can converted to other player.
                            for (int k=1;k<Math.abs(i-i1);k++)
                                sum++;
                        }
                        else{
                            //counting the cells can converted to other player.
                            for (int k=1;k<Math.abs(i-i1);k++)
                                sum++;

                        }
                    }
                    //in one column
                    else if(j==j1){
                        //counting the cells can converted to other player.
                        for(int k=1;k<Math.abs(i-i1);k++)
                            sum++;
                    }
                    //in one row
                    else if(i==i1){
                        //counting the cells can converted to other player.
                        for(int k=1;k<Math.abs(j1-j);k++)
                            sum++;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * A simple method to determine and print the result of game and winner.
     */
    public void winner(){
        int player1=0;
        int player2=0;
        //2 loop for count the player's cell
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++){
                if(board[i][j].equals("  1  "))
                    player1++;
                else if(board[i][j].equals("  2  "))
                    player2++;
            }
        System.out.println("Player1 : "+player1+"\nPlayer2 : "+player2);
        if(player1>player2)
            System.out.println("Player 1 won");
        if(player1<player2)
            System.out.println("Player 2 won");
    }
}
