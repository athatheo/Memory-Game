public class Player {
    private int score;

    /**
     * Constructor that initializes the score variable
     */
    public Player(){
        score=0;
    }

    /**
     * Function that increases the score by 1.
     */

    public void increaseScore(){
        score++;
    }

    /**
     *
     * @return score
     */
    public int getScore(){
        return score;
    }
}
