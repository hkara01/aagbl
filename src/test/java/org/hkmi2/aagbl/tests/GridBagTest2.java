package org.hkmi2.aagbl.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hkmi2.aagbl.GridBagLayoutBuilder;
import org.hkmi2.aagbl.LayoutParseException;
import org.hkmi2.aagbl.LayoutParser;

public class GridBagTest2
implements ActionListener
{
  GridBagLayoutBuilder gblb;
  JLabel cA = new JLabel("A");
  JTextArea cB = new JTextArea("B is a big component with lots of text");
  JButton cC = new JButton("C");
  JTextField cD = new JTextField("D");
  
  public GridBagTest2() {
  }

  public void init() throws LayoutParseException {
    String spec = 
        "+-----+-----------------+\n"+
        "|A    | ^               |\n"+
        "|     |<        B      >|\n"+
        "|     |                 |\n"+
        "+-----+-----------------+\n"+
        "|     |                 |\n"+
        "|     |                 |\n"+
        "|     |                 |\n"+
        "|< C >|                 |\n"+
        "|     |                 |\n"+
        "|     |                 |\n"+
        "|     |                D|\n"+
        "+-----+-----------------+\n"
    ;
    LayoutParser parser = new LayoutParser();
    parser.parseSpec(parser.readSpec(new BufferedReader( new StringReader(spec) )));
    gblb = new GridBagLayoutBuilder(parser.getCRects());
    gblb.add("A", cA);
    gblb.add("B", cB);
    gblb.add("C", cC);
    gblb.add("D", cD);
    cC.addActionListener(this);
  }
  
  public void run() {
    JFrame jfrm = new JFrame("testGridBag1()");
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setContentPane(gblb.getJPanel());
    jfrm.pack();
    jfrm.setVisible(true);    
  }
  
  public static void main(String[] args) throws Exception {
    GridBagTest2 app = new GridBagTest2();
    app.init();
    app.run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cC) {
      System.out.println("Button C pressed.");
    }
  }
  
}
