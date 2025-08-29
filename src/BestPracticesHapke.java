/**
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 8/22/2025
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BestPracticesHapke {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is your name, Brave adventurer?");

        String name = scnr.nextLine();
        Adventurer littleJimmy = new Adventurer(name, 10);

        boolean keepPlaying = true;
        while (keepPlaying){
            System.out.println("What do you do next " + littleJimmy.getName() + "?");
            System.out.println("Options are: \nFight a Monster\nSee money\nPay Money\nGain Money\nSee Items\nGain Item\nShare With Friends\nQuit");
            String userInput = scnr.nextLine();
            keepPlaying = runAdventure(userInput, littleJimmy);
        }
        scnr.close();

    }
    public static boolean runAdventure(String userInput, Adventurer littleJimmy){
        String flattenedUserInput = userInput.toLowerCase();
        if (flattenedUserInput.equals("quit")){
            System.out.println("\n    Thanks for Playing!\n    Goodbye!\n");
            return false;
        }
        if (flattenedUserInput.equals("see money")){
            System.out.print("\n   " + littleJimmy.getName() + " has ");
            System.out.printf("%.2f", littleJimmy.getGold());
            System.out.println(" gold currently\n");
        }
        if (flattenedUserInput.equals("pay money")){
            System.out.println("\n   How much money do you pay?");
            Scanner scnr = new Scanner(System.in);
            double amount = Math.abs(scnr.nextDouble());
            System.out.print("   " + littleJimmy.getName() + " has ");
            System.out.printf("%.2f", littleJimmy.useMoney("spend", amount));
            System.out.println(" gold left.\n");
        }
        if (flattenedUserInput.equals("gain money")){
            System.out.println("\n   How much money do you gain?");
            Scanner scnr = new Scanner(System.in);
            double amount = Math.abs(scnr.nextDouble());
            System.out.print("   " + littleJimmy.getName() + " has ");
            System.out.printf("%.2f", littleJimmy.useMoney("gain", amount));
            System.out.println(" gold.\n");
        }
        if (flattenedUserInput.equals("see items")) {
            System.out.println("\n    " + littleJimmy.getEquipment() + "\n");
        }
        if (flattenedUserInput.equals("gain item")) {
            Scanner scnr = new Scanner(System.in);
            System.out.println("\n   What item do you gain?");
            System.out.println("\n    " + littleJimmy.gainEquipment(scnr.nextLine()) + "\n");
        }
        if (flattenedUserInput.equals("share with friends")) {
            Scanner scnr = new Scanner(System.in);
            System.out.println("\n   How many friends do you have?");
            int friends = Math.abs(scnr.nextInt());
            int personalShareOfMoney = 2;
            double remainingGold = (littleJimmy.getGold() / (friends + personalShareOfMoney));

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.getGold());
            System.out.print("gold currently.\n   You share ");
            System.out.printf("%.2f", remainingGold);
            System.out.print(" of your gold with each of your friends\n\n   But you keep ");
            System.out.printf("%.2f", (personalShareOfMoney * remainingGold));
            System.out.println(" gold for yourself!\n");

            littleJimmy.setGold(personalShareOfMoney * remainingGold);
        }
        if (flattenedUserInput.equals("fight a monster")) {
            double fightResult = 100 * Math.random();

            System.out.print("\n   You roll a ");
            System.out.printf("%.2f", fightResult);
            System.out.println(" / 100.00");
            if (fightResult >= 50){
                System.out.println("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"");
                System.out.print("   You won the fight, and ");
                System.out.printf("%.2f", (fightResult / 10));
                System.out.println(" gold!");

                System.out.print("   You have ");
                System.out.printf("%.2f", littleJimmy.useMoney("gain", (fightResult / 10)));
                System.out.println(" gold currently\n");
            } else if (5 < fightResult){
                System.out.println("   " + littleJimmy.getName() + " Shouts \"Ouchie!!\"");
                System.out.print("   You Lost the fight, and ");
                System.out.printf("%.2f", (fightResult / 10));
                System.out.println(" gold.");

                System.out.print("   You have ");
                System.out.printf("%.2f", littleJimmy.useMoney("spend", (fightResult / 10)));
                System.out.println(" gold currently\n");
            } else {
                System.out.println("   " + littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"");
                System.out.println("   You lost the fight and Died!\n\n\n\nChoose a new Adventurer Name!");

                Scanner scnr = new Scanner(System.in);
                littleJimmy.setName(scnr.nextLine());
                littleJimmy.setGold(10);
                ArrayList<String> equipment = new ArrayList<>();
                equipment.add("Sword");
                equipment.add("Shield");
                littleJimmy.setEquipment(equipment);
            }
        }

        return true;
    }
}



