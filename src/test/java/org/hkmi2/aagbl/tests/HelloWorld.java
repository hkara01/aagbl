package org.hkmi2.aagbl.tests;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.hkmi2.aagbl.AsciiArtGridBagLayout;
import org.hkmi2.aagbl.LayoutParseException;

@SuppressWarnings("serial")
public class HelloWorld 
  extends JFrame
{

  String aa =
      "+--------------------------------------------+\n"+
      "|                                            |\n"+
      "|                     L                      |\n"+
      "|                                            |\n"+
      "+--------------+--------------+--------------+\n"+
      "|              |              |              |\n"+
      "|      S1      |<     B      >|      S2      |\n"+
      "|              |              |              |\n"+
      "+--------------+--------------+--------------+\n";
  
  AsciiArtGridBagLayout gbl;
  JLabel L = new JLabel("Hello, world !");
  JButton B = new JButton("OK");
  JLabel S1 = new JLabel("  "); //spacing invisible label
  JLabel S2 = new JLabel("  "); //spacing invisible label
  
  public HelloWorld() 
      throws LayoutParseException 
  {
    super("Hello, world !");
    //say we want to exit on close
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //create our layout
    gbl = new AsciiArtGridBagLayout(aa);
    //associate the rectangles with our components
    gbl.setConstraints("B", B);
    gbl.setConstraints("L", L);
    gbl.setConstraints("S1", S1);
    gbl.setConstraints("S2", S2);
    //now set this as our layout
    setLayout(gbl);
    //and add the components 
    add(L);
    add(B);
    add(S1);
    add(S2);
    //add a handler that will close the frame when OK button is pressed
    B.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) { dispose(); }
    });
    //put a larger and bold font to see our message better
    Font labelFont = L.getFont();
    labelFont = labelFont.deriveFont(60f);
    labelFont = labelFont.deriveFont(Font.BOLD);
    L.setFont(labelFont);
  }
  
  public static void main(String[] args) 
      throws Exception 
  {
    HelloWorld frm = new HelloWorld();
    frm.setSize(600, 200);
    frm.setVisible(true);
  }

}
