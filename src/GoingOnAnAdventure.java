/**
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 9/27/2025
 *
 * As an aside, I have been using w3schools as a reference. Mostly to look up string or arrayList methods.
 * But it's a great resource. So here is its reference.
 *
 */
import java.util.Scanner;

public class GoingOnAnAdventure {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is your name, Brave adventurer?");

        String name = scnr.nextLine();
        Adventurer littleJimmy = new Adventurer(name, 10);

        boolean keepPlaying = true;
        while (keepPlaying){
            System.out.println("What do you do next " + littleJimmy.getName() + "?");
            System.out.println("Options are: \nFight a Monster\t\tSee Money\nStatus\tHeal\tSee Items\nShop\tSell Item\tShare With Friends\nQuit");
            String userInput = scnr.nextLine();
            if (userInput.equalsIgnoreCase("quit")){
                keepPlaying = false;
            } else {
                runAdventure.run(userInput, littleJimmy);
            }
        }
        System.out.println("\n    Thanks for Playing!\n    Goodbye!\n");
        scnr.close();
    }
}



