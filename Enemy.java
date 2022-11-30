import java.awt.*;

public class Enemy extends Character{
    private Image img;
    private Inventory enemyInventory;

    public Enemy(){
        Inventory enemyInventory = new Inventory();
        this.enemyInventory=enemyInventory;
    }

    public void generateEnemy(int turnCount){
        setDefense(1+turnCount/3);
        setPower(1+turnCount/4);
        Weapon weapon = weaponlist(turnCount);
        enemyInventory.addItem(weapon);
        Armor armor = armorList(turnCount);
        enemyInventory.addItem(armor);
    }

    public Weapon getEWeapon(){
        return enemyInventory.getEquippedWeapon();
    }

    public Armor getEArmor(){
        return enemyInventory.getEquippedArmor();
    }

    public void removeItem(Item item){
        enemyInventory.remove(item);
    }
}
