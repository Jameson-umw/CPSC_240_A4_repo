import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int inventoryWeight;

    // Constructor
    public Inventory() {
        ArrayList<Item> items = new ArrayList<>();
        this.items = items;
        this.equippedWeapon=equippedWeapon;
        this.equippedArmor=equippedArmor;
        this.inventoryWeight=inventoryWeight;
    }

    // Changes the equipped status of armor and weapons
    // May fail if there is no longer an item equipped/ at the start when a player has no weapon
    public void equipItem(Item item){
        if(!item.getEquipped()) {
            if (item.getItemType().equals("Weapon")) {
                try {
                    equippedWeapon.changeEquip();
                    item.changeEquip();
                    equippedWeapon = (Weapon) item;
                }catch (NullPointerException e){
                    item.changeEquip();
                    equippedWeapon = (Weapon) item;
                }
            } else {
                try {
                    equippedArmor.changeEquip();
                    item.changeEquip();
                    equippedArmor = (Armor) item;
                }catch (NullPointerException e){
                    item.changeEquip();
                    equippedArmor = (Armor) item;
                }
            }
        }
    }

    // Changes equip status of a weapon or armor to false
    // can likely remove the first if statement, it is more or less a failsafe for performance should the code attempt to unequip a healing item
    public void unequipItem(Item item) {
        if (item.getEquipped()) {
            if (item==equippedWeapon) {
                item.changeEquip();
                equippedWeapon=null;
            } else if (item==equippedArmor) {
                item.changeEquip();
                equippedArmor=null;
            }
        }
    }

    // updates the inventory weight by looping through all the items
    public void setInventoryWeight(){
        inventoryWeight=0;
        for(Item item: items){
            inventoryWeight=inventoryWeight+item.getWeight();
        }
    }

    // Just adds an item from the inventory
    public void addItem(Item item){
        items.add(item);
    }

    // Removes an item from the inventory, and unequips the item should it be equipped
    public void remove(Item item){
        if(item.equals(equippedWeapon) || item.equals(equippedArmor)){
            unequipItem(item);
        }
        items.remove(item);
    }


    // saveState is the method that saves the game. We dont call this method, however. Instead, call player.saveGame
    public void saveState(int turnCount){
        try{
            new FileOutputStream("./SaveFile/save.txt", false).close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("./SaveFile/save.txt");
            myWriter.write(String.valueOf(turnCount));
            myWriter.write(String.format("%n"));
        for(Item item: items) {
            myWriter.write(String.valueOf(item.getWeight()));
            myWriter.write(String.format("%n"));
            myWriter.write(item.getItemName());
            myWriter.write(String.format("%n"));
            myWriter.write(String.valueOf(item.getDurability()));
            myWriter.write(String.format("%n"));
            myWriter.write(String.valueOf(item.getValue()));
            myWriter.write(String.format("%n"));
            myWriter.write(String.valueOf(item.getEquipped()));
            myWriter.write(String.format("%n"));
            myWriter.write(item.getItemType());
            myWriter.write(String.format("%n"));
            myWriter.write(String.format("%n"));
        }
        myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item>getInventory(){
        return items;
    }
    public int getInventoryWeight(){
        return inventoryWeight;
    }
    public Weapon getEquippedWeapon(){
        return equippedWeapon;
    }
    public Armor getEquippedArmor(){
        return equippedArmor;
    }
}
