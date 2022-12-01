import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends Character {

    //x and y pos of player

    private boolean visible;
    private Inventory playerInventory;

    public Player() {
        Inventory playerInventory = new Inventory();
        this.playerInventory = playerInventory;
    }

    //TODO call this when we overlap with the weapon stand
    // Makes a random weapon
    public void generateWeapon(int turnCount, boolean obtainedWeapon) {
        Weapon weapon = weaponlist(turnCount);
        //reworked if statement to fit notif method
        if(obtainedWeapon){
            notif(ItemStatus.OBTAINED);
        }else {
            if(playerInventory.getInventoryWeight() < maxInventoryWeight){
                playerInventory.addItem(weapon);
                notif(ItemStatus.FITS);
            } else {
                notif(ItemStatus.NOTFIT);
            }
        }
        playerInventory.setInventoryWeight();
    }

    //TODO call this when we overlap with the armor stand
    // Makes a random armor piece
    public void generateArmor(int turnCount, boolean obtainedArmor) {
        Armor armor = armorList(turnCount);
        //reworked if statement to work with notif method
        if(obtainedArmor){
            notif(ItemStatus.OBTAINED);
        } else{
            if(playerInventory.getInventoryWeight()<maxInventoryWeight){
                playerInventory.addItem(armor);
                notif(ItemStatus.FITS);
            } else {
                notif(ItemStatus.NOTFIT);
            }
        }
        playerInventory.setInventoryWeight();
    }

    public enum ItemStatus{FITS, NOTFIT, OBTAINED}
    //when item is picked up, or you try to pick something up when the bag is full, display corresponding notification
    public void notif(ItemStatus status){
        Gameplay g=new Gameplay();
        switch (status){
            case OBTAINED:
                JOptionPane.showMessageDialog(g.sendFrametoNotif(),"You already obtained that item");
                break;
            case NOTFIT:
                JOptionPane.showMessageDialog(g.sendFrametoNotif(),"Your bag is full. Drop an item and try again");
                break;
            case FITS:
                JOptionPane.showMessageDialog(g.sendFrametoNotif(),"An item was added to your inventory!");
        }
    }

    // Checks to see if there is a previous save. If there is not, it builds a file. if it is not, it calls readSaveGame
    public void fileMaker() {
        try {
            String filename = ("./SaveFile/save.txt");
            File file = new File(filename);

            // This can only happen if a game is new
            if (file.createNewFile()) {

            }
            // This can only happen if a game is old
            else {
                readSaveGame(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that reads in file. Turns the save data into the previous weapons and armor of a previous save. Counts turnCount, as its the most important
    // Variable in a lot of methods
    public int readSaveGame(String filename) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            try {
                String line = fileReader.readLine();
                if (!line.equals("")) {
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
                    fileReader.close();
                    return turncount;
                }
            }catch (NullPointerException e){
                fileReader.close();
                fileDeleter();
                fileMaker();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Deletes a file when a charecter dies or if the file has an error and detects null pointer exceptions. if a null pointer exception is called, it
    //effectivly removes the bad file and starts again.
    public void fileDeleter(){
        File tmpFile = new File("./SaveFile/save.txt");
        boolean exists = tmpFile.exists();
        if(exists){
            //@TODO See if removing this from the if block doesnt mess with stuff
            if(tmpFile.delete()){
            }
        }
    }

    //Call this when we save the game
    public void saveGame(int turnCount){
        playerInventory.saveState(turnCount);
    }

    //Updates health, strength, and power of the player
    public void updatePlayer(int turnCount){
        setHealth(100);
        setDefense(1+turnCount/5);
        setPower(3+turnCount/5);
    }

    //TODO call to get the inventory of the player. Display in a menu?
    public void printInventory() {
        playerInventory.print();
    }

    //inheritence methods
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
