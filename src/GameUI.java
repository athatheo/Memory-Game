import java.util.Scanner;

public class GameUI {
    private Game game;
    private int rows;
    private int cols;
    private int[][] found; //Keeps which cards have been opened for interface reasons
    Scanner scanner;
    int choice;

    /**
     * Constructor
     * @param choice which is to be played
     */
    public GameUI(int choice){
        this.choice = choice;
        game = new Game(choice);
        rows = game.getTable().getRows();
        cols = game.getTable().getColumns();
        found = new int[rows][cols];
        scanner = new Scanner(System.in);
    }

    /**
     * This is where the interface is being run. Prints the table, messages for the player, and also calls function from the Game Class for checks.
     * Also calls some functions from this class for better modularity.
     */
    public void start(){
        initializeFound2DArray(); //Initializes the found array
        int row1,col1,row2,col2,row3,col3;
        while(true){
            printTable();
            System.out.println("You have done "+game.getPlayerScore()+" moves so far!");
            System.out.println("If you want to exit enter the number 100 for the first row!");
            System.out.println("Please choose row and column for the cards (first row is 1, not 0, same for columns): ");
            row1 = scanForInt()-1;
            if (row1==99){
                break; //The player asked for exit
            }
            row1 = checkForRow(row1); //Checks if it's a valid row
            col1 = scanForInt()-1;
            col1 = checkForCol(col1); //Checks if it's a valid column
            if (game.checkForValid(row1,col1)){ //Checks if the choice of card can be played
                printTable(row1,col1);
                row2 = scanForInt()-1;
                row2 = checkForRow(row2);
                col2 = scanForInt()-1;
                col2 = checkForCol(col2);
                if (game.checkForValid(row2,col2)){
                    printTable(row1,col1,row2,col2);
                    if (choice==3)
                    {
                        row3 = scanForInt()-1;
                        row3 = checkForRow(row3);
                        col3 = scanForInt()-1;
                        col3 = checkForCol(col3);
                        if (game.checkForValid(row3,col3)){
                            printTable(row1,col1,row2,col2,row3,col3);
                            if(game.checkCards(row1,col1,row2,col2,row3,col3)) { //Checks if the cards are the same or not and if the player chose the same card multiple times
                                found[row1][col1]=1; //Changes the found array in ones, so in the next printTable the chosen cards will be displayed as '-'
                                found[row2][col2]=1;
                                found[row3][col3]=1;
                                System.out.println("Well Done! Correct Guess!");
                                if(game.checkForWin()){
                                    break;
                                }
                            } else {
                                System.out.println("Wrong! Try Again!");
                            }
                        } else {
                            System.out.println("Please choose a valid card!");
                        }
                    } else {
                        if(game.checkCards(row1,col1,row2,col2)){
                            found[row1][col1]=1;
                            found[row2][col2]=1;
                            System.out.println("Well Done! Correct Guess!");
                            if(game.checkForWin()){
                                break;
                            }
                        } else {
                            System.out.println("Wrong! Try Again!");
                        }
                    }
                } else {
                    System.out.println("Please choose a valid card!");
                }
            } else {
                System.out.println("Please choose a valid card!");
            }
        }
        if (row1!=99){
            System.out.println("You finished in "+game.getPlayerScore()+" steps!");
        }
        System.out.println("You exited the game!");
    }

    /**
     * Checks if the input is integer, to prevent from crash
     * @return the integer scanned
     */

    private int scanForInt(){
        while (!scanner.hasNextInt()){
            System.out.println("Please enter an integer!");
            scanner.next();
        }
        int num1 = scanner.nextInt();
        return num1;
    }

    /**
     *
     * @param row
     * @return boolean value if its an acceptable value for the row
     */
    private int checkForRow(int row){
        while (row>=rows || row<0 ){
            System.out.println("Please enter a valid number for a row!");
            row = scanForInt()-1;
        }
        return row;
    }

    /**
     *
     * @param col
     * @return boolean value if its an acceptable value for the row
     */
    private int checkForCol(int col){
        while (col>=cols || col<0){
            System.out.println("Please enter a valid number for a column!");
            col = scanForInt()-1;
        }
        return col;
    }

    /**
     * Just initializes the found array with zeroes
     */
    private void initializeFound2DArray(){
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                found[i][j]=0;
            }
        }
    }

    /**
     * Prints the table with everything hidden
     */
    private void printTable(){
        System.out.println("-------------------------------------------");
        System.out.print(" ");
        for (int i=0; i<cols; i++){
            System.out.print("  ");
            System.out.print(i+1);
        }
        System.out.println();
        for (int i=0; i<rows; i++){
            System.out.print(i+1+": ");
            for (int j=0; j<cols; j++){
                if (found[i][j]==0){
                    System.out.print("[] ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the table with one card shown
     * @param row1 indicates the row of the wanted card
     * @param col1 indicates the column of the wanted card
     */
    private void printTable(int row1,int col1){
        System.out.print(" ");
        for (int i=0; i<cols; i++){
            System.out.print("  ");
            System.out.print(i+1);
        }
        System.out.println();
        for (int i=0; i<rows; i++){
            System.out.print(i+1+": ");
            for (int j=0; j<cols; j++){
                if (i==row1&&j==col1){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else{
                    if (found[i][j]==0){
                        System.out.print("[] ");
                    } else {
                        System.out.print(" - ");
                    }

                }
            }
            System.out.println();
        }
    }

    /**
     *
     * Prints the table with two card shown
     * @param row1 indicates the row of the 1st wanted card
     * @param col1 indicates the column of the 1st wanted card
     * @param row2 indicates the row of the 2nd wanted card
     * @param col2 indicates the column of the 2nd wanted card
     */
    private void printTable(int row1,int col1, int row2, int col2){
        System.out.print(" ");
        for (int i=0; i<cols; i++){
            System.out.print("  ");
            System.out.print(i+1);
        }
        System.out.println();
        for (int i=0; i<rows; i++){
            System.out.print(i+1+": ");
            for (int j=0; j<cols; j++){
                if (i==row1&&j==col1){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else if (i==row2&&j==col2){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else {
                    if (found[i][j]==0){
                        System.out.print("[] ");
                    } else {
                        System.out.print(" - ");
                    }

                }
            }
            System.out.println();
        }
    }

    /**
     *
     * Prints the table with two card shown
     * @param row1 indicates the row of the 1st wanted card
     * @param col1 indicates the column of the 1st wanted card
     * @param row2 indicates the row of the 2nd wanted card
     * @param col2 indicates the column of the 2nd wanted card
     * @param row3 indicates the row of the 3rd wanted card
     * @param col3 indicates the column of the 3rd wanted card
     */
    private void printTable(int row1,int col1, int row2, int col2, int row3, int col3){
        System.out.print(" ");
        for (int i=0; i<cols; i++){
            System.out.print("  ");
            System.out.print(i+1);
        }
        System.out.println();
        for (int i=0; i<rows; i++){
            System.out.print(i+1+": ");
            for (int j=0; j<cols; j++){
                if (i==row1&&j==col1){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else if(i==row2&&j==col2){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else if(i==row3&&j==col3){
                    System.out.print(" "+game.getTable().getCard(i,j).getContent()+" ");
                } else {
                    if (found[i][j]==0){
                        System.out.print("[] ");
                    } else {
                        System.out.print(" - ");
                    }
                }
            }
            System.out.println();
        }
    }
}
