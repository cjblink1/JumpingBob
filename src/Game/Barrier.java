package Game;

import Player.Bob;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Connor on 5/10/15.
 */
public class Barrier {

    private double x;
    private double y;
    private int width;
    private int height;
    private double vx;
    private double vy;
    private BufferedImage image;

    public Barrier(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vx = 0;
        this.vy = 0;

        try {
            image = ImageIO.read(new File("res/cloud3.png"));
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void update(){

    }


    public void draw(Graphics g)
    {
//        g.setColor(Color.BLUE);
//        g.fillRect((int)x,(int)y,width,height);

        g.drawImage(image, (int)x, (int)y, width, height, null);

    }

    public int getX() {return (int)this.x;}
    public int getY() {return (int)this.y;}
    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}
    public int getVX(){return (int)this.vx;}
    public int getVY(){return (int)this.vy;}

    public Rectangle getHypotheticalRectangle()
    {
        return new Rectangle((int)(this.x+vx),(int)(this.y+vy), this.width, this.height);
    }
}
