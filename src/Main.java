import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello, let's test your memory!");
        int choice=-1;
        GameUI newGame;
        while (choice!=4){
            System.out.println("Choose a game from the following:\n 1) Basic \n 2) Double \n 3) Trio \n 4) Exit");
            choice = scanForInt();
            if (!(choice>0&&choice<5)){
                System.out.println("Please choose a number between 1 and 3 :)");
            } else if (choice>0&&choice<4){
                newGame = new GameUI(choice);
                newGame.start();
            }
        }

    }
    /**
     * Checks if the input is integer, to prevent from crash
     * @return the integer scanned
     */
    private static int scanForInt(){
        while (!scanner.hasNextInt()){
            System.out.println("Please enter an integer!");
            scanner.next();
        }
        int num1 = scanner.nextInt();
        return num1;
    }
}
