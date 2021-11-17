package possystem;

import java.util.Date;

public class ClockThread extends Thread{
    
    private CustomPanel currentPanel;
    private String time;
    
    //clock thread holds the current panel's clock label and is updated continuously
    public ClockThread(CustomPanel currentPanel){
        this.currentPanel = currentPanel;
        start();
    }
    
    @Override
    public void run(){
        while(true){
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
