import java.awt.*;

public class Enemy extends Character{
    private Image img;
    private Inventory enemyInventory;

    public Enemy(){
        Inventory enemyInventory = new Inventory();
        this.enemyInventory=enemyInventory;
    }

    public void generateEnemy(int turnCount){
        setDefense(getDefense()+turnCount-1);
        Weapon weapon = weaponlist(turnCount);
        enemyInventory.addItem(weapon);
        Armor armor = armorList(turnCount);
        enemyInventory.addItem(armor);
    }
}
