package NonCodeFiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test implements Runnable{
    JFrame frame;
    int xPos=50;
    int yPos=50;
    Canvas canvas;
    BufferStrategy bufferStrategy;
    boolean running=true;
    BufferedImage image;
    BufferedImage image2;
    int walkCount;
    BufferedImage anim;

    public test(){
        frame=new JFrame("Basic Game");
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
        canvas.requestFocus();
        walkCount=1;
        anim=image;

    }
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
        test ex=new test();
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
        //paints luigi
        g.drawImage(anim, xPos, yPos, null);

    }
    //moves character accordingly
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
