/**
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 8/22/2025
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
            System.out.println("Options are: \nFight a Monster\tSee Money\tSee Status\nPay Money\tGain Money\tHeal\nSee Items\tGain Item\nShare With Friends\tQuit");
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
        if (flattenedUserInput.equals("see status")){
            littleJimmy.seeStatus();
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
            goShopping(littleJimmy);
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
            runCombat(littleJimmy);
        }
        if (flattenedUserInput.equals("heal")){
            System.out.println(littleJimmy.getName() + " goes to a local healer, and spends 10 gp to heal 5HP\n");
            littleJimmy.useMoney("spend", 10);
            littleJimmy.feelBetter(5);
            System.out.printf("%.2f", littleJimmy.getGold());
            System.out.println(" gold remaining\n" + littleJimmy.getHealth() + " / " + littleJimmy.getMaxHealth() + " HP");
        }

        return true;
    }

    public static void runCombat(Adventurer littleJimmy){
        Scanner scnr = new Scanner(System.in);
        double fightResult = 100 * Math.random();
        double itemBonus = 0;
        int damage = 1 + ((int) (Math.random() * 3));

        System.out.println("Do you want to use an item? Y/n");
        String useItem = scnr.nextLine();
        if (useItem.equals("Y") || useItem.equals("y") || useItem.equals("yes") || useItem.equals("Yes")){
            System.out.println("available items are:");
            for (Item item: littleJimmy.getEquipment()){
                if (item.combatBonus > 0){
                    System.out.println(item.name);
                }
            }
            System.out.println("Which item will you use?");
            useItem = scnr.nextLine();
            useItem = useItem.toLowerCase();
            for (Item item : littleJimmy.getEquipment()){
                if (item.name.toLowerCase().equals(useItem) && item.combatBonus > 0){
                    itemBonus = item.combatBonus;
                }
            }
        }


        System.out.print("\n   You roll a ");
        System.out.printf("%.2f", (fightResult + itemBonus));
        System.out.println(" / 100.00");
        if (fightResult + itemBonus >= 90){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"");
            System.out.print("   You won the fight, and ");
            System.out.printf("%.2f", (fightResult / 10));
            System.out.println(" gold!");

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.useMoney("gain", (fightResult / 10)));
            System.out.println(" gold currently\n");
        } else if (fightResult + itemBonus >= 50){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"");
            System.out.print("   You won the fight, and ");
            System.out.printf("%.2f", (fightResult / 10));
            System.out.println(" gold!");

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.useMoney("gain", (fightResult / 10)));
            System.out.println(" gold currently");

            System.out.println("   But you still took " + damage + " damage");
            System.out.println("   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining\n");
        } else if (fightResult + itemBonus > 5){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"Ouchie!!\"");
            System.out.print("   You Lost the fight, and ");
            System.out.printf("%.2f", (fightResult / 10));
            System.out.println(" gold.");

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.useMoney("spend", (fightResult / 10)));
            System.out.println(" gold currently");

            System.out.println("   You also took " + 2 * damage + " damage");
            System.out.println("   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(2 * damage) + " HP remaining\n");
        }
        if (fightResult + itemBonus <= 5 || littleJimmy.getHealth() == 0){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"");
            System.out.println("   You lost the fight and Died!\n");
            littleJimmy.getScore();
            System.out.println("\n\n\nChoose a new Adventurer Name!");

            littleJimmy.resetCharacter();
            littleJimmy.setName(scnr.nextLine());

        } else {
            littleJimmy.gainExperience((int) fightResult);
        }
    }

    public static void goShopping(Adventurer littleJimmy){
        Scanner scnr = new Scanner(System.in);
        System.out.println("\n   What item do you want?\n   'Sword' 20gp combat + 5\t'Shield' 40gp combat + 10\n   'Magic Wand' 60gp combat + 15\tany other item you can name 10gp combat + 2");
        String desiredItem = scnr.nextLine();
        String flattendDesiredItem = desiredItem.toLowerCase();
        switch (flattendDesiredItem) {
            case "sword" -> {
                littleJimmy.useMoney("spend", 20);
                littleJimmy.gainEquipment(new Item("Sword", 5, true));
            }
            case "shield" -> {
                littleJimmy.useMoney("spend", 40);
                littleJimmy.gainEquipment(new Item("Shield", 10, true));
            }
            case "magic wand" -> {
                littleJimmy.useMoney("spend", 60);
                littleJimmy.gainEquipment(new Item("Magic Wand", 15, true));
            }
            default -> {
                littleJimmy.useMoney("spend", 10);
                littleJimmy.gainEquipment(new Item(desiredItem, 2, true));
            }
        }
        System.out.print("\n   [");
        int equipmentIndex = 1;
        int piecesOfEquipment = littleJimmy.getEquipment().size();
        for (Item item: littleJimmy.getEquipment()){
            System.out.print(item.name);
            if (!(equipmentIndex == piecesOfEquipment)){
                System.out.print(", ");
            }
            equipmentIndex++;
        }
        System.out.println("]\n");
    }
}



