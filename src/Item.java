public class Item {
    public String name;
    public int combatBonus;
    public boolean valuable;

    public Item(String newName, int bonus, boolean value){
        name = newName;
        combatBonus = bonus;
        valuable = value;
    }
}
