public class Car extends Entity{
    int imageBase;
    int startX;
    public Car(int row) {
        super(((row%2==0)?-64:(512+64)), row*32+1, 62, 28);//even rows start from left, odd start from right
        double random = Math.random();
        imageBase = 24 + 8*((int)(random*3));
        startX = this.x;
        xVelocity = (int)(2.5*(1+(int)(3*random)));
        if (startX > 0)
        {
            xVelocity *= -1;
        }
    }

    @Override
    public void step() {
        super.step();
        currentImage = ManagerPanel.baseImages[imageBase+(ManagerPanel.timeKeep/2)%8];
        if ((x < -64)|| (x > ManagerPanel.frameWidth+64)){
            if (Math.random()>.9)
            {
                x = startX;
            }
        }
    }
}
