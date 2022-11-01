package org.hkmi2.aagbl.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.hkmi2.aagbl.AsciiArtGridBagLayout;
import org.hkmi2.aagbl.LayoutParseException;

public class FileChooserTest
implements ActionListener
{
  AsciiArtGridBagLayout aagbl;
  JLabel a = new JLabel("A");
  JTextArea b = new JTextArea("B is a big component with lots of text");
  JFileChooser fc = new JFileChooser();
  //JLabel fc = new JLabel("File chooser");
  JLabel s1 = new JLabel("                            ");
  JButton ok = new JButton("OK");
  JButton cancel = new JButton("Cancel");
  
  public FileChooserTest() {
  }

  public void init() throws LayoutParseException {
    String spec = 
        "+-----+-----------------+\n"+
        "|     | ^               |\n"+
        "|  a  |<        b      >|\n"+
        "|     |                 |\n"+
        "+-----+-----------------+\n"+
        "| ^                     |\n"+
        "|                       |\n"+
        "|                       |\n"+
        "|< fc                  >|\n"+
        "|                       |\n"+
        "+-----------------------+\n"+
        "|<     s1    >| ok | ca |\n"+
        "+-----------------------+\n"
    ;
    aagbl = new AsciiArtGridBagLayout(spec);
    aagbl.setConstraints("a", a);
    aagbl.setConstraints("b", b);
    aagbl.setConstraints("fc", fc);
    aagbl.setConstraints("s1", s1);
    aagbl.setConstraints("ok", ok);
    aagbl.setWeightx("ok", 0);
    aagbl.setConstraints("ca", cancel);
    aagbl.setWeightx("ca", 0);
    ok.addActionListener(this);
    cancel.addActionListener(this);
  }
  
  public void run() {
    JFrame jfrm = new JFrame("testGridBag1()");
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel mainPanel = new JPanel(aagbl);
    aagbl.addAllComponentsTo(mainPanel);
    jfrm.setContentPane(mainPanel);
    jfrm.pack();
    jfrm.setVisible(true);    
  }
  
  public static void main(String[] args) throws Exception {
    FileChooserTest app = new FileChooserTest();
    app.init();
    app.run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == ok) {
      System.out.println("Button OK pressed.");
    }
    if (e.getSource() == cancel) {
      System.out.println("Button Cancel pressed.");
    }
  }
  
}
