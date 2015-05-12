package Player;

import Game.Barrier;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Connor on 5/10/15.
 */
public class Bob {

    private double x;
    private double y;
    private int width;
    private int height;
    private double vx;
    private double vy;
    private final double GRAVITY = .15;
    private final double FRICTION = .06;
    private int jetPackCount = 4;

    public boolean falling;
    public boolean jumping;
    public boolean bottomCollision;

    public Bob(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.width = 50;
        this.height = 50;

        this.vx = 0;
        this.vy = 0;

        this.jumping = false;
        this.falling = true;


    }

    public void update (ArrayList<Barrier> barriers)
    {

        vy += GRAVITY;

        if (Math.abs(vx) > 0) {
            if (vx > 0) vx -= FRICTION;
            if (vx < 0) vx += FRICTION;
        }
        if (!falling && Math.abs(vy) < 0.2) {
            jumping = false;
            falling = true;
            vy = 0;
        }

        //check collisions
        checkCollisions(barriers);


        //update velocities



        x += vx;
        y += vy;
    }

    private void checkCollisions(ArrayList<Barrier> barriers)
    {
        for(Barrier b : barriers)
        {
            if(this.getHypotheticalRectangle().intersects(b.getHypotheticalRectangle()))
            {

                if(!jumping && b.getHypotheticalRectangle().intersectsLine(x+vx,y+height+vy,x+width+vx,y+height+vy) )
                {
                    bottomCollision = true;
                    y  = b.getY() - height;
                    vy = 0;
                    falling = false;
                    jumping = true;
                    jetPackCount = 5;
                }



                return;

            }


        }

    }

    public void draw(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,width,height);
    }

    public Rectangle getHypotheticalRectangle()
    {
        return new Rectangle((int)(this.x+vx),(int)(this.y+vy), this.width, this.height);
    }


    public void setVY(double vy){this.vy = vy;}
    public void setVX(double vx){this.vx = vx;}
    public int getY(){return (int)y;}
    public int getHeight() {return height;}
    public void setY(double y) {this.y = y;}
    public int getJetPackCount() {return jetPackCount;}
    public void subtractJetPack() {jetPackCount--;}

}
