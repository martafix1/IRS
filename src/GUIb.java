

import com.panayotis.gnuplot.swing.JPlot;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIb {
    private JPanel main;
      private JPanel Displaypannel;

    GUIb(JPlot plot){
        Displaypannel = new JPanel();
        Displaypannel.add(plot, BorderLayout.CENTER);
        plot.plot();
        $$$setupUI$$$();
    }

    public void init(JPlot plot) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUIb(plot).main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void $$$setupUI$$$() {
        main = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        main.add(Displaypannel, gbc);
    }
}
