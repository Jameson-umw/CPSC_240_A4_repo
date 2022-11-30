package TeachersAnimationExample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

// a character in a possible game
class Character {
    // the speed measured in pixels/second
    private static final double SPEED = 100.0;

    // the current position and speed
    private double x, y, dx, dy;

    // now we have an array of images!
    private Image[] mario;

    // the current image we are on
    private int current = 0;

    // whether we are facing right or not
    private boolean right = true;

    // whether we are moving or not
    private boolean moving = false;

    // time between flips in the animation
    private static final double FLIP_TIME = 0.125;

    // time since last flip
    private double timer = 0.0;

    public Character() {
        // load all the images
        try {
            mario = new Image[4];
            mario[0] = ImageIO.read(new File("r1.png"));
            mario[1] = ImageIO.read(new File("r2.png"));
            mario[2] = ImageIO.read(new File("l1.png"));
            mario[3] = ImageIO.read(new File("l2.png"));
        } catch (Exception e) {
            mario = null;
        }

        // set default position and speed
        x = 100;
        y = 100;
        dx = 0;
        dy = 0;
    }

    // draw the character to the screen
    public void draw(Graphics g) {
        // add two to the index if going left
        int add = 0;
        if (!right) {
            add = 2;
        }

        // draw mario on the screen
        g.drawImage(mario[current + add], (int) x, (int) y, null);
    }

    // stop mario
    public void stop() {
        dx = 0;
        dy = 0;
        moving = false;
    }

    // left/up/right/down
    public void left() {
        moving = true;
        right = false;
        dx = -SPEED;
    }
    public void up() {
        moving = true;
        dy = -SPEED;
    }
    public void right() {
        moving = true;
        right = true;
        dx = SPEED;
    }
    public void down() {
        moving = true;
        dy = SPEED;
    }

    // update him
    public void update(double dt) {
        // update position first
        x += (dx * dt);
        y += (dy * dt);

        if (y < 0) {
            y = 500;
        }
        if (y > 500) {
            y = 0;
        }
        if (x < 0) {
            x = 500;
        }
        if (x > 500) {
            x = 0;
        }

        // then update the animation
        if (moving) {
            timer += dt;

            // if it has been long enough since the last flip
            if (timer > FLIP_TIME) {
                // then flip the image
                timer = 0;
                current = (current + 1) % 2;
            }
        }
    }
}
