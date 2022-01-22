import java.awt.event.KeyEvent;
import java.rmi.server.LogStream;
import java.util.ArrayList;

public class GameCard extends Card{
    public ArrayList<Entity>[] entityRows;
    int numRows = 16;
    int[] hazards;//marks whether a row is hazardous or safe for froggie
    Frog frog;
    Flag flag;
    int winCountdown;

    /**
     * Creates a new Game Card, shows when the game is played
     * @param jpanel the manager panel
     * @param background the index of the background in the images array
     */
    public GameCard(ManagerPanel jpanel, int background) {
        super(jpanel, background);
        entityRows = new ArrayList[numRows];
        for (int i = 0; i < numRows; i++)
        {
            entityRows[i] = new ArrayList<>();
        }
        //Between 9 and 15 is the road
        for (int i = 9; i < 15; i++)
        {
            addEntity(new Car(i));
        }
        //Between 2 and 8 is the river
        for (int i = 2; i < 8; i++)
        {
            Log base1 = new Log(i,0);
            Log base2 = new Log(i, (int)(Math.random()*3)+5);
            addEntity(base1);
            addEntity(base2);
            for (int j = 0; j < Math.random()*3;j++)
            {
                addEntity(base2.cloneLog());
            }
            for (double j = 0; j < Math.random()*3;j++)
            {
                addEntity(base1.cloneLog());
            }
        }
        hazards = new int[numRows];
        for (int i = 0; i <numRows; i++)
        {
            if (i >= 2 && i < 8)
            {
                hazards[i] =1; //the river is hazardous unless log
            }
            else {
                hazards[i] = 0; //the road is safe unless car
            }
        }
        frog = new Frog(256,480);
        entities.add(frog);
        flag = new Flag((int)(Math.random()*400+50),0);
        entityRows[0].add(flag);
        entities.add(flag);
        winCountdown = 10;
    }

    /**
     * Adds an entity to both a row and the general list.
     * @param entity the entity
     */
    public void addEntity(Entity entity)
    {
        entityRows[entity.y/32].add(entity);
        this.entities.add(entity);
    }

    /**
     * Removes an entity from its respective row and the general list
     * @param entity the entity
     */
    public void removeEntity(Entity entity)
    {
        entityRows[entity.y/32].remove(entity);
        this.entities.remove(entity);
    }
    @Override
    public void receiveKeyInput(int keyCode)
    {
        super.receiveKeyInput(keyCode);
        switch (keyCode)
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                frog.setMove(Frog.FrogState.FORWARD);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                frog.setMove(Frog.FrogState.LEFT);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                frog.setMove(Frog.FrogState.RIGHT);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                frog.setMove(Frog.FrogState.BACK);
                break;
            case KeyEvent.VK_SPACE:
                frog.die();
        }
    }

    @Override
    public void step() {
        super.step();
        int frogIndex = frog.y/32;
        boolean frogIntersects = false;
        if (frogIndex > 0) {
            for (Entity entity : entityRows[frogIndex-1])
            {
                if (entity.intersects(frog))
                {
                    frogIntersects = true;
                    if (entity instanceof Car)
                    {
                        frog.hp--;
                    }

                }
            }}
        if (frogIndex < numRows-1) {
            for (Entity entity : entityRows[frogIndex+1])
            {
                if (entity.intersects(frog))
                {
                    frogIntersects = true;
                    if (entity instanceof Car)
                    {
                        frog.hp--;
                    }
                }
            }}
        for (Entity entity : entityRows[frogIndex])
        {
            if (entity.intersects(frog))
            {
                frogIntersects = true;
                frog.xVelocity = entity.xVelocity;
                if (entity instanceof Car)
                {
                    frog.hp--;
                    frog.xVelocity /= 2;
                }
                if (entity instanceof Flag)
                {
                    flag.reached();
                    managerPanel.cards[1].background = 16;
                }
            }
        }
        if (flag.imageBase < 56)
        {
            winCountdown--;
        }
        if (winCountdown < 0)
        {
            winCountdown = 10;
            managerPanel.flipCard();
        }
        if (!frogIntersects || frogIndex < 2)
        {
            frog.xVelocity=0;
            frog.hp = 10;
        }
        if (hazards[frogIndex]==1 && ! frogIntersects)
        {
            frog.die();
            frog.xVelocity=0;
        }
    }
}

