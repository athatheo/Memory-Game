public class Game {
    private Table table;
    private Player player;
    private int matchesMade;
    private int choice;

    /**
     * Constructor that creates the table with the contents for the game that was chosen
     * @param choice that indicates which game the player chose
     */
    public Game(int choice){
        matchesMade=0;
        this.choice = choice;
        player = new Player();
        if (choice == 1){
            table = new Table(4,6);
        } else if (choice == 2){
            table = new Table(6,8);
        } else {
            table = new Table(6,6);
        }
    }

    /**
     * @return boolean value on if the game is over or not
     */
    public boolean checkForWin(){
        matchesMade++;
        if (choice==2&&matchesMade==24){
            return true;
        } else if ((choice==1||choice==3)&&matchesMade==12){
            return true;
        }
        return false;
    }

    /**
     *
     * @param row1 indicates the row of the wanted card
     * @param col1 indicates the row of the wanted card
     * @return if its an invalid card (already played)
     */
    public boolean checkForValid(int row1, int col1){
        return table.getCard(row1,col1).getContent()!='-';
    }

    /**
     *
     * @param row1 indicates the row of the 1st wanted card
     * @param col1 indicates the column of the 1st wanted card
     * @param row2 indicates the row of the 2nd wanted card
     * @param col2 indicates the column of the 2nd wanted card
     * @return boolean value on if the two chosen cards were
     */

    public boolean checkCards(int row1, int col1, int row2, int col2){
        if (row1==row2&&col1==col2){
            System.out.println("You can't choose the same card! Don't cheat :) ");
            return false;
        }
        if (table.getCard(row1,col1).getContent()==table.getCard(row2,col2).getContent()){
            table.setCard(row1,col1);
            table.setCard(row2,col2);
            updateScore();
            return true;
        }
        updateScore();
        return false;
    }

    /**
     * Same as the previous function but for 3 cards
     * @return boolean value on whether the 3 cards are the same
     */
    public boolean checkCards(int row1, int col1, int row2, int col2,int row3, int col3){
        if ((row1==row2&&col1==col2)||(row1==row3&&col1==col3)||(row2==row3&&col2==col3)){
            System.out.println("You can't choose the same card! Don't cheat :) ");
            return false;
        }
        if (table.getCard(row1,col1).getContent()==table.getCard(row2,col2).getContent()){
            if (table.getCard(row2,col2).getContent()==table.getCard(row3,col3).getContent()){
                table.setCard(row1,col1);
                table.setCard(row2,col2);
                table.setCard(row3,col3);
                updateScore();
                return true;
            }
        }
        updateScore();
        return false;
    }

    /**
     *
     * @return the table of the game
     */
    public Table getTable(){ return table;}

    /**
     * Increases the score of the player of this game
     */
    private void updateScore(){
        player.increaseScore();
    }

    /**
     *
     * @return the score of the player of this game
     */
    public int getPlayerScore(){
        return player.getScore();
    }
}
