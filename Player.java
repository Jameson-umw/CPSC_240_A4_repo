import java.awt.*;
<<<<<<< HEAD

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
=======
import javax.swing.*;
public class Player {
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public Image image;
    public boolean visible;
    public Player(){
        this.xPos=xPos;
        this.yPos=yPos;
        visible=true;
    }
    public void getImageDimensions(){
        width=image.getWidth(null);
        height=image.getHeight(null);
    }
    public void loadImage(String imageName){
        ImageIcon imIcon=new ImageIcon(imageName);
        image=imIcon.getImage();
    }
    public Image getImage() {
        return image;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds(){
        return new Rectangle(xPos,yPos,width,height);
>>>>>>> origin/main
    }
}
