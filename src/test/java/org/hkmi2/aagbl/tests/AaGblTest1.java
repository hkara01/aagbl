package org.hkmi2.aagbl.tests;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.hkmi2.aagbl.AsciiArtGridBagLayout;
import org.hkmi2.aagbl.LayoutParseException;

public class AaGblTest1 extends JFrame
{
  /**
   * To remove warning
   */
  private static final long serialVersionUID = 1L;

  String spec = 
      "+-----+-----------------+\n"+
      "|A    |                B|\n"+
      "|     |                 |\n"+
      "+-----+-----------------+\n"+
      "|     |                 |\n"+
      "|     |                 |\n"+
      "|     |                 |\n"+
      "|  C  |                 |\n"+
      "|     |                 |\n"+
      "|     |                 |\n"+
      "|     |                D|\n"+
      "+-----+-----------------+\n"
  ;

  JLabel cA = new JLabel("A");
  JLabel cB = new JLabel("B");
  JButton cC = new JButton("C");
  JTextArea cD = new JTextArea("D");
  AsciiArtGridBagLayout ly;
  
  public AaGblTest1() throws HeadlessException, LayoutParseException {
    this(" ");
  }

  public AaGblTest1(String title) throws HeadlessException, LayoutParseException {
    super(title);
    ly = new AsciiArtGridBagLayout(spec);
    JPanel mainPanel = new JPanel();
    ly.setConstraints("A", cA);
    ly.setConstraints("B", cB);
    ly.setConstraints("C", cC);
    ly.setConstraints("D", cD);
    mainPanel.setLayout(ly);
    ly.addAllComponentsTo(mainPanel);
    setContentPane(mainPanel);
  }

  public static void main(String[] args) throws HeadlessException, LayoutParseException {
    AaGblTest1 frm = new AaGblTest1();
    frm.setSize(800, 600);
    frm.setVisible(true);
  }
}
