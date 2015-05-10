package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Connor on 5/10/15.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{

    private boolean running;
    private final int FPS = 30;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(640,480));
        this.setFocusable(true);
        this.requestFocus();

        addKeyListener(this);

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        System.out.println("New thread!");
        long startTime = System.currentTimeMillis();

        this.init();
        while(running)
        {
            long newTime = System.currentTimeMillis();
            long elapsedTime = newTime - startTime;
            if(newTime - startTime > 1000/FPS)
            {
                this.update();
                this.draw();
                this.drawToScreen();

                System.out.println(System.currentTimeMillis());

                startTime = System.currentTimeMillis();
            }
            try {
                Thread.sleep(elapsedTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void drawToScreen() {


    }

    private void draw()
    {
        this.paint(this.getGraphics());

    }


    public void paint(Graphics g)
    {
        g.fillRect(200,200,200,200);

    }
    private void update()
    {


    }

    private void init() {

        this.running = true;

    }
}
