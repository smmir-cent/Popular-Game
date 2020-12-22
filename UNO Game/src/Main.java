import java.util.Random;
import java.util.Scanner;

public class Main {
    //if the game is over or not
    public static boolean endGame(Player[] players){
        for (Player p:players)
            if(p.getSize()==0)
                return true;
        return false;
    }
    public static void result(Player[] players){
        int n = players.length;
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=i;
        for (int i = 0; i < n-1; i++)
            for (int j = i; j < n-i-1; j++)
                if (players[j].getScore() > players[j+1].getScore()){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        for (int i=0;i<n;i++)
            System.out.println(arr[i]);
        for (int i=0;i<n;i++)
            System.out.println("Player"+arr[i]+" : "+players[arr[i]].getScore()+" points");

    }
    public static void main(String[] args){
        Game uno=new Game();
        Scanner scanner =new Scanner(System.in);
        int c,h;
        //get human and computer players
        do {
            System.out.print("Human player: ");
            h =scanner.nextInt();
            System.out.print("Computer player: ");
            c =scanner.nextInt();

        }while ((c+h)*7>100 || h<=0 ||c<0);
        Player[] players=uno.firstInitial(h,c);
        int size=players.length;
        int draw2=0,draw4=0;
        Random random=new Random();
        //start randomly
        int start=random.nextInt(size);
        int index;
        int flag=0;
        int count=0;
        System.out.println(start);
        System.out.println("first Card");
        uno.getFirstCard().printCard();
        while (!endGame(players)){
            //for clock-wise rotation
            for(int i=0;flag!=2;i++){
                System.out.println("Clockwise");
                index = (start+(i))%size;
                //if first is reverse card
                if(count==0){
                    count++;
                    if(uno.getFirstCard() instanceof ReverseCard)
                        break;
                }
                if(players[index] instanceof HumanPlayer){
                    System.out.println("HPlayer"+index);
                    ((HumanPlayer)players[index]).printCards();
                    //if can't choose
                    if(!((HumanPlayer)players[index]).canChooseCard(uno.getFirstCard())){
                        flag=players[index].chooseCard(uno,uno.getFirstCard(),0);
                    }
                    else {
                        System.out.print("Your choice: ");
                        int temp=scanner.nextInt();
                        if(temp>-1 && temp<players[index].getSize())
                            flag=players[index].chooseCard(uno,uno.getFirstCard(),temp);
                        else
                            flag=0;
                    }
                }
                else{
                    System.out.println("CPlayer"+index);
                    flag=players[index].chooseCard(uno,uno.getFirstCard(),0);
                }

                //reverse card
                if(flag==2){
                    if((--index)==-1){
                        start=size-1;
                        index=size-1;
                    }
                    else
                        start=index;
                    uno.getFirstCard().printCard();
                    break;
                }
                //skip card
                if(flag==1){
                    i++;
                }

                //wild color card
                if(flag==5) {
                    if(players[index] instanceof HumanPlayer){
                        System.out.print("Color(1:red 2:yellow 3:green 4:blue): ");
                        uno.getFirstCard().setColor(scanner.nextInt());
                    }
                    else{
                        int color=random.nextInt(4)+1;
                        uno.getFirstCard().setColor(color);
                        switch (color){
                            case 1:
                                System.out.println("Red");
                                break;
                            case 2:
                                System.out.println("Yellow");
                                break;
                            case 3:
                                System.out.println("Green");
                                break;
                            case 4:
                                System.out.println("Blue");
                                break;
                        }
                    }

                }
                //doesn't have related card
                if(flag==6){
                    if(players[index] instanceof HumanPlayer)
                        if(((HumanPlayer)players[index]).canChooseCard(uno.getFirstCard()))
                            i--;
                    if(players[index] instanceof ComputerPlayer)
                        if(((ComputerPlayer)players[index]).canChooseCard(uno.getFirstCard()))
                            i--;
                }
                //for human player it means wrong input
                if(flag==0){
                    i--;
                    System.out.println("Wrong card");
                }

                //draw2
                if(flag==3){
                    if(players[(start+(i+1))%size].contains(uno.getFirstCard())){
                        draw2+=2;
                    }
                    else{
                        draw2+=2;
                        if(players[(start+(i+1))%size] instanceof HumanPlayer)
                            for(int j=0;j<draw2;j++)
                                ((HumanPlayer)players[(start+(i+1))%size]).addCard(uno.getCardRandomly());
                        if(players[(start+(i+1))%size] instanceof ComputerPlayer)
                            for(int j=0;j<draw2;j++)
                                ((ComputerPlayer)players[(start+(i+1))%size]).addCard(uno.getCardRandomly());
                        draw2=0;
                    }
                }
                //wild draw
                if(flag==4){
                    if(players[(start+(i+1))%size].contains(uno.getFirstCard())){
                        draw4+=4;
                    }
                    else{
                        draw4+=4;
                        if(players[(start+(i+1))%size] instanceof HumanPlayer){
                            for(int j=0;j<draw4;j++)
                                ((HumanPlayer)players[(start+(i+1))%size]).addCard(uno.getCardRandomly());
                        }
                        if(players[(start+(i+1))%size] instanceof ComputerPlayer){
                            for(int j=0;j<draw4;j++)
                                ((ComputerPlayer)players[(start+(i+1))%size]).addCard(uno.getCardRandomly());

                        }
                        if(players[index] instanceof HumanPlayer){
                            System.out.print("Color(1:red 2:yellow 3:green 4:blue): ");
                            uno.getFirstCard().setColor(scanner.nextInt());
                        }
                        if(players[index] instanceof ComputerPlayer) {
                            int color=random.nextInt(4)+1;
                            uno.getFirstCard().setColor(color);
                            switch (color){
                                case 1:
                                    System.out.println("Red");
                                    break;
                                case 2:
                                    System.out.println("Yellow");
                                    break;
                                case 3:
                                    System.out.println("Green");
                                    break;
                                case 4:
                                    System.out.println("Blue");
                                    break;
                            }
                        }
                        draw4=0;
                    }
                }
                //if +2 card hasn't chosen by players more than 1
                if(flag!=3 && draw2!=0){
                    if(players[index] instanceof HumanPlayer)
                        for(int j=0;j<draw2;j++)
                            ((HumanPlayer)players[index]).addCard(uno.getCardRandomly());
                    if(players[index] instanceof ComputerPlayer)
                        for(int j=0;j<draw2;j++)
                            ((ComputerPlayer)players[index]).addCard(uno.getCardRandomly());

                        draw2=0;
                }
                /*if(flag!=4 && draw4!=0){
                    if(players[index] instanceof HumanPlayer)
                    for(int j=0;j<draw4;j++)
                        ((HumanPlayer)players[index]).addCard(uno.getCardRandomly());
                    if(players[index] instanceof ComputerPlayer)
                        for(int j=0;j<draw4;j++)
                            ((ComputerPlayer)players[index]).addCard(uno.getCardRandomly());
                    draw4=0;
                    if(players[(start+(i-1))%size] instanceof HumanPlayer){
                        System.out.println("Player"+(start+(i-1))%size+" enter color :");

                    }
                }*/
                //System.out.println("Last Card");
                if(flag!=6 && flag!=0){
                    uno.getFirstCard().printCard();
                }
                for(int j=0;j<size;j++){
                    System.out.print("player"+j+" : "+players[j].getSize()+" |");
                }
                if(endGame(players))
                    break;

            }
            if(endGame(players))
                break;
            //for anti clock-wise rotation
            for(int i=0;true;i++){
                index = (start-(i)+size) % size;
                index=(index<0)?size+index:index;
                System.out.println("Anti Clockwise");
                if(players[index] instanceof HumanPlayer){
                    System.out.println("HPlayer"+index);
                    ((HumanPlayer)players[index]).printCards();
                    if(!((HumanPlayer)players[index]).canChooseCard(uno.getFirstCard())){
                        flag=players[index].chooseCard(uno,uno.getFirstCard(),0);
                    }
                    else {
                        System.out.print("Your choice: ");
                        int temp=scanner.nextInt();
                        if(temp>-1 && temp<players[index].getSize())
                            flag=players[index].chooseCard(uno,uno.getFirstCard(),temp);
                        else
                            flag=0;
                    }

                }
                else{
                    System.out.println("CPlayer"+index);
                    flag=players[index].chooseCard(uno,uno.getFirstCard(),0);

                }
                //reverse card
                if(flag==2){
                    if((++index)==size){
                        start=0;
                        index=0;
                    }
                    else{
                        start=index;
                    }
                    uno.getFirstCard().printCard();
                    break;
                }
                //skip card
                if(flag==1){
                    i++;
                }
                if(flag==5) {
                    if(players[index] instanceof HumanPlayer){
                        System.out.print("Color(1:red 2:yellow 3:green 4:blue): ");
                        uno.getFirstCard().setColor(scanner.nextInt());
                    }
                    else{
                        int color=random.nextInt(4)+1;
                        uno.getFirstCard().setColor(color);
                        switch (color){
                            case 1:
                                System.out.println("Red");
                                break;
                            case 2:
                                System.out.println("Yellow");
                                break;
                            case 3:
                                System.out.println("Green");
                                break;
                            case 4:
                                System.out.println("Blue");
                                break;
                        }
                    }
                }
                //doesn't have related card
                if(flag==6){
                    if(players[index] instanceof HumanPlayer) {
                        if (((HumanPlayer) players[index]).canChooseCard(uno.getFirstCard()))
                            i--;
                    }
                    else
                        if(((ComputerPlayer)players[index]).canChooseCard(uno.getFirstCard()))
                            i--;
                }
                //for human:entered wrong card
                if(flag==0){
                    i--;
                    System.out.println("Wrong card");
                }

                //draw2
                if(flag==3){
                    int temp = (start-(i+1)+size) % size;
                    temp=(temp<0)?size+temp:temp;
                    if(players[temp].contains(uno.getFirstCard())){
                        draw2+=2;
                    }
                    else{
                        draw2+=2;
                        if(players[temp] instanceof HumanPlayer)
                            for(int j=0;j<draw2;j++)
                                ((HumanPlayer)players[temp]).addCard(uno.getCardRandomly());
                        if(players[temp] instanceof ComputerPlayer)
                            for(int j=0;j<draw2;j++)
                                ((ComputerPlayer)players[temp]).addCard(uno.getCardRandomly());
                        draw2=0;
                    }
                }
                //wild draw
                if(flag==4){
                    int temp = (start-(i+1)+size) % size;
                    temp=(temp<0)?size+temp:temp;
                    if(players[temp].contains(uno.getFirstCard())){
                        draw4+=4;
                    }
                    else{
                        draw4+=4;
                        if(players[temp] instanceof HumanPlayer){
                            for(int j=0;j<draw4;j++)
                                ((HumanPlayer)players[temp]).addCard(uno.getCardRandomly());

                        }

                        if(players[temp] instanceof ComputerPlayer){
                            for(int j=0;j<draw4;j++)
                                ((ComputerPlayer)players[temp]).addCard(uno.getCardRandomly());

                        }
                        if(players[index] instanceof HumanPlayer){
                            System.out.print("Color(1:red 2:yellow 3:green 4:blue): ");
                            uno.getFirstCard().setColor(scanner.nextInt());
                            int color=uno.getFirstCard().getColor();
                            switch (color){
                                case 1:
                                    System.out.println("Red");
                                    break;
                                case 2:
                                    System.out.println("Yellow");
                                    break;
                                case 3:
                                    System.out.println("Green");
                                    break;
                                case 4:
                                    System.out.println("Blue");
                                    break;
                            }
                        }
                        if(players[index] instanceof ComputerPlayer){
                            int color=random.nextInt(4)+1;
                            uno.getFirstCard().setColor(color);
                            switch (color){
                                case 1:
                                    System.out.println("Red");
                                    break;
                                case 2:
                                    System.out.println("Yellow");
                                    break;
                                case 3:
                                    System.out.println("Green");
                                    break;
                                case 4:
                                    System.out.println("Blue");
                                    break;
                            }
                        }
                        draw4=0;
                    }
                }
                if(flag!=3 && draw2!=0){
                    if(players[index] instanceof HumanPlayer)
                        for(int j=0;j<draw2;j++)
                            ((HumanPlayer)players[index]).addCard(uno.getCardRandomly());
                    if(players[index] instanceof ComputerPlayer)
                        for(int j=0;j<draw2;j++)
                            ((ComputerPlayer)players[index]).addCard(uno.getCardRandomly());

                    draw2=0;
                }
                /*if(flag!=4 && draw4!=0){
                    if(players[index] instanceof HumanPlayer)
                    for(int j=0;j<draw4;j++)
                        ((HumanPlayer)players[index]).addCard(uno.getCardRandomly());
                    if(players[index] instanceof ComputerPlayer)
                        for(int j=0;j<draw4;j++)
                            ((ComputerPlayer)players[index]).addCard(uno.getCardRandomly());
                    draw4=0;
                    if(players[(start+(i-1))%size] instanceof HumanPlayer){
                        System.out.println("Player"+(start+(i-1))%size+" enter color :");

                    }
                }*/
                //System.out.println("Last Card");
                //uno.getFirstCard().printCard();
                if(flag!=6 && flag!=0){
                    uno.getFirstCard().printCard();
                }
                for(int j=0;j<size;j++){
                    System.out.print("player"+j+" : "+players[j].getSize()+" |");
                }
                if(endGame(players))
                    break;
            }
        }
        System.out.println();
        result(players);
    }
}
