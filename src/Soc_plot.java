
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.utils.Debug;

public class Soc_plot {
    public static void main(String[] args) {
        JavaPlot.getDebugger().setLevel(Debug.VERBOSE);
        Spectrum x = new Spectrum("spektrum 1","./prop/a.txt");//class spektrum, nacita data, uchovava je v sobe a v budoucnu dopisu metody na upravu dat (peakovani, prevody emit-transmit, ...)
        
        /*PlotHandler bla=new PlotHandler("D:/Martin/Programovani/SOC/gnuplot/bin/gnuplot.exe");//ma v sobe arraylist spekter, moznost pro jejich odebirani a pridavani, to stejne s grafy, nejake metody na upravu stylu
        bla.addData(x);//pridejme data do handleru
        bla.addPlot(bla.getData(0).getData());//pridame novy graf prebirajici hodnoty spektra
        bla.addPlot("sin(x*5)/20+0.1");//pro prdel pridam graf sinusu
        bla.setColor(0,255,0,0);//prvni bude cerveny
        bla.setColor(1,100,0,255);//druhy fialovy
        bla.setRange("x", 400, 4000);
        bla.setRange("y", 0, 0.25);
        
        GUIb form = new GUIb(bla.getGraph());//nejak pochybne to vykreslim tridou GUI. pouzij vlastni, tohle nebude uplne pouzitelne
        form.init(bla.getGraph());*/
    }
    
}
