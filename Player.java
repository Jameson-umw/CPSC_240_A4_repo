import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Character {

    //x and y pos of player
    private int xPos;
    private int yPos;
    private BufferedImage playerStill;
    private BufferedImage playerWalk;
    private BufferedImage anim;
    private boolean visible;
    private Inventory playerInventory;

    public Player() {
        Inventory playerInventory = new Inventory();
        this.playerInventory = playerInventory;
        this.xPos=xPos;
        this.yPos=yPos;
        visible=true;
        try{
            playerStill= ImageIO.read(new File("knight l1.png"));
            playerWalk= ImageIO.read(new File("knight l2.png"));
        }
        catch(IOException e){}
        anim=playerStill;
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

    public void fileReader() {
        try {
            String filename = "save.txt";
            File file = new File("./SaveFile/", filename);

            // This can only happen if a game is new
            if (file.createNewFile()) {
                System.out.println("Built");
            }
            // This can only happen if a game is old
            else {
                readSaveGame(filename);
            }
            //@TODO Adjust IOException based on gameplay
        } catch (IOException e) {

        }
    }

    public int readSaveGame(String filename) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            String line = fileReader.readLine();
            int turncount = Integer.parseInt(line);
            line = fileReader.readLine();
            while (line != null) {
                int weight = Integer.parseInt(line);
                line = fileReader.readLine();
                String WeaponName = line;
                line = fileReader.readLine();
                int durability = Integer.parseInt(line);
                line = fileReader.readLine();
                int value = Integer.parseInt(line);
                line = fileReader.readLine();
                boolean equipped = Boolean.parseBoolean(line);
                line = fileReader.readLine();
                String itemType = line;
                line = fileReader.readLine();
                line = fileReader.readLine();
                if (itemType.equals("Weapon")) {
                    Weapon weapon = new Weapon(weight, WeaponName, durability, value, equipped);
                    playerInventory.addItem(weapon);
                } else {
                    Armor armor = new Armor(weight, WeaponName, durability, value, equipped);
                    playerInventory.addItem(armor);
                }
            }
            return turncount;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return 0;
    }


    public void saveGame(int turnCount){
        playerInventory.saveState(turnCount);

    }

    //@TODO need the location of turn count and so that i can fix the rest of the code
    public void updatePlayer(){
        int turnCount=0;
        setHealth(100);
        setDefense(1+turnCount/5);
        setPower(1+turnCount/5);
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
    //getters and setters for player position
    public int getyPos() {return yPos;}
    public int getxPos() {return xPos;}
    public void setxPos(int xPos) {this.xPos = xPos;}
    public void setyPos(int yPos) {this.yPos = yPos;}
    //getters for image and setter for animation image

    public void setAnim(BufferedImage anim) {
        this.anim = anim;
    }
    public BufferedImage getAnim() {return anim;}

    public BufferedImage getPlayerWalk() {return playerWalk;}

    public BufferedImage getPlayerStill() {return playerStill;}
}
