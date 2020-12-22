import java.util.Scanner;
/**
 * This class is to execute the game.
 * @author Mahdi Mirfendereski
 * @version 1.0
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Choose \n1:1 player\n2:2 player");
        System.out.println("You must enter this format : [number] [character](without any space after character)");
        Scanner scanner=new Scanner(System.in);
        int order=scanner.nextInt();
        GameBoard g1=new GameBoard();
        scanner.nextLine();
        //2 players
        if(order==2){
            g1.print();
            System.out.println();
            String temp="";
            int i=0;
            //checking the end of game for both player
            while(!g1.endGame("  1  ") || !g1.endGame("  2  ")){
                //If it's the first player's turn
                if(i%2==0 && !g1.endGame("  1  ")){
                    System.out.println("***Player 1***");
                    temp=scanner.nextLine();
                    //checking the legal condition for input and legal coordinates
                    while (!( temp.length()==3 && temp.charAt(0)>='A' && temp.charAt(0)<='H' && temp.charAt(2)>='1' && temp.charAt(2)<='8' && g1.updateBoard(temp.charAt(0)-'A',temp.charAt(2)-'1',"  1  "))){
                        System.out.println("Wrong input");
                        temp=scanner.nextLine();
                        if(temp.equals("quit"))
                            break;
                    }
                    g1.print();
                    System.out.println();
                }
                //If it's the second player's turn
                if (i%2==1 && !g1.endGame("  2  ")){
                    System.out.println("***Player 2***");
                    temp=scanner.nextLine();
                    //checking the legal condition for input and legal coordinates
                    while (!(temp.length()==3 && temp.charAt(0)>='A' && temp.charAt(0)<='H' && temp.charAt(2)>='1' && temp.charAt(2)<='8' && g1.updateBoard(temp.charAt(0)-'A',temp.charAt(2)-'1',"  2  "))){
                        System.out.println("Wrong input");
                        temp=scanner.nextLine();
                        if(temp.equals("quit"))
                            break;
                    }
                    g1.print();
                    System.out.println();
                }
                if(temp.equals("quit"))
                    break;
                i++;
            }
        }
        //1 player
        else if(order==1){
            System.out.println("You are player 1");
            g1.print();
            System.out.println();
            String temp;
            int i=0;
            while(!g1.endGame("  1  ") || !g1.endGame("  2  ")) {
                if (i % 2 == 0 && !g1.endGame("  1  ")) {
                    System.out.println("***Player 1***");
                    temp = scanner.nextLine();
                    //checking the legal condition for input and legal coordinates
                    while (!( temp.length()==3 && temp.charAt(0) >= 'A' && temp.charAt(0) <= 'H' && temp.charAt(2) >= '1' && temp.charAt(2) <= '8' && g1.updateBoard(temp.charAt(0) - 'A', temp.charAt(2) - '1', "  1  "))) {
                        System.out.println("Wrong input");
                        temp = scanner.nextLine();
                        if (temp.equals("quit"))
                            break;
                    }
                    g1.print();
                    System.out.println();
                }
                if (i%2==1 && !g1.endGame("  2  ")){
                    System.out.println("***Player 2***");
                    g1.computerChoice();
                    g1.print();
                    System.out.println();
                }
                i++;
            }
        }
        g1.winner();
        g1.print();
        scanner.close();
    }
}
