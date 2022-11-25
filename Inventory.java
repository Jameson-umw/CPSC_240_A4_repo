import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    // Constructor
    public Inventory() {
        ArrayList<Item> items = new ArrayList<>();
        this.items = items;
        this.equippedWeapon=equippedWeapon;
        this.equippedArmor=equippedArmor;
    }

    // Changes the equipped status of armor and weapons
    // May fail if there is no longer an item equipped/ at the start when a player has no weapon
    // May require a try/catch block in case someone attempts to equip a healing item. Though, I feel that this should work just fine
    public void equipItem(Item item){
        if(item.getEquipped()==false) {
            if (item.getItemType().equals("Weapon")) {
                equippedWeapon.changeEquip();
                item.changeEquip();
                equippedWeapon = (Weapon) item;
            } else if (item.getItemType().equals("Armor")) {
                equippedArmor.changeEquip();
                item.changeEquip();
                equippedArmor = (Armor) item;
            }
        }
    }

    // Changes equip status of a weapon or armor to false
    // can likely remove the first if statement, it is more or less a failsafe for performance should the code attempt to unequip a healing item
    public void unequipItem(Item item) {
        if (item.getEquipped() == true) {
            if (item==equippedWeapon) {
                item.changeEquip();
                equippedWeapon=null;
            } else if (item==equippedArmor) {
                item.changeEquip();
                equippedArmor=null;
            }
        }
    }

    // Returns the value of a heal item, and removes it from the inventory. Later when we program health, we can call this method, and then add value to player health
    public int useHealing(Healing healItem){
        int value=0;
        if(items.contains(healItem)){
            value = healItem.getHealValue();
            removeItem(healItem);
        }
        return value;
    }

    //we can make this method later, while its very simple, im not sure yet on what we are doing with this just yet
    public void print(){

    }

    // Just adds an item from the inventory
    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
