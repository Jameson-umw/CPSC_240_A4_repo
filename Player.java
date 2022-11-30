import java.awt.*;
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
    }
}
