import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        GameBoard pentago=new GameBoard();
        System.out.println("Please enter your Name and your color('1:R'and'2:B')(first name is a starter)");
        Scanner scanner =new Scanner(System.in);
        //instance of player
        Player player1=new Player(scanner.nextLine(),scanner.nextInt());
        scanner.nextLine();
        //instance of player
        Player player2=new Player(scanner.nextLine(),scanner.nextInt());
        scanner.nextLine();
        pentago.printBoard();
        Player winner=null;
        //while is continue until we have no winner and board has empty cell
        while(!pentago.isWinner(player1)&&!pentago.isWinner(player2)&&pentago.hasEmptyCell()){
            System.out.println("Player1");
            int tempx=scanner.nextInt();
            int tempy=scanner.nextInt();
            //for wrong input for cell
            while(tempx>5 || tempx<0 || tempy>5 || tempy<0 || !pentago.updateBoard(player1,tempx,tempy)){
                System.out.println("Wrong input");
                tempx=scanner.nextInt();
                tempy=scanner.nextInt();
            }
            if(pentago.isWinner(player1)){
                winner=player1;
                break;
            }
            boolean temp=pentago.canPassWithoutRotation();
            System.out.println(temp?"can pass:0|block1 : 1|block2 : 2|block3 : 3|block4 : 4|":"block1 : 1|block2 : 2|block3 : 3|block4 : 4|");
            int block=scanner.nextInt();
            //for wrong input for block
            while(temp?(block<0||block>4):(block<1||block>4)){
                System.out.println("Wrong input");
                block=scanner.nextInt();
            }
            int angle;
            if(block!=0){
                System.out.println("90 or -90");
                angle=scanner.nextInt();
                //for wrong input for angle
                while (angle!=90&&angle!=-90){
                    System.out.println("Wrong input");
                    angle=scanner.nextInt();
                }
                //pentago.printRotatedBlock(angle,block);
                pentago.rotation(angle,block);
            }
            pentago.printBoard();
            if(pentago.isWinner(player1)){
                winner=player1;
                break;
            }
            System.out.println("Player2");
            tempx=scanner.nextInt();
            tempy=scanner.nextInt();
            //for wrong input for cell
            while(tempx>5 || tempx<0 || tempy>5 || tempy<0 || !pentago.updateBoard(player2,tempx,tempy)){
                System.out.println("Wrong input");
                tempx=scanner.nextInt();
                tempy=scanner.nextInt();
            }
            if(pentago.isWinner(player2)){
                winner=player2;
                break;
            }
            temp=pentago.canPassWithoutRotation();
            System.out.println(temp?"can pass:0|block1 : 1|block2 : 2|block3 : 3|block4 : 4|":"block1 : 1|block2 : 2|block3 : 3|block4 : 4|");
            block=scanner.nextInt();
            //for wrong input for block
            while(temp?(block<0||block>4):(block<1||block>4)){
                System.out.println("Wrong input");
                block=scanner.nextInt();
            }
            if(block!=0){
                System.out.println("90 or -90");
                angle=scanner.nextInt();
                //for wrong input for rotation
                while (angle!=90&&angle!=-90){
                    System.out.println("Wrong input");
                    angle=scanner.nextInt();
                }
                //pentago.printRotatedBlock(angle,block);
                pentago.rotation(angle,block);
            }
            pentago.printBoard();
        }
        if((pentago.isWinner(player1) && pentago.isWinner(player2))|| !pentago.hasEmptyCell())
            System.out.println("Draw");
        else if (pentago.isWinner(player1))
            System.out.println(player1.getName()+" won the game");
        else if (pentago.isWinner(player2))
            System.out.println(player2.getName()+" won the game");
    }
}
