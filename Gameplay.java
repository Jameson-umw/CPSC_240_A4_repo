import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gameplay implements Runnable{
    private int roomNum=1;
    private int turnCount;
    public Player player = new Player();
    //number of times key is pressed
    int walkCount=1;
    //x and y position
    private int xPos=25;
    private int yPos=300;
    //imgs
    private BufferedImage door;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage anim;
    private BufferedImage aRack;
    private BufferedImage sRack;
    //frame set up
    JFrame frame; Canvas canvas; BufferStrategy bufferStrategy; boolean running=true;

    public Gameplay(){
        //uploading images
        try{
            image1= ImageIO.read(new File("Sprites/knight l1.png"));
            image2= ImageIO.read(new File("Sprites/knight l2.png"));
            door=ImageIO.read(new File("Sprites/door.png"));
            aRack=ImageIO.read(new File("Sprites/armorRack.png"));
            sRack=ImageIO.read(new File("Sprites/swordRack.png"));
        }
        catch(IOException e){}
        anim=image1;
        //setting up frame and panel
        frame=new JFrame("Final Project");
        JPanel panel=(JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(1000,650));
        panel.setLayout(null);
        canvas=new Canvas();
        canvas.setBounds(0,0,1000,650);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        //keylistener for moving
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                moveIt(event);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy= canvas.getBufferStrategy();
        //sets up turn count
        this.turnCount=1;
        GAMEPLAY();
    }
   //runs the program
    public void run(){
        while(running=true){
            Paint();
            try{
                Thread.sleep(25);
            }
            catch (InterruptedException e){}
        }
    }
    //main method
    public static void main(String[] args) {
        Gameplay ex=new Gameplay();
        new Thread(ex).start();
    }
    //paints objexts on screen (gets cleared and repainted when objects move)
    public void Paint(){
        Graphics2D g=(Graphics2D) bufferStrategy.getDrawGraphics();
        //clears thing on each repaint
        g.clearRect(0,0,1000,650);
        Paint(g);
        bufferStrategy.show();
    }
    protected void Paint(Graphics2D g) {
        g.drawImage(anim,xPos,yPos,null);
        g.drawImage(door,943,300,null);
        if(roomNum==1){
            g.drawImage(aRack,500,150,null);
            g.drawImage(sRack,500,450,null);
        }
    }
    public int getTurnCount(){
        return turnCount;
    }

    public void setTurnCount(){
        this.turnCount=turnCount;
    }

    //@TODO We need the sprites and stuff and we need someone to import img files
    public void GAMEPLAY(){
        player.fileReader();


    }
    //moves player according to keystroke
    public void moveIt(KeyEvent evt){
        switchIm();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_DOWN:
                if((yPos+5)>600){yPos=586;}
                else{yPos+=5;}
                break;
            case KeyEvent.VK_UP:
                if((yPos-5)<0){yPos=0;}
                else{yPos-=5;}
                break;
            case KeyEvent.VK_LEFT:
                if((xPos-5)<0){xPos=0;}
                else{xPos-=5;}
                break;
            case KeyEvent.VK_RIGHT:
                if((xPos+5)>970){xPos=936;}
                else{xPos+=5;}
                break;
        }
        walkCount++;
    }
    //switches animation image after 5 keystrokes
    public void switchIm(){
        if((walkCount%5)==0){
            if(anim.equals(image1)){anim=image2;}
            else{anim=image1;}}
    }
}
