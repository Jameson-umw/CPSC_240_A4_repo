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
    private boolean chestTouch=false;
    private boolean slimeTouch=false;
    private boolean obtainedWeapon=false;
    private boolean obtainedArmor=false;
    public Player player = new Player();
    public Enemy enemy = new Enemy(turnCount);
    //number of times key is pressed
    int walkCount=1;
    //x and y position
    private int xPos=25;
    private int yPos=300;
    //imgs
    private BufferedImage door1; private BufferedImage door2; private BufferedImage door3;
    private BufferedImage image1; private BufferedImage image2; private BufferedImage anim;
    private BufferedImage aRack; private BufferedImage sRack;
    private BufferedImage slime;
    private BufferedImage chest;
    //frame set up
    JFrame frame; Canvas canvas; BufferStrategy bufferStrategy; boolean running=true;

    public Gameplay(){
        //uploading images
        try{
            image1= ImageIO.read(new File("Sprites/knight l1.png"));
            image2= ImageIO.read(new File("Sprites/knight l2.png"));
            door1=ImageIO.read(new File("Sprites/door.png"));
            door2=ImageIO.read(new File("Sprites/door.png"));
            door3=ImageIO.read(new File("Sprites/door.png"));
            aRack=ImageIO.read(new File("Sprites/armorRack.png"));
            sRack=ImageIO.read(new File("Sprites/swordRack.png"));
            slime=ImageIO.read(new File("Sprites/slime.png"));
            chest=ImageIO.read(new File("Sprites/chest.png"));
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
        setTurnCount(player.fileMaker());
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
        if(roomNum==1){
            g.drawImage(aRack,500,150,null);
            g.drawImage(sRack,500,450,null);
            g.drawImage(door1,943,300,null);
        }
        if(roomNum==2){
            g.drawImage(door2,943,300,null);
            g.drawImage(slime,500,300,null);
        }
        if(roomNum==3){
            g.drawImage(door3,943,300,null);
            g.drawImage(chest,500,300,null);
        }
    }
    public int getTurnCount(){
        return turnCount;
    }

    public void setTurnCount(int turnCount){
        this.turnCount=turnCount;
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
        newRoom();
        touchChest();
        touchEnemy();
        touchArmorRack();
        touchWeaponRack();
    }
    //switches animation image after 5 keystrokes
    public void switchIm(){
        if((walkCount%5)==0){
            if(anim.equals(image1)){anim=image2;}
            else{anim=image1;}}
    }
    public void touchChest(){
        if(roomNum==3&&!chestTouch){
            if(485<=xPos && 590>=xPos && 285<=yPos && 370>=yPos){
                chestTouch=true;
            }
        }
    }
    public void touchEnemy(){
        if(roomNum==2&&!slimeTouch){
            if(485<=xPos && 285<=yPos && 580>=xPos && 380>=yPos){
                slimeTouch=true;
                new Combat(turnCount, player, enemy);
            }
        }
    }
    public void touchArmorRack(){
        if(roomNum==1&&!obtainedArmor){
            if(485<=xPos && 610>=xPos && 135<=yPos && 265>=yPos){
                player.generateArmor(turnCount,obtainedArmor);
                obtainedArmor=true;
            }
        }
    }
    public void touchWeaponRack(){
        if(roomNum==1&&!obtainedWeapon){
            if(485<=xPos && 615>=xPos && 435<=yPos && 565>=yPos){
                player.generateWeapon(turnCount,obtainedWeapon);
                obtainedWeapon=true;
            }
        }
    }
    public void newRoom(){
        if((925<=xPos)&&(280<=yPos)&&(yPos<=420)){
            if(roomNum<3){
                xPos=25;
                yPos=300;
                roomNum++;}
            else{
                chestTouch=false;
                slimeTouch=false;
                enemy=new Enemy(turnCount);
                obtainedArmor=false;
                obtainedWeapon=false;
                xPos=25;
                yPos=300;
                roomNum=1;
                turnCount++;
                int result = JOptionPane.showConfirmDialog(frame,"Would you like to save your progress?", "Save",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    player.saveGame(turnCount);
                    System.out.println("save");
                }else if (result == JOptionPane.NO_OPTION){
                    System.out.println("not saved");
                }
            }

        }
    }

    public JFrame sendFrametoNotif(){
        return frame;
    }

}
