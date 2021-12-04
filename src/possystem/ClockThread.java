package possystem;

import java.util.Date;
import javax.swing.JPanel;

public class ClockThread extends Thread {

    private CustomPanel currentPanel;
    private String time;

    public ClockThread(CustomPanel currentPanel) {
        this.currentPanel = currentPanel;
        start();
    }

    public void run() {
        while (true) {
            time = new Date().toString();
            currentPanel.setClock(time);
        }
    }

    public CustomPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(CustomPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
}
