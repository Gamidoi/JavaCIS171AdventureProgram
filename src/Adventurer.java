import java.util.ArrayList;

public class Adventurer{
    String name;
    double gold;
    ArrayList<String> equipment = new ArrayList<String>();
    Adventurer(){
        name = "Boring Adventurer";
        gold = 10.0;
        equipment.add("shield");
        equipment.add("sword");
    }
    Adventurer(String givenName, double startingGold){
        name = givenName;
        gold = startingGold;
        equipment.add("shield");
        equipment.add("sword");
    }
    public double getGold(){
        return gold;
    }
    public double setGold(double newGoldAmount) {
        gold = newGoldAmount;
        return gold;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    public ArrayList<String> getEquipment(){
        return equipment;
    }
    public void setEquipment(ArrayList<String> newEquipment){
        equipment = newEquipment;
    }
    public double useMoney(String gainOrSpend, double amountOfChange){
        if (gainOrSpend.equals("gain")){
            gold += amountOfChange;
        } else if (gainOrSpend.equals("spend")){
            gold -= amountOfChange;
        }
        if (gold != Math.abs(gold)){
            String amountOverdrawn = "" + Math.ceil(Math.abs(gold));
            gold = 0;
            equipment.add("IOU to local shopkeep in the amount of " + amountOverdrawn + " gold");
        }
        return gold;
    }
    public ArrayList<String> gainEquipment(String item){
        equipment.add(item);
        return equipment;
    }
}
