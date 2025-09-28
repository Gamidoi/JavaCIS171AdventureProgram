import java.util.Scanner;

public class runAdventure {
    public static boolean run(String userInput, Adventurer littleJimmy){
        String flattenedUserInput = userInput.toLowerCase();
        Scanner scnr = new Scanner(System.in);
        switch (flattenedUserInput){
            case "quit": {
                System.out.println("\n    Thanks for Playing!\n    Goodbye!\n");
                return false;
            }
            case "see status": {
                littleJimmy.seeStatus();
                break;
            }
            case "see money": {
                System.out.print("\n   " + littleJimmy.getName() + " has ");
                System.out.printf("%.2f", littleJimmy.getGold());
                System.out.println(" gold currently\n");
                break;
            }
            case "see items": {
                System.out.println("\n    " + littleJimmy.listEquipment() + "\n");
                break;
            }
            case "shop":  {
                goShopping(littleJimmy);
                break;
            }
            case "share with friends": {
                System.out.println("\n   How many friends do you have?");
                int friends = Math.abs(scnr.nextInt());
                sharingIsCaring(littleJimmy, friends);
                break;
            }
            case "fight a monster": {
                runCombat(littleJimmy);
                break;
            }
            case "heal": {
                System.out.println(littleJimmy.getName() + " goes to a local healer, and spends 10 gp to heal 5HP\n");
                littleJimmy.useMoney("spend", 10);
                littleJimmy.feelBetter(5);
                System.out.printf("%.2f", littleJimmy.getGold());
                System.out.println(" gold remaining\n" + littleJimmy.getHealth() + " / " + littleJimmy.getMaxHealth() + " HP");
                break;
            }
        }

        return true;
    }

    public static void runCombat(Adventurer littleJimmy){
        final double GREATSUCCESS = 90.0;
        final double SUCCESS = 50.0;
        final double FAIL = 5.0;

        Scanner scnr = new Scanner(System.in);
        double fightResult = 100 * Math.random();
        double itemBonus = 0;
        int damage = 1 + ((int) (Math.random() * 3));

        System.out.println("Do you want to use an item? Y/n");
        String useItem = scnr.nextLine();
        if (useItem.contains("Y") || useItem.contains("y")){
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
        if (fightResult + itemBonus >= GREATSUCCESS){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"");
            System.out.print("   You won the fight, and ");
            System.out.printf("%.2f", (fightResult / 10));
            System.out.println(" gold!");

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.useMoney("gain", (fightResult / 10)));
            System.out.println(" gold currently\n");
        } else if (fightResult + itemBonus >= SUCCESS){
            System.out.println("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"");
            System.out.print("   You won the fight, and ");
            System.out.printf("%.2f", (fightResult / 10));
            System.out.println(" gold!");

            System.out.print("   You have ");
            System.out.printf("%.2f", littleJimmy.useMoney("gain", (fightResult / 10)));
            System.out.println(" gold currently");

            System.out.println("   But you still took " + damage + " damage");
            System.out.println("   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining\n");
        } else if (fightResult + itemBonus > FAIL){
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
        if (fightResult + itemBonus <= FAIL || littleJimmy.getHealth() == 0){
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
                if (littleJimmy.getGold() >= 20.0) {
                    littleJimmy.useMoney("spend", 20.0);
                    littleJimmy.gainEquipment(new Item("Sword", 5, true));
                } else {
                    System.out.println(littleJimmy.getName() + " does not have enough gold.");
                }
            }
            case "shield" -> {
                if (littleJimmy.getGold() >= 40.0) {
                    littleJimmy.useMoney("spend", 40.0);
                    littleJimmy.gainEquipment(new Item("Shield", 10, true));
                } else {
                    System.out.println(littleJimmy.getName() + " does not have enough gold.");
                }
            }
            case "magic wand" -> {
                if (littleJimmy.getGold() >= 60.0) {
                    littleJimmy.useMoney("spend", 60.0);
                    littleJimmy.gainEquipment(new Item("Magic Wand", 15, true));
                } else {
                    System.out.println(littleJimmy.getName() + " does not have enough gold.");
                }
            }
            default -> {
                if (littleJimmy.getGold() >= 10.0) {
                    littleJimmy.useMoney("spend", 10.0);
                    littleJimmy.gainEquipment(new Item(desiredItem, 2, true));
                } else {
                    System.out.println(littleJimmy.getName() + " does not have enough gold.");
                }
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

    public static void sharingIsCaring(Adventurer littleJimmy, int friends){
        int personalShareOfMoney = 2;
        int totalShares = friends + personalShareOfMoney;
        double remainingGold = (littleJimmy.getGold() / (totalShares));

        System.out.print("   You have ");
        System.out.printf("%.2f", littleJimmy.getGold());
        System.out.print("gold currently.\n   You share ");
        System.out.printf("%.2f", remainingGold);
        System.out.print(" of your gold with each of your friends\n\n   But you keep ");
        System.out.printf("%.2f", (personalShareOfMoney * remainingGold));
        System.out.println(" gold for yourself!\n");

        littleJimmy.gainScore((int) (remainingGold * totalShares));
        littleJimmy.setGold(personalShareOfMoney * remainingGold);
    }

}
