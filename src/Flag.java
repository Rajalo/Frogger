public class Flag extends Entity{
    public int imageBase;
    public Flag(int x, int y) {
        super(x, y, 32, 32);
        imageBase = 56;
        currentImage = ManagerPanel.baseImages[imageBase+ManagerPanel.timeKeep%8];
    }

    @Override
    public void step() {
        super.step();
        currentImage = ManagerPanel.baseImages[imageBase+ManagerPanel.timeKeep%8];
    }

    public void reached()
    {
        imageBase = 48;
    }
}
