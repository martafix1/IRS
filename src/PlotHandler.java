
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.style.RgbPlotColor;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.swing.JPlot;
import java.util.ArrayList;

class PlotHandler {
    private String gnuplotpath;
    private JavaPlot p;
    ArrayList<Spectrum>data=new ArrayList<Spectrum>();
    
    PlotHandler(String path){
        this.gnuplotpath=path;
        p = new JavaPlot(gnuplotpath);
        p.setTitle("spectrum");
        p.getAxis("x").setLabel("wave length");
        p.getAxis("y").setLabel("absorbance");
    }
    
    //spectrums management
    boolean addData(Spectrum spectrum){//prida data ve forme class spectrum do arraylistu
        return data.add(spectrum);
    }
    
    Spectrum getData(int index){
        Spectrum tmp;
        try{
            tmp=data.get(index);
            return tmp;
        }
        catch(IndexOutOfBoundsException exc){
            exc.printStackTrace();
            return null;
        }
    }
    
    ArrayList getAllData(){//vraci arraylist vsech nactenych spekter (i tech momentalne nevykreslenych)
        return data;
    }
    
    boolean removeData(int index){//odebere data z arraylistu
        try{
            data.remove(index);
            return true;
        }
        catch(IndexOutOfBoundsException exc){
            exc.printStackTrace();
            return false;
        }
    }
    
    //plots management
    void addPlot(int index){//prida graf do Jplotu (pripraveny k vykresleni), vybira z ulozenych spekter
        try{
            p.addPlot(data.get(index).getData());

        }
        catch(IndexOutOfBoundsException exc){
            exc.printStackTrace();

        }
        //p.addPlot(data.get(index).getData());
    }
    
    void addPlot(double[][] array){//prida graf libovolnych hodnot do Jplotu
        p.addPlot(array);
    }
    
    void addPlot(String funct){//prida graf funkce do Jplotu
        p.addPlot(funct);
    }
    
    ArrayList getPlots(){//vraci arraylist vsech vykreslenych plotu. nepouzivat, dokud neprijdu na to, jak se pracuje s navratovou hodnotou
        return p.getPlots();
    }
    
    boolean removePlot(int index){//odebere graf z Jplotu, ponecha ho v arraylistu spekter
        try{
            p.getPlots().remove(index);
            return true;
        }
        catch(IndexOutOfBoundsException exc){
            exc.printStackTrace();
            return false;
        }
    }
    
    //styles, parameters
    void setRange(String axis, double from, double to){//nastavi rozsah os, pls jak se to prevraci
        p.getAxis(axis).setBoundaries(from, to);
    }
    
    boolean setColor(int plotIndex, int red, int green, int blue){//nastavi rgb barvu pro graf na urcitem indexu
        try{
            ((AbstractPlot) p.getPlots().get(plotIndex)).getPlotStyle().setStyle(Style.LINES);
            ((AbstractPlot) p.getPlots().get(plotIndex)).getPlotStyle().setLineType(new RgbPlotColor(red,green,blue));
            return true;
        }
        catch(IndexOutOfBoundsException exc){
            exc.printStackTrace();
        }
        return false;
    }
     
    JPlot getGraph(){//'nahraje' javaplot do jplotu a ten pouzije jako navratovou hodnotu pro ruzne graficke picovinz
        JPlot plot;
        ((AbstractPlot) p.getPlots().get(0)).getPlotStyle().setStyle(Style.LINES);

        plot = new JPlot(p);
        plot.getJavaPlot().plot();

        return plot;
    }   
}
