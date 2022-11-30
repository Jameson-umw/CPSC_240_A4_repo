import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Character {

    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public BufferedImage playerStill;
    public BufferedImage playerWalk;
    public boolean visible;
    private Inventory playerInventory;

    public Player() {
        Inventory playerInventory = new Inventory();
        this.playerInventory = playerInventory;
        this.xPos=xPos;
        this.yPos=yPos;
        visible=true;
        try{
            playerStill= ImageIO.read(new File(""));
            playerWalk= ImageIO.read(new File(""));
        }
        catch(IOException e){}
    }

    public void generateWeapon(int turnCount) {
        Weapon weapon = weaponlist(turnCount);
        if (playerInventory.getInventoryWeight() < maxInventoryWeight) {
            playerInventory.addItem(weapon);
        }
    }

    public void generateArmor(int turnCount) {
        Armor armor = armorList(turnCount);
        if (playerInventory.getInventoryWeight() < maxInventoryWeight) {
            playerInventory.addItem(armor);
        }
    }

    public void printInventory() {
        playerInventory.print();
    }

    public Weapon getEWeapon(){
        return playerInventory.getEquippedWeapon();
    }

    public Armor getEArmor(){
        return playerInventory.getEquippedArmor();
    }

    public void removeItem(Item item){
        playerInventory.remove(item);
    }

}
