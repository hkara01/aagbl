package org.hkmi2.aagbl.tests;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hkmi2.aagbl.AsciiArtGridBagLayout;
import org.hkmi2.aagbl.LayoutParseException;

/**
 * A simple calculator demo to demonstrate layout capabilities
 * @author Harry
 *
 */
public class Calculator
extends JFrame
{

  /** to remove warning */
  private static final long serialVersionUID = 1L;
  
  static String aa = 
      "+---------------------------+\n"+
      "| ^                         |\n"+
      "|<            D            >|\n"+
      "|                           |\n"+
      "+------+------+------+------+\n"+
      "| ^    | ^    | ^    | ^    |\n"+
      "|< BC >|< L1 >|< BD >|< BS >|\n"+
      "|      |      |      |      |\n"+
      "+------+------+------+------+\n"+
      "| ^    | ^    | ^    | ^    |\n"+
      "|< B7 >|< B8 >|< B9 >|< BM >|\n"+
      "|      |      |      |      |\n"+
      "+------+------+------+------+\n"+
      "| ^    | ^    | ^    | ^    |\n"+
      "|< B4 >|< B5 >|< B6 >|< BP >|\n"+
      "|      |      |      |      |\n"+
      "+------+------+------+------+\n"+
      "| ^    | ^    | ^    | ^    |\n"+
      "|< B1 >|< B2 >|< B3 >|      |\n"+
      "|      |      |      |      |\n"+
      "+------+------+------+< BE >|\n"+
      "| ^    | ^    | ^    |      |\n"+
      "|< L2 >|< B0 >|< BZ >|      |\n"+
      "|      |      |      |      |\n"+
      "+------+------+------+------+\n"
  ;
  
  ScriptEngine engine;
  
  public Calculator(String title) 
      throws HeadlessException, LayoutParseException 
  {
          //LayoutParser.debugParser = true; //uncomment to see parsing details
  	  AsciiArtGridBagLayout gbl = new AsciiArtGridBagLayout(aa);
  	  //create the components
  	  JTextField D = new JTextField(12);
          JButton B0 = new JButton("0");
          JButton B1 = new JButton("1");
          JButton B2 = new JButton("2");
          JButton B3 = new JButton("3");
          JButton B4 = new JButton("4");
          JButton B5 = new JButton("5");
          JButton B6 = new JButton("6");
          JButton B7 = new JButton("7");
          JButton B8 = new JButton("8");
          JButton B9 = new JButton("9");
          JButton BD = new JButton("/");
          JButton BS = new JButton("*");
          JButton BP = new JButton("+");
          JButton BM = new JButton("-");
          JButton BZ = new JButton(".");
          JButton BC = new JButton("C");
          JButton BE = new JButton("=");
          JLabel L1 = new JLabel(" ");
          JLabel L2 = new JLabel(" ");
          //make a map of the components
          Object[] objs = new Object[] { "D",D,"B0",B0,"B1",B1,"B2",B2,"B3",B3,
              "B4",B4,"B5",B5,"B6",B6,"B7",B7,"B8",B8,"B9",B9, "BD",BD,"BS",BS,
              "BP",BP,"BM",BM,"BZ",BZ,"BC",BC,"BE",BE,"L1",L1,"L2",L2 };
          HashMap<String, Component> componentsByName = AsciiArtGridBagLayout.makeMap(objs);
          gbl.setConstraints(componentsByName);
          //add the action handlers for the buttons
          ActionListener lsnr = new ActionListener() { //general button action handler
            @Override
            public void actionPerformed(ActionEvent e) {
              String txt = D.getText();
              txt += ((JButton)e.getSource()).getText();
              D.setText(txt);
            }
          };
          JButton[] buttons = new JButton[] {B0,B1,B2,B3,B4,B5,B6,B7,B8,B9,BD,BS,BP,BM,BZ};
          for (JButton btn : buttons) {
            btn.addActionListener(lsnr);
          }
          ActionListener evalAction = new ActionListener() { //Button Equal handler
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Object r = engine.eval(D.getText());
                D.setText(String.valueOf(r));
              }
              catch (ScriptException e1) {
                D.setText("?");
              }
            }
          };
          BE.addActionListener(evalAction);
          D.addActionListener(evalAction); //eval also called if we enter an operation by hand
          BC.addActionListener(new ActionListener() { //Button Clear action handler
            @Override
            public void actionPerformed(ActionEvent e) { D.setText(""); }
          });
          getContentPane().setLayout(gbl);
          gbl.addAllComponentsTo(getContentPane());
          //yes we use the javascript engine just for this ...
          engine = new ScriptEngineManager().getEngineByName("JavaScript");
          //adjust alignment for a more realistic calculator display
          D.setHorizontalAlignment(JTextField.RIGHT);
  }

  public static void main(String[] args) throws Exception {
    Calculator frm = new Calculator("Calculator demo");
    frm.pack();
    frm.setVisible(true);
  }

}
