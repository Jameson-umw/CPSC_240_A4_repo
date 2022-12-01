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
    BufferedImage image;
    BufferedImage image2;
    BufferedImage anim;
    int xPos=50;
    int yPos=50;
    int walkCount=1;
    private int turnCount;
    public Player player = new Player();
    JFrame frame; Canvas canvas; BufferStrategy bufferStrategy; boolean running=true;

    public Gameplay(){
        frame=new JFrame("Final Project");
        JPanel panel=(JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(1000,650));
        panel.setLayout(null);
        canvas=new Canvas();
        canvas.setBounds(0,0,1000,650);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
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
        try{
            image= ImageIO.read(new File("knight l2.png"));
            image2=ImageIO.read(new File("knight l1.png"));
        }
        catch(IOException e){}
        anim=image;
        this.turnCount=1;
        GAMEPLAY();
    }
   /* public void setUp(){
        frame=new JFrame("Final Project");
        JPanel panel=(JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(1000,650));
        panel.setLayout(null);
        canvas=new Canvas();
        canvas.setBounds(0,0,1000,650);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
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
    }*/
    public void run(){
        while(running=true){
            Paint();
            try{
                Thread.sleep(25);
            }
            catch (InterruptedException e){}
        }
    }
    public static void main(String[] args) {
        Gameplay ex=new Gameplay();
        new Thread(ex).start();
    }
    public void Paint(){
        Graphics2D g=(Graphics2D) bufferStrategy.getDrawGraphics();
        //clears thing on each repaint
        g.clearRect(0,0,1000,600);
        Paint(g);
        bufferStrategy.show();

    }
    protected void Paint(Graphics2D g) {
        g.fillRect(xPos,yPos, 65,65);
    }
    public int getTurnCount(){
        return turnCount;
    }

    public void setTurnCount(){
        this.turnCount=turnCount;
    }

    //@TODO We need the sprites and stuff and we need someone to import img files
    public void GAMEPLAY(){
        player.fileMaker();


    }
    //moves player according to keystroke
    public void moveIt(KeyEvent evt){
        switchIm();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_DOWN:
                if((yPos+5)>600){yPos=600;}
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
                if((xPos+5)>970){xPos=970;}
                else{xPos+=5;}
                break;

        }
    }
    //switches image on every 5th keystroke
    public void switchIm(){
        if((walkCount%5)==0){
            if(anim==image){anim=image2;}
            else{anim=image;}}
        walkCount++;
    }

}
