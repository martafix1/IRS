import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class bootup {

    public static void main(String[] args) {
        String  gnuplotpath;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e){e.printStackTrace();}
        Properties prop = readProperties();
        gnuplotpath =  prop.getProperty("gnuplotpath");
        double d = Double.valueOf("5.02");
        System.out.println((d+0.3));
        //IS form = new IS();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        dimension.width /=2;
        dimension.height /=2;
        GUI form2  = new GUI("title",dimension,gnuplotpath);
       // try{}catch (Exception e){e.printStackTrace();}



      //  form.init();
        form2.init();
    }


    public static Properties readProperties() {

        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("./prop/config.properties");
            prop.load(input);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                return null;

        }
        return prop;
    }

    public static void writeProperties(Properties prop) {

        OutputStream output = null;
        try {

            output = new FileOutputStream("./prop/config.properties");

            // set the properties value !!-tmp
            // prop.setProperty("gnuplotpath", "D:/Martin/Programovani/SOC/gnuplot/bin/gnuplot.exe");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
