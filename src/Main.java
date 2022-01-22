import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.net.URL;

/**
 * The main class, used to start the program
 */
public class Main {
    public static JFrame frame = new JFrame("SBGD Frogger");

    /**
     * The main function, used to make the executable
     * @param args
     * @throws Throwable
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable, Throwable {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ManagerPanel panel = new ManagerPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(512,548);
        frame.setVisible(true);
    }
}
