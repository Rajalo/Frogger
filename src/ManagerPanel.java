import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * A JPanel class that handles all of the event listening and sets up the timer
 */
public class ManagerPanel extends JPanel implements ActionListener, MouseListener, KeyListener{
    Timer timer;
    public static int timeKeep;
    public static boolean paused;
    int numberOfCards = 2;
    Card[] cards;
    static boolean moveCards = false;
    int cardIndex = 0;
    public static BufferedImage[] baseImages;
    public static int frameWidth = 512;
    public static int frameHeight = 512;
    public static String[] baseImagePaths = {
    /*0-23*/            "images/lose1.png","images/lose2.png","images/lose3.png","images/lose4.png","images/lose5.png","images/lose6.png","images/lose7.png","images/lose8.png","images/map1.png","images/map2.png","images/map3.png","images/map4.png","images/map5.png","images/map6.png","images/map7.png","images/map8.png","images/win1.png","images/win2.png","images/win3.png","images/win4.png","images/win5.png","images/win6.png","images/win7.png","images/win8.png",
    /*24-47 */          "images/cars/bluecar1.png","images/cars/bluecar2.png","images/cars/bluecar3.png","images/cars/bluecar4.png","images/cars/bluecar5.png","images/cars/bluecar6.png","images/cars/bluecar7.png","images/cars/bluecar8.png","images/cars/greencar1.png","images/cars/greencar2.png","images/cars/greencar3.png","images/cars/greencar4.png","images/cars/greencar5.png","images/cars/greencar6.png","images/cars/greencar7.png","images/cars/greencar8.png","images/cars/redcar1.png","images/cars/redcar2.png","images/cars/redcar3.png","images/cars/redcar4.png","images/cars/redcar5.png","images/cars/redcar6.png","images/cars/redcar7.png","images/cars/redcar8.png",
    /*48-63*/           "images/flag/reachedFlag1.png","images/flag/reachedFlag2.png","images/flag/reachedFlag3.png","images/flag/reachedFlag4.png","images/flag/reachedFlag5.png","images/flag/reachedFlag6.png","images/flag/reachedFlag7.png","images/flag/reachedFlag8.png","images/flag/unreachedFlag1.png","images/flag/unreachedFlag2.png","images/flag/unreachedFlag3.png","images/flag/unreachedFlag4.png","images/flag/unreachedFlag5.png","images/flag/unreachedFlag6.png","images/flag/unreachedFlag7.png","images/flag/unreachedFlag8.png",
    /*64-151*/          "images/frog/frogDeath1.png","images/frog/frogDeath2.png","images/frog/frogDeath3.png","images/frog/frogDeath4.png","images/frog/frogDeath5.png","images/frog/frogDeath6.png","images/frog/frogDeath7.png","images/frog/frogDeath8.png",
        /*72*/   "images/frog/frogMoveBack1.png","images/frog/frogMoveBack2.png","images/frog/frogMoveBack3.png","images/frog/frogMoveBack4.png","images/frog/frogMoveBack5.png","images/frog/frogMoveBack6.png","images/frog/frogMoveBack7.png","images/frog/frogMoveBack8.png",
        /*80*/   "images/frog/frogMoveForward1.png","images/frog/frogMoveForward2.png","images/frog/frogMoveForward3.png","images/frog/frogMoveForward4.png","images/frog/frogMoveForward5.png","images/frog/frogMoveForward6.png","images/frog/frogMoveForward7.png","images/frog/frogMoveForward8.png",
        /*88*/   "images/frog/frogMoveLeft1.png","images/frog/frogMoveLeft2.png","images/frog/frogMoveLeft3.png","images/frog/frogMoveLeft4.png","images/frog/frogMoveLeft5.png","images/frog/frogMoveLeft6.png","images/frog/frogMoveLeft7.png","images/frog/frogMoveLeft8.png",
        /*96*/   "images/frog/frogMoveRight1.png","images/frog/frogMoveRight2.png","images/frog/frogMoveRight3.png","images/frog/frogMoveRight4.png","images/frog/frogMoveRight5.png","images/frog/frogMoveRight6.png","images/frog/frogMoveRight7.png","images/frog/frogMoveRight8.png",
        /*104*/  "images/frog/frogStillBack1.png","images/frog/frogStillBack2.png","images/frog/frogStillBack3.png","images/frog/frogStillBack4.png","images/frog/frogStillBack5.png","images/frog/frogStillBack6.png","images/frog/frogStillBack7.png","images/frog/frogStillBack8.png",
        /*112*/  "images/frog/frogStillForward1.png","images/frog/frogStillForward2.png","images/frog/frogStillForward3.png","images/frog/frogStillForward4.png","images/frog/frogStillForward5.png","images/frog/frogStillForward6.png","images/frog/frogStillForward7.png","images/frog/frogStillForward8.png",
        /*120*/  "images/frog/frogStillLeft1.png","images/frog/frogStillLeft2.png","images/frog/frogStillLeft3.png","images/frog/frogStillLeft4.png","images/frog/frogStillLeft5.png","images/frog/frogStillLeft6.png","images/frog/frogStillLeft7.png","images/frog/frogStillLeft8.png",
        /*128*/  "images/frog/frogStillRight1.png","images/frog/frogStillRight2.png","images/frog/frogStillRight3.png","images/frog/frogStillRight4.png","images/frog/frogStillRight5.png","images/frog/frogStillRight6.png","images/frog/frogStillRight7.png","images/frog/frogStillRight8.png",
        /*136*/  "images/frog/frogTurnLeft1.png","images/frog/frogTurnLeft2.png","images/frog/frogTurnLeft3.png","images/frog/frogTurnLeft4.png","images/frog/frogTurnLeft5.png","images/frog/frogTurnLeft6.png","images/frog/frogTurnLeft7.png","images/frog/frogTurnLeft8.png",
        /*144*/  "images/frog/frogTurnRight1.png","images/frog/frogTurnRight2.png","images/frog/frogTurnRight3.png","images/frog/frogTurnRight4.png","images/frog/frogTurnRight5.png","images/frog/frogTurnRight6.png","images/frog/frogTurnRight7.png","images/frog/frogTurnRight8.png",
        /*152*/  "images/frog/frogTurnBack1.png","images/frog/frogTurnBack2.png","images/frog/frogTurnBack3.png","images/frog/frogTurnBack4.png","images/frog/frogTurnBack5.png","images/frog/frogTurnBack6.png","images/frog/frogTurnBack7.png","images/frog/frogTurnBack8.png",
    /*160-167*/         "images/logs/log1.png","images/logs/log2.png","images/logs/log3.png","images/logs/log4.png","images/logs/log5.png","images/logs/log6.png","images/logs/log7.png","images/logs/log8.png"

    };
    //Use the extra images if you're adding in new images, if you mess with baseImages I can't guarantee
    //the basic function will hold
    public static BufferedImage[] extraImages;
    public static String[] extraImagePaths = {
            //insert a list of filepaths to your images
    };

    /**
     * Creates a new Manager Panel object, imports all the images.
     */
    public ManagerPanel()
    {
        timer = new Timer(1000/30, this);
        timer.start();
        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
        timeKeep = 0;
        paused = false;
        baseImages = new BufferedImage[baseImagePaths.length];
        extraImages = new BufferedImage[extraImagePaths.length];
        int i = 0;
        for (String filepath : baseImagePaths)
        {
            BufferedImage nextImage = null;
            try {
                URL url = getClass().getResource(filepath);
                nextImage = ImageIO.read(url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            baseImages[i] = nextImage;
            i++;
        }
        i = 0;
        for (String filepath : extraImagePaths)
        {
            BufferedImage nextImage = null;
            try {
                URL url= getClass().getResource(extraImagePaths[i]);
                nextImage = ImageIO.read(url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            extraImages[i] = nextImage;
        }
        //This sets up the cards. Use this if you want multiple slides like a start animation
        this.cards = new Card[numberOfCards];
        this.cards[0] = new GameCard(this, 8);
        this.cards[1] = new EndCard(this, 0);
    }

    /**
     * Pauses the game
     */
    public void pause() {
        timer.stop();
    }

    /**
     * Resumes the game
     */
    public void resume(){
        timer.restart();
    }

    /**
     * Toggles pause on and off
     */
    public void togglePause()
    {
        if (paused)
        {
            resume();
            paused = false;
        }
        else
        {
            pause();
            paused = true;
        }
    }

    /**
     * Flips to the next card
     */
    public void flipCard()
    {
        moveCards = true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cards == null || cards[cardIndex] == null)
        {
            return;
        }
        if (moveCards)
        {
            moveCards = false;
            if (cardIndex == 0)//The game being played!
            {
                cards[0] = new GameCard(this, 8);
            }
            else if (cardIndex == 1)
            {
                cards[1] = new EndCard(this, 0);
            }
            cardIndex = (cardIndex+1)%numberOfCards;
        }
        if (cards == null)
            return;
        cards[cardIndex].step();
        repaint();
        timeKeep++;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        cards[cardIndex].receiveKeyInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        cards[cardIndex].paint(g);
    }
}
