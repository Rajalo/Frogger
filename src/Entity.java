import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An extendable class to help with making new obstacles
 */
public class Entity {
    int x,y,width,height;
    int xVelocity, yVelocity;
    BufferedImage currentImage;
    public Entity(int x, int y, int width,int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }
    /*
    Run this every frame. Deals with movement of the entity, other chronic updates
     */
    public void step()
    {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    /**
     * Repaints the entity on screen in case it moved or changed look
     * @param g
     */
    public void paint(Graphics g)
    {
        g.drawImage(currentImage, x, y, null);
    }

    /**
     * Detects if two entities have collided
     * @param other another entity
     * @return true if this and other overlap
     */
    public boolean intersects(Entity other)
    {
        if ((x >= other.x && x <= other.x+other.width) || (other.x >= x && other.x <= x + width))
        {
            return (y >= other.y && y <= other.y+ other.height) || (other.y >= y && other.y <= y + height);
        }
        return false;
    }
}
