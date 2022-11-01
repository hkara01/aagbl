package org.hkmi2.aagbl.tests;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Test hand-made layout
 * @author Harry
 *
 */
public class GblTest1 extends JFrame
{ 
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  JLabel a = new JLabel("a");
  JTextArea b = new JTextArea("Lorem ipsum.");
  JTextField c = new JTextField("Hello world");
  JButton d = new JButton("Press me");
  
  public GblTest1(String title) throws HeadlessException {
    super(title);
  }

  public void run() {
    JPanel mainPanel = new JPanel();
    
    GridBagLayout gbl = new GridBagLayout();
    
    GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, .5, .5, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, getInsets(), 0, 0);
    
    mainPanel.setLayout(gbl);
    
    //starting with gridx = 0 and gridy = 0
    gbc.gridx = 0; gbc.gridy = 0;
    gbl.setConstraints(a, gbc); mainPanel.add(a);
    gbc.gridx = 1; gbc.gridy = 0;
    gbl.setConstraints(b, gbc); mainPanel.add(b);
    gbc.gridx = 0; gbc.gridy = 1;
    gbl.setConstraints(c, gbc); mainPanel.add(c);
    gbc.gridx = 1; gbc.gridy = 1;
    gbl.setConstraints(d, gbc); mainPanel.add(d);
    
    setContentPane(mainPanel);
    
    setSize(800,600);
    setVisible(true);    
  }
  
  public static void main(String[] args) {
    GblTest1 frm = new GblTest1("GridBagLayout Test 1");
    frm.run();
  }

}
