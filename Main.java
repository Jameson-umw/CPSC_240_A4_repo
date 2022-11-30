import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Gameplay();
        JFrame frame=new JFrame("Final Project");
        JPanel panel=(JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(1300,650));
        panel.setLayout(null);
        Canvas canvas=new Canvas();
        canvas.setBounds(0,0,1300,650);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
