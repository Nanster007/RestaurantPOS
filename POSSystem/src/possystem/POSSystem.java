/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

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
