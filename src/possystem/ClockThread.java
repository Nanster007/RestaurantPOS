package possystem;

import java.util.Date;

public class ClockThread extends Thread {

    //clockthread holding current panel allows 1 clock to persist through all panels
    private CustomPanel currentPanel;
    
    //standard variables
    private String time;
    private Date date;

    //clock thread holds the current panel's clock label and is updated continuously
    public ClockThread(CustomPanel currentPanel) {
        this.currentPanel = currentPanel;
        start();
    }

    @Override
    public void run() {
        while (true) {
            this.date = new Date();
            time = date.toString();
            currentPanel.setClock(time);
        }
    }

    public Date getDate() {
        return this.date;
    }

    public CustomPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(CustomPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
}
