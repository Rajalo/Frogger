import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Used to display a given phase of the game
 * You can add your own subclasses such as an Animation Card
 * or in the example GameCard and EndCard
 */
public class Card extends JPanel {
    public ArrayList<Entity> entities;
    public int background;
    public ManagerPanel managerPanel;

    /**
     * Creates a new Card
     * @param jpanel the Manager Panel
     * @param background the index of the background in the images array
     */
    public Card(ManagerPanel jpanel, int background)
    {
        this.background = background;
        this.managerPanel = jpanel;
        this.entities = new ArrayList<>();
    }

    /**
     * Paints all the Entities in this card to the screen
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(ManagerPanel.baseImages[background+ManagerPanel.timeKeep/2%8], 0,0, null);
        for (Entity entity : entities)
        {
            entity.paint(g);
        }
    }

    /**
     * Does all the actions required in this card for the next frame
     */
    public void step()
    {
        for (Entity entity : entities)
        {
            entity.step();
        }
    }

    /**
     * Runs what is required when keyboard input is received
     * @param keyCode
     */
    public void receiveKeyInput(int keyCode)
    {
        if (keyCode == KeyEvent.VK_ESCAPE)
        {
            managerPanel.togglePause();
        }
    }
}
