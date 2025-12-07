package model;

import java.util.ArrayList;
import java.util.function.Function;

public class Adventurer{
    private String name;
    private double gold;
    private int health;
    private int maxHealth;
    private int experience;
    private ArrayList<Item> equipment = new ArrayList<Item>();
    private int score;
    private Function random;


    public Adventurer(){
        name = "Boring model.Adventurer";
        gold = 10.0;
        equipment.add(new Item("Stick", 0, 0));
        health = maxHealth = 10;
        experience = 0;
        score = -13;
        random = (x)->{return Math.random();};
    }
    public Adventurer(String givenName, double startingGold){
        name = givenName;
        gold = startingGold;
        equipment.add(new Item("Stick", 0, 0));
        health = maxHealth = 10;
        experience = 0;
        score = -13;
        random = (x)->{return Math.random();};
    }
    public void resetCharacter(){
        name = "Boring model.Adventurer";
        gold = 10.0;
        equipment.clear();
        equipment.add(new Item("Stick", 0, 0));
        health = maxHealth = 10;
        experience = 0;
        score = -13;
    }


    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }


    public double getGold(){
        return gold;
    }
    public void setGold(double newGoldAmount) {
        gold = newGoldAmount;
    }
    public double useMoney(String gainOrSpend, double amountOfChange){
        if (gainOrSpend.equals("gain")){
            gold += amountOfChange;
        } else if (gainOrSpend.equals("spend")){
            gold -= amountOfChange;
        }
        if (gold != Math.abs(gold)){
            score += (int) Math.floor(gold);
            gold = 0;
        }
        return gold;
    }


    public int getMaxHealth(){
        return maxHealth;
    }
    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public int getHurt(int damage){
        health -= damage;
        if (health < 0){
            health = 0;
        }
        return health;
    }
    public void feelBetter(int heal){
        health += heal;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    public ArrayList<Item> getEquipment(){
        return equipment;
    }
    public String listEquipment(){
        String equipmentList = "[";
        int lastIndex = equipment.size() - 1;
        int index = 0;
        for (Item eachItem : equipment){
            equipmentList += eachItem.name;
            if (index != lastIndex){
                equipmentList += ", ";
            };
            index++;
        }
        equipmentList += "]";
        return equipmentList;
    }
    public void setEquipment(ArrayList<Item> newEquipment){
        equipment = newEquipment;
    }
    public void gainEquipment(Item item){
        equipment.add(item);
    }
    public void loseEquipment(String itemName){
        int equipmentIndex = -1;
        int index = 0;
        for (Item item: equipment){
            if (itemName.equalsIgnoreCase(item.name)){
                equipmentIndex = index;
            }
            index++;
        }
        if (equipmentIndex >= 0) {
            equipment.remove(equipmentIndex);
        }
    }

    public String seeStatus(){
        String output = "";
        output += "   " + name + "\n   " + health + " / " + maxHealth + " HP\n   ";
        output += String.format("%.2f", gold);
        int requiredXP = maxHealth * 10;
        output += " gold\n   " + listEquipment() + "\n   " + experience + " / " + requiredXP + " for next level\n";
        return output;
    }
    public int getExperience(){
        return experience;
    }
    public String gainExperience(int XP){
        String output = "";
        experience += XP;
        int requiredXP = maxHealth * 10;
        if (experience >= requiredXP){
            maxHealth += 2;
            health += 2;
            experience -= requiredXP;
            output = "\n   Congrats, " + name + " leveled up\n   max health is now " + maxHealth + "\n";
            output += "   HP: " + health + " / " + maxHealth + "\n";
        }
        return output;
    }

    public void gainScore(int add){
        score += add;
    }

    public String getScore(){
        String output = "";
        score += health;
        score += (int) gold / 2;
        for (Item item: equipment){
            if (item.value > 0){
                score += item.value;
            } else {
                score -= 2;
            }
        }
        output = "   " + name + " final score is: " + score + "\n";
        return output;
    }

    public void setRandom(Function newFunction){
        random = newFunction;
    }
    public double getRandom(){
        return (double) random.apply(null);
    }
}
