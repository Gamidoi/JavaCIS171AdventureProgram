import java.util.ArrayList;

public class Adventurer{
    private String name;
    private double gold;
    private int health;
    private int maxHealth;
    private int experience;
    private ArrayList<Item> equipment = new ArrayList<Item>();
    private int score;


    Adventurer(){
        name = "Boring Adventurer";
        gold = 10.0;
        equipment.add(new Item("Stick", 0, true));
        health = maxHealth = 10;
        score = 0;
    }
    Adventurer(String givenName, double startingGold){
        name = givenName;
        gold = startingGold;
        equipment.add(new Item("Stick", 0, true));
        health = maxHealth = 10;
        score = 0;
    }
    public void resetCharacter(){
        name = "Boring Adventurer";
        gold = 10.0;
        equipment.clear();
        equipment.add(new Item("Stick", 0, true));
        health = maxHealth = 10;
        score = 0;
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
        String equipmentList = "";
        for (Item eachItem : equipment){
            equipmentList += eachItem.name + " , ";
        }
        return equipmentList;
    }
    public void setEquipment(ArrayList<Item> newEquipment){
        equipment = newEquipment;
    }
    public void gainEquipment(Item item){
        equipment.add(item);
    }
    public ArrayList<Item> loseEquipment(String itemName){
        int equipmentIndex = -1;
        int index = 0;
        for (Item item: equipment){
            if (itemName.equals(item.name)){
                equipmentIndex = index;
            }
            index++;
        }
        if (equipmentIndex >= 0) {
            equipment.remove(equipmentIndex);
        }
        return equipment;
    }


    public void seeStatus(){
        System.out.print("   " + name + "\n   " + health + " / " + maxHealth + " HP\n   ");
        System.out.printf("%.2f", gold);
        System.out.print(" gold\n   [");
        int equipmentIndex = 1;
        int piecesOfEquipment = equipment.size();
        for (Item item: equipment){
            System.out.print(item.name);
            if (equipmentIndex != piecesOfEquipment){
                System.out.print(", ");
            }
            equipmentIndex++;
        }
        System.out.println("]\n   " + experience + " / 1000 for next level\n");
    }
    public void gainExperience(int XP){
        experience += XP;
        if (experience >= 1000){
            maxHealth += 10;
            health += 10;
            experience -= 1000;
            System.out.println("\n   Congrats, you leveled up\n   your max health is now " + maxHealth);
        }
    }

    public void gainScore(int add){
        score += add;
    }



    public void getScore(){
        score += health;
        score += (maxHealth * 2);
        score += (int) gold;
        for (Item item: equipment){
            if (item.valuable){
                score += 10;
            } else {
                score -= 10;
            }
        }
        System.out.println("   " + name + " final score is: " + score);
    }
}
