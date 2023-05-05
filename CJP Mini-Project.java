import java.util.Random;
import java.util.Scanner;

class LaunchGame 
{
   static char[][] board;

    public LaunchGame()
    {
        board = new char[3][3];
        initBoard();
    }

    void initBoard()
    {
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[i].length; j++)
            {
                board[i][j] = ' ';
            }
        }
    }

    static void dispBoard()
    {
        System.out.println("-------------");
        for(int i=0; i<board.length; i++)
        {
            System.out.print("| ");
            for(int j=0; j<board[i].length; j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }


    static void placemark(int row,int col, char mark)
    {
        if(row>=0 && row<=2 && col>=0 && col<=2)
        {
            board[row][col] = mark;
        }
        else{
            System.out.println("Invalid Position!");
        }
    }

    static boolean checkColwin()
    {
        for(int j=0; j<=2; j++)
        {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowwin()
    {
        for(int i=0; i<=2; i++)
        {
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagwin()
    {
        if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] 
        || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static boolean checkDraw()
    {
        for(int i=0; i<=2; i++)
        {
            for(int j=0; j<=2; j++)
            {
                if(board[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}


abstract class player
{
    String name;
    char mark;

    abstract void makeMove();
    Boolean isvalidMove(int row,int col)
    {
        if(row>=0 && row <=2 && col>=0 && col<=2)
        {
            if(LaunchGame.board[row][col] == ' ')
            {
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends player
{
    HumanPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do{
            System.out.println("Enter Row And Coloumn");
            row = sc.nextInt();
            col = sc.nextInt();
        }
        while(!isvalidMove(row, col));

        LaunchGame.placemark(row, col, mark);

    }
}

class AIPlayer extends player
{
    AIPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    void makeMove()
    {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do
        {
           Random r = new Random();
           row = r.nextInt(3);
           col = r.nextInt(3);   
        }
        while(!isvalidMove(row, col));

        LaunchGame.placemark(row, col, mark);

    }
}

class TicTacToe
{
    public static void main(String[] args)
    {
        LaunchGame t = new LaunchGame();
        Scanner k = new Scanner(System.in);
        System.out.println("Enter your choice : ");
        System.out.println("1)Want to play in Multiplayer");
        System.out.println("2)Want to play With AI");
        int s = k.nextInt();

        if(s == 1){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter name of player 1 : ");
            String name = sc.nextLine();
            System.out.println("Enter name of Player 2 : ");
            String name1 = sc.nextLine();
            System.out.println(name + " Kindly Choose a Symbol(X or O) : ");
            char mark = sc.next().charAt(0);
            // System.out.println(name1 + "Kindly Choose a Symbol(X or O) : ");
            // char mark1 = sc.next().charAt(0);
            char mark1;
            if(mark == 'X'){
                mark1 = 'O';
                System.out.println(name1 + " You Got " + mark1);
            }
            else{
                mark1 = 'X';
                System.out.println(name1 + " You Got " + mark1);
            }
            HumanPlayer p1 = new HumanPlayer(name,mark);
            HumanPlayer p2 = new HumanPlayer(name1, mark1);
        
            player cp;
            cp = p1;

            while(true)
            {
                System.out.println(cp.name +" Turn");
                cp.makeMove();
                LaunchGame.dispBoard();
                if(LaunchGame.checkColwin() || LaunchGame.checkRowwin() || LaunchGame.checkDiagwin())
                {
                    System.out.println(cp.name + " Has Won");
                    break;
                }
                else if(LaunchGame.checkDraw())
                {
                    System.out.println("OOPS IT's A Draw!");
                    break;
                }
                else
                {
                    if(cp == p1)
                    {
                    cp = p2;
                    }
                    else{
                    cp = p1;
                    }
                }
            }

        }
        else if(s == 2){
            Scanner j = new Scanner(System.in);
            System.out.println("Enter name of Player : ");
            String name2 = j.nextLine();
            HumanPlayer p1 = new HumanPlayer(name2,'X');
            AIPlayer p2 = new AIPlayer("AI", 'O');
        
            player cp;
            cp = p1;

            while(true)
            {
                System.out.println(cp.name +" Turn");
                cp.makeMove();
                LaunchGame.dispBoard();
                if(LaunchGame.checkColwin() || LaunchGame.checkRowwin() || LaunchGame.checkDiagwin())
                {
                    System.out.println(cp.name + " Has Won");
                    break;
                }
                else if(LaunchGame.checkDraw())
                {
                    System.out.println("Draw Game!");
                    break;
                }
                else
                {
                    if(cp == p1)
                    {
                    cp = p2;
                    }
                    else{
                    cp = p1;
                    }
                }
            }

        }
        else{
            System.out.println("Invalid Number!");
        }
    }
}
