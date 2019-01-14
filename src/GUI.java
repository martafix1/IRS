import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.swing.JPlot;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
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
    private JButton Button1;
    /**-----------**/
    private PlotHandler plotHandler;
    private JPlot plot;
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
        plotHandler =new PlotHandler(gnuplotpath);
        menuBar = new JMenuBar();
        File = new JMenu("File");
        Settings = new JMenu("Settings");
        Help = new JMenu("Help");
        Exit = new JMenuItem("Exit");
        textPane = new JTextArea(10,30);
        Displaypannel = new JPanel();
        Button1 = new JButton("Click me");

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
        //tryPlot();
        //plot = new JPlot(new JavaPlot("D:/Martin/Programovani/SOC/gnuplot/bin/gnuplot.exe", true));
        //plot.getJavaPlot().addPlot("x**2");
        //PlotHandler x=new PlotHandler("D:/Martin/Programovani/SOC/gnuplot/bin/gnuplot.exe");

        Spectrum spc=new Spectrum("ahoj","./prop/a.txt");
        plotHandler.addData(spc);

        plotHandler.addPlot(0);
        plot = plotHandler.getGraph();
        //plot.setPreferredSize(new DimensionUIResource(100, 100));
        Displaypannel = new JPanel();
        //Displaypannel.setLayout(new BorderLayout(0, 0));
        //plotHandler.addPlot("x**2");
        Displaypannel.setLayout(new BorderLayout());
        Displaypannel.add(plot, BorderLayout.CENTER);
        //plotHandler.getGraph().plot();
       //  plot.plot();
       // this.getContentPane().add(Displaypannel);
    //this.getContentPane().add(plotHandler.getGraph());
       //Displaypannel.add(plotHandler.getGraph());
       this.getContentPane().setLayout(new BorderLayout());
       this.getContentPane().add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(Displaypannel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)),BorderLayout.CENTER);
      this.getContentPane().add(Button1,BorderLayout.SOUTH);
       //this.getContentPane().add(,BorderLayout.EAST);
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plotHandler.addPlot("0.1");
                plot = plotHandler.getGraph();
                Displaypannel.remove(0);
                System.err.println("Clicked");
                Displaypannel.add(plot);
                GUI.super.getContentPane().revalidate();
            }
        });

       this.setVisible(true);


    }

    public void exit(){

        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,"Do you realy want to exit?",super.getTitle(),JOptionPane.YES_NO_OPTION))
        System.exit(0);


    }


}
