import java.awt.*;

public class Player extends Character{

    private int xcord;
    private int ycord;
    private Image img;
    private Inventory playerInventory;

    public Player(){
        Inventory playerInventory = new Inventory();
        this.playerInventory=playerInventory;
    }

    public void generateWeapon(int turnCount){
        Weapon weapon = weaponlist(turnCount);
        if(playerInventory.getInventoryWeight()<maxInventoryWeight){
            playerInventory.addItem(weapon);
        }
    }

    public void generateArmor(int turnCount){
        Armor armor = armorList(turnCount);
        if(playerInventory.getInventoryWeight()<maxInventoryWeight){
            playerInventory.addItem(armor);
        }
    }
    public void printInventory(){
        playerInventory.print();
    }
}
