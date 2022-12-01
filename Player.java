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

    public void fileMaker() {
        try {
            String filename = ("./SaveFile/save.txt");
            File file = new File(filename);

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
            e.printStackTrace();
        }
    }

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

    public void fileDeleter(){
        File tmpFile = new File("./SaveFile/save.txt");
        boolean exists = tmpFile.exists();
        if(exists){
            //@TODO See if removing this from the if block doesnt mess with stuff
            if(tmpFile.delete()){
            }
        }
    }

    public void saveGame(int turnCount){
        playerInventory.saveState(turnCount);
    }

    //@TODO need the location of turn count and so that i can fix the rest of the code
    public void updatePlayer(int turnCount){
        setHealth(100);
        setDefense(1+turnCount/5);
        setPower(3+turnCount/5);
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


}
