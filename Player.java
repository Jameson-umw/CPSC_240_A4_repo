import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {

    private Inventory playerInventory;

    public Player() {
        Inventory playerInventory = new Inventory();
        this.playerInventory = playerInventory;
    }

    public int generateWeapon(int turnCount, boolean obtainedWeapon) {
        int cases = 0;
        Weapon weapon = weaponlist(turnCount);
        if(obtainedWeapon){
            cases =1;
        }else {
            if((playerInventory.getInventoryWeight()+weapon.weight) < maxInventoryWeight){
                playerInventory.addItem(weapon);
                cases =2;
            }
        }
        playerInventory.setInventoryWeight();
        return cases;
    }

    public int generateArmor(int turnCount, boolean obtainedArmor) {
        int cases = 0;
        Armor armor = armorList(turnCount);
        if(obtainedArmor){
            cases = 1;
        } else{
            if((playerInventory.getInventoryWeight() + armor.weight)<maxInventoryWeight){
                playerInventory.addItem(armor);
                cases =2;
            }
        }
        playerInventory.setInventoryWeight();
        return cases;
    }

    // Checks to see if there is a previous save. If there is not, it builds a file. if it is not, it calls readSaveGame
    public int fileMaker() {
        int turnCount = 0;
        try {
            File save = new File("./SaveFile/");
            if (!save.exists()){
                save.mkdirs();
            }
            String filename = ("./SaveFile/save.txt");
            File file = new File(filename);

            // This can only happen if a game is new
            if (file.createNewFile()) {

            }
            // This can only happen if a game is old
            else {
                turnCount = readSaveGame(filename);
                return turnCount;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return turnCount;
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
                            if(equipped){
                                Weapon weapon = new Weapon(weight, WeaponName, durability, value, false);
                                playerInventory.addItem(weapon);
                                equip(weapon);
                            } else {
                                Weapon weapon = new Weapon(weight, WeaponName, durability, value, equipped);
                                playerInventory.addItem(weapon);
                            }
                        } else {
                            if(equipped){
                                Armor armor = new Armor(weight, WeaponName, durability, value, false);
                                playerInventory.addItem(armor);
                                equip(armor);
                            } else {
                                Armor armor = new Armor(weight, WeaponName, durability, value, equipped);
                                playerInventory.addItem(armor);
                            }

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
            if(tmpFile.delete()){
            }
        }
    }

    //Call this when we save the game
    public void saveGame(int turnCount){
        playerInventory.saveState(turnCount);
    }

    public void equip(Item item){
        playerInventory.equipItem(item);
    }

    public void unequipItem(Item item){
        playerInventory.unequipItem(item);
    }

    //Updates health, strength, and power of the player
    public void updatePlayer(int turnCount){
        setDefense(4+turnCount/5);
        setPower(5+turnCount/5);
    }

    //get inventory for inventory menu
    public ArrayList<Item> getPlayerInventory(){
        return playerInventory.getInventory();
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
