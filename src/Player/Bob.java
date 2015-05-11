package Player;

import Game.Barrier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private double gravity = .15;
    private double friction = .1;
    private double heightCount = 0;
    private int jetPackCount = 4;

    public boolean falling;
    public boolean jumping;
    public boolean topCollision;
    public boolean bottomCollision;
    public boolean leftCollision;
    public boolean rightCollision;

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

        vy += gravity;

        if (Math.abs(vx) > 0) {
            if (vx > 0) vx -= friction;
            if (vx < 0) vx += friction;
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

                if(!jumping && b.getHypotheticalRectangle().intersectsLine(x+vx, y+vy, x+width+vx, y+vy));
                {
                    topCollision = true;
                    //y = b.getY() + b.getHeight();


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

    public boolean isJumping(){return jumping;}

    public void setVY(double vy){this.vy = vy;}
    public void setVX(double vx){this.vx = vx;}
    public int getY(){return (int)y;}
    public int getHeight() {return height;}
    public void setY(double y) {this.y = y;}
    public int getJetPackCount() {return jetPackCount;}
    public void subtractJetPack() {jetPackCount--;}

}
