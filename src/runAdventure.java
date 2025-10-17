import java.util.Scanner;

public class runAdventure {
    Scanner scnr = new Scanner(System.in);
    public void run(String userInput, Adventurer littleJimmy){
        String flattenedUserInput = userInput.toLowerCase();
        switch (flattenedUserInput){
            case "status":
            case "statu":
            case "stats":
            case "stat":
            case "inventory":
                System.out.print(littleJimmy.seeStatus());
                break;

            case "see money":
            case "money":
            case "see gold":
            case "gold":
                System.out.print("\n   " + littleJimmy.getName() + " has " + String.format("%.2f", littleJimmy.getGold()) + " gold currently\n");
                break;

            case "see items":
            case "see item":
            case "items":
            case "item":
                System.out.print("\n    " + littleJimmy.listEquipment() + "\n");
                break;

            case "shop":
            case "buy":
            case "buy items":
            case "buy item":
                goShopping(littleJimmy);
                break;

            case "sell items":
            case "sell item":
            case "sell":
                goSelling(littleJimmy);
                break;

            case "share with friends":
            case "share with friend":
            case "share":
            case "friends":
            case "friend":
            case "share with":
                sharingIsCaring(littleJimmy);
                break;

            case "fight a monster":
            case "fight":
            case "monsters":
            case "monster":
            case "fight monsters":
            case "fight monster":
                runCombat(littleJimmy);
                break;

            case "heal":
            case "recover":
                seeHealer(littleJimmy);
                break;
        }
    }

    public void seeHealer(Adventurer littleJimmy){
        double costToHeal = littleJimmy.getMaxHealth();
        int amountToHeal = littleJimmy.getMaxHealth() / 2;
        double randomHealMod = littleJimmy.getRandom();
        if (randomHealMod > 0.60){
            amountToHeal++;
        } else if (randomHealMod < 0.30){
            amountToHeal--;
        }

        if (littleJimmy.getGold() >= costToHeal) {
            System.out.print("\n   " + littleJimmy.getName() + " goes to the local healer, and spends " + costToHeal + " gp to heal " + amountToHeal + "HP\n\n");
            littleJimmy.useMoney("spend", costToHeal);
            littleJimmy.feelBetter(amountToHeal);
            System.out.print(String.format("%.2f", littleJimmy.getGold()) + " gold remaining\n" + littleJimmy.getHealth() + " / " + littleJimmy.getMaxHealth() + " HP\n");
        } else {
            System.out.print("\n   the local healer is asking for " + costToHeal + "gp for his services.\n   " + littleJimmy.getName() + " is too poor to see the healer\n");
        }
    }

    public void runCombat(Adventurer littleJimmy){
        final double GREATSUCCESS = 90.0;
        final double SUCCESS = 50.0;
        final double FAIL = 20.0;
        final double MAJORFAIL = 5.5;

        double fightResult = 100 * littleJimmy.getRandom();
        double itemBonus = 0;
        int damage = (int) Math.ceil(littleJimmy.getRandom() * 3.0);

        System.out.print("Do you want to use an item? Y/n\n");
        String useItem = scnr.nextLine();
        if (useItem.contains("Y") || useItem.contains("y")){
            System.out.print("available items are:\n");
            for (Item item: littleJimmy.getEquipment()){
                if (item.combatBonus > 0){
                    System.out.println(item.name);
                }
            }
            System.out.print("Which item will you use?\n");
            useItem = scnr.nextLine();
            useItem = useItem.toLowerCase();
            for (Item item : littleJimmy.getEquipment()){
                if (item.name.toLowerCase().equals(useItem) && item.combatBonus > 0){
                    itemBonus = item.combatBonus;
                }
            }
        }

        double rewardResult = fightResult / 9;
        if (fightResult  + itemBonus < 65.0){
            rewardResult *= 0.8;
        }

        System.out.println("\n   You roll a " + String.format("%.2f", (fightResult + itemBonus)) + " / 100.00");
        if (fightResult + itemBonus >= GREATSUCCESS){
            System.out.print("   " + littleJimmy.getName() + " Shouts \"Huzzah!!\"\n   You won the fight, and "+ String.format("%.2f", (rewardResult)) + " gold!\n");
            System.out.print("   You have " + String.format("%.2f", littleJimmy.useMoney("gain", (rewardResult))) + " gold currently\n");
            littleJimmy.gainScore(2);
        } else if (fightResult + itemBonus >= SUCCESS){
            System.out.print("   " + littleJimmy.getName() + " Shouts \"Yay!\"\n   You won the fight, and " + String.format("%.2f", (rewardResult)) + " gold!\n");
            System.out.print("   You have " + String.format("%.2f", littleJimmy.useMoney("gain", (rewardResult))) + " gold currently\n");
            System.out.print("   But you still took " + damage + " damage\n   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining\n");
            littleJimmy.gainScore(1);
        } else if (fightResult + itemBonus > FAIL){
            System.out.print("   " + littleJimmy.getName() + " Shouts \"Oww!!\"\n   You Lost the fight, and " + String.format("%.2f", (rewardResult)) + " gold.\n");
            System.out.print("   You have " + String.format("%.2f", littleJimmy.useMoney("spend", (rewardResult))) + " gold currently\n");
            System.out.print("   You also took " + damage + " damage\n   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining\n");
        } else if (fightResult + itemBonus > MAJORFAIL){
            System.out.print("   " + littleJimmy.getName() + " Shouts \"" + randomSwearWords() + "\"\n   You Lost the fight, and " + String.format("%.2f", (rewardResult)) + " gold.\n");
            System.out.print("   You have " + String.format("%.2f", littleJimmy.useMoney("spend", (rewardResult))) + " gold currently\n");
            System.out.print("   You also took " + 2 * damage + " damage\n   " + littleJimmy.getName() + " has " + littleJimmy.getHurt(2 * damage) + " HP remaining\n");
        } else {
            System.out.print("   " + littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"\n   You lost the fight and Died!\n\n");
            characterDeath(littleJimmy);
        }
        if (littleJimmy.getHealth() == 0){
            System.out.print("   " + littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"\n   HP dropped to 0 and " + littleJimmy.getName() + " Died!\n\n");
            characterDeath(littleJimmy);
        } else if (fightResult > MAJORFAIL){
            String levelUpMessage = littleJimmy.gainExperience((int) fightResult / 2);
            if (!levelUpMessage.isEmpty()){
                System.out.print(levelUpMessage);
            }
        }
    }

    public String randomSwearWords(){
        double randomizer = Math.random() * 4;
        if (randomizer > 3){
            return "Son of a Biscuit!!";
        } else if (randomizer > 2){
            return "What a Cockatrice!!";
        } else if (randomizer > 1){
            return "Mother of Fudge!!";
        }
        return "Fracking is pollution too!!";
    }

    public void characterDeath(Adventurer littleJimmy){
        System.out.print(littleJimmy.getScore());
        System.out.print("\n\nChoose a new Adventurer Name!\n");
        littleJimmy.resetCharacter();
        littleJimmy.setName(scnr.nextLine());
    }

    public void goShopping(Adventurer littleJimmy){
        System.out.print("\n   What item do you want?\n   'Sword' 20gp combat + 5\t'Shield' 40gp combat + 10\n   'Magic Wand' 60gp combat + 15\tTrinket 5gp useless\n   any other item you can name 10gp combat + 2\n");
        String desiredItem = scnr.nextLine();
        String flattendDesiredItem = desiredItem.toLowerCase();
        switch (flattendDesiredItem) {
            case "sword" -> {
                if (littleJimmy.getGold() >= 20.0) {
                    littleJimmy.useMoney("spend", 20.0);
                    littleJimmy.gainEquipment(new Item("Sword", 5, 5));
                } else {
                    System.out.print(littleJimmy.getName() + " does not have enough gold.\n");
                }
            }
            case "shield" -> {
                if (littleJimmy.getGold() >= 40.0) {
                    littleJimmy.useMoney("spend", 40.0);
                    littleJimmy.gainEquipment(new Item("Shield", 10, 10));
                } else {
                    System.out.print(littleJimmy.getName() + " does not have enough gold.\n");
                }
            }
            case "magic wand" -> {
                if (littleJimmy.getGold() >= 60.0) {
                    littleJimmy.useMoney("spend", 60.0);
                    littleJimmy.gainEquipment(new Item("Magic Wand", 15, 15));
                } else {
                    System.out.print(littleJimmy.getName() + " does not have enough gold.\n");
                }
            }
            case "trinket" -> {
                if (littleJimmy.getGold() >= 5.0) {
                    littleJimmy.useMoney("spend", 5.0);
                    littleJimmy.gainEquipment(new Item("Trinket", 15, 0));
                } else {
                    System.out.print(littleJimmy.getName() + " does not have enough gold.\n");
                }
            }
            default -> {
                if (littleJimmy.getGold() >= 10.0) {
                    littleJimmy.useMoney("spend", 10.0);
                    littleJimmy.gainEquipment(new Item(desiredItem, 2, 5));
                } else {
                    System.out.print(littleJimmy.getName() + " does not have enough gold.\n");
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
        System.out.print("]\n");
    }

    public void goSelling(Adventurer littleJimmy){
        System.out.println("\nWhich item would you like to sell?\n    " + littleJimmy.listEquipment() + "\n");
        String soldItem = scnr.nextLine();
        for (Item item: littleJimmy.getEquipment()){
            if (item.name.equalsIgnoreCase(soldItem)){
                System.out.println(littleJimmy.getName() + " sells " + item.name + " for " + (item.value / 2.0) + "gp.");
                littleJimmy.useMoney("gain", (item.value / 2.0));
                littleJimmy.loseEquipment(soldItem);
                break;
            }
        }
    }

    public void sharingIsCaring(Adventurer littleJimmy){
        System.out.println("\n   How many friends do you have?");
        int friends = Math.abs(scnr.nextInt());
        int personalShareOfMoney = 2;
        int totalShares = friends + personalShareOfMoney;
        double remainingGold = (littleJimmy.getGold() / (totalShares));

        System.out.print("   You have " + String.format("%.2f", littleJimmy.getGold()) + "gold currently.\n   You share " +
            String.format("%.2f", remainingGold) + " of your gold with each of your friends\n\n   But you keep " +
            String.format("%.2f", (personalShareOfMoney * remainingGold)) + " gold for yourself!\n");

        littleJimmy.gainScore((int) (remainingGold * totalShares));
        littleJimmy.setGold(personalShareOfMoney * remainingGold);
    }
}
