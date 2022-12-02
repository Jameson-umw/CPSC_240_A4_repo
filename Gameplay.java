import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;

public class Gameplay implements Runnable{
    private int slimeCount=0;
    private int roomNum=0;
    private int turnCount;
    private boolean chestTouch=false;
    private boolean slimeTouch=false;
    private boolean obtainedWeapon=false;
    private boolean obtainedArmor=false;
    private boolean roundWon=true;
    public Player player = new Player();
    public Enemy enemy = new Enemy(turnCount);
    //number of times key is pressed
    int walkCount=1;
    //x and y position
    private int xPos=25;
    private int yPos=300;
    //imgs
    private BufferedImage nextImg;
    private int roundCount;
    private BufferedImage door1; private BufferedImage door2; private BufferedImage door3;
    private BufferedImage image1; private BufferedImage image2; private BufferedImage anim;
    private BufferedImage image3; private BufferedImage image4;
    private BufferedImage aRack; private BufferedImage sRack;
    private BufferedImage slime;
    private BufferedImage chest;
    private BufferedImage congrats;
    private BufferedImage lost;
    private int lostCount=0;
    private int congratsCount;
    private JPopupMenu pm;
    //frame set up
    JFrame frame; Canvas canvas; BufferStrategy bufferStrategy; boolean running=true;


    //TODO: Add a JLabel to prompt user to click to start game, jlabel then clears when done
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
            congrats=ImageIO.read(new File("Sprites/Congrats.png"));
            lost=ImageIO.read((new File("Sprites/You Died.png")));
            nextImg=ImageIO.read(new File("Sprites/Next Turn.png"));
            image3=ImageIO.read(new File("Sprites/base knight l1.png"));
            image4=ImageIO.read(new File("Sprites/base knight l2.png"));
        }
        catch(IOException e){}
        anim=image3;
        //setting up frame and panel
        frame=new JFrame("Final Project");
        JPanel panel=(JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(1000,650));
        panel.setLayout(null);
        //extra stuff so the inventory works, shouldn't affect anything else
        panel.setFocusable(true);
        panel.requestFocusInWindow();

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
        turnCount=player.fileMaker();
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
        if(roomNum==0){
            JOptionPane.showMessageDialog(sendFrametoNotif(),"Press 'I' for inventory. Make sure to keep an eye on it items can break and enemies get harder!");
            roomNum=1;
        }
        if(roomNum==1){
            lostCount=0;
            g.drawImage(aRack,500,150,null);
            g.drawImage(sRack,500,450,null);
            g.drawImage(door1,943,300,null);
        }
        if(roomNum==2){
            if(slimeTouch&&!roundWon&&lostCount<50){
                g.drawImage(lost,0,0,null);
                lostCount++;
            }
            else{
                g.drawImage(door2,943,300,null);
                if(slimeCount<10){
                    g.drawImage(slime,500,300,null);
                }
            }
            if(lostCount>=50) {
                frame.dispose();
                System.exit(0);
            }
        }
        if(roomNum==3){
            if(chestTouch&&congratsCount<50) {
                g.drawImage(congrats, 0, 0, null);
                congratsCount++;
            }
            else{
                g.drawImage(door3, 943, 300, null);
                g.drawImage(chest, 500, 300, null);}
        }
        if(roomNum==4){
            g.drawImage(nextImg,0,0,null);
            roundCount++;
            if(roundCount>70){roomNum=1;
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
            case KeyEvent.VK_I:
                openInventory(evt);
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
            if(Objects.equals(player.getEWeapon(), null) || Objects.equals(player.getEArmor(), null)){
                if(anim.equals(image1)||anim.equals(image2)){anim=image3;}
                if(anim.equals(image3)){
                    anim=image4;
                }
                else{anim=image3;}
            }
            else{
                if(anim.equals(image1)){anim=image2;}
                else{anim=image1;}}}
    }
    public void touchChest(){
        if(roomNum==3&&!chestTouch){
            if(485<=xPos && 590>=xPos && 285<=yPos && 370>=yPos){
                if(!chestTouch){JOptionPane.showMessageDialog(sendFrametoNotif(),"Your Health Has Been Restored!");}
                player.setHealth(100);
                chestTouch=true;
            }
        }
    }
    public void touchEnemy(){
        if(roomNum==2){
            if(485<=xPos && 285<=yPos && 580>=xPos && 380>=yPos){
                slimeCount++;
                if(!slimeTouch){
                    slimeTouch=true;
                    Combat combat=new Combat(turnCount, player, enemy);
                    roundWon= combat.getWin();
                    if(roundWon){
                        JOptionPane.showMessageDialog(sendFrametoNotif(),"You Won!");

                    }
                }
            }
        }
    }
    public void touchArmorRack(){
        if(roomNum==1&&!obtainedArmor){
            if(485<=xPos && 610>=xPos && 135<=yPos && 265>=yPos){

                int cases =player.generateArmor(turnCount,obtainedArmor);
                switch (cases) {
                    case 1:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "You already obtained that item");
                    case 0:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "An item was added to your inventory!");
                    case 2:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "An item was added to your inventory!");
                        obtainedArmor = true;
                    }
                }
            }
        }
    public void touchWeaponRack(){
        if(roomNum==1&&!obtainedWeapon){
            if(485<=xPos && 615>=xPos && 435<=yPos && 565>=yPos){
                int cases =player.generateWeapon(turnCount,obtainedWeapon);
                switch (cases) {
                    case 1:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "You already obtained that item");
                    case 0:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "An item was added to your inventory!");
                    case 2:
                        JOptionPane.showMessageDialog(sendFrametoNotif(), "An item was added to your inventory!");
                        obtainedWeapon = true;
                }
            }
        }
    }

    //opens inventory pop-up when user presses I
    public void openInventory(KeyEvent event){
        pm=new JPopupMenu("Testing");
        //add three buttons to the popup: check, equip, drop
        JMenuItem check=new JMenuItem("Check Inventory");
        JMenuItem equip=new JMenuItem("Equip an item");
        JMenuItem drop=new JMenuItem("Drop an item");
        pm.add(check);
        pm.add(equip);
        pm.add(drop);

        JPopupMenu popupMenu=new JPopupMenu();
        //actionListener to each item
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem menuItem;
                //for each item in the inventory, print it as a button
                if(player.getPlayerInventory().size()>0){
                    int i=1;
                    for (Item item:player.getPlayerInventory()) {

                        //if the item is equipped, add a star to it
                        if(item.equals(player.getEArmor())){
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else if (item.equals(player.getEWeapon())) {
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else {
                            menuItem=new JMenuItem(item.itemName);
                        }
                        popupMenu.add(menuItem);
                    }
                    popupMenu.show(frame,300,300);
                } else {
                    menuItem=new JMenuItem("There's nothing in your inventory");
                    popupMenu.add(menuItem);
                    popupMenu.show(frame,300,300);
                }
            }
        });

        // action listener for equipping and unequipping
        equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem menuItem;
                //for each item in the inventory, print it as a button. When that button is pressed, equip that item
                if(player.getPlayerInventory().size()>0){
                    int i=1;
                    for (Item item:player.getPlayerInventory()) {
                        //display each item
                        if(item.equals(player.getEArmor())){
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else if (item.equals(player.getEWeapon())) {
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else {
                            menuItem=new JMenuItem(item.itemName);
                        }
                        popupMenu.add(menuItem);

                        menuItem.addActionListener(new ActionListener() {
                            //when item is clicked, equip it
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //if you click on an item that's already equipped, unequip it
                                if(item.equals(player.getEWeapon())||item.equals(player.getEArmor())){
                                    JOptionPane.showMessageDialog(sendFrametoNotif(),"Unequipped "+item.getItemName());
                                    player.unequipItem(item);
                                } else {
                                    JOptionPane.showMessageDialog(sendFrametoNotif(),"Equipped "+item.getItemName());
                                    player.equip(item);
                                }
                            }
                        });

                    }
                    popupMenu.show(frame,300,300);
                } else {
                    menuItem=new JMenuItem("There's nothing in your inventory");
                    popupMenu.add(menuItem);
                    popupMenu.show(frame,300,300);
                }
            }
        });
        drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //for each item in the inventory, print it as a button. When that button is pressed, remove it and the item from the inventory
                JMenuItem menuItem;
                //if there are items, for each in the inventory, print it as a button
                if(player.getPlayerInventory().size()>0){
                    int i=1;
                    for (Item item:player.getPlayerInventory()) {

                        //list items
                        if(item.equals(player.getEArmor())){
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else if (item.equals(player.getEWeapon())) {
                            menuItem=new JMenuItem("* "+item.itemName);
                        } else {
                            menuItem=new JMenuItem(item.itemName);
                        }
                        popupMenu.add(menuItem);

                        menuItem.addActionListener(new ActionListener() {
                            //drops the item that's clicked on
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(sendFrametoNotif(),"Dropped "+item.getItemName());
                                player.removeItem(item);
                            }
                        });
                    }
                    popupMenu.show(frame,300,300);
                } else {
                    menuItem=new JMenuItem("There's nothing in your inventory");
                    popupMenu.add(menuItem);
                    popupMenu.show(frame,300,300);
                }
            }
        });
        pm.show(frame,200,200);
    }

    public void newRoom(){
        if((925<=xPos)&&(280<=yPos)&&(yPos<=420)){
            if(roomNum==1){
                xPos=25;
                yPos=300;
                roomNum++;}
            else if(roomNum==2&&slimeTouch){
                xPos=25;
                yPos=300;
                roomNum++;
            }
            else if (roomNum==3){
                roomNum=4;
                //roomNum=1;
                chestTouch=false;
                slimeTouch=false;
                enemy=new Enemy(turnCount);
                obtainedArmor=false;
                obtainedWeapon=false;
                xPos=25;
                yPos=300;
                slimeCount=0;
                congratsCount=0;
                turnCount++;
            }

        }
    }

    public JFrame sendFrametoNotif(){
        return frame;
    }

}
