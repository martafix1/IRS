import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.swing.JPlot;

import java.util.ArrayList;


public class JPlotHandler extends JPlot {

    private ArrayList <String> Scripts;
    private JavaPlot JP;

    public JPlotHandler() {
        Scripts = new ArrayList<>();
    }

    public JPlotHandler(ArrayList<String> Script) {
        this.Scripts = Scripts;
    }

    public ArrayList<String> getScripts() {
        return Scripts;
    }

    public void setScripts(ArrayList<String> Scripts) {
        this.Scripts = Scripts;
    }



    public void paintScripts(){
        for(String Script: Scripts){
            JP.addPlot(Script);
        }

    }
}
