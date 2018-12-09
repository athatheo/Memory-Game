public class Card {
    private char content;

    /**
     * Constructor
     * @param content is the letter of each card
     */
    public Card (char content){
        this.content = content;
    }

    /**
     * Changes the content of the card
     * @param content the letter of the card
     */
    public void setContent(char content){
        this.content = content;
    }

    /**
     *
     * @return the content (character) of each card
     */
    public char getContent(){
        return content;
    }
}
