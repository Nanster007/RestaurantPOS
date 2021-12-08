package possystem;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author tylar
 */
public class POSSystem {

    public MainFrame boardframe;
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        POSSystem posSystem = new POSSystem();
        posSystem.boardframe = new MainFrame();
        posSystem.boardframe.setVisible(true);
    }
    
}
