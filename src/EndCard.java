import java.awt.event.KeyEvent;

public class EndCard extends Card {
    public EndCard(ManagerPanel jpanel, int background) {
        super(jpanel, background);
    }
    @Override
    public void receiveKeyInput(int keyCode)
    {
        super.receiveKeyInput(keyCode);
        if (keyCode == KeyEvent.VK_SPACE)
        {
            managerPanel.flipCard();
        }
    }
}
