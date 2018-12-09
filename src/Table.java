import java.util.Random;

public class Table {
    private int rows;
    private int columns;
    private Card[][] cards;

    /**
     * Constructor of the class
     * @param rows indicates how many rows the Table has
     * @param columns indicates how many columns the Table has
     */
    public Table(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.cards = new Card[rows][columns];
        fillTheTable();
    }

    /**
     *
     * @param row indicates in which row the wanted card is
     * @param col indicates in which columns the wanted card is
     * @return the card in a specific row and column
     */

    public Card getCard(int row, int col){
        return cards[row][col];
    }

    /**
     *
     * @return the number of rows of the table
     */

    public int getRows(){ return rows;}

    /**
     *
     * @return the number of columns of the table
     */
    public int getColumns(){ return columns;}

    /**
     * This function changes the card with an "empty", "invalid" one
     * @param row indicates in which row the wanted card is
     * @param col indicates in which columns the wanted card is
     */
    public void setCard(int row, int col){
        cards[row][col] = new Card('-');

    }

    /**
     * Depending on which game is being played, this function fills the table with content (a,b,c..)
     */
    private void fillTheTable(){
        int temp;
        Random rn = new Random();
        if (rows==4){
            Content content = new Content(12,2);
            for (int i=0; i<4; i++){
                for (int j=0; j<6; j++){
                    temp = rn.nextInt(content.possibleContent.size());
                    cards[i][j] = new Card(content.possibleContent.get(temp).charValue());
                    content.possibleContent.remove(temp);
                }
            }
        } else if (columns==8){
            Content content = new Content(24,2);
            for (int i=0; i<6; i++){
                for (int j=0; j<8; j++){
                    temp = rn.nextInt(content.possibleContent.size());
                    cards[i][j] = new Card(content.possibleContent.get(temp).charValue());
                    content.possibleContent.remove(temp);
                }
            }
        } else if (columns==6){
            Content content = new Content(12,3);
            for (int i=0; i<6; i++){
                for (int j=0; j<6; j++){
                    temp = rn.nextInt(content.possibleContent.size());
                    cards[i][j] = new Card(content.possibleContent.get(temp).charValue());
                    content.possibleContent.remove(temp);
                }
            }
        }
    }
}
