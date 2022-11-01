package org.hkmi2.aagbl;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/** component rectangle */
public class CRect {
  String name;
  int x, y; //coordinates x and y 
  int w, h;
  int flags;
  int lineOfName;
  int halign; //1 left 2 centered 3 right
  int valign; //1 top  2 centered 3 bottom
  double weightx = -1; //if >= 0 will be used to force weightx
  double weighty = -1; //if >= 0 will be used to force weighty
  int ipadx = 0;
  int ipady = 0;
  Insets insets = new Insets(0, 0, 0, 0);
  public String toString() { return "['"+name+"',"+x+","+y+","+w+","+h+"]"; }
}