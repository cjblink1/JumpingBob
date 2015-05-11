package Game;

import Player.Bob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import  java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener
{

    private BufferedImage image;
    private Graphics2D g;
    private final int FPS = 30;
    private int heightCount = 0;
    private Thread thread;

    private boolean running;

    private ArrayList<Barrier> barriers;
    private Bob bob;
    private Random randy;

    public GamePanel()
    {
        setPreferredSize(new Dimension(640, 480));
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);


    }

    public void addNotify()
    {
        super.addNotify();
        if (thread == null)
        {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }

    }


    public void init()
    {
        image = new BufferedImage(640, 480,BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();

        randy = new Random();


        barriers = new ArrayList<Barrier>();
        barriers.add(new Barrier(randy.nextInt(500),100,100,20));
        barriers.add(new Barrier(randy.nextInt(500),200,100,20));
        barriers.add(new Barrier(randy.nextInt(500),300,100,20));
        barriers.add(new Barrier(0,400,640,80));

        bob = new Bob(20,20);



        running = true;

    }

    @Override
    public void run() {
        init();
        while(running)
        {
            long start = System.currentTimeMillis();

            update();
            draw();
            drawToScreen();

            long elapsed = System.currentTimeMillis() - start;

            long wait = 1000/FPS - elapsed;

            if (wait < 0) wait = 5;
            try
            {
             Thread.sleep(wait);

            }catch(Exception e)
            {
                e.printStackTrace();
            }



        }


    }

    private void draw() {

        g.setColor(Color.cyan);
        g.fillRect(0, 0, 640, 480);


        for (Barrier b: barriers)
        {

            b.draw(g);
        }
        g.setFont(new Font("Helvetica",Font.ITALIC,40));
        g.drawString("Level:" + String.valueOf(heightCount+1), 450, 40);
        g.drawString("JetPack:" + String.valueOf(bob.getJetPackCount()), 450, 80);

        bob.draw(g);


//        g.drawString("Jumping :"+String.valueOf(bob.jumping),500,20);
//        g.drawString("Falling :"+String.valueOf(bob.falling),500,40);
//        g.drawString("TopCollision :"+String.valueOf(bob.topCollision),500,60);
//        g.drawString("BottomCollision:"+String.valueOf(bob.bottomCollision),500,80);
//        g.drawString("RightCollision:"+String.valueOf(bob.rightCollision),500,100);
//        g.drawString("LeftCollision:" + String.valueOf(bob.leftCollision), 500, 120);




    }

    private void update(){

        if(bob.getY() < -bob.getHeight())
        {
            heightCount++;
            changeBarriers();
            bob.setY(420);
        }

        bob.update(barriers);

    }

    private void changeBarriers() {

        if(heightCount >= 15) {
            barriers.clear();
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+100,randy.nextInt(80)+20, randy.nextInt(50)));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+200,randy.nextInt(80)+20,randy.nextInt(50)));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+300,randy.nextInt(80)+20, randy.nextInt(50)));

        }

        else if(heightCount >= 10) {
            barriers.clear();
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+100,randy.nextInt(80)+20,20));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+200,randy.nextInt(80)+20,20));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+300,randy.nextInt(80)+20, 20));

        }

        else if(heightCount >= 5) {
            barriers.clear();
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+100,100,20));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+200,100,20));
            barriers.add(new Barrier(randy.nextInt(500),randy.nextInt(100)+300, 100, 20));

        }
        else {

            barriers.clear();
            barriers.add(new Barrier(randy.nextInt(500), 100, 100, 20));
            barriers.add(new Barrier(randy.nextInt(500), 200, 100, 20));
            barriers.add(new Barrier(randy.nextInt(500), 300, 100, 20));
        }
    }

    private void drawToScreen (){
        this.getGraphics().drawImage(image, 0,0, null);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && bob.getJetPackCount() > 0)
        {
            bob.setVY(-5);
            bob.jumping = true;
            bob.falling = false;
            bob.subtractJetPack();
        }


        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            bob.setVX(-5);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            bob.setVX(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}