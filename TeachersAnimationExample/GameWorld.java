package TeachersAnimationExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

class GameWorld extends JComponent implements KeyListener {
    // store the game object(s) and elapsed time
    private Character mario;
    private long elapsed;

    public GameWorld() {
        elapsed = new Date().getTime();
        mario = new Character();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // needed to implement the interface, but we don't care
        // about "typed" keys
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // this is what we care about - what key was pressed?
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mario.right();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mario.left();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            mario.up();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            mario.down();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if any key is released, stop the character
        mario.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        // draw the character
        mario.draw(g);

        // now update just like before
        long time_now = new Date().getTime();
        double seconds = (time_now - elapsed) / 1000.0f;
        elapsed = time_now;
        mario.update(seconds);

        // force an update of the screen
        revalidate();
        repaint();
    }
}
