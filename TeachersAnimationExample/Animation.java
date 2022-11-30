package TeachersAnimationExample;

import javax.swing.*;

public class Animation {
    public static void main(String args[]) {
        // create and set up the window.
        JFrame frame = new JFrame("Animation Example!");

        // make the program close when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the GameWorld component
        GameWorld g = new GameWorld();
        frame.add(g);
        frame.addKeyListener(g);

        // display the window.
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
