import java.util.ArrayList;

public class Content {
    public ArrayList<Character> possibleContent;
    private int numOfLetters;
    private int copies;

    /**
     * Constructor
     * @param numOfLetters how many letters should be used (e.g. Basic game 12 Letters)
     * @param copies how many copies of each letter (e.g. Trio has 3 copies of each letter)
     */
    public Content(int numOfLetters, int copies){
        this.numOfLetters=numOfLetters;
        this.copies=copies;
        possibleContent = new ArrayList<Character>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        addContent(alphabet);
    }

    /**
     * Function that adds every copy of every letter needed according to the parameters given in the constructor
     * @param alphabet a char array with every character in the alphabet
     */
    private void addContent(char[] alphabet){
        for (int i = 0; i<numOfLetters; i++){
            for (int j=0; j<copies; j++){
                possibleContent.add(Character.valueOf(alphabet[i]));
            }
        }
    }
}
