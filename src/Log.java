public class Log extends Entity{
    int clones;
    int startX;
    int imageBase;
    public Log(int row,int delay) {
        super(((row%2==0)?-64:(512+64)), row*32, 63, 31);//even rows start from left, odd start from right
        imageBase = 160;
        startX = this.x;
        xVelocity = row%3+1;
        if (startX>0)
        {
            x += delay * 64;
            xVelocity *= -1;
        }
        else {
            x -= delay * 64;
        }
        clones = delay;
    }
    /**
        makes a new copy of the log, used for long logs
     */
    public Log cloneLog()
    {
        Log clone = new Log(this.y/32, ++clones);
        clone.xVelocity = this.xVelocity;
        return clone;
    }
    @Override
    public void step() {
        super.step();
        currentImage = ManagerPanel.baseImages[imageBase+(ManagerPanel.timeKeep/2)%8];
        if ((startX > 0 && x < -64)||(startX < 0 && x > 512+64)){
            x = startX;
        }
    }
}
