import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.swing.JPlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    private JMenuBar menuBar ;
    private JMenu File;
    private JMenuItem Exit;
    private JMenu Settings;
    private JMenu Help;
    private JTextArea textPane;
    private JPanel Displaypannel;

    /**-----------**/
    private PlotHandler plotHandler;
    //private JMenu Help;
    private Dimension dimension;
    /*String gnupath = prop.getProperty("gnuplotpath");
        try {
        Jplot = new JPlot(new JavaPlot(gnupath));
        Jplot.getJavaPlot().addPlot("x ** 2");
        Jplot.plot();
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(Jplot);
        //Jplot.getJavaPlot().setGNUPlotPath(prop.getProperty("gnuplotpath"));
    } catch (Exception e) {
        e.printStackTrace();
    }*/

    public GUI(String title, Dimension dimension,String gnuplotpath) throws HeadlessException {
        super(title);
        this.dimension = dimension;
        plotHandler = new PlotHandler(gnuplotpath);
        menuBar = new JMenuBar();
        File = new JMenu("File");
        Settings = new JMenu("Settings");
        Help = new JMenu("Help");
        Exit = new JMenuItem("Exit");
        textPane = new JTextArea(10,30);
        Displaypannel = new JPanel();

    }

    public void init(){
       this.setSize(dimension);
       this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       this.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               exit();
           }
       });
       setJMenuBar(menuBar);
       menuBar.add(File);
       File.add(Exit);
       Exit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
           exit();
           }
       });
       menuBar.add(Settings);
       menuBar.add(Help);
        tryPlot();
    this.getContentPane().add(plotHandler.getGraph());
//       Displaypannel.add(plotHandler.getGraph());
//       this.getContentPane().setLayout(new BorderLayout());
//       this.getContentPane().add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(Displaypannel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)),BorderLayout.CENTER);

       //this.getContentPane().add(,BorderLayout.EAST);


       this.setVisible(true);


    }

    private void tryPlot(){
        Spectrum x = new Spectrum("spektrum 1","./prop/a.txt");//class spektrum, nacita data, uchovava je v sobe a v budoucnu dopisu metody na upravu dat (peakovani, prevody emit-transmit, ...)

        //PlotHandler bla=new PlotHandler("D:/program files/gnuplot/bin/gnuplot.exe");//ma v sobe arraylist spekter, moznost pro jejich odebirani a pridavani, to stejne s grafy, nejake metody na upravu stylu
        plotHandler.addData(x);//pridejme data do handleru
        plotHandler.addPlot(plotHandler.getData(0).getData());//pridame novy graf prebirajici hodnoty spektra
        plotHandler.addPlot("sin(x*5)/20+0.1");//pro prdel pridam graf sinusu
        plotHandler.setColor(0,255,0,0);//prvni bude cerveny
        plotHandler.setColor(1,100,0,255);//druhy fialovy
        plotHandler.setRange("x", 400, 4000);
        plotHandler.setRange("y", 0, 0.25);


    }

    public void exit(){

        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,"Do you realy want to exit?",super.getTitle(),JOptionPane.YES_NO_OPTION))
        System.exit(0);


    }


}
