/**
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 9/27/2025
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
            System.out.println("Options are: \nFight a Monster\tSee Money\tSee Status\nHeal\tSee Items\tShop\tSell Item\nShare With Friends\tQuit");
            String userInput = scnr.nextLine();
            keepPlaying = runAdventure.run(userInput, littleJimmy);
        }
        scnr.close();
    }
}



