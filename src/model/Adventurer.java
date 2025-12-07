package model;

import java.util.ArrayList;
import java.util.function.Function;

public class Adventurer{
    private String name;
    private double gold;
    private int health;
    private int maxHealth;
    private int experience;
    private ArrayList<Item> equipment = new ArrayList<>();
    private int score;
    private int monstersSlain;
    private Function random;


    public Adventurer(){
        name = "Boring Adventurer";
        gold = 10.0;
        equipment.add(new Item("Stick", 0, 0));
        health = maxHealth = 10;
        experience = 0;
        score = -13;
        random = (x)-> Math.random();
        monstersSlain = 0;
    }
    public void resetCharacter(){
        name = "Boring Adventurer";
        gold = 10.0;
        equipment.clear();
        equipment.add(new Item("Stick", 0, 0));
        health = maxHealth = 10;
        experience = 0;
        score = -13;
        monstersSlain = 0;
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
        StringBuilder equipmentList = new StringBuilder();
        int lastIndex = equipment.size() - 1;
        int index = 0;
        for (Item eachItem : equipment){
            equipmentList.append(eachItem.name);
            equipmentList.append("(");
            equipmentList.append(eachItem.combatBonus);
            equipmentList.append(")");
            if (index != lastIndex){
                equipmentList.append(", ");
            }
            index++;
        }
        return equipmentList.toString();
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

    public int getExperience(){
        return experience;
    }
    public ArrayList<String> gainExperience(int XP){
        ArrayList<String> output = new ArrayList<>();
        experience += XP;
        int requiredXP = maxHealth * 10;
        if (experience >= requiredXP){
            maxHealth += 2;
            health += 2;
            experience -= requiredXP;
            output.add("");
            output.add( "Congrats, " + name + " leveled up");
            output.add("   max health is now " + maxHealth);
            output.add("   HP: " + health + " / " + maxHealth);
        }
        return output;
    }

    public void gainScore(int add){
        score += add;
    }

    public ArrayList<String> getScore(){
        ArrayList<String> output = new ArrayList<>();
        score += health;
        score += ((maxHealth / 2) - 5);
        score += (int) gold / 2;
        score += monstersSlain * 2;
        for (Item item: equipment){
            if (item.value > 0){
                score += item.value;
            } else {
                score -= 2;
            }
        }
        output.add(name + " final score is: " + score);
        output.add(name + " slayed " + monstersSlain + " monsters.");
        return output;
    }

    public void setRandom(Function newFunction){
        random = newFunction;
    }
    public double getRandom(){
        return (double) random.apply(null);
    }

    public void setMonstersSlain(int numMonsters){
        monstersSlain = numMonsters;
    }
    public void slayMonster(){
        monstersSlain++;
    }
    public int getMonstersSlain(){
        return monstersSlain;
    }

    public ArrayList<String> getStatus(){
        ArrayList<String> output = new ArrayList<>();
        output.add("Name: " + name);
        output.add("HP: " + health + " / " + maxHealth);
        output.add("Gold: " + String.format("%.2f", gold));
        output.add("Monsters Slain: " + monstersSlain);
        return output;
    }
}
