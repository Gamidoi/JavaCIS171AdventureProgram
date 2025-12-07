package logic;

import model.Adventurer;
import model.Item;

import java.util.ArrayList;

public class runAdventure {


    public ArrayList<String> seeHealer(Adventurer littleJimmy){
        ArrayList<String> output = new ArrayList<>();
        double costToHeal = littleJimmy.getMaxHealth();
        int amountToHeal = littleJimmy.getMaxHealth() / 2;
        double randomHealMod = littleJimmy.getRandom();
        if (randomHealMod > 0.60){
            amountToHeal++;
        } else if (randomHealMod < 0.30){
            amountToHeal--;
        }

        if (littleJimmy.getGold() >= costToHeal) {
            output.add(littleJimmy.getName() + " goes to the local healer,");
            output.add("and spends " + costToHeal + " gp to heal " + amountToHeal + "HP");
            output.add("");
            littleJimmy.useMoney("spend", costToHeal);
            littleJimmy.feelBetter(amountToHeal);
            output.add(littleJimmy.getName() + " has " + String.format("%.2f", littleJimmy.getGold()) + " gold remaining");
            output.add("and " + littleJimmy.getHealth() + " / " + littleJimmy.getMaxHealth() + " HP");
        } else {
            output.add("The local healer is asking for " + costToHeal + "gp for his services.");
            output.add(littleJimmy.getName() + " is too poor to see the healer");
        }
        return output;
    }

    public ArrayList<String> runCombat(Adventurer littleJimmy, double itemBonus){
        final double GREATSUCCESS = 90.0;
        final double SUCCESS = 50.0;
        final double FAIL = 20.0;
        final double MAJORFAIL = 5.5;
        ArrayList<String> output = new ArrayList<>();

        double fightResult = 100 * littleJimmy.getRandom();
        int damage = (int) Math.ceil(littleJimmy.getRandom() * 3.0);

        double rewardResult = fightResult / 9;
        if (fightResult  + itemBonus < 65.0){
            rewardResult *= 0.8;
        }

        output.add("You roll a " + String.format("%.2f", (fightResult)) + " / 100.00");
        if (itemBonus > 0){
            output.add("With your item it becomes a " + String.format("%.2f", (fightResult + itemBonus)));
        }
        if (fightResult + itemBonus >= GREATSUCCESS){
            output.add(littleJimmy.getName() + " Shouts \"Huzzah!!\"");
            output.add("You won the fight, and "+ String.format("%.2f", (rewardResult)) + " gold!");
            output.add("You have " + String.format("%.2f", littleJimmy.useMoney("gain", (rewardResult))) + " gold currently");
            littleJimmy.gainScore(2);
            littleJimmy.slayMonster();
        } else if (fightResult + itemBonus >= SUCCESS){
            output.add(littleJimmy.getName() + " Shouts \"Yay!\"");
            output.add("You won the fight, and " + String.format("%.2f", (rewardResult)) + " gold!");
            output.add("You have " + String.format("%.2f", littleJimmy.useMoney("gain", (rewardResult))) + " gold currently");
            output.add("But you still took " + damage + " damage");
            output.add(littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining");
            littleJimmy.gainScore(1);
            littleJimmy.slayMonster();
        } else if (fightResult + itemBonus > FAIL){
            output.add(littleJimmy.getName() + " Shouts \"Oww!!\"");
            output.add("You Lost the fight, and " + String.format("%.2f", (rewardResult)) + " gold.");
            output.add("You have " + String.format("%.2f", littleJimmy.useMoney("spend", (rewardResult))) + " gold currently");
            output.add("You also took " + damage + " damage");
            output.add(littleJimmy.getName() + " has " + littleJimmy.getHurt(damage) + " HP remaining");
        } else if (fightResult + itemBonus > MAJORFAIL){
            output.add(littleJimmy.getName() + " Shouts \"" + randomSwearWords() + "\"");
            output.add("You Lost the fight, and " + String.format("%.2f", (rewardResult)) + " gold.");
            output.add("You have " + String.format("%.2f", littleJimmy.useMoney("spend", (rewardResult))) + " gold currently");
            output.add("You also took " + 2 * damage + " damage");
            output.add(littleJimmy.getName() + " has " + littleJimmy.getHurt(2 * damage) + " HP remaining");
        } else {
            output.add(littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"");
            output.add("You lost the fight and Died!");
            output.addAll(characterDeath(littleJimmy));
        }
        if (littleJimmy.getHealth() == 0){
            output.add(littleJimmy.getName() + " Shouts \"AUUGH!!! gurgle gurgle...\"");
            output.add("HP dropped to 0 and " + littleJimmy.getName() + " Died!");
            output.addAll(characterDeath(littleJimmy));
        } else if (fightResult > MAJORFAIL){
            output.addAll(littleJimmy.gainExperience((int) fightResult / 2));
        }
        return output;
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

    public ArrayList<String> characterDeath(Adventurer littleJimmy){
        ArrayList<String> output = new ArrayList<>();
        output.addAll(littleJimmy.getScore());
        output.add("Choose a new Adventurer Name!");
        littleJimmy.resetCharacter();
        return output;
    }

    public ArrayList<String> displayShoppingItems() {
        ArrayList<String> output = new ArrayList<>();
        output.add("What item do you want?");
        output.add("");
        output.add("'Sword' 20gp combat + 5");
        output.add("'Shield' 40gp combat + 10");
        output.add("'Magic Wand' 60gp combat + 15");
        output.add("'Trinket' 5gp useless");
        output.add("any other item you can name 10gp combat + 2");
        return output;
    }
    public ArrayList<String> chooseAShoppingItem(Adventurer littleJimmy, String desiredItem){
        ArrayList<String> output = new ArrayList<>();
        String flattenedDesiredItem = desiredItem.toLowerCase();
        switch (flattenedDesiredItem) {
            case "sword" -> {
                if (littleJimmy.getGold() >= 20.0) {
                    littleJimmy.useMoney("spend", 20.0);
                    littleJimmy.gainEquipment(new Item("Sword", 5, 5));
                    output.add("Thanks for Your Purchase!");
                    output.add(littleJimmy.getName() + " leaves happily.");
                } else {
                    output.add(littleJimmy.getName() + " does not have enough gold for the Sword.");
                    output.add(littleJimmy.getName() + " leaves in dismay.");
                }
            }
            case "shield" -> {
                if (littleJimmy.getGold() >= 40.0) {
                    littleJimmy.useMoney("spend", 40.0);
                    littleJimmy.gainEquipment(new Item("Shield", 10, 10));
                    output.add("Thanks for Your Purchase!");
                    output.add(littleJimmy.getName() + " leaves overjoyed.");
                } else {
                    output.add(littleJimmy.getName() + " does not have enough gold for the Shield.");
                    output.add(littleJimmy.getName() + " leaves with longing.");
                }
            }
            case "magic wand" -> {
                if (littleJimmy.getGold() >= 60.0) {
                    littleJimmy.useMoney("spend", 60.0);
                    littleJimmy.gainEquipment(new Item("Magic Wand", 15, 15));
                    output.add("Thanks for Your Purchase!");
                    output.add(littleJimmy.getName() + " leaves with a skip in their step!");
                } else {
                    output.add(littleJimmy.getName() + " does not have enough gold for the Magic Wand.");
                    output.add(littleJimmy.getName() + " leaves with regrets.");
                }
            }
            case "trinket" -> {
                if (littleJimmy.getGold() >= 5.0) {
                    littleJimmy.useMoney("spend", 5.0);
                    littleJimmy.gainEquipment(new Item("Trinket", 0, 1));
                    output.add("Thanks for Your Purchase!");
                    output.add(littleJimmy.getName() + " leaves with the  unique satisfaction");
                    output.add("   of owning something useless!");
                } else {
                    output.add(littleJimmy.getName() + " does not have enough gold for a Trinket.");
                    output.add(littleJimmy.getName() + " leaves disappointed,");
                    output.add("but sees the bright side of not spending money.");
                }
            }
            case "none", "", "no", "nothing" -> {
                output.add(littleJimmy.getName() + " decides it's better to save money today.");
                output.add(littleJimmy.getName() + " leaves without buying anything.");
            }
            default -> {
                if (littleJimmy.getGold() >= 10.0) {
                    littleJimmy.useMoney("spend", 10.0);
                    littleJimmy.gainEquipment(new Item(desiredItem, 2, 5));
                    output.add("Thanks for Your Purchase!");
                    output.add(littleJimmy.getName() + " leaves wanting to show off the "  + desiredItem + ".");
                } else {
                    output.add(littleJimmy.getName() + " does not have enough gold for " + desiredItem + ".");
                    output.add(littleJimmy.getName() + " leaves in a grumpy mood.");
                }
            }
        }
        return output;
    }

    public ArrayList<String> goSelling(Adventurer littleJimmy, String soldItem){
        ArrayList<String> output = new ArrayList<>();
        boolean didSell = false;
        for (Item item: littleJimmy.getEquipment()){
            if (item.name.equalsIgnoreCase(soldItem)){
                output.add(littleJimmy.getName() + " sells " + item.name + " for " + (item.value / 2.0) + "gp.");
                output.add(littleJimmy.getName() + " feels lighter and richer,");
                output.add("and ready to fight another monster!");
                littleJimmy.useMoney("gain", (item.value / 2.0));
                littleJimmy.loseEquipment(soldItem);
                didSell = true;
                break;
            }
        }
        if (!didSell && !soldItem.isEmpty()){
            output.add("Despite searching, " + littleJimmy.getName() + " did not find " + soldItem);
            output.add("Perhaps he imagined having it?");
            output.add("Or maybe he'll find it another day.");
        } else if (soldItem.isEmpty()){
            output.add(littleJimmy.getName() + " thinks that holding on to the equipment");
            output.add("is a better idea today.");
        }
        return output;
    }

    public ArrayList<String> sharingIsCaring(Adventurer littleJimmy, int friends){
        ArrayList<String> output = new ArrayList<>();
        int personalShareOfMoney = 2;
        int totalShares = friends + personalShareOfMoney;
        double remainingGold = (littleJimmy.getGold() / (totalShares));

        output.add("   You have " + String.format("%.2f", littleJimmy.getGold()) + "gold currently.");
        output.add("   You share " + String.format("%.2f", remainingGold) + " of your gold with " + (friends != 1 ? "each of" : "") + " your " + friends + " friend" + (friends != 1 ? "s" : ""));
        output.add("");
        output.add("   But you keep " + String.format("%.2f", (personalShareOfMoney * remainingGold)) + " gold for yourself!");

        littleJimmy.gainScore((int) (remainingGold * totalShares));
        littleJimmy.setGold(personalShareOfMoney * remainingGold);
        return output;
    }
}
