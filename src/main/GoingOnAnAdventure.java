package main; /*
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 9/27/2025
 */
import logic.runAdventure;
import model.Adventurer;

import java.util.Scanner;

public class GoingOnAnAdventure {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is your name, Brave adventurer?");

        String name = scnr.nextLine();
        Adventurer littleJimmy = new Adventurer(name, 10);
        runAdventure adventure = new runAdventure();

        boolean keepPlaying = true;
        while (keepPlaying){
            System.out.println("\n   What do you do next " + littleJimmy.getName() + "?");
            System.out.println("Options are: \nFight a Monster\t\tSee Money\nStatus\tHeal\tSee Items\nShop\tSell Item\tShare With Friends\nQuit");
            String userInput = scnr.nextLine();
            if (userInput.equalsIgnoreCase("quit")){
                keepPlaying = false;
            } else {
                adventure.run(userInput, littleJimmy);
            }
        }
        System.out.println("\n    Thanks for Playing!\n    Goodbye!\n");
        scnr.close();
    }
}



