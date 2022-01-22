
public class Frog extends Entity
{
    int imageBase;
    /**
     * Frog image bases:
     *  Death: 64
     *  Still: 112
     *  Turning Left: 136
     *  Turning Right: 144
     *  Turning Back: 152
     *  Moving Left: 88
     *  Moving Right: 96
     *  Moving Back: 72
     */
    int frogTime;
    int hp;
    enum FrogState {
      STILL, FORWARD, BACK, LEFT, RIGHT, DEAD
    };
    FrogState nextMove;
    FrogState currentState;
    int reverseImage; //used if we're playing the animation in reverse
    public Frog(int x, int y) {
        super(x, y, 32, 32);
        imageBase = 112; //still forward
        currentImage = ManagerPanel.baseImages[imageBase];
        currentState = FrogState.STILL;
        nextMove = FrogState.STILL;
        reverseImage = 0;
        hp = 10;
    }
    public void setMove(FrogState move)
    {
        if (currentState == FrogState.STILL)
        {
            currentState = move;
            frogTime = 7; //puts the frog on the precipice of changing phase
        }
    }
    public void die()
    {
        currentState = FrogState.DEAD;
        imageBase = 64;
        frogTime = 0;
    }
    public void nextPhase()
    {
        if (currentState == FrogState.LEFT)
        {
            if (imageBase == 112)
            {
                imageBase = 136;
            }
            else if (imageBase == 136 && reverseImage == 0)
            {
                imageBase = 88;
            }
            else if (imageBase == 136) //if we're returning from a reverso
            {
                imageBase = 112;
                reverseImage = 0;
                currentState = nextMove;
                nextMove = FrogState.STILL;
            }
            else
            {
                imageBase = 136;
                reverseImage = 1;
            }
        }
        if (currentState == FrogState.RIGHT)
        {
            if (imageBase == 112)
            {
                imageBase = 144;
            }
            else if (imageBase == 144 && reverseImage == 0)
            {
                imageBase = 96;
            }
            else if (imageBase == 144) //if we're returning from a reverso
            {
                imageBase = 112;
                reverseImage = 0;
                currentState = nextMove;
                nextMove = FrogState.STILL;
            }
            else //if moving
            {
                imageBase = 144;
                reverseImage = 1;
            }
        }
        if (currentState == FrogState.BACK)
        {
            if (imageBase == 112)
            {
                imageBase = 152;
            }
            else if (imageBase == 152 && reverseImage == 0)
            {
                imageBase = 72;
            }
            else if (imageBase == 152) //if we're returning from a reverso
            {
                imageBase = 112;
                reverseImage = 0;
                currentState = nextMove;
                nextMove = FrogState.STILL;
            }
            else //if moving
            {
                imageBase = 152;
                reverseImage = 1;
            }
        }
        if (currentState == FrogState.FORWARD)
        {
            if (imageBase == 112)
            {
                imageBase = 80;
            }
            else
            {
                imageBase = 112;
                currentState = nextMove;
                nextMove = FrogState.STILL;
            }
        }
    }
    @Override
    public void step()
    {
        if (hp < 3)
        {
            die();
        }
        if (hp == 0)
        {
            ManagerPanel.moveCards = true;
            return;
        }
        if (currentState == FrogState.DEAD)
            hp--;
        switch (imageBase)
        {
            case 72:
                y+=3;
                break;
            case 80:
                y -= 4;
                break;
            case 88:
                x -= 3;
                break;
            case 96:
                x += 3;
                break;
            case 152:
                y+=reverseImage;
                break;
            case 136:
                x -= reverseImage;
                break;
            case 144:
                x += reverseImage;
                break;
        }
        super.step();
        if (currentState != FrogState.STILL && frogTime%8 == 7)
        {
            nextPhase();
        }
        frogTime++;
        if (reverseImage == 0)
            currentImage = ManagerPanel.baseImages[imageBase+frogTime%8];
        else
            currentImage = ManagerPanel.baseImages[imageBase+7-frogTime%8];
    }
}
